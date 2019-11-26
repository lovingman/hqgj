package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CKTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.CheckTree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.RoleDao;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.service.RoleService;
import com.huacainfo.ace.portal.vo.RoleQVo;
import com.huacainfo.ace.portal.vo.RoleVo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(用户角色)
 */
public class RoleServiceImpl implements RoleService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RoleDao roleDao;
    @Resource
    private SqlSessionTemplate sqlSession;

    @Resource
    private ResourcesService resourceService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(用户角色分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<RoleVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    public PageDTO<RoleVo> page(RoleQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<RoleVo> rst = new PageDTO<>();
        List<RoleVo> list = this.roleDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.roleDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建用户角色)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "用户角色", operationType = "创建", detail = "创建用户角色")
    public ResponseDTO create(Role o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getRoleName())) {
            return new ResponseDTO(ResultCode.FAIL, "角色名称不能为空！");
        }
        o.setRoleId(String.valueOf(new Date().getTime()));
        o.setCreateUserId(userProp.getUserId());
        o.setSyid(userProp.getActiveSyId());
        o.setCreateTime(new Date());
        int temp = this.roleDao.isExitRoleByRoleName(o.getRoleName());
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "角色名称已存在！");
        }
        this.roleDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新用户角色)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "用户角色", operationType = "变更", detail = "变更用户角色")
    public ResponseDTO update(Role role, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(role.getRoleId())) {
            return new ResponseDTO(ResultCode.FAIL, "角色编号不能为空！");
        }
        if (CommonUtils.isBlank(role.getRoleName())) {
            return new ResponseDTO(ResultCode.FAIL, "角色名称不能为空！");
        }
        if (CommonUtils.isBlank(role.getSyid())) {
            role.setSyid(userProp.getActiveSyId());
        }

        roleDao.updateByPrimaryKey(role);

        return new ResponseDTO(ResultCode.SUCCESS, "角色信息变更完成！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取用户角色)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Role>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    public ResponseDTO<RoleVo> getById(String id) throws Exception {
        ResponseDTO<RoleVo> rst = new ResponseDTO<>();
        rst.setData(this.roleDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除用户角色)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Log(operationObj = "用户角色", operationType = "删除", detail = "删除用户角色")
    public ResponseDTO deleteById(String id) throws Exception {
        this.roleDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除用户角色)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "用户角色", operationType = "批量删除", detail = "批量删除用户角色")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.roleDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    @Override
    @Transactional
    @Log(operationObj = "用户角色", operationType = "分配权限", detail = "分配权限")
    public ResponseDTO insertRoleResources(String roleId, String[] resourcesIds, UserProp userProp) {

        SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            RoleDao roleDao = session.getMapper(RoleDao.class);
            roleDao.deleteRoleResources(roleId);
            int i = 0;
            for (String resId : resourcesIds) {
                roleDao.insertRoleResources(roleId, resId);
                i++;
                if (i % 200 == 0) {
                    session.commit();
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.clearCache();
            session.close();
        }
        return new ResponseDTO(ResultCode.SUCCESS, "权限分配完成！");
    }

    @Override
    public List<CheckTree> getRoleResTreeList(String roleId) {
        CKTreeUtils checkTreeUtils = new CKTreeUtils(roleDao.findRoleResTreeList(roleId));
        return checkTreeUtils.getCheckTreeList("0");
    }

    /**
     * @throws Exception
     * @Title:selectRoleResByRoleId
     * @Description: TODO(角色编号获取已经分配的资源ID)
     * @param: @param roleId
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-07-14
     */
    @Override
    public ResponseDTO<String[]> selectRoleResByRoleId(String roleId) throws Exception{
        String [] res=this.roleDao.selectRoleResByRoleId(roleId);
        ResponseDTO< String []> rst = new ResponseDTO<>();
        rst.setData(res);
        return rst;
    }


}
