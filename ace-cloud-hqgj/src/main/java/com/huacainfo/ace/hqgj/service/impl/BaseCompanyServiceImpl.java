package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.CommonBeanUtils;
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
import com.huacainfo.ace.hqgj.dao.BaseCompanyDao;
import com.huacainfo.ace.hqgj.model.BaseCompany;
import com.huacainfo.ace.hqgj.service.BaseCompanyService;
import com.huacainfo.ace.hqgj.vo.BaseCompanyVo;
import com.huacainfo.ace.hqgj.vo.BaseCompanyQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-06
 * @Description: TODO(企业管理)
 */
public class BaseCompanyServiceImpl implements BaseCompanyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseCompanyDao baseCompanyDao;

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
     * <BaseCompanyVo>
     * @author: 何双
     * @version: 2019-12-06
     */
    @Override
    public PageDTO
            <BaseCompanyVo> page(BaseCompanyQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<BaseCompanyVo> rst = new PageDTO<>();
        List<BaseCompanyVo> list = this.baseCompanyDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.baseCompanyDao.findCount(condition);
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
     * @author: 何双
     * @version: 2019-12-06
     */
    @Override
    @Transactional
    @Log(operationObj = "企业管理", operationType = "创建", detail = "创建企业管理")
    public ResponseDTO create(BaseCompany o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }

        int temp = this.baseCompanyDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "企业管理名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.baseCompanyDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新企业管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    @Override
    @Transactional
    @Log(operationObj = "企业管理", operationType = "变更", detail = "变更企业管理")
    public ResponseDTO update(BaseCompany o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.baseCompanyDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseCompany>
     * @author: 何双
     * @version: 2019-12-06
     */
    @Override
    @Transactional
    public ResponseDTO<BaseCompanyVo> getById(String id) throws Exception {
        ResponseDTO<BaseCompanyVo> rst = new ResponseDTO<>();
        rst.setData(this.baseCompanyDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    @Override
    @Log(operationObj = "企业管理", operationType = "删除", detail = "删除企业管理")
    public ResponseDTO deleteById(String id) throws Exception {
        this.baseCompanyDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企业管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    @Override
    @Transactional
    @Log(operationObj = "企业管理", operationType = "批量删除", detail = "批量删除企业管理")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.baseCompanyDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    
    @Override
    @Transactional
    @Log(operationObj = "企业管理", operationType = "Excel导入", detail = "导入Excel数据")
    public ResponseDTO importXls(List<Object> list,UserProp userProp) throws Exception {
        int i = 1;

        for (Object row : list) {
            BaseCompany o = new BaseCompany();
            CommonBeanUtils.copyProperties(o,row);
                if (CommonUtils.isBlank(o.getCompanyName())) {
                    return new ResponseDTO(0,"行"+i+ ",企业名称不能为空！");
                }

            o.setId(GUIDUtil.getGUID());
            o.setCreateDate(new Date());
            o.setStatus("1");
            o.setCreateUserName(userProp.getName());
            o.setCreateUserId(userProp.getUserId());
            o.setModifyDate(new Date());
                int temp = baseCompanyDao.isExist(o);
                if (temp > 0) {
                    this.baseCompanyDao.updateByPrimaryKey(o);
                }else {
                    this.baseCompanyDao.insert(o);
                }

                i++;
            }
        return new ResponseDTO(1, "成功！");
    }

    }



