package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.ResourcesDao;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.vo.ResourcesQVo;
import com.huacainfo.ace.portal.vo.ResourcesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(资源文件管理)
 */
public class ResourcesServiceImpl implements ResourcesService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ResourcesDao resourcesDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(资源文件管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<ResourcesVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    public PageDTO<ResourcesVo> page(ResourcesQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ResourcesVo> rst = new PageDTO<>();
        List<ResourcesVo> list = this.resourcesDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.resourcesDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建资源文件管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "资源文件管理", operationType = "创建", detail = "创建资源文件管理")
    public ResponseDTO create(Resources o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getParentResourcesId())) {
            return new ResponseDTO(ResultCode.FAIL, "父编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getResourcesName())) {
            return new ResponseDTO(ResultCode.FAIL, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getResourcesType())) {
            return new ResponseDTO(ResultCode.FAIL, "类型不能为空！");
        }
        o.setSyid(userProp.getActiveSyId());
        o.setCreateTime(new Date());
        o.setStauts("1");
        o.setCreateUserId(userProp.getUserId());
        this.resourcesDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新资源文件管理)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "资源文件管理", operationType = "变更", detail = "变更资源文件管理")
    public ResponseDTO update(Resources o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getResourcesId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getResourcesId())) {
            return new ResponseDTO(ResultCode.FAIL, "编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getParentResourcesId())) {
            return new ResponseDTO(ResultCode.FAIL, "父编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getResourcesName())) {
            return new ResponseDTO(ResultCode.FAIL, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getResourcesType())) {
            return new ResponseDTO(ResultCode.FAIL, "类型不能为空！");
        }
        o.setSyid(userProp.getActiveSyId());
        this.resourcesDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取资源文件管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Resources>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    public ResponseDTO<ResourcesVo> getById(String id) throws Exception {
        ResponseDTO<ResourcesVo> rst = new ResponseDTO<>();
        rst.setData(this.resourcesDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除资源文件管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Log(operationObj = "资源文件管理", operationType = "删除", detail = "删除资源文件管理")
    public ResponseDTO deleteById(String id) throws Exception {
        this.resourcesDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除资源文件管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "资源文件管理", operationType = "批量删除", detail = "批量删除资源文件管理")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.resourcesDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    @Override
    public List<Tree> getResTreeList(String topId) {
        List<Map<String, Object>> resources = resourcesDao.selectResourcesTreeList();
        CommonTreeUtils commonTreeUtils = new CommonTreeUtils(resources);
        return commonTreeUtils.getTreeList(topId);
    }

    /**
     * execel导入接口
     *
     * @param list     文件流数组
     * @param userProp 当前登录用户
     * @return ResponseDTO
     * @throws Exception
     */
    @Log(operationObj = "资源文件管理", operationType = "批量导入", detail = "批量导入资源文件管理")
    @Transactional
    @Override
    public ResponseDTO importXls(List<Object> list, UserProp userProp) throws Exception{
        int i = 1;
        for (Object row : list) {
            Resources o = new Resources();
            CommonBeanUtils.copyProperties(o,row);
            o.setCreateTime(new Date());
            o.setCreateUserId(userProp.getUserId());
            o.setStauts("1");
            if (CommonUtils.isBlank(o.getSyid())) {
                o.setSyid(userProp.getActiveSyId());
            }
            this.logger.info(o.toString());
            if (CommonUtils.isBlank(o.getResourcesId())) {
                return new ResponseDTO(ResultCode.FAIL, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getParentResourcesId())) {
                return new ResponseDTO(ResultCode.FAIL, "行" + i + ",父编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getResourcesName())) {
                return new ResponseDTO(ResultCode.FAIL, "行" + i + ",名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getResourcesType())) {
                return new ResponseDTO(ResultCode.FAIL, "行" + i + ",类型不能为空！");
            }
            int t = resourcesDao.isExist(o);
            if (t > 0) {
                resourcesDao.updateByPrimaryKey(o);

            } else {
                resourcesDao.insertNoKey(o);
            }
            i++;
        }

        return new ResponseDTO(ResultCode.SUCCESS, "资源导入完成！");
    }

    /**
     * 资源排序接口
     *
     * @param list     list
     * @param userProp 当前登录用户
     * @return ResponseDTO
     * @throws Exception
     */
    @Log(operationObj = "资源文件管理", operationType = "资源排序", detail = "更新资源顺序")
    @Transactional
    @Override
    public ResponseDTO updateSequence(List<Map<String, Object>> list, UserProp userProp) {

        for (Map<String, Object> o : list) {
            resourcesDao.updateSequence((int) o.get("sequence"), (String) o.get("resourcesId"));
        }

        return new ResponseDTO(ResultCode.SUCCESS, "完成！");
    }

    /**
     * 鉴权加载系统的资源
     */
    @Override
    public List<Map<String, String>> loadResourceDefine() {
        return resourcesDao.loadResourceDefine();
    }


    @Override
    public ResponseDTO<List<Map<String, Object>>> getListByPid(String pid) throws Exception {
        ResponseDTO<List<Map<String, Object>>> rst= new ResponseDTO<>();
        rst.setData(this.resourcesDao.getListByPid(pid));
        return rst;
    }
}
