package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActActivityReport;
import com.huacainfo.ace.zcpa.vo.ActActivityReportQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityReportVo;

public interface ActActivityReportDao {

    int deleteByPrimaryKey(String id);

    int insert(ActActivityReport o);

    int updateByPrimaryKey(ActActivityReport o);

    ActActivityReportVo selectVoByPrimaryKey(String id);

    List<ActActivityReportVo> findList(@Param("condition") ActActivityReportQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActActivityReportQVo condition);

    int isExist(ActActivityReport o);

    int deleteByIds(String[] ids);

    int deleteByPostingIds(String[] ids);
}
