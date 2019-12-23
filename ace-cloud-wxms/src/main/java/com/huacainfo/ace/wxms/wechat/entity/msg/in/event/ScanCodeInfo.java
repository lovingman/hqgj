/**
 * project name:distribution
 * file name:ScanCodeInfo
 * package name:com.arvin.wechat.pojo.msg.in.event
 * date:2016-07-27 11:17
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in.event;

/**
 * description: 菜单二维码扫描的结果实体
 * date: 2016-07-27 11:17
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public class ScanCodeInfo {
    private String ScanType;
    private String ScanResult;

    public ScanCodeInfo(String scanType, String scanResult) {
        super();
        ScanType = scanType;
        ScanResult = scanResult;
    }

    public String getScanType() {
        return ScanType;
    }

    public void setScanType(String scanType) {
        ScanType = scanType;
    }

    public String getScanResult() {
        return ScanResult;
    }

    public void setScanResult(String scanResult) {
        ScanResult = scanResult;
    }

}
