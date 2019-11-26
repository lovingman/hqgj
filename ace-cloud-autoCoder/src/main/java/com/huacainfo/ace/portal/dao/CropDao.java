package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Crop;

public interface CropDao {
    int deleteByPrimaryKey(String id);

    int insert(Crop record);

    int insertSelective(Crop record);

    Crop selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Crop record);

    int updateByPrimaryKey(Crop record);
}