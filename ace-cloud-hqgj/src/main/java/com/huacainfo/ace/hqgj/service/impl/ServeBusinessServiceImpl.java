package com.huacainfo.ace.hqgj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.*;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;
import com.huacainfo.ace.hqgj.model.ServeBusinessDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.model.ServeBusiness;
import com.huacainfo.ace.hqgj.service.ServeBusinessService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务包)
 */
public class ServeBusinessServiceImpl implements ServeBusinessService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeBusinessDetailDao serveBusinessDetailDao;
    @Resource
    private ServeBusinessDao serveBusinessDao;
    @Resource
    private BasicAnnexDao basicAnnexDao;
    @Resource
    private ServeBusinessAppendDao serveBusinessAppendDao;
    @Resource
    private RegisterDao registerDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(创业服务包分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO
            <ServeBusinessVo> page(ServeBusinessQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeBusinessVo> rst = new PageDTO<>();
        List<ServeBusinessVo> list = this.serveBusinessDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveBusinessDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建创业服务包)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务包", operationType = "创建", detail = "创建创业服务包")
    public ResponseDTO create(String jsons, UserProp userProp) throws Exception {
        JSONObject jsonObj= JSON.parseObject(jsons);
        if(!jsonObj.containsKey("serveBusiness")||!jsonObj.containsKey("serveBusinessDetail")) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        ServeBusiness serveBusiness=JSON.parseObject(jsonObj.getString("serveBusiness"), ServeBusiness.class);
        List<ServeBusinessDetail> serveBusinessDetails=new ArrayList<ServeBusinessDetail>(
                JSONArray.parseArray(jsonObj.getString("serveBusinessDetail"), ServeBusinessDetail.class));
        List<ServeBusinessAppend> appends=new ArrayList<ServeBusinessAppend>(
                JSONArray.parseArray(jsonObj.getString("serveBusinessAppend"), ServeBusinessAppend.class));
        Users user =registerDao.selectUserInfo(userProp.getUserId());
        //基础信息
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
        String businessId=GUIDUtil.getGUID();
        serveBusiness.setId(businessId);
        serveBusiness.setLsNo("CY"+sdf.format(new Date()));
        serveBusiness.setApplyPersonName(userProp.getName());
        serveBusiness.setApplyPersonTel(user.getMobile());
        serveBusiness.setIdCard(user.getIdCard());
        serveBusiness.setCreateDate(new Date());
        serveBusiness.setStatus("0");
        serveBusiness.setCreateUserName(userProp.getName());
        serveBusiness.setCreateUserId(userProp.getUserId());
        serveBusiness.setModifyDate(new Date());
        serveBusiness.setBasicStatus("0");
        this.serveBusinessDao.insert(serveBusiness);

        //人员信息
        for(ServeBusinessDetail o:serveBusinessDetails) {
            String memberId = GUIDUtil.getGUID();
            o.setId(memberId);
            o.setBusinessId(businessId);
            int temp = serveBusinessDetailDao.isExist(o);
            if (temp > 0) {
                return new ResponseDTO(ResultCode.FAIL, "创业服务资料清单人员表名称重复！");
            }
            o.setCreateDate(new Date());
            o.setStatus("1");
            o.setCreateUserName(userProp.getName());
            o.setCreateUserId(userProp.getUserId());
            o.setModifyDate(new Date());
            //人员资料附件
            if (!CommonUtils.isBlank(o.getBasicAnnexes())) {
                List<BasicAnnex> fileURL = o.getBasicAnnexes();
                for (BasicAnnex a : fileURL) {
                    if(CommonUtils.isBlank(a.getFileName())){
                        return new ResponseDTO(ResultCode.FAIL, "文件名称不能为空！");
                    }
                    a.setId(GUIDUtil.getGUID());
                    a.setRelationId(memberId);
                    a.setFileURL(a.getFileURL());
                    //1-培训提升日程安排附件；2-法律服务附件; 3-创业服务资料清单人员附件; 4-创业服务其它附件
                    a.setType("3");
                    a.setRemark("创业服务资料清单附件");
                    a.setCreateDate(new Date());
                    a.setStatus("1");
                    basicAnnexDao.insert(a);
                }
            }
            serveBusinessDetailDao.insert(o);
        }
        //其他选项
        if(appends!=null){
            for(ServeBusinessAppend append:appends){
                append.setId(GUIDUtil.getGUID());
                append.setBusinessId(businessId);
                append.setCreateDate(new Date());
                append.setStatus("1");
                append.setCreateDate(new Date());
                append.setCreateUserName(userProp.getName());
                append.setCreateUserId(userProp.getUserId());
                append.setModifyDate(new Date());
                serveBusinessAppendDao.insert(append);
            }
        }

        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新创业服务包)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务包", operationType = "变更", detail = "变更创业服务包")
    public ResponseDTO update(String jsons, UserProp userProp) throws Exception {
        JSONObject jsonObj= JSON.parseObject(jsons);
        if(!jsonObj.containsKey("serveBusiness")||!jsonObj.containsKey("serveBusinessDetail")) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        ServeBusiness serveBusiness=JSON.parseObject(jsonObj.getString("serveBusiness"), ServeBusiness.class);
        List<ServeBusinessDetail> serveBusinessDetails=new ArrayList<ServeBusinessDetail>(
                JSONArray.parseArray(jsonObj.getString("serveBusinessDetail"), ServeBusinessDetail.class));
        List<ServeBusinessAppend> appends=new ArrayList<ServeBusinessAppend>(
                JSONArray.parseArray(jsonObj.getString("serveBusinessAppend"), ServeBusinessAppend.class));

        if (CommonUtils.isBlank(serveBusiness.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        serveBusiness.setBasicStatus("0");
        serveBusiness.setStatus("0");
        serveBusiness.setModifyDate(new Date());
        serveBusiness.setModifyUserName(userProp.getName());
        serveBusiness.setModifyUserId(userProp.getUserId());
        this.serveBusinessDao.updateByPrimaryKey(serveBusiness);
        String businessId=serveBusiness.getId();
        //清除人员后新增
        List<String> list =serveBusinessDao.selectBusinessId(businessId);
        String[] sb =new String[list.size()];
        basicAnnexDao.deleteByRelationIds(list.toArray(sb));
        serveBusinessDetailDao.deleteByBusinessIds(businessId.split(","));
        serveBusinessAppendDao.deleteByBusinessIds(businessId.split(","));
        //人员信息
        for(ServeBusinessDetail o:serveBusinessDetails) {
            String memberId = GUIDUtil.getGUID();
            o.setId(memberId);
            o.setBusinessId(businessId);
            int temp = serveBusinessDetailDao.isExist(o);
            if (temp > 0) {
                return new ResponseDTO(ResultCode.FAIL, "创业服务资料清单人员表名称重复！");
            }
            o.setCreateDate(new Date());
            o.setStatus("1");
            o.setCreateUserName(userProp.getName());
            o.setCreateUserId(userProp.getUserId());
            o.setModifyDate(new Date());
            //人员资料附件
            if (!CommonUtils.isBlank(o.getBasicAnnexes())) {
                List<BasicAnnex> fileURL = o.getBasicAnnexes();
                for (BasicAnnex a : fileURL) {
                    a.setId(GUIDUtil.getGUID());
                    a.setRelationId(memberId);
                    a.setFileURL(a.getFileURL());
                    //1-培训提升日程安排附件；2-法律服务附件; 3-创业服务资料清单人员附件; 4-创业服务其它附件
                    a.setType("3");
                    a.setRemark("创业服务资料清单附件");
                    a.setCreateDate(new Date());
                    a.setStatus("1");
                    basicAnnexDao.insert(a);
                }
            }
            serveBusinessDetailDao.insert(o);
        }
        //其他选项
        if(appends!=null){
            for(ServeBusinessAppend append:appends){
                append.setId(GUIDUtil.getGUID());
                append.setBusinessId(businessId);
                append.setCreateDate(new Date());
                append.setStatus("1");
                append.setCreateDate(new Date());
                append.setCreateUserName(userProp.getName());
                append.setCreateUserId(userProp.getUserId());
                append.setModifyDate(new Date());
                serveBusinessAppendDao.insert(append);
            }
        }

        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusiness>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<ServeBusinessVo> getById(String id) throws Exception {
        ResponseDTO<ServeBusinessVo> rst = new ResponseDTO<>();
        rst.setData(this.serveBusinessDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除创业服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "创业服务包", operationType = "删除", detail = "删除创业服务包")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveBusinessDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务包)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务包", operationType = "批量删除", detail = "批量删除创业服务包")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveBusinessDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     *创业服务包免费服务申请表
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO<ServeBusinessVo> previewInfo(String id) throws Exception {
        if(CommonUtils.isBlank(id)){
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        ServeBusinessVo vo =serveBusinessDao.previewInfo(id);
        if(vo==null){
            return new ResponseDTO(ResultCode.FAIL, "查无数据！");
        }
        return  new ResponseDTO(ResultCode.SUCCESS, "成功！",vo);
    }

    /**
     * 审核基础信息
     * @param id
     * @param status
     * @return
     */
    @Override
    public ResponseDTO updateBasicStatus(String id, String status,String type) {
        if(CommonUtils.isBlank(id)||CommonUtils.isBlank(status)||CommonUtils.isBlank(type)){
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        //基础信息审核状态
        if(type.equals("1")){
            type="basicStatus";
        }
        else if(type.equals("2")){
            type="status";
        }
        //标记修改
        else if(type.equals("3")){
            type="tab";
        }else{
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        int i=  serveBusinessDao.updateBasicStatus(id,status,type);
        if(type.equals("tab") && status.equals("7")){
            serveBusinessDao.updateBasicStatus(id,"3","status");
        }
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "更新失败");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "更新成功", status);
    }

    /**
     * 是否完成审核
     * @param id
     * @return
     */
    @Override
    public ResponseDTO selectBasicStatus(String id) {
        if(CommonUtils.isBlank(id)){
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        List<String> count=serveBusinessDao.selectBasicStatus(id);
        return   new ResponseDTO(ResultCode.SUCCESS, "成功！",count);
    }


}
