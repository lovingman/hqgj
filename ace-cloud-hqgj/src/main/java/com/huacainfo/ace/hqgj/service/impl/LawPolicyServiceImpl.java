package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.BasicAnnexDao;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.LawPolicyDao;
import com.huacainfo.ace.hqgj.model.LawPolicy;
import com.huacainfo.ace.hqgj.service.LawPolicyService;
import com.huacainfo.ace.hqgj.vo.LawPolicyVo;
import com.huacainfo.ace.hqgj.vo.LawPolicyQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 豆文国
 * @version: 2019-12-10
 * @Description: TODO(政策服务)
 */
public class LawPolicyServiceImpl implements LawPolicyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private LawPolicyDao lawPolicyDao;
    @Resource
    private BasicAnnexDao basicAnnexDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(政策服务分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <LawPolicyVo>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    public PageDTO
            <LawPolicyVo> page(LawPolicyQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<LawPolicyVo> rst = new PageDTO<>();
        List<LawPolicyVo> list = this.lawPolicyDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.lawPolicyDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建政策服务)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "政策服务", operationType = "创建", detail = "创建政策服务")
    public ResponseDTO create(LawPolicy o, UserProp userProp) throws Exception {
        String cultivateId = GUIDUtil.getGUID();
        o.setId(cultivateId);
        int temp = this.lawPolicyDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "政策服务名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.lawPolicyDao.insert(o);
        if (o.getBasicAnnexes().size()>0) {
            List<BasicAnnex> fileURL = o.getBasicAnnexes();
            for (BasicAnnex a : fileURL) {
                a.setId(GUIDUtil.getGUID());
                a.setRelationId(cultivateId);
                a.setFileURL(a.getFileURL());
                a.setType("4");
                a.setStatus("1");
                a.setCreateDate(new Date());
                a.setRemark("政策服务附件");
                basicAnnexDao.insert(a);
            }
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新政策服务)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "政策服务", operationType = "变更", detail = "变更政策服务")
    public ResponseDTO update(LawPolicy o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.lawPolicyDao.updateByPrimaryKey(o);
        basicAnnexDao.deleteByRelationIds(o.getId().split(","));
        if (o.getBasicAnnexes().size()>0) {
            List<BasicAnnex> fileURL = o.getBasicAnnexes();
            for (BasicAnnex a : fileURL) {
                a.setId(GUIDUtil.getGUID());
                a.setRelationId(o.getId());
                a.setFileURL(a.getFileURL());
                a.setType("4");
                a.setStatus("1");
                a.setCreateDate(new Date());
                a.setRemark("政策服务附件");
                basicAnnexDao.insert(a);
            }
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取政策服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<LawPolicy>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    public ResponseDTO<LawPolicyVo> getById(String id) throws Exception {
        ResponseDTO<LawPolicyVo> rst = new ResponseDTO<>();
        rst.setData(this.lawPolicyDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除政策服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Log(operationObj = "政策服务", operationType = "删除", detail = "删除政策服务")
    public ResponseDTO deleteById(String id) throws Exception {
        this.lawPolicyDao.deleteByPrimaryKey(id);
        basicAnnexDao.deleteByRelationIds(id.split(","));
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除政策服务)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "政策服务", operationType = "批量删除", detail = "批量删除政策服务")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.lawPolicyDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 审核基础信息
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public ResponseDTO updateStatus(String id, String status) {
        if (CommonUtils.isBlank(id) || CommonUtils.isBlank(status)) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        int i = lawPolicyDao.updateStatus(id, status);
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "更新失败");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "更新成功", status);
    }

}
