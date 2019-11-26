package com.huacainfo.ace.portal.controller.base;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.huacainfo.ace.common.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName BizController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/5/17 15:29
 */
public class BizController extends BaseController {
    @Value("${fdfs.web-server-url}")
    public String webServerUrl;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getResAccessUrl(StorePath storePath) {
        String fileUrl = webServerUrl + "/" + storePath.getFullPath();
        logger.info("{}", fileUrl);
        return fileUrl;
    }
}
