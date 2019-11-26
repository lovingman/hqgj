package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActPersonDao;
import com.huacainfo.ace.zcpa.model.ActPerson;
import com.huacainfo.ace.zcpa.service.ActPersonService;
import com.huacainfo.ace.zcpa.vo.ActPersonQVo;
import com.huacainfo.ace.zcpa.vo.ActPersonVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目被服务对象)
 */
public class ActPersonServiceImpl implements ActPersonService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActPersonDao actPersonDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目被服务对象分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActPersonVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    public PageDTO
            <ActPersonVo> page(ActPersonQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ActPersonVo> rst = new PageDTO<>();
        List<ActPersonVo> list = this.actPersonDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actPersonDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }


    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目被服务对象)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被服务对象", operationType = "创建", detail = "创建项目被服务对象")
    public ResponseDTO create(ActPerson o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPersonName())) {
            return new ResponseDTO(ResultCode.FAIL, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new ResponseDTO(ResultCode.FAIL, "身份证号不能为空！");
        }

        int temp = this.actPersonDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "项目被服务对象名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.actPersonDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目被服务对象)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被服务对象", operationType = "变更", detail = "变更项目被服务对象")
    public ResponseDTO update(ActPerson o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPersonName())) {
            return new ResponseDTO(ResultCode.FAIL, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new ResponseDTO(ResultCode.FAIL, "身份证号不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.actPersonDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目被服务对象)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActPerson>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    public ResponseDTO<ActPersonVo> getById(String id) throws Exception {
        ResponseDTO<ActPersonVo> rst = new ResponseDTO<>();
        rst.setData(this.actPersonDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目被服务对象)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Log(operationObj = "项目被服务对象", operationType = "删除", detail = "删除项目被服务对象")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actPersonDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目被服务对象)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被服务对象", operationType = "批量删除", detail = "批量删除项目被服务对象")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actPersonDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
