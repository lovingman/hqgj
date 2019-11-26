package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.CorpDao;
import com.huacainfo.ace.portal.model.Corp;
import com.huacainfo.ace.portal.service.CorpService;
import com.huacainfo.ace.portal.vo.CorpQVo;
import com.huacainfo.ace.portal.vo.CorpVo;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CacheConfig(cacheNames = {"corp"})
@Service
/**
 * @author: 陈晓克
 * @version: 2019-05-16
 * @Description: TODO(企事业单位)
 */
public class CorpServiceImpl implements CorpService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CorpDao corpDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企事业单位分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<CorpVo>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    public PageDTO<CorpVo> page(CorpQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<CorpVo> rst = new PageDTO<>();
        List<CorpVo> list = this.corpDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.corpDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建企事业单位)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Transactional
    @Log(operationObj = "企事业单位", operationType = "创建", detail = "创建企事业单位")
    public ResponseDTO create(Corp o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getPid())) {
            return new ResponseDTO(ResultCode.FAIL, "上级编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "企事业单位名称不能为空！");
        }
        int temp = this.corpDao.isExit(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "企事业单位名称重复！");
        }
        if (CommonUtils.isBlank(o.getId())) {
            o.setId(GUIDUtil.getGUID());
        }
        this.corpDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新企事业单位)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Transactional
    @Log(operationObj = "企事业单位", operationType = "变更", detail = "变更企事业单位")
    public ResponseDTO update(Corp o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getPid())) {
            return new ResponseDTO(ResultCode.FAIL, "上级编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "企事业单位名称不能为空！");
        }
        this.corpDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企事业单位)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Corp>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Cacheable
    @Override
    @Transactional
    public ResponseDTO<CorpVo> getById(String id) throws Exception {
        ResponseDTO<CorpVo> rst = new ResponseDTO<>();
        rst.setData(this.corpDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除企事业单位)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Log(operationObj = "企事业单位", operationType = "删除", detail = "删除企事业单位")
    public ResponseDTO deleteById(String id) throws Exception {
        this.corpDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }







    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企事业单位)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Transactional
    @Log(operationObj = "企事业单位", operationType = "批量删除", detail = "批量删除企事业单位")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.corpDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(名称、行政区划查询企事业单位，主要用于可搜索下拉框)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListDTO<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-05-17
     */
    @Override
    public ListDTO<Map<String,Object>> getList(Map<String,Object> p) throws Exception{
        this.logger.info("{}",p);
        ListDTO<Map<String,Object>> rst = new ListDTO<>();
        rst.setData(this.corpDao.getList(p));
        return rst;
    }

    @Override
    @Transactional
    @Log(operationObj = "单位管理", operationType = "Excel导入", detail = "导入Excel数据")
    public ResponseDTO importXls(List<Object> list) throws Exception {
        int i = 1;
        List<Map<String, String>> listArea =this.corpDao.getAreaDict();
        Map<String,String> area=new HashMap<>();
        for(Map<String,String> o:listArea){
            area.put(o.get("name"),o.get("code"));
        }
        List<Map<String, String>> listType =this.corpDao.getTypeDict();
        Map<String,String> type=new HashMap<>();
        for(Map<String,String> o:listType){
            type.put(o.get("name"),o.get("code"));
        }

        List<Map<String, String>> listCorp =this.corpDao.getCorpDict();
        Map<String,String> corp=new HashMap<>();
        for(Map<String,String> o:listCorp){
            corp.put(o.get("name"),o.get("code"));
        }

        for (Object row : list) {
            Corp o = new Corp();
            CommonBeanUtils.copyProperties(o,row);
            if(CommonUtils.isNotEmpty(o.getName())){
                this.logger.info(o.toString());
                if (CommonUtils.isBlank(o.getName())) {
                    return new ResponseDTO(0,"行"+i+ ",名称不能为空！");
                }
                if (CommonUtils.isBlank(o.getAreaCode())) {
                    return new ResponseDTO(0, "行"+i+ ",行政区划不能为空！");
                }
                if(CommonUtils.isBlank(area.get(o.getAreaCode()))){
                    return new ResponseDTO(0, "行"+i+ ",行政区划名称不正确！");
                }
                o.setAreaCode(area.get(o.getAreaCode()));
                if (CommonUtils.isBlank(o.getType())) {
                    return new ResponseDTO(0, "行"+i+ ",类型不能为空！");
                }
                if(CommonUtils.isBlank(type.get(o.getType()))){
                    return new ResponseDTO(0, "行"+i+ ",行政区划名称不正确！");
                }
                o.setType(type.get(o.getType()));

                if (CommonUtils.isBlank(o.getPid())) {
                    o.setPid("0");
                }else{
                    o.setPid(corp.get(o.getPid()));
                }
                if (CommonUtils.isBlank(o.getId())) {
                    o.setId(GUIDUtil.getGUID());
                }
                int t = corpDao.isExit(o);
                if (t > 0) {
                    this.corpDao.updateByPrimaryKey(o);

                } else {
                    this.corpDao.insert(o);
                }
                i++;
            }
        }
        return new ResponseDTO(1, "成功！");
    }


}
