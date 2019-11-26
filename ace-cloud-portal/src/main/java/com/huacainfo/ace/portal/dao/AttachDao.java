package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachDao {

    int deleteByPrimaryKey(String id);

    int insert(Attach o);

    int updateByPrimaryKey(Attach o);

    AttachVo selectVoByPrimaryKey(String id);

    List<AttachVo> findList(@Param("condition") AttachQVo condition);

    int findCount(@Param("condition") AttachQVo condition);

    int isExist(Attach o);

    int deleteByIds(String[] ids);
}
