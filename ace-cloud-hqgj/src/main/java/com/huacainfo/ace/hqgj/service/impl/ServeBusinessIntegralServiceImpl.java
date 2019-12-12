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
import com.huacainfo.ace.hqgj.dao.ServeBusinessIntegralDao;
import com.huacainfo.ace.hqgj.model.ServeBusinessIntegral;
import com.huacainfo.ace.hqgj.service.ServeBusinessIntegralService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessIntegralVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessIntegralQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(积分管理)
 */
public class ServeBusinessIntegralServiceImpl implements ServeBusinessIntegralService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeBusinessIntegralDao serveBusinessIntegralDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(积分管理分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessIntegralVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO
            <ServeBusinessIntegralVo> page(ServeBusinessIntegralQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeBusinessIntegralVo> rst = new PageDTO<>();
        List<ServeBusinessIntegralVo> list = this.serveBusinessIntegralDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveBusinessIntegralDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建积分管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "积分管理", operationType = "创建", detail = "创建积分管理")
    public ResponseDTO create(ServeBusinessIntegral o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new ResponseDTO(ResultCode.FAIL, "企业ID（关联base_company表id）不能为空！");
        }

        int temp = this.serveBusinessIntegralDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "积分管理名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveBusinessIntegralDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新积分管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "积分管理", operationType = "变更", detail = "变更积分管理")
    public ResponseDTO update(ServeBusinessIntegral o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new ResponseDTO(ResultCode.FAIL, "企业ID（关联base_company表id）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveBusinessIntegralDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取积分管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessIntegral>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<ServeBusinessIntegralVo> getById(String id) throws Exception {
        ResponseDTO<ServeBusinessIntegralVo> rst = new ResponseDTO<>();
        rst.setData(this.serveBusinessIntegralDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除积分管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "积分管理", operationType = "删除", detail = "删除积分管理")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveBusinessIntegralDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除积分管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "积分管理", operationType = "批量删除", detail = "批量删除积分管理")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveBusinessIntegralDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
