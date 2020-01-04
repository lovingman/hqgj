package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.BaseOrganizationMemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.BaseOrganizationDao;
import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.service.BaseOrganizationService;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationVo;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 豆文国
 * @version: 2019-12-09
 * @Description: TODO(企业管理)
 */
public class BaseOrganizationServiceImpl implements BaseOrganizationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseOrganizationDao baseOrganizationDao;
    @Resource
    private BaseOrganizationMemberDao baseOrganizationMemberDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseOrganizationVo>
     * @author: 豆文国
     * @version: 2019-12-09
     */
    @Override
    public PageDTO
            <BaseOrganizationVo> page(BaseOrganizationQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<BaseOrganizationVo> rst = new PageDTO<>();
        List<BaseOrganizationVo> list = this.baseOrganizationDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.baseOrganizationDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建企业管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "企业管理", operationType = "创建", detail = "创建企业管理")
    public ResponseDTO create(BaseOrganization o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());

        int temp = this.baseOrganizationDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "企业管理名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.baseOrganizationDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新企业管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "企业管理", operationType = "变更", detail = "变更企业管理")
    public ResponseDTO update(BaseOrganization o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.baseOrganizationDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseOrganization>
     * @author: 豆文国
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<BaseOrganizationVo> getById(String id) throws Exception {
        ResponseDTO<BaseOrganizationVo> rst = new ResponseDTO<>();
        rst.setData(this.baseOrganizationDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "企业管理", operationType = "删除", detail = "删除企业管理")
    public ResponseDTO deleteById(String id) throws Exception {
        this.baseOrganizationDao.deleteByPrimaryKey(id);
        int i=baseOrganizationMemberDao.deleteByOrgId(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企业管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "企业管理", operationType = "批量删除", detail = "批量删除企业管理")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.baseOrganizationDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
