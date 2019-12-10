package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.LawServeDao;
import com.huacainfo.ace.hqgj.model.LawServe;
import com.huacainfo.ace.hqgj.service.LawServeService;
import com.huacainfo.ace.hqgj.vo.LawServeVo;
import com.huacainfo.ace.hqgj.vo.LawServeQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 豆文国
 * @version: 2019-12-10
 * @Description: TODO(法律服务)
 */
public class LawServeServiceImpl implements LawServeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private LawServeDao lawServeDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(法律服务分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <LawServeVo>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    public PageDTO
            <LawServeVo> page(LawServeQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<LawServeVo> rst = new PageDTO<>();
        List<LawServeVo> list = this.lawServeDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.lawServeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建法律服务)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "法律服务", operationType = "创建", detail = "创建法律服务")
    public ResponseDTO create(LawServe o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());

        int temp = this.lawServeDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "法律服务名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.lawServeDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新法律服务)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "法律服务", operationType = "变更", detail = "变更法律服务")
    public ResponseDTO update(LawServe o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.lawServeDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取法律服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<LawServe>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    public ResponseDTO<LawServeVo> getById(String id) throws Exception {
        ResponseDTO<LawServeVo> rst = new ResponseDTO<>();
        rst.setData(this.lawServeDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除法律服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Log(operationObj = "法律服务", operationType = "删除", detail = "删除法律服务")
    public ResponseDTO deleteById(String id) throws Exception {
        this.lawServeDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除法律服务)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "法律服务", operationType = "批量删除", detail = "批量删除法律服务")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.lawServeDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
