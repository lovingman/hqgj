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
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.ServeBusinessAppendDao;
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;
import com.huacainfo.ace.hqgj.service.ServeBusinessAppendService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessAppendVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessAppendQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-13
 * @Description: TODO(创业服务资料其它附加信息)
 */
public class ServeBusinessAppendServiceImpl implements ServeBusinessAppendService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeBusinessAppendDao serveBusinessAppendDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(创业服务资料其它附加信息分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessAppendVo>
     * @author: 何双
     * @version: 2019-12-13
     */
    @Override
    public PageDTO
            <ServeBusinessAppendVo> page(ServeBusinessAppendQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeBusinessAppendVo> rst = new PageDTO<>();
        List<ServeBusinessAppendVo> list = this.serveBusinessAppendDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveBusinessAppendDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建创业服务资料其它附加信息)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-13
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料其它附加信息", operationType = "创建", detail = "创建创业服务资料其它附加信息")
    public ResponseDTO create(ServeBusinessAppend o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new ResponseDTO(ResultCode.FAIL, "创业服务表ID（关联serve_business表ID）不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new ResponseDTO(ResultCode.FAIL, "类别 1-申请免费服务项目 2-确认培训意向 3-开户银行 4-是否选择代理记账服务 5-特殊说明不能为空！");
        }
        if (CommonUtils.isBlank(o.getOption())) {
            return new ResponseDTO(ResultCode.FAIL, "选项值（各类别对应的选项值，如type=1 申请免费服务项目，则 1-工商注册代办服务 2-银行基本开户代办服务 3-银行开户费小额账户管理费等，具体根据字典来）不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new ResponseDTO(ResultCode.FAIL, "状态  1-生效 0-失效不能为空！");
        }

        int temp = this.serveBusinessAppendDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "创业服务资料其它附加信息名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveBusinessAppendDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新创业服务资料其它附加信息)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-13
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料其它附加信息", operationType = "变更", detail = "变更创业服务资料其它附加信息")
    public ResponseDTO update(ServeBusinessAppend o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new ResponseDTO(ResultCode.FAIL, "创业服务表ID（关联serve_business表ID）不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new ResponseDTO(ResultCode.FAIL, "类别 1-申请免费服务项目 2-确认培训意向 3-开户银行 4-是否选择代理记账服务 5-特殊说明不能为空！");
        }
        if (CommonUtils.isBlank(o.getOption())) {
            return new ResponseDTO(ResultCode.FAIL, "选项值（各类别对应的选项值，如type=1 申请免费服务项目，则 1-工商注册代办服务 2-银行基本开户代办服务 3-银行开户费小额账户管理费等，具体根据字典来）不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new ResponseDTO(ResultCode.FAIL, "状态  1-生效 0-失效不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveBusinessAppendDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务资料其它附加信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessAppend>
     * @author: 何双
     * @version: 2019-12-13
     */
    @Override
    @Transactional
    public ResponseDTO<ServeBusinessAppendVo> getById(String id) throws Exception {
        ResponseDTO<ServeBusinessAppendVo> rst = new ResponseDTO<>();
        rst.setData(this.serveBusinessAppendDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除创业服务资料其它附加信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-13
     */
    @Override
    @Log(operationObj = "创业服务资料其它附加信息", operationType = "删除", detail = "删除创业服务资料其它附加信息")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveBusinessAppendDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务资料其它附加信息)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-13
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料其它附加信息", operationType = "批量删除", detail = "批量删除创业服务资料其它附加信息")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveBusinessAppendDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
