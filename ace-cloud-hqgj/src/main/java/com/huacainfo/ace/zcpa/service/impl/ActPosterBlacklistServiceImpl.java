package com.huacainfo.ace.zcpa.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActPosterBlacklistDao;
import com.huacainfo.ace.zcpa.model.ActPosterBlacklist;
import com.huacainfo.ace.zcpa.service.ActPosterBlacklistService;
import com.huacainfo.ace.zcpa.vo.ActPosterBlacklistQVo;
import com.huacainfo.ace.zcpa.vo.ActPosterBlacklistVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: 何双
 * @version: 2019-08-07
 * @Description: TODO(发贴人黑名单列表)
 */
public class ActPosterBlacklistServiceImpl implements ActPosterBlacklistService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActPosterBlacklistDao actPosterBlacklistDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(发贴人黑名单列表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActPosterBlacklistVo>
     * @author: 何双
     * @version: 2019-08-07
     */
    @Override
    public PageDTO<ActPosterBlacklistVo> page(ActPosterBlacklistQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ActPosterBlacklistVo> rst = new PageDTO<>();
        List<ActPosterBlacklistVo> list = this.actPosterBlacklistDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actPosterBlacklistDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建发贴人黑名单列表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    @Override
    @Transactional
    @Log(operationObj = "发贴人黑名单列表", operationType = "创建", detail = "创建发贴人黑名单列表")
    public ResponseDTO create(String jsons, UserProp userProp) throws Exception {
        JSONObject object = JSON.parseObject(jsons);
        List<ActPosterBlacklist> actPosterBlacklist = new ArrayList<ActPosterBlacklist>(
                JSONArray.parseArray(object.getString("actFamilyVolunteer"), ActPosterBlacklist.class));
        if (CommonUtils.isBlank(actPosterBlacklist)) {
            return new ResponseDTO(ResultCode.FAIL, "用户不能为空");
        }
        for (ActPosterBlacklist o : actPosterBlacklist) {
            if (CommonUtils.isBlank(o.getPostingId())) {
                return new ResponseDTO(ResultCode.FAIL, "发帖人Id不能为空");
            }
            int temp = this.actPosterBlacklistDao.isExist(o.getPostingId());
            if (temp > 0) {
                return new ResponseDTO(ResultCode.FAIL, "该用户已经存在黑名单！");
            }
            o.setId(GUIDUtil.getGUID());
            o.setCreateDate(new Date());
            o.setStatus("1");
            o.setCreateUserName(userProp.getName());
            o.setCreateUserId(userProp.getUserId());
            this.actPosterBlacklistDao.insert(o);
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新发贴人黑名单列表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    @Override
    @Transactional
    @Log(operationObj = "发贴人黑名单列表", operationType = "变更", detail = "变更发贴人黑名单列表")
    public ResponseDTO update(ActPosterBlacklist o, UserProp userProp) throws Exception {

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.actPosterBlacklistDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取发贴人黑名单列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActPosterBlacklist>
     * @author: 何双
     * @version: 2019-08-07
     */
    @Override
    @Transactional
    public ResponseDTO<ActPosterBlacklistVo> getById(String id) throws Exception {
        ResponseDTO<ActPosterBlacklistVo> rst = new ResponseDTO<>();
        rst.setData(this.actPosterBlacklistDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除发贴人黑名单列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    @Override
    @Log(operationObj = "发贴人黑名单列表", operationType = "删除", detail = "删除发贴人黑名单列表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actPosterBlacklistDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除发贴人黑名单列表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    @Override
    @Transactional
    @Log(operationObj = "发贴人黑名单列表", operationType = "批量删除", detail = "批量删除发贴人黑名单列表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new ResponseDTO(ResultCode.FAIL, "Id不能为空");
        }
        int i=this.actPosterBlacklistDao.deleteByIds(ids);
        if(i<1){
            return new ResponseDTO(ResultCode.FAIL, "失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 判断用户是否存在黑名单
     *
     * @param postingId
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO isExist(String postingId) throws Exception {
        ResponseDTO dto = new ResponseDTO();
        if (CommonUtils.isBlank(postingId)) {
            return new ResponseDTO(ResultCode.FAIL, "发帖人Id不能为空");
        }
        if (actPosterBlacklistDao.isExist(postingId) > 0) {
            dto.setData(false);
            return dto;
        }
        dto.setData(true);
        return dto;
    }


}
