package com.huacainfo.ace.zcpa.dao;


import com.huacainfo.ace.zcpa.dataVo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DataDockingDao {

   List<AreaCodeNumVo> findVolunteerNum(@Param("condition") DataDockingQVo condition);

   List<AreaCodeNumVo> findProjectNum(@Param("condition")DataDockingQVo condition);

   List<FamilyTargetVo> findFamilyTargetList(@Param("condition")DataDockingQVo condition);

   List<FamilyTargetListVo> findFamilyVolunteertList(@Param("condition")DataDockingQVo condition);

   List<ActivityVo> findActivityList(@Param("condition")DataDockingQVo condition);

}
