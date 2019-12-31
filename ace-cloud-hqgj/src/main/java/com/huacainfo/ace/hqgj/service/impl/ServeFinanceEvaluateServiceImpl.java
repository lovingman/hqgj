package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.PersonCenterDao;
import com.huacainfo.ace.hqgj.dao.ServeFinanceOrderDao;
import com.huacainfo.ace.hqgj.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.ServeFinanceEvaluateDao;
import com.huacainfo.ace.hqgj.model.ServeFinanceEvaluate;
import com.huacainfo.ace.hqgj.service.ServeFinanceEvaluateService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceEvaluateVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceEvaluateQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-12
 * @Description: TODO(财税服务订单评价表)
 */
public class ServeFinanceEvaluateServiceImpl implements ServeFinanceEvaluateService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeFinanceEvaluateDao serveFinanceEvaluateDao;
    @Resource
    private PersonCenterDao personCenterDao;
    @Resource
    private ServeFinanceOrderDao serveFinanceOrderDao;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(财税服务订单评价表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceEvaluateVo>
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    public PageDTO
            <ServeFinanceEvaluateVo> page(ServeFinanceEvaluateQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeFinanceEvaluateVo> rst = new PageDTO<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!CommonUtils.isBlank(condition.getEndTime())) {
            String endTime = sdf.format(condition.getEndTime()) + " 23:59:59 ";
            condition.setEndTime(sdf2.parse(endTime));
        }
        if (!CommonUtils.isBlank(condition.getStartTime())) {
            String startTime = sdf.format(condition.getStartTime()) + " 00:00:00 ";
            condition.setStartTime(sdf2.parse(startTime));
        }
        List<ServeFinanceEvaluateVo> list = this.serveFinanceEvaluateDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveFinanceEvaluateDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建财税服务订单评价表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务订单评价表", operationType = "创建", detail = "创建财税服务订单评价表")
    public ResponseDTO create(ServeFinanceEvaluate o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "订单id不能为空！");
        }
        String orderId=o.getId();
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getType())) {
            return new ResponseDTO(ResultCode.FAIL, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderNo())) {
            return new ResponseDTO(ResultCode.FAIL, "订单号不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgId())) {
            return new ResponseDTO(ResultCode.FAIL, "服务机构ID（关联base_organization表id）不能为空！");
        }
        if (CommonUtils.isBlank(o.getEvaluateGrade())) {
            return new ResponseDTO(ResultCode.FAIL, "评分不能为空！");
        }
        UsersVo vo =personCenterDao.selectUserInfo(userProp.getUserId());
        o.setCompanyId(vo.getCompanyId());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveFinanceEvaluateDao.insert(o);
        //已完成已评价
        int i=serveFinanceOrderDao.updateStatus(orderId,"32");
        if(i==0){
            return new ResponseDTO(ResultCode.FAIL, "内部错误！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新财税服务订单评价表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务订单评价表", operationType = "变更", detail = "变更财税服务订单评价表")
    public ResponseDTO update(ServeFinanceEvaluate o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderNo())) {
            return new ResponseDTO(ResultCode.FAIL, "订单号不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgId())) {
            return new ResponseDTO(ResultCode.FAIL, "服务机构ID（关联base_organization表id）不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new ResponseDTO(ResultCode.FAIL, "企业ID（关联base_company表id）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveFinanceEvaluateDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务订单评价表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinanceEvaluate>
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Transactional
    public ResponseDTO<ServeFinanceEvaluateVo> getById(String id) throws Exception {
        ResponseDTO<ServeFinanceEvaluateVo> rst = new ResponseDTO<>();
        rst.setData(this.serveFinanceEvaluateDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除财税服务订单评价表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Log(operationObj = "财税服务订单评价表", operationType = "删除", detail = "删除财税服务订单评价表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveFinanceEvaluateDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务订单评价表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务订单评价表", operationType = "批量删除", detail = "批量删除财税服务订单评价表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveFinanceEvaluateDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
