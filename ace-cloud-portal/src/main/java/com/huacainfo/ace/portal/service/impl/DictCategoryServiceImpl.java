package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.DictCategoryDao;
import com.huacainfo.ace.portal.model.DictCategory;
import com.huacainfo.ace.portal.service.DictCategoryService;
import com.huacainfo.ace.portal.vo.DictCategoryQVo;
import com.huacainfo.ace.portal.vo.DictCategoryVo;
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
 * @version: 2019-05-17
 * @Description: TODO(数据字典)
 */
public class DictCategoryServiceImpl implements DictCategoryService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DictCategoryDao dictCategoryDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(数据字典分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<DictCategoryVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    public PageDTO<DictCategoryVo> page(DictCategoryQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<DictCategoryVo> rst = new PageDTO<>();
        List<DictCategoryVo> list = this.dictCategoryDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.dictCategoryDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建数据字典)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "数据字典", operationType = "创建", detail = "创建数据字典")
    public ResponseDTO create(DictCategory o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        o.setSyid(userProp.getActiveSyId());
        o.setCreateTime(new Date());
        int temp = this.dictCategoryDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "数据字典名称重复！");
        }
        this.dictCategoryDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新数据字典)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "数据字典", operationType = "变更", detail = "变更数据字典")
    public ResponseDTO update(DictCategory o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getCategoryId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }

        this.dictCategoryDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取数据字典)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<DictCategory>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    public ResponseDTO<DictCategoryVo> getById(String id) throws Exception {
        ResponseDTO<DictCategoryVo> rst = new ResponseDTO<>();
        rst.setData(this.dictCategoryDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除数据字典)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Log(operationObj = "数据字典", operationType = "删除", detail = "删除数据字典")
    public ResponseDTO deleteById(String id) throws Exception {
        this.dictCategoryDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除数据字典)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "数据字典", operationType = "批量删除", detail = "批量删除数据字典")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.dictCategoryDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @param userProp
     * @throws
     * @Title:findDictCategoryListAll
     * @Description: TODO(获取所有字典类型列表)
     * @param: @return
     * @param: @throws Exception
     * @return: List<DictCategory>
     */
    @Override
    public List<DictCategory> findDictCategoryListAll(UserProp userProp) {
        return this.dictCategoryDao.findListAll(userProp.getActiveSyId());
    }


}
