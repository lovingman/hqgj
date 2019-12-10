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
import com.huacainfo.ace.hqgj.dao.BaseOrganizationMemberDao;
import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.service.BaseOrganizationMemberService;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationMemberVo;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationMemberQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 豆文国
 * @version: 2019-12-10
 * @Description: TODO(服务机构成员)
 */
public class BaseOrganizationMemberServiceImpl implements BaseOrganizationMemberService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseOrganizationMemberDao baseOrganizationMemberDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(服务机构成员分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseOrganizationMemberVo>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    public PageDTO
            <BaseOrganizationMemberVo> page(BaseOrganizationMemberQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<BaseOrganizationMemberVo> rst = new PageDTO<>();
        List<BaseOrganizationMemberVo> list = this.baseOrganizationMemberDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.baseOrganizationMemberDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建服务机构成员)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "服务机构成员", operationType = "创建", detail = "创建服务机构成员")
    public ResponseDTO create(BaseOrganizationMember o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());

        int temp = this.baseOrganizationMemberDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "服务机构成员名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.baseOrganizationMemberDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新服务机构成员)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "服务机构成员", operationType = "变更", detail = "变更服务机构成员")
    public ResponseDTO update(BaseOrganizationMember o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgId())) {
            return new ResponseDTO(ResultCode.FAIL, "服务机构ID（关联base_organization表id）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.baseOrganizationMemberDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取服务机构成员)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseOrganizationMember>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    public ResponseDTO<BaseOrganizationMemberVo> getById(String id) throws Exception {
        ResponseDTO<BaseOrganizationMemberVo> rst = new ResponseDTO<>();
        rst.setData(this.baseOrganizationMemberDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除服务机构成员)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Log(operationObj = "服务机构成员", operationType = "删除", detail = "删除服务机构成员")
    public ResponseDTO deleteById(String id) throws Exception {
        this.baseOrganizationMemberDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除服务机构成员)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "服务机构成员", operationType = "批量删除", detail = "批量删除服务机构成员")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.baseOrganizationMemberDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
