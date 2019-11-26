package com.huacainfo.ace.zcpa.service.impl;

//import com.github.pagehelper.PageHelper;

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
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.zcpa.dao.BaseSocialOrgDao;
import com.huacainfo.ace.zcpa.model.BaseSocialOrg;
import com.huacainfo.ace.zcpa.service.BaseSocialOrgService;
import com.huacainfo.ace.zcpa.vo.BaseSocialOrgVo;
import com.huacainfo.ace.zcpa.vo.BaseSocialOrgQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 豆文国
 * @version: 2019-09-25
 * @Description: TODO(志愿者社会组织)
 */
public class BaseSocialOrgServiceImpl implements BaseSocialOrgService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseSocialOrgDao baseSocialOrgDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(志愿者社会组织分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseSocialOrgVo>
     * @author: 豆文国
     * @version: 2019-09-25
     */
    @Override
    public PageDTO<BaseSocialOrgVo> page(BaseSocialOrgQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<BaseSocialOrgVo> rst = new PageDTO<>();
        List<BaseSocialOrgVo> list = this.baseSocialOrgDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.baseSocialOrgDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建志愿者社会组织)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-09-25
     */
    @Override
    @Transactional
    @Log(operationObj = "志愿者社会组织", operationType = "创建", detail = "创建志愿者社会组织")
    public ResponseDTO create(BaseSocialOrg o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgName())) {
            return new ResponseDTO(ResultCode.FAIL, "组织名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getUniCreditCode())) {
            return new ResponseDTO(ResultCode.FAIL, "统一信用代码不能为空！");
        }

        int temp = this.baseSocialOrgDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "志愿者社会组织名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        this.baseSocialOrgDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新志愿者社会组织)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-09-25
     */
    @Override
    @Transactional
    @Log(operationObj = "志愿者社会组织", operationType = "变更", detail = "变更志愿者社会组织")
    public ResponseDTO update(BaseSocialOrg o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgName())) {
            return new ResponseDTO(ResultCode.FAIL, "组织名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getUniCreditCode())) {
            return new ResponseDTO(ResultCode.FAIL, "统一信用代码不能为空！");
        }

        this.baseSocialOrgDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取志愿者社会组织)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseSocialOrg>
     * @author: 豆文国
     * @version: 2019-09-25
     */
    @Override
    @Transactional
    public ResponseDTO<BaseSocialOrgVo> getById(String id) throws Exception {
        ResponseDTO<BaseSocialOrgVo> rst = new ResponseDTO<>();
        rst.setData(this.baseSocialOrgDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除志愿者社会组织)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-09-25
     */
    @Override
    @Log(operationObj = "志愿者社会组织", operationType = "删除", detail = "删除志愿者社会组织")
    public ResponseDTO deleteById(String id) throws Exception {
        this.baseSocialOrgDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除志愿者社会组织)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-09-25
     */
    @Override
    @Transactional
    @Log(operationObj = "志愿者社会组织", operationType = "批量删除", detail = "批量删除志愿者社会组织")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.baseSocialOrgDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
