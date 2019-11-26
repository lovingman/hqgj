package com.huacainfo.ace.zcpa.oracleDao;


import com.huacainfo.ace.zcpa.oracleModel.ActivityFile;

import java.util.List;

public interface ActivityFileDao {
    int insert(ActivityFile o);

    List<ActivityFile> findList();

    int deleteFile();

    int deleteByIds(String[] ids);

}
