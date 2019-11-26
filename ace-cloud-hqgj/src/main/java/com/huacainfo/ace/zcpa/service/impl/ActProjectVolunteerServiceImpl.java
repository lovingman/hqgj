package com.huacainfo.ace.zcpa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActProjectDao;
import com.huacainfo.ace.zcpa.dao.ActProjectVolunteerDao;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteer;
import com.huacainfo.ace.zcpa.service.ActProjectVolunteerService;
import com.huacainfo.ace.zcpa.vo.ActProjectVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目志愿者列表 < 除情暖家庭外 >)
 */
public class ActProjectVolunteerServiceImpl implements ActProjectVolunteerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActProjectVolunteerDao actProjectVolunteerDao;
    @Resource
    private ActProjectDao actProjectDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目志愿者列表 < 除情暖家庭外 > 分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectVolunteerVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    public PageDTO<ActProjectVolunteerVo> page(ActProjectVolunteerQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ActProjectVolunteerVo> rst =new PageDTO<>();
        List<ActProjectVolunteerVo> list= this.actProjectVolunteerDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actProjectVolunteerDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者列表<除情暖家庭外>", operationType = "创建", detail = "创建项目志愿者列表<除情暖家庭外>")
    public ResponseDTO create(String jsons, UserProp userProp) throws Exception {
        JSONObject object = JSON.parseObject(jsons);
        String projectId = object.getString("projectId");
        String targetId = object.getString("targetId");
        List<ActProjectVolunteer> actProjectVolunteer = new ArrayList<ActProjectVolunteer>(
                JSONArray.parseArray(object.getString("actProjectVolunteer"), ActProjectVolunteer.class));

        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(actProjectVolunteer)) {
            return new ResponseDTO(ResultCode.FAIL, "志愿者不能为空！");
        }
        ActProjectVo vo=actProjectDao.selectVoByPrimaryKey(projectId,null);
        if (!CommonUtils.isBlank(vo)) {
            int num =vo.getNeedNum();
            if(num==0){
                return new ResponseDTO(ResultCode.FAIL, "志愿者已满！");
            }
        }

            for (ActProjectVolunteer o : actProjectVolunteer) {
                if (CommonUtils.isBlank(o.getAreaCode())) {
                    return new ResponseDTO(ResultCode.FAIL, "所属辖区不能为空");
                }
                if (CommonUtils.isBlank(o.getVolunteerId())) {
                    return new ResponseDTO(ResultCode.FAIL, "志愿者Id不能为空");
                }
                o.setId(GUIDUtil.getGUID());
                o.setProjectId(projectId);
                if (!CommonUtils.isBlank(targetId)) {
                   o.setTargetId(targetId);
                }
                int temp = this.actProjectVolunteerDao.isExist(o);
                if (temp > 0) {
                    return new ResponseDTO(ResultCode.FAIL, "志愿者已经存在！");
                }

                o.setCreateDate(new Date());
                o.setStatus("1");
                actProjectVolunteerDao.insert(o);
            }

        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者列表<除情暖家庭外>", operationType = "变更", detail = "变更项目志愿者列表<除情暖家庭外>")
    public ResponseDTO update(ActProjectVolunteer o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new ResponseDTO(ResultCode.FAIL, "所属辖区不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getVolunteerId())) {
            return new ResponseDTO(ResultCode.FAIL, "志愿者主键不能为空！");
        }

        this.actProjectVolunteerDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    public ResponseDTO<ActProjectVolunteerVo> getById(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        ResponseDTO<ActProjectVolunteerVo> rst = new ResponseDTO<>();
        rst.setData(this.actProjectVolunteerDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Log(operationObj = "项目志愿者列表<除情暖家庭外>", operationType = "删除", detail = "删除项目志愿者列表<除情暖家庭外>")
    public ResponseDTO deleteById(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        this.actProjectVolunteerDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者列表<除情暖家庭外>", operationType = "批量删除", detail = "批量删除项目志愿者列表<除情暖家庭外>")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        this.actProjectVolunteerDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 项目关联的志愿者
     * @param projectId
     * @return
     * @throws Exception
     */
    @Override
    public List<ActProjectVolunteerVo> selectByProjectId(String projectId,String targetId) throws Exception {
        return actProjectVolunteerDao.selectByProjectId(projectId,targetId);
    }


}
