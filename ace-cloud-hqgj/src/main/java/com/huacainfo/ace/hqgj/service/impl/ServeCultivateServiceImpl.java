package com.huacainfo.ace.hqgj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.lang.reflect.Array;
import java.security.Guard;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.BasicAnnexDao;
import com.huacainfo.ace.hqgj.dao.ServeCultivateScheduleDao;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.model.ServeCultivateSchedule;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.ServeCultivateDao;
import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.service.ServeCultivateService;
import com.huacainfo.ace.hqgj.vo.ServeCultivateVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升基础表)
 */
public class ServeCultivateServiceImpl implements ServeCultivateService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeCultivateDao serveCultivateDao;
    @Resource
    private ServeCultivateScheduleDao serveCultivateScheduleDao;
    @Resource
    private BasicAnnexDao basicAnnexDao;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(培训提升基础表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO
            <ServeCultivateVo> page(ServeCultivateQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeCultivateVo> rst = new PageDTO<>();
        List<ServeCultivateVo> list = this.serveCultivateDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveCultivateDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建培训提升基础表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升基础表", operationType = "创建", detail = "创建培训提升基础表")
    public ResponseDTO create(String jsons, UserProp userProp) throws Exception {
        JSONObject jsonObj = JSON.parseObject(jsons);
        if(!jsonObj.containsKey("serveCultivate")||!jsonObj.containsKey("serveCultivateSchedule")) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        ServeCultivate s = JSON.parseObject(jsonObj.getString("serveCultivate"), ServeCultivate.class);
        List<ServeCultivateScheduleVo> serveCultivateSchedule = new ArrayList<ServeCultivateScheduleVo>(
                JSONArray.parseArray(jsonObj.getString("serveCultivateSchedule"), ServeCultivateScheduleVo.class));
        String  cultivateId= GUIDUtil.getGUID();
        s.setId(cultivateId);
        int temp = this.serveCultivateDao.isExist(s);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "培训提升基础表名称重复！");
        }
        s.setStatus("0");//未审核
        s.setCreateDate(new Date());
        s.setCreateUserName(userProp.getName());
        s.setCreateUserId(userProp.getUserId());
        s.setModifyDate(new Date());
        this.serveCultivateDao.insert(s);

        if (!CommonUtils.isBlank(serveCultivateSchedule)) {
            for (ServeCultivateSchedule o : serveCultivateSchedule) {
                String cultivateScheduleId = GUIDUtil.getGUID();
                o.setId(cultivateScheduleId);
                o.setServeCultivateId(cultivateId);
                o.setCreateDate(new Date());
                o.setStatus("1");
                o.setCreateUserName(userProp.getName());
                o.setCreateUserId(userProp.getUserId());
                o.setModifyDate(new Date());
                this.serveCultivateScheduleDao.insert(o);
                //课程附件
                if (!CommonUtils.isBlank(o.getBasicAnnexes())) {
                    List<BasicAnnex> fileURL = o.getBasicAnnexes();
                    for (BasicAnnex a : fileURL) {
                        a.setId(GUIDUtil.getGUID());
                        a.setRelationId(cultivateScheduleId);
                        a.setFileURL(a.getFileURL());
                        a.setType("1");
                        a.setRemark("培训提升日程表附件");
                        a.setCreateDate(new Date());
                        a.setStatus("1");
                        basicAnnexDao.insert(a);
                    }
                }
            }
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新培训提升基础表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升基础表", operationType = "变更", detail = "变更培训提升基础表")
    public ResponseDTO update(String jsons, UserProp userProp) throws Exception {
        JSONObject jsonObj = JSON.parseObject(jsons);
        if(!jsonObj.containsKey("serveCultivate")|| !jsonObj.containsKey("serveCultivateSchedule")) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        ServeCultivate s = JSON.parseObject(jsonObj.getString("serveCultivate"), ServeCultivate.class);
        List<ServeCultivateSchedule> serveCultivateSchedule = new ArrayList<ServeCultivateSchedule>(
                JSONArray.parseArray(jsonObj.getString("serveCultivateSchedule"), ServeCultivateSchedule.class));

        if(CommonUtils.isBlank(s.getId())){
            return new ResponseDTO(ResultCode.FAIL, "培训提升id不能为空！");
        }
        s.setModifyDate(new Date());
        s.setModifyUserName(userProp.getName());
        s.setModifyUserId(userProp.getUserId());
        this.serveCultivateDao.updateByPrimaryKey(s);
       //删除日程后新增
        List<String> list =serveCultivateScheduleDao.selectScheduleId(s.getId());
        String[] sb =new String[list.size()];
        serveCultivateScheduleDao.deleteByCultivateIds(s.getId().split(","));
        basicAnnexDao.deleteByRelationIds(list.toArray(sb));

        if (!CommonUtils.isBlank(serveCultivateSchedule)) {
            for (ServeCultivateSchedule o : serveCultivateSchedule) {
                String cultivateScheduleId = GUIDUtil.getGUID();
                o.setId(cultivateScheduleId);
                o.setServeCultivateId(s.getId());
                o.setCreateDate(new Date());
                o.setStatus("1");
                o.setCreateUserName(userProp.getName());
                o.setCreateUserId(userProp.getUserId());
                o.setModifyDate(new Date());
                 this.serveCultivateScheduleDao.insert(o);
                //课程附件
                if (!CommonUtils.isBlank(o.getBasicAnnexes())) {
                    List<BasicAnnex> fileURL = o.getBasicAnnexes();
                    for (BasicAnnex a : fileURL) {
                        a.setId(GUIDUtil.getGUID());
                        a.setRelationId(cultivateScheduleId);
                        a.setFileURL(a.getFileURL());
                        a.setType("1");
                        a.setRemark("培训提升日程表附件");
                        a.setCreateDate(new Date());
                        a.setStatus("1");
                        basicAnnexDao.insert(a);
                    }
                }
            }
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升基础表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivate>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<ServeCultivateVo> getById(String id) throws Exception {
        ResponseDTO<ServeCultivateVo> rst = new ResponseDTO<>();
        rst.setData(this.serveCultivateDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除培训提升基础表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "培训提升基础表", operationType = "删除", detail = "删除培训提升基础表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveCultivateDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除培训提升基础表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "培训提升基础表", operationType = "批量删除", detail = "批量删除培训提升基础表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveCultivateDao.deleteByIds(ids);
        serveCultivateScheduleDao.deleteByCultivateIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 更新封面图片
     * @param id 提升基础表id
     * @param coverUrl  图片url
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO updateCoverUrl(String id, String coverUrl){
        int i = serveCultivateDao.updateCoverUrl(id, coverUrl);
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "数据更新失败");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "数据更新成功", coverUrl);
    }


    /**
     * 修改状态  0-待审核  1-进行中 2-未通过 3-已结束',
     * @param id
     * @param status
     * @return
     */
    @Override
    public ResponseDTO updateStatus(String id, String status) {
       int i=  serveCultivateDao.updateStatus(id,status);
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "更新失败");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "更新成功", status);
    }


}
