package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.DeptDao;
import com.huacainfo.ace.portal.model.Dept;
import com.huacainfo.ace.portal.service.DeptService;
import com.huacainfo.ace.portal.vo.DeptQVo;
import com.huacainfo.ace.portal.vo.DeptVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
/**
 * @author: 陈晓克
 * @version: 2019-05-16
 * @Description: TODO(科室)
 */
public class DeptServiceImpl implements DeptService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DeptDao deptDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(科室分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<DeptVo>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    public PageDTO<DeptVo> page(DeptQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<DeptVo> rst = new PageDTO<>();
        List<DeptVo> list = this.deptDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.deptDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建科室)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Transactional
    @Log(operationObj = "科室", operationType = "创建", detail = "创建科室")
    public ResponseDTO create(Dept o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getCorpId())) {
            o.setCorpId(userProp.getCorpId());
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "科室名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPid())) {
            o.setPid("0");
        }
        int temp = this.deptDao.isExit(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "科室名称重复！");
        }
        o.setId(GUIDUtil.getGUID());

        this.deptDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新科室)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Transactional
    @Log(operationObj = "科室", operationType = "变更", detail = "变更科室")
    public ResponseDTO update(Dept o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "科室名称不能为空！");
        }
        this.deptDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取科室)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Dept>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Transactional
    public ResponseDTO<DeptVo> getById(String id) throws Exception {
        ResponseDTO<DeptVo> rst = new ResponseDTO<>();
        rst.setData(this.deptDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除科室)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Log(operationObj = "科室", operationType = "删除", detail = "删除科室")
    public ResponseDTO deleteById(String id) throws Exception {
        this.deptDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除科室)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @Override
    @Transactional
    @Log(operationObj = "科室", operationType = "批量删除", detail = "批量删除科室")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.deptDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }
    /**
     * @throws
     * @Title:getTree
     * @Description: TODO(科室树)
     * @param: @param corpId
     * @param: @throws Exception
     * @return: ListDTO<Tree>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
@Override
   public ListDTO<Tree> getTree(String corpId) throws Exception{
       List<Map<String, Object>> list = this.deptDao.getTree(corpId);
       CommonTreeUtils tool = new CommonTreeUtils(list);
       ListDTO<Tree> rst = new ListDTO();
       rst.setData(tool.getTreeList("0"));
       return rst;
   }

    @Override
    public ListDTO<Map<String,Object>> getList(Map<String,Object> p) throws Exception{
        this.logger.info("{}",p);
        ListDTO<Map<String,Object>> rst = new ListDTO<>();
        rst.setData(this.deptDao.getList(p));
        return rst;
    }


}
