package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.DictDao;
import com.huacainfo.ace.portal.model.Corp;
import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.service.DictService;
import com.huacainfo.ace.portal.vo.DictQVo;
import com.huacainfo.ace.portal.vo.DictVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(数据字典)
 */
public class DictServiceImpl implements DictService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DictDao dictDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(数据字典分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<DictVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    public PageDTO<DictVo> page(DictQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<DictVo> rst = new PageDTO<>();
        List<DictVo> list = this.dictDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.dictDao.findCount(condition);
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
    public ResponseDTO create(Dict o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPcode())) {
            o.setPcode("0");
        }
        o.setSpell(CommonUtils.getPinYinHeadChar(o.getName()));
        int temp = dictDao.isExitByNameAndCategoryId(o.getName(), o.getCategoryId());
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "数据字典名称重复！");
        }
        o.setCreateTime(new Date());
        this.dictDao.insert(o);
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
    public ResponseDTO update(Dict o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getCode())) {

            return new ResponseDTO(ResultCode.FAIL, "编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategoryId())) {

            return new ResponseDTO(ResultCode.FAIL, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {

            return new ResponseDTO(ResultCode.FAIL, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPcode())) {
            o.setPcode("0");
        }
        o.setSpell(CommonUtils.getPinYinHeadChar(o.getName()));
        this.dictDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取数据字典)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Dict>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    public ResponseDTO<DictVo> getById(String id) throws Exception {
        ResponseDTO<DictVo> rst = new ResponseDTO<>();
        rst.setData(this.dictDao.selectVoByPrimaryKey(id));
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
        this.dictDao.deleteByPrimaryKey(id);
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
        this.dictDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 根据字典分类ID获取数据字典对象，可以一次获取多个
     *
     * @param categoryIds 多个CategoryId
     * @return ResponseDTO<Map < String, List < DictVo>>>
     */
    @Override
    public ResponseDTO<Map<String, List<Dict>>> getByCategoryIds(String[] categoryIds) {
        //读取数据库
        List<Dict> dataList = dictDao.getByCategoryIds(categoryIds);
        //装载集合对象
        Map<String, List<Dict>> rst = new HashMap<>();
        String key;
        List<Dict> value;
        for (Dict dict : dataList) {
            key = dict.getCategoryId();
            if (rst.containsKey(key)) {
                value = rst.get(key);
            } else {
                value = new ArrayList<>();
            }

            value.add(dict);
            rst.put(key, value);
        }

        return new ResponseDTO<>(ResponseDTO.SUCCESS, "获取成功", rst);
    }

    /**
     * 根据字典分类ID获取数据字典对象，可以一次获取多个
     *
     * @param categoryIds 多个CategoryId
     * @return ResponseDTO<Map < String, Map < String, String>>>
     */
    @Override
    public ResponseDTO<Map<String, Map<String, String>>> getDictKeyMap(String[] categoryIds) {
        //读取数据库
        List<Dict> dataList = dictDao.getByCategoryIds(categoryIds);
        //装载集合对象
        Map<String, Map<String, String>> rst = new HashMap<>();
        String key;
        Map<String, String> value;
        for (Dict dict : dataList) {
            key = dict.getCategoryId();
            if (rst.containsKey(key)) {
                value = rst.get(key);
            } else {
                value = new HashMap<>();
            }

            value.put(dict.getCode(), dict.getName());
            rst.put(key, value);
        }

        return new ResponseDTO<>(ResponseDTO.SUCCESS, "获取成功", rst);
    }


    /**
     * 获取树形字典
     * @param topId
     * @param categoryId
     * @return
     */
    @Override
    public ListDTO<Tree> getResTreeList(String topId,String categoryId) {
        List<Map<String, Object>> resources = dictDao.selectDictTreeList(categoryId);
        CommonTreeUtils tool = new CommonTreeUtils(resources);
        ListDTO<Tree> rst = new ListDTO();
        rst.setData(tool.getTreeList(topId));
        return rst;

    }

    @Override
    @Transactional
    @Log(operationObj = "单位管理", operationType = "Excel导入", detail = "导入Excel数据")
    public ResponseDTO importXls(List<Object> list,String categoryId) throws Exception {
        int i = 1;


        for (Object row : list) {
            Dict o = new Dict();
            CommonBeanUtils.copyProperties(o,row);
            if(CommonUtils.isNotEmpty(o.getName())){
                this.logger.info(o.toString());
                if(CommonUtils.isNotEmpty(categoryId)){
                    o.setCategoryId(categoryId);
                }
                if (CommonUtils.isBlank(o.getName())) {
                    return new ResponseDTO(0,"行"+i+ ",名称不能为空！");
                }
                if (CommonUtils.isBlank(o.getCode())) {
                    return new ResponseDTO(0, "行"+i+ ",编码不能为空！");
                }
                if (CommonUtils.isBlank(o.getCategoryId())) {
                    return new ResponseDTO(0, "行"+i+ ",类别不能为空！");
                }
                if (CommonUtils.isBlank(o.getPcode())) {
                    o.setPcode("0");
                }
                o.setSpell(CommonUtils.getPinYinHeadChar(o.getName()));
                o.setCreateTime(new Date());
                int temp = dictDao.isExitByNameAndCategoryId(o.getName(), o.getCategoryId());
                if (temp > 0) {
                   this.dictDao.updateByCategoryAndCode(o);
                }else {
                    this.dictDao.insert(o);
                }

                i++;
            }
        }
        return new ResponseDTO(1, "成功！");
    }
}
