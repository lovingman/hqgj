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

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.BasicAnnexDao;
import com.huacainfo.ace.hqgj.dao.ServeBusinessAppendDao;
import com.huacainfo.ace.hqgj.dao.ServeBusinessDao;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.model.ServeBusiness;
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;
import com.huacainfo.ace.hqgj.vo.AnnexVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.ServeBusinessDetailDao;
import com.huacainfo.ace.hqgj.model.ServeBusinessDetail;
import com.huacainfo.ace.hqgj.service.ServeBusinessDetailService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessDetailVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessDetailQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务资料清单人员表)
 */
public class ServeBusinessDetailServiceImpl implements ServeBusinessDetailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeBusinessDetailDao serveBusinessDetailDao;
    @Resource
    private ServeBusinessDao serveBusinessDao;
    @Resource
    private BasicAnnexDao basicAnnexDao;
    @Resource
    private ServeBusinessAppendDao serveBusinessAppendDao;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(创业服务资料清单人员表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessMemberVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO
            <ServeBusinessDetailVo> page(ServeBusinessDetailQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeBusinessDetailVo> rst = new PageDTO<>();
        if(!CommonUtils.isBlank(condition.getType())){
            condition.setTypes(condition.getType().split(","));
        }
        List<ServeBusinessDetailVo> list = this.serveBusinessDetailDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveBusinessDetailDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建创业服务资料清单人员表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料清单人员表", operationType = "创建", detail = "创建创业服务资料清单人员表")
    public ResponseDTO create(String jsons, UserProp userProp) throws Exception {
        JSONObject jsonObj= JSON.parseObject(jsons);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新创业服务资料清单人员表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料清单人员表", operationType = "变更", detail = "变更创业服务资料清单人员表")
    public ResponseDTO update(ServeBusinessDetail o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new ResponseDTO(ResultCode.FAIL, "创业服务表ID（关联serve_business表ID）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveBusinessDetailDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务资料清单人员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessMember>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<ServeBusinessDetailVo> getById(String id) throws Exception {
        ResponseDTO<ServeBusinessDetailVo> rst = new ResponseDTO<>();
        rst.setData(this.serveBusinessDetailDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除创业服务资料清单人员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "创业服务资料清单人员表", operationType = "删除", detail = "删除创业服务资料清单人员表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveBusinessDetailDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务资料清单人员表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料清单人员表", operationType = "批量删除", detail = "批量删除创业服务资料清单人员表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveBusinessDetailDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * 查询创业服务附件
     * @param id 主键
     * @return
     */
    @Override
    public ResponseDTO<AnnexVo> annexList(String id) {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
        ServeBusiness b = serveBusinessDao.selectVoByPrimaryKey(id);
        if (b==null) {
            return new ResponseDTO(ResultCode.FAIL, "没有数据");
        }
        List<AnnexVo> list =serveBusinessDetailDao.annexList(id);
        AnnexVo vo =new AnnexVo();
        vo.setFileName("武陵区中小企业服务平台“创业服务一条龙”免费服务申请表");
        vo.setAnnexNum(1);
        vo.setCreateDate(b.getCreateDate());
        list.add(vo);
        if(list==null){
            return new ResponseDTO(ResultCode.FAIL, "获取失败！");
        }
        return  new ResponseDTO(ResultCode.SUCCESS, "获取成功！" ,list);
    }

    /**
     * 修改创业服务资料清单表状态
     *
     * @param serveBusinessDetail
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ResponseDTO updateState(ServeBusinessDetail serveBusinessDetail) throws Exception {
        if (CommonUtils.isBlank(serveBusinessDetail.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(serveBusinessDetail.getStatus())) {
            return new ResponseDTO(ResultCode.FAIL, "状态不能为空！");
        }
        if (CommonUtils.isBlank(serveBusinessDetail.getBusinessId())) {
            return new ResponseDTO(ResultCode.FAIL, "创业id不能为空！");
        }
        if(serveBusinessDetail.getStatus().equals("2")){
            serveBusinessDao.updateBasicStatus(serveBusinessDetail.getBusinessId(),"2","status");
        }

        this.serveBusinessDetailDao.updateState(serveBusinessDetail);
        return new ResponseDTO(ResultCode.SUCCESS, "成功");
    }


    /**
     * 创业服务基础信息预览图片信息
     * @param id
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO previewImg(String id, String type) throws Exception {
        if (CommonUtils.isBlank(id)||CommonUtils.isBlank(type)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        ResponseDTO<List<ServeBusinessDetailVo>> rst = new ResponseDTO<>();
        List<ServeBusinessDetailVo> list=serveBusinessDetailDao.previewImg(id,type);
        if(list.size()<0){
            return new ResponseDTO(ResultCode.FAIL, "获取信息失败！");
        }
        rst.setData(list);
        return rst;
    }
}
