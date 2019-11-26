package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.BaseVolunteerDao;
import com.huacainfo.ace.zcpa.dao.RegisterDao;
import com.huacainfo.ace.zcpa.model.BaseVolunteer;
import com.huacainfo.ace.zcpa.service.BaseVolunteerService;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
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
 * @Description: TODO(群众 / 志愿者)
 */
public class BaseVolunteerServiceImpl implements BaseVolunteerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseVolunteerDao baseVolunteerDao;
    @Resource
    private RegisterDao registerDao;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(群众 / 志愿者分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO<BaseVolunteerVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    public PageDTO<BaseVolunteerVo> page(BaseVolunteerQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<BaseVolunteerVo> rst = new PageDTO<>();
        List<BaseVolunteerVo> list = this.baseVolunteerDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.baseVolunteerDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建群众 / 志愿者)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "群众/志愿者", operationType = "创建", detail = "创建群众/志愿者")
    public ResponseDTO create(BaseVolunteer o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getSex())) {
            return new ResponseDTO(ResultCode.FAIL, "性别不能为空！");
        }
        if (CommonUtils.isBlank(o.getMobile())) {
            return new ResponseDTO(ResultCode.FAIL, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new ResponseDTO(ResultCode.FAIL, "身份证号码不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserType())) {
            return new ResponseDTO(ResultCode.FAIL, "用户类型不能为空！");
        }

        int temp = this.baseVolunteerDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "身份证号码重复！");
        }
        o.setId(CommonUtils.isBlank(o.getId()) ? GUIDUtil.getGUID() : o.getId());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.baseVolunteerDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新群众 / 志愿者)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "群众/志愿者", operationType = "变更", detail = "变更群众/志愿者")
    public ResponseDTO update(BaseVolunteer o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.baseVolunteerDao.updateByPrimaryKey(o);

        Users user = getUsers(o);
        registerDao.updateUser(user);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    private Users getUsers(BaseVolunteer dto) {
        Users user = new Users();
        user.setSex(dto.getSex());
        user.setAreaCode(dto.getAreaCode());
        user.setUserId(dto.getId());
        user.setDeptId(dto.getDeptId());
        user.setUserType(dto.getUserType());
        return user;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取群众 / 志愿者)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    public ResponseDTO<BaseVolunteerVo> getById(String id) throws Exception {
        ResponseDTO<BaseVolunteerVo> rst = new ResponseDTO<>();
        rst.setData(this.baseVolunteerDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除群众 / 志愿者)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Log(operationObj = "群众/志愿者", operationType = "删除", detail = "删除群众/志愿者")
    public ResponseDTO deleteById(String id) throws Exception {
        this.baseVolunteerDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除群众 / 志愿者)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "群众/志愿者", operationType = "批量删除", detail = "批量删除群众/志愿者")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.baseVolunteerDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
