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
import com.huacainfo.ace.hqgj.dao.CompanyMaterialDao;
import com.huacainfo.ace.hqgj.model.CompanyMaterial;
import com.huacainfo.ace.hqgj.service.CompanyMaterialService;
import com.huacainfo.ace.hqgj.vo.CompanyMaterialVo;
import com.huacainfo.ace.hqgj.vo.CompanyMaterialQVo;
import javax.annotation.Resource;
@Service
/**
* @author: 豆文国
* @version: 2020-02-21
* @Description:  TODO(企业物资表)
*/
public class CompanyMaterialServiceImpl implements CompanyMaterialService {
Logger logger = LoggerFactory.getLogger(this.getClass());
@Resource
private CompanyMaterialDao companyMaterialDao;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(企业物资表分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<CompanyMaterialVo>
    * @throws
    * @author: 豆文国
    * @version: 2020-02-21
    */
    @Override
    public PageDTO
    <CompanyMaterialVo> page(CompanyMaterialQVo condition, int start, int limit, String orderBy) throws Exception {
            PageDTO<CompanyMaterialVo> rst = new PageDTO<>();
                List<CompanyMaterialVo> list = this.companyMaterialDao.findList(condition, start, limit, orderBy);
                    rst.setRows(list);
                    if (start <= 1) {
                    int allRows = this.companyMaterialDao.findCount(condition);
                    rst.setTotal(allRows);
                    }
                    return rst;

            }

            /**
            *
            * @Title:create
            * @Description: TODO(创建企业物资表)
            * @param: @param o
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2020-02-21
            */
            @Override
            @Transactional
            @Log(operationObj = "企业物资表", operationType = "创建", detail = "创建企业物资表")
            public ResponseDTO create(CompanyMaterial o, UserProp userProp) throws Exception {
            
            int temp = this.companyMaterialDao.isExist(o);
            if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "企业物资表名称重复！");
            }
            o.setId(GUIDUtil.getGUID());
            o.setStatus("1");
            this.companyMaterialDao.insert(o);
            return new ResponseDTO(ResultCode.SUCCESS, "成功！");
            }

            /**
            *
            * @Title:update
            * @Description: TODO(更新企业物资表)
            * @param: @param o
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2020-02-21
            */
            @Override
            @Transactional
            @Log(operationObj = "企业物资表", operationType = "变更", detail = "变更企业物资表")
            public ResponseDTO update(CompanyMaterial o, UserProp userProp) throws Exception {

            this.companyMaterialDao.updateByPrimaryKey(o);
            return new ResponseDTO(ResultCode.SUCCESS, "成功！");
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取企业物资表)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<CompanyMaterial>
            * @throws
            * @author: 豆文国
            * @version: 2020-02-21
            */
            @Override
            @Transactional
            public ResponseDTO<CompanyMaterialVo> getById(String id) throws Exception {
                ResponseDTO<CompanyMaterialVo> rst = new ResponseDTO<>();
                    rst.setData(this.companyMaterialDao.selectVoByPrimaryKey(id));
                    return rst;
                    }

                    /**
                    *
                    * @Title:deleteById
                    * @Description: TODO(删除企业物资表)
                    * @param: @param id
                    * @param: @throws Exception
                    * @return: ResponseDTO
                    * @throws
                    * @author: 豆文国
                    * @version: 2020-02-21
                    */
                    @Override
                    @Log(operationObj = "企业物资表", operationType = "删除", detail = "删除企业物资表")
                    public ResponseDTO deleteById(String id) throws Exception {
                    this.companyMaterialDao.deleteByPrimaryKey(id);
                    return new ResponseDTO(ResultCode.SUCCESS, "成功！");
                    }


                    /**
                    *
                    * @Title:deleteByIds
                    * @Description: TODO(批量删除企业物资表)
                    * @param: @param ids
                    * @param: @throws Exception
                    * @return: ResponseDTO
                    * @throws
                    * @author: 豆文国
                    * @version: 2020-02-21
                    */
                    @Override
                    @Transactional
                    @Log(operationObj = "企业物资表", operationType = "批量删除", detail = "批量删除企业物资表")
                    public ResponseDTO deleteByIds(String[] ids) throws Exception {
                    this.companyMaterialDao.deleteByIds(ids);
                    return new ResponseDTO(ResultCode.SUCCESS, "成功！");
                    }



                    }
