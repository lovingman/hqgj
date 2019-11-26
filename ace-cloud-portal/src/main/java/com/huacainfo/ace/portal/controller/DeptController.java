package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Dept;
import com.huacainfo.ace.portal.service.DeptService;
import com.huacainfo.ace.portal.vo.DeptQVo;
import com.huacainfo.ace.portal.vo.DeptVo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dept")
/**
 * @author: 陈晓克
 * @version: 2019-05-16
 * @Description: TODO(科室)
 */
@Api(value = "/dept", tags = "单位信息接口")
public class DeptController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DeptService deptService;

    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO<DeptVo>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<DeptVo> page(DeptQVo condition, PageParam page) throws Exception {
        if (StringUtils.isEmpty(condition.getCorpId())) {
            condition.setCorpId(this.getCurUserProp().getCorpId());
        }
        PageDTO<DeptVo> rst = this.deptService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertDept
     * @Description: TODO(创建科室)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Dept obj = JSON.parseObject(jsons, Dept.class);

        return this.deptService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateDept
     * @Description: TODO(更新科室)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Dept obj = JSON.parseObject(jsons, Dept.class);
        return this.deptService.update(obj, this.getCurUserProp());
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
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<DeptVo> getById(String id) throws Exception {
        return this.deptService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteDeptByDeptId
     * @Description: TODO(删除科室)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @GetMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.deptService.deleteById(id);
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
    @GetMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.deptService.deleteByIds(ids.split(","));
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
    @GetMapping(value = "/getTree", produces = "application/json;charset=UTF-8")
    public ListDTO<Tree> getTree(String corpId) throws Exception {
        return this.deptService.getTree(corpId);
    }

    @GetMapping(value = "/getList", produces = "application/json;charset=UTF-8")
    public ListDTO<Map<String, Object>> getList(String name) throws Exception {
        Map<String, Object> p = new HashMap<>();
        p.put("name", name);
        p.put("corpId", this.getCurUserProp().getCorpId());
        return this.deptService.getList(p);
    }

}
