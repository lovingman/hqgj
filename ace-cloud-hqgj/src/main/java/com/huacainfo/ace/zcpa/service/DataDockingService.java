package com.huacainfo.ace.zcpa.service;


import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.zcpa.dataVo.DataDockingQVo;
import com.huacainfo.ace.zcpa.dataVo.FamilyTargetListVo;
import com.huacainfo.ace.zcpa.dataVo.FamilyTargetVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerQVo;


public interface DataDockingService {
    ResponseDTO findVolunteerNum(DataDockingQVo condition) throws Exception;

    ResponseDTO findProjectNum(DataDockingQVo condition) throws Exception;

    ResponseDTO<FamilyTargetVo> findFamilyTargetList(DataDockingQVo condition);

    ResponseDTO<FamilyTargetListVo> findFamilyVolunteertList(DataDockingQVo condition);

    // ResponseDTO<ActivityVo> findActivityList(DataDockingQVo condition);
    ResponseDTO findActivityList(DataDockingQVo condition);


    ResponseDTO findVolunteerList(BaseVolunteerQVo condition)throws Exception ;
}

