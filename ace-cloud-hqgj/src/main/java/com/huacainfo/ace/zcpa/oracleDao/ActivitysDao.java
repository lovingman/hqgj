package com.huacainfo.ace.zcpa.oracleDao;

import com.huacainfo.ace.zcpa.oracleModel.Activitys;

public interface ActivitysDao {
    int insert(Activitys o);

    int deleteActivitys();

    int deleteByIds(String[] ids);

    int updateByPrimaryKey(Activitys o);

    int isExist(Activitys o);
}
