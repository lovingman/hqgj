package com.huacainfo.ace.portal.service;


import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.Tree;

import java.util.Map;

public interface SystemService {

    ListDTO<Tree> getSysTreeMenu(String userId) throws Exception;

    ListDTO<Map<String, String>> getSysButtons(String userId) throws Exception;


    ListDTO<Tree> getAreaTree(String pid,Integer type,String hasSelf) throws Exception;

    /**
     * pid 顶级行政区化编码
     * type 数据展示层级 5 省 4市 3县区 2 乡镇 1村;多级别请用，隔开
     * hasSelf 是否包含顶级行政区化
     **/
    ListDTO<Tree> getNewAreaTree(String pid, String type, String hasSelf);


    String refreshToken(String oldToken);


    ResponseDTO<String[]> getPAreaCode(String areaCode) throws Exception;;
}
