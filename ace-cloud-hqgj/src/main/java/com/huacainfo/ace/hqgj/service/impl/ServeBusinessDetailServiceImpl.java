package com.huacainfo.ace.hqgj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.BasicAnnexDao;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
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
    private BasicAnnexDao basicAnnexDao;
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
        List<ServeBusinessDetail> serveBusinessMembers=new ArrayList<ServeBusinessDetail>(
                JSONArray.parseArray(jsonObj.getString("serveBusinessMember"), ServeBusinessDetail.class));
        for(ServeBusinessDetail o:serveBusinessMembers) {
            String memberId = GUIDUtil.getGUID();
            o.setId(memberId);
            if (CommonUtils.isBlank(o.getBusinessId())) {
                return new ResponseDTO(ResultCode.FAIL, "创业服务表ID不能为空！");
            }
            int temp = this.serveBusinessDetailDao.isExist(o);
            if (temp > 0) {
                return new ResponseDTO(ResultCode.FAIL, "创业服务资料清单人员表名称重复！");
            }
            o.setCreateDate(new Date());
            o.setStatus("1");
            o.setCreateUserName(userProp.getName());
            o.setCreateUserId(userProp.getUserId());
            o.setModifyDate(new Date());
            this.serveBusinessDetailDao.insert(o);
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
        }
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
        List<AnnexVo> list =serveBusinessDetailDao.annexList(id);
        if(list==null){
            return new ResponseDTO(ResultCode.FAIL, "获取失败！");
        }
        return  new ResponseDTO(ResultCode.SUCCESS, "获取成功！" ,list);
    }


}
