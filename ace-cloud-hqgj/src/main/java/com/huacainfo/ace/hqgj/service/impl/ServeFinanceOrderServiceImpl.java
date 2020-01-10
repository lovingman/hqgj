package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.PersonCenterDao;
import com.huacainfo.ace.hqgj.dao.ServeBusinessIntegralDao;
import com.huacainfo.ace.hqgj.dao.ServeFinanceDao;
import com.huacainfo.ace.hqgj.model.ServeFinance;
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
import com.huacainfo.ace.hqgj.dao.ServeFinanceOrderDao;
import com.huacainfo.ace.hqgj.model.ServeFinanceOrder;
import com.huacainfo.ace.hqgj.service.ServeFinanceOrderService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceOrderVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceOrderQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-12
 * @Description: TODO(财税服务订单表)
 */
public class ServeFinanceOrderServiceImpl implements ServeFinanceOrderService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeFinanceOrderDao serveFinanceOrderDao;
    @Resource
    private ServeFinanceDao serveFinanceDao;
    @Resource
    private PersonCenterDao personCenterDao;
    @Resource
    private ServeBusinessIntegralDao serveBusinessIntegralDao;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(财税服务订单表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceOrderVo>
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    public PageDTO
            <ServeFinanceOrderVo> page(ServeFinanceOrderQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeFinanceOrderVo> rst = new PageDTO<>();
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
        if(!CommonUtils.isBlank(condition.getType())){
            condition.setTypes(condition.getType().split(","));
        }
        if(!CommonUtils.isBlank(condition.getStatus())){
            condition.setStatuss(condition.getStatus().split(","));
        }
        List<ServeFinanceOrderVo> list = this.serveFinanceOrderDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveFinanceOrderDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建财税服务订单表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务订单表", operationType = "创建", detail = "创建财税服务订单表")
    public ResponseDTO create(ServeFinanceOrder o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getType())) {
            return new ResponseDTO(ResultCode.FAIL, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceId())) {
            return new ResponseDTO(ResultCode.FAIL, "财税服务表ID不能为空！");
        }
        String orderNo="";
        switch (o.getType()){
            case "1":
                orderNo ="JZ"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                break;
            case "2":
                orderNo = "CS"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                break;
            case "3":
                orderNo = "WZ"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                break;
            default:
                orderNo=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        }
        o.setOrderNo(orderNo);
        if(o.getType().equals("2")){
          if (CommonUtils.isBlank(o.getItemId())) {
              return new ResponseDTO(ResultCode.FAIL, "参数错误");
          }
       }
        if(o.getType().equals("3")){
            if (CommonUtils.isBlank(o.getContactId())) {
                return new ResponseDTO(ResultCode.FAIL, "参数错误");
            }
        }
        ServeFinance f =serveFinanceDao.selectVoByPrimaryKey(o.getFinanceId(),userProp.getUserId());
        if (f ==null) {
            return new ResponseDTO(ResultCode.FAIL, "数据错误");
        }
        UsersVo vo =personCenterDao.selectUserInfo(userProp.getUserId());
        if(CommonUtils.isBlank(vo.getCompanyId())){
            return new ResponseDTO(ResultCode.FAIL, "未绑定企业");
        }
        o.setCompanyId(vo.getCompanyId());
        o.setOrgId(f.getOrgId());
        o.setCreateDate(new Date());
        o.setStatus("0");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveFinanceOrderDao.insert(o);
        //下单成功减去积分
        serveBusinessIntegralDao.updateIntegral(userProp.getUserId());
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新财税服务订单表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务订单表", operationType = "变更", detail = "变更财税服务订单表")
    public ResponseDTO update(ServeFinanceOrder o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrderNo())) {
            return new ResponseDTO(ResultCode.FAIL, "订单号不能为空！");
        }
        if (CommonUtils.isBlank(o.getType())) {
            return new ResponseDTO(ResultCode.FAIL, "类型（1-代理记账 2-财税管理 3-专家问诊）不能为空！");
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
        this.serveFinanceOrderDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务订单表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinanceOrder>
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Transactional
    public ResponseDTO<ServeFinanceOrderVo> getById(String id) throws Exception {
        ResponseDTO<ServeFinanceOrderVo> rst = new ResponseDTO<>();
        rst.setData(this.serveFinanceOrderDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除财税服务订单表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Log(operationObj = "财税服务订单表", operationType = "删除", detail = "删除财税服务订单表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveFinanceOrderDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务订单表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务订单表", operationType = "批量删除", detail = "批量删除财税服务订单表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveFinanceOrderDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 修改状态
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO updateStatus(String id,String status) throws Exception {
        int i=  serveFinanceOrderDao.updateStatus(id,status);
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "更新失败");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "更新成功", status);
    }


}
