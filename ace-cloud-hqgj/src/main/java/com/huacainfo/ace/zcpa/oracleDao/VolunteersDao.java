package com.huacainfo.ace.zcpa.oracleDao;

import com.huacainfo.ace.zcpa.oracleModel.Volunteers;
import org.apache.ibatis.annotations.Param;

public interface VolunteersDao {
    int insert(Volunteers o);

    int deleteActivitys();

    int deleteByIds(String[] ids);

    int updateByPrimaryKey(Volunteers o);

    int isExist(@Param("id") String id);
}
