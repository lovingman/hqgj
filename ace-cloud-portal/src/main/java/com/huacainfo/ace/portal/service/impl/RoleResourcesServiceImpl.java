package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.RoleResourcesDao;
import com.huacainfo.ace.portal.model.RoleResources;
import com.huacainfo.ace.portal.service.RoleResourcesService;
import com.huacainfo.ace.portal.vo.RoleResourcesQVo;
import com.huacainfo.ace.portal.vo.RoleResourcesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(角色资源)
 */
public class RoleResourcesServiceImpl implements RoleResourcesService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RoleResourcesDao roleResourcesDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(角色资源分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<RoleResourcesVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    public PageDTO<RoleResourcesVo> page(RoleResourcesQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<RoleResourcesVo> rst = new PageDTO<>();
        List<RoleResourcesVo> list = this.roleResourcesDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.roleResourcesDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建角色资源)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "角色资源", operationType = "创建", detail = "创建角色资源")
    public ResponseDTO create(RoleResources o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getRoleId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getResourcesId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }

        int temp = this.roleResourcesDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "角色资源名称重复！");
        }
        this.roleResourcesDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新角色资源)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "角色资源", operationType = "变更", detail = "变更角色资源")
    public ResponseDTO update(RoleResources o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getRoleId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getResourcesId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }

        this.roleResourcesDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取角色资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<RoleResources>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    public ResponseDTO<RoleResourcesVo> getById(String id) throws Exception {
        ResponseDTO<RoleResourcesVo> rst = new ResponseDTO<>();
        rst.setData(this.roleResourcesDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除角色资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Log(operationObj = "角色资源", operationType = "删除", detail = "删除角色资源")
    public ResponseDTO deleteById(String id) throws Exception {
        this.roleResourcesDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除角色资源)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "角色资源", operationType = "批量删除", detail = "批量删除角色资源")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.roleResourcesDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
