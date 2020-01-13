package com.huacainfo.ace.hqgj.plugins.wechat.kit;

import com.huacainfo.ace.common.tools.CommonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

//import org.apache.commons.compress.utils.IOUtils;

/**
 * HttpKit
 */
public class HttpKit {

    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String CHARSET = "UTF-8";
    private static final TrustAnyHostnameVerifier trustAnyHostnameVerifier = new HttpKit().new TrustAnyHostnameVerifier();
    protected static Logger logger = LoggerFactory.getLogger(HttpKit.class);
    private static final SSLSocketFactory sslSocketFactory = initSSLSocketFactory();

    private HttpKit() {
    }

    private static SSLSocketFactory initSSLSocketFactory() {
        try {
            TrustManager[] tm = {new HttpKit().new TrustAnyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("TLS", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            logger.error("HttpKit error", e);
            throw new RuntimeException(e);
        }
    }

    private static HttpURLConnection getHttpConnection(String url, String method, Map<String, String> headers)
            throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        URL _url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
        if (conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
            ((HttpsURLConnection) conn).setHostnameVerifier(trustAnyHostnameVerifier);
        }

        conn.setRequestMethod(method);
        conn.setDoOutput(true);
        conn.setDoInput(true);

        conn.setConnectTimeout(19000);
        conn.setReadTimeout(19000);

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");

        if (headers != null && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        return conn;
    }

    /**
     * Send GET request
     */
    public static String get(String url, Map<String, String> queryParas, Map<String, String> headers) {
        HttpURLConnection conn = null;
        try {
            conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), GET, headers);
            conn.connect();
            return readResponseString(conn);
        } catch (Exception e) {
            logger.error("HttpKit error", e);
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static String get(String url, Map<String, String> queryParas) {
        return get(url, queryParas, null);
    }

    public static String get(String url) {
        return get(url, null, null);
    }

    /**
     * Send POST request
     */
    public static String post(String url, Map<String, String> queryParas, String data, Map<String, String> headers) {
        HttpURLConnection conn = null;
        OutputStream out = null;
        try {
            conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), POST, headers);
            conn.connect();

            out = conn.getOutputStream();
            out.write(data.getBytes(CHARSET));
            out.flush();
            out.close();

            return readResponseString(conn);
        } catch (Exception e) {
            logger.error("HttpKit.error.url={}", url);
            logger.error("HttpKit error", e);
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e1) {
                logger.error("HttpKit.error.url1={}", url);
                logger.error("HttpKit error1", e1);
            }
        }
    }

    public static String post(String url, Map<String, String> queryParas, String data) {
        return post(url, queryParas, data, null);
    }

    public static String post(String url, String data, Map<String, String> headers) {
        return post(url, null, data, headers);
    }

    public static String post(String url, String data) {
        return post(url, null, data, null);
    }

    private static String readResponseString(HttpURLConnection conn) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("run error", e);
                }
            }
        }
    }

    /**
     * Build queryString of the url
     */
    private static String buildUrlWithQueryString(String url, Map<String, String> queryParas) {
        if (queryParas == null || queryParas.isEmpty()) {
            return url;
        }

        StringBuilder sb = new StringBuilder(url);
        boolean isFirst;
        if (url.indexOf("?") == -1) {
            isFirst = true;
            sb.append("?");
        } else {
            isFirst = false;
        }

        for (Entry<String, String> entry : queryParas.entrySet()) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("&");
            }

            String key = entry.getKey();
            String value = entry.getValue();
            if (CommonUtils.isNotEmpty(value)) {
                try {
                    value = URLEncoder.encode(value, CHARSET);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            sb.append(key).append("=").append(value);
        }
        return sb.toString();
    }

    public static String readIncommingRequestData(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            StringBuilder result = new StringBuilder();
            br = request.getReader();
            for (String line = null; (line = br.readLine()) != null; ) {
                result.append(line).append("\n");
            }

            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error("run error", e);
                }
            }
        }
    }

    public static String readData(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            StringBuilder result = new StringBuilder();
            br = request.getReader();
            for (String line = null; (line = br.readLine()) != null; ) {
                result.append(line).append("\n");
            }

            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 根据网络地址获取 InputStream
     *
     * @param url
     * @return
     */
    public static InputStream getUrlInputStream(String url) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();

//        httpget.releaseConnection();

        return inputStream;
    }

    /**
     * Send POST request
     */
    public static byte[] getPostBytes(String url) {
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            conn = getHttpConnection(buildUrlWithQueryString(url, null), POST, null);
            conn.connect();

            OutputStream out = conn.getOutputStream();
            out.write("".getBytes(CHARSET));
            out.flush();
            out.close();

            inputStream = conn.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] in2b = swapStream.toByteArray();
            return in2b;
        } catch (Exception e) {
            logger.error("HttpKit error", e);
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 涉及资金回滚的接口会使用到商户证书，包括退款、撤销接口的请求
     *
     * @param url      请求的地址
     * @param data     xml数据
     * @param cert     证书文件目录
     * @param certPass 证书密码
     * @return String 回调的xml信息
     */
    public static String postSSL(String url, String data, byte[] cert, String certPass) {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        InputStream inputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        BufferedReader reader = null;
        try {
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            byteArrayInputStream = new ByteArrayInputStream(cert);
            clientStore.load(byteArrayInputStream, certPass.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(clientStore, certPass.toCharArray());
            KeyManager[] kms = kmf.getKeyManagers();
            SSLContext sslContext = SSLContext.getInstance("TLSv1");

            sslContext.init(kms, null, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            URL _url = new URL(url);
            conn = (HttpsURLConnection) _url.openConnection();

            conn.setConnectTimeout(25000);
            conn.setReadTimeout(25000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("User-Agent", DEFAULT_USER_AGENT);
            conn.connect();

            out = conn.getOutputStream();
            out.write(data.getBytes(CHARSET));
            out.flush();

            inputStream = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //IOUtils.closeQuietly(out);
            //IOUtils.closeQuietly(reader);
            //IOUtils.closeQuietly(inputStream);
            //IOUtils.closeQuietly(byteArrayInputStream);
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * https 域名校验
     */

    private class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * https 证书管理
     */
    private class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }
}
