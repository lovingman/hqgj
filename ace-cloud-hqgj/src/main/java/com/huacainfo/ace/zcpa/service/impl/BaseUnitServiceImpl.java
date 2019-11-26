package com.huacainfo.ace.zcpa.service.impl;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
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
import com.huacainfo.ace.zcpa.dao.BaseUnitDao;
import com.huacainfo.ace.zcpa.model.BaseUnit;
import com.huacainfo.ace.zcpa.service.BaseUnitService;
import com.huacainfo.ace.zcpa.vo.BaseUnitVo;
import com.huacainfo.ace.zcpa.vo.BaseUnitQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-09-27
 * @Description: TODO(平安单位)
 */
public class BaseUnitServiceImpl implements BaseUnitService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseUnitDao baseUnitDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(平安单位分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseUnitVo>
     * @author: 何双
     * @version: 2019-09-27
     */
    @Override
    public PageDTO<BaseUnitVo> page(BaseUnitQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<BaseUnitVo> rst = new PageDTO<>();
        List<BaseUnitVo> list = this.baseUnitDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.baseUnitDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建平安单位)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-09-27
     */
    @Override
    @Transactional
    @Log(operationObj = "平安单位", operationType = "创建", detail = "创建平安单位")
    public ResponseDTO create(BaseUnit o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主鍵不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "单位名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAddress())) {
            return new ResponseDTO(ResultCode.FAIL, "单位地址不能为空！");
        }

        int temp = this.baseUnitDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "平安单位名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.baseUnitDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新平安单位)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-09-27
     */
    @Override
    @Transactional
    @Log(operationObj = "平安单位", operationType = "变更", detail = "变更平安单位")
    public ResponseDTO update(BaseUnit o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主鍵不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "单位名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getAddress())) {
            return new ResponseDTO(ResultCode.FAIL, "单位地址不能为空！");
        }

        this.baseUnitDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取平安单位)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseUnit>
     * @author: 何双
     * @version: 2019-09-27
     */
    @Override
    @Transactional
    public ResponseDTO<BaseUnitVo> getById(String id) throws Exception {
        ResponseDTO<BaseUnitVo> rst = new ResponseDTO<>();
        rst.setData(this.baseUnitDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除平安单位)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-09-27
     */
    @Override
    @Log(operationObj = "平安单位", operationType = "删除", detail = "删除平安单位")
    public ResponseDTO deleteById(String id) throws Exception {
        this.baseUnitDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除平安单位)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-09-27
     */
    @Override
    @Transactional
    @Log(operationObj = "平安单位", operationType = "批量删除", detail = "批量删除平安单位")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.baseUnitDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
