package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.BaseCompanyDao;
import com.huacainfo.ace.hqgj.dao.CompanyMaterialDao;
import com.huacainfo.ace.hqgj.model.CompanyMaterial;
import com.huacainfo.ace.hqgj.vo.CompanyAppealExlVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.CompanyAppealDao;
import com.huacainfo.ace.hqgj.model.CompanyAppeal;
import com.huacainfo.ace.hqgj.service.CompanyAppealService;
import com.huacainfo.ace.hqgj.vo.CompanyAppealVo;
import com.huacainfo.ace.hqgj.vo.CompanyAppealQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 豆文国
 * @version: 2020-02-21
 * @Description: TODO(企业诉求表)
 */
public class CompanyAppealServiceImpl implements CompanyAppealService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CompanyAppealDao companyAppealDao;
    @Resource
    private CompanyMaterialDao companyMaterialDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业诉求表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <CompanyAppealVo>
     * @author: 豆文国
     * @version: 2020-02-21
     */
    @Override
    public PageDTO
            <CompanyAppealVo> page(CompanyAppealQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<CompanyAppealVo> rst = new PageDTO<>();
        if (!CommonUtils.isBlank(condition.getEndTime()) && !CommonUtils.isBlank(condition.getStartTime())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTime = sdf.format(condition.getEndTime()) + " 23:59:59 ";
            String startTime = sdf.format(condition.getStartTime()) + " 00:00:00 ";
            condition.setEndTime(sdf2.parse(endTime));
            condition.setStartTime(sdf2.parse(startTime));
        }
        List<CompanyAppealVo> list = this.companyAppealDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.companyAppealDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    @Override
    public PageDTO
            <CompanyAppealVo> userpage(CompanyAppealQVo condition, UserProp userProp, int start, int limit, String orderBy) throws Exception {
        PageDTO<CompanyAppealVo> rst = new PageDTO<>();
        condition.setCreateUserId(userProp.getUserId());
        List<CompanyAppealVo> list = this.companyAppealDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.companyAppealDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    @Override
    public PageDTO
            <CompanyAppealExlVo> exportPage(CompanyAppealQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<CompanyAppealExlVo> rst = new PageDTO<>();
        List<CompanyAppealExlVo> list = this.companyAppealDao.exportList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.companyAppealDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建企业诉求表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2020-02-21
     */
    @Override
    @Transactional
    @Log(operationObj = "企业诉求表", operationType = "创建", detail = "创建企业诉求表")
    public ResponseDTO create(CompanyAppeal o, UserProp userProp) throws Exception {
        String id = GUIDUtil.getGUID();
        int temp = this.companyAppealDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "企业诉求表名称重复！");
        }
        o.setId(id);
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.companyAppealDao.insert(o);

//            if (o.getType().equals("1")) {
//                    CompanyMaterial companyMaterial = new CompanyMaterial();
//                    companyMaterial.setId(GUIDUtil.getGUID());
//                    companyMaterial.setAppealId(id);
//                    companyMaterial.setCategory(o.getCategory());
//                    companyMaterial.setNumber(o.getNumber());
//                    companyMaterial.setStatus("1");
//                    companyMaterialDao.insert(companyMaterial);
//            }
        if (o.getCompanyMaterial().size() > 0 && o.getType().equals("1")) {
            List<CompanyMaterial> fileURL = o.getCompanyMaterial();
            for (CompanyMaterial a : fileURL) {
                a.setId(GUIDUtil.getGUID());
                a.setAppealId(id);
                a.setCategory(a.getCategory());
                a.setNumber(a.getNumber());
                a.setStatus("1");
                this.companyMaterialDao.insert(a);
            }
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新企业诉求表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2020-02-21
     */
    @Override
    @Transactional
    @Log(operationObj = "企业诉求表", operationType = "变更", detail = "变更企业诉求表")
    public ResponseDTO update(CompanyAppeal o, UserProp userProp) throws Exception {

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.companyAppealDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企业诉求表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<CompanyAppeal>
     * @author: 豆文国
     * @version: 2020-02-21
     */
    @Override
    @Transactional
    public ResponseDTO<CompanyAppealVo> getById(String id) throws Exception {
        ResponseDTO<CompanyAppealVo> rst = new ResponseDTO<>();
        rst.setData(this.companyAppealDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除企业诉求表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2020-02-21
     */
    @Override
    @Log(operationObj = "企业诉求表", operationType = "删除", detail = "删除企业诉求表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.companyAppealDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企业诉求表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2020-02-21
     */
    @Override
    @Transactional
    @Log(operationObj = "企业诉求表", operationType = "批量删除", detail = "批量删除企业诉求表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.companyAppealDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    @Override
    @Transactional
    @Log(operationObj = "企业诉求表", operationType = "变更状态", detail = "变更企业诉求表状态")
    public ResponseDTO updateProjectState(CompanyAppeal companyAppeal) throws Exception {

        this.companyAppealDao.updateProjectState(companyAppeal);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

}
