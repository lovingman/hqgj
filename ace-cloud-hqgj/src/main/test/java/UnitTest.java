import com.huacainfo.ace.zcpa.plugins.wechat.kit.HttpKit;
import org.junit.Test;

/**
 * @ClassName UnitTest
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/6 15:08
 */
public class UnitTest {
    @Test
    public void test() {
        String accessToken = HttpKit.get("http://wx.cdwmsq1314.com/weChat/weChatAccount/findAccessToken.action").replace("\"", "");
//        System.out.println(rst);

//        loop();


        String menu = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
        System.out.println(menu);
    }

    private void loop() {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    System.out.println(HttpKit.get("http://wx.cdwmsq1314.com/weChat/weChatAccount/findAccessToken.action"));
                }
                System.out.println("run ending");
            }
        };

        t.run();
    }
}
