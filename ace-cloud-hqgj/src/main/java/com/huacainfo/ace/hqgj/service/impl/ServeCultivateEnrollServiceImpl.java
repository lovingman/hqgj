package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.ServeCultivateDao;
import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.vo.ServeCultivateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.ServeCultivateEnrollDao;
import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;
import com.huacainfo.ace.hqgj.service.ServeCultivateEnrollService;
import com.huacainfo.ace.hqgj.vo.ServeCultivateEnrollVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateEnrollQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升 - 报名管理)
 */
public class ServeCultivateEnrollServiceImpl implements ServeCultivateEnrollService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeCultivateEnrollDao serveCultivateEnrollDao;
    @Resource
    private ServeCultivateDao serveCultivateDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(培训提升 - 报名管理分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateEnrollVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO<ServeCultivateEnrollVo> page(ServeCultivateEnrollQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeCultivateEnrollVo> rst = new PageDTO<>();
        if(!CommonUtils.isBlank(condition.getStatus())){
            condition.setStatuss(condition.getStatus().split(","));
        }
        List<ServeCultivateEnrollVo> list = this.serveCultivateEnrollDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveCultivateEnrollDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建培训提升 - 报名管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升-报名管理", operationType = "创建", detail = "创建培训提升-报名管理")
    public ResponseDTO create(ServeCultivateEnroll o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        String serveCultivateId=o.getServeCultivateId();
        if (CommonUtils.isBlank(serveCultivateId)) {
            return new ResponseDTO(ResultCode.FAIL, "培训提升基础表ID（关联serve_cultivate表id）不能为空！");
        }
        o.setIsSign("n");
        o.setCreateDate(new Date());
        o.setStatus("0");
        o.setEnrollUserId(userProp.getUserId());
        o.setEnrollName(userProp.getName());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveCultivateEnrollDao.insert(o);
        //培训基础信息 报名人数+1
       int i = serveCultivateDao.addEnrollNum(serveCultivateId);

        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新培训提升 - 报名管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升-报名管理", operationType = "变更", detail = "变更培训提升-报名管理")
    public ResponseDTO update(ServeCultivateEnroll o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getServeCultivateId())) {
            return new ResponseDTO(ResultCode.FAIL, "培训提升基础表ID（关联serve_cultivate表id）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveCultivateEnrollDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升 - 报名管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivateEnroll>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<ServeCultivateEnrollVo> getById(String id) throws Exception {
        ResponseDTO<ServeCultivateEnrollVo> rst = new ResponseDTO<>();
        rst.setData(this.serveCultivateEnrollDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除培训提升 - 报名管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "培训提升-报名管理", operationType = "删除", detail = "删除培训提升-报名管理")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveCultivateEnrollDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除培训提升 - 报名管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升-报名管理", operationType = "批量删除", detail = "批量删除培训提升-报名管理")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveCultivateEnrollDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 批量修改状态
     * @param status
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO updateStatus(String status, String[] ids) throws Exception {
        if(CommonUtils.isBlank(status) || CommonUtils.isBlank(ids)){
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        this.serveCultivateEnrollDao.updateStatus(status,ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 取消报名
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO cancelEnroll(String id) throws Exception {
        if(CommonUtils.isBlank(id)){
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        serveCultivateEnrollDao.updateStatus("1",id.split(","));
        ServeCultivateEnrollVo vo =serveCultivateEnrollDao.selectVoByPrimaryKey(id);
        if(vo==null){
            return new ResponseDTO(ResultCode.FAIL, "内部错误！");
        }
       int i = serveCultivateDao.cancalEnrollNum(vo.getServeCultivateId());

        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
