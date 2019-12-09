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
import com.huacainfo.ace.hqgj.dao.BaseCompanyMemberDao;
import com.huacainfo.ace.hqgj.model.BaseCompanyMember;
import com.huacainfo.ace.hqgj.service.BaseCompanyMemberService;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberVo;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(企业成员表)
 */
public class BaseCompanyMemberServiceImpl implements BaseCompanyMemberService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseCompanyMemberDao baseCompanyMemberDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业成员表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseCompanyMemberVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO<BaseCompanyMemberVo> page(BaseCompanyMemberQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<BaseCompanyMemberVo> rst = new PageDTO<>();
        List<BaseCompanyMemberVo> list = this.baseCompanyMemberDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.baseCompanyMemberDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建企业成员表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "企业成员表", operationType = "创建", detail = "创建企业成员表")
    public ResponseDTO create(BaseCompanyMember o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new ResponseDTO(ResultCode.FAIL, "企业ID（关联base_company表id）不能为空！");
        }

        int temp = this.baseCompanyMemberDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "企业成员表名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.baseCompanyMemberDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新企业成员表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "企业成员表", operationType = "变更", detail = "变更企业成员表")
    public ResponseDTO update(BaseCompanyMember o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getCompanyId())) {
            return new ResponseDTO(ResultCode.FAIL, "企业ID（关联base_company表id）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.baseCompanyMemberDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企业成员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseCompanyMember>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<BaseCompanyMemberVo> getById(String id) throws Exception {
        ResponseDTO<BaseCompanyMemberVo> rst = new ResponseDTO<>();
        rst.setData(this.baseCompanyMemberDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除企业成员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "企业成员表", operationType = "删除", detail = "删除企业成员表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.baseCompanyMemberDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企业成员表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "企业成员表", operationType = "批量删除", detail = "批量删除企业成员表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.baseCompanyMemberDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
