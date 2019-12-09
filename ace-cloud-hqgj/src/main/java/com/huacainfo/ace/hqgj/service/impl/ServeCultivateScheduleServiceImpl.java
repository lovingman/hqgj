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
import com.huacainfo.ace.hqgj.dao.ServeCultivateScheduleDao;
import com.huacainfo.ace.hqgj.model.ServeCultivateSchedule;
import com.huacainfo.ace.hqgj.service.ServeCultivateScheduleService;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升日程表)
 */
public class ServeCultivateScheduleServiceImpl implements ServeCultivateScheduleService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeCultivateScheduleDao serveCultivateScheduleDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(培训提升日程表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateScheduleVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO
            <ServeCultivateScheduleVo> page(ServeCultivateScheduleQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeCultivateScheduleVo> rst = new PageDTO<>();
        List<ServeCultivateScheduleVo> list = this.serveCultivateScheduleDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveCultivateScheduleDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建培训提升日程表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升日程表", operationType = "创建", detail = "创建培训提升日程表")
    public ResponseDTO create(ServeCultivateSchedule o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getServeCultivateId())) {
            return new ResponseDTO(ResultCode.FAIL, "培训提升基础表ID（关联serve_cultivate表id）不能为空！");
        }

        int temp = this.serveCultivateScheduleDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "培训提升日程表名称重复！");
        }

        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveCultivateScheduleDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新培训提升日程表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升日程表", operationType = "变更", detail = "变更培训提升日程表")
    public ResponseDTO update(ServeCultivateSchedule o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getServeCultivateId())) {
            return new ResponseDTO(ResultCode.FAIL, "培训提升基础表ID（关联serve_cultivate表id）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveCultivateScheduleDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升日程表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivateSchedule>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<ServeCultivateScheduleVo> getById(String id) throws Exception {
        ResponseDTO<ServeCultivateScheduleVo> rst = new ResponseDTO<>();
        rst.setData(this.serveCultivateScheduleDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除培训提升日程表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "培训提升日程表", operationType = "删除", detail = "删除培训提升日程表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveCultivateScheduleDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除培训提升日程表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升日程表", operationType = "批量删除", detail = "批量删除培训提升日程表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveCultivateScheduleDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
