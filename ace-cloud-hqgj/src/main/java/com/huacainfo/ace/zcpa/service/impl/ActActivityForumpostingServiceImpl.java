package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.*;
import com.huacainfo.ace.zcpa.model.ActActivityForumposting;
import com.huacainfo.ace.zcpa.service.ActActivityForumpostingService;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: 何双
 * @version: 2019-08-06
 * @Description: TODO(论坛发贴记录)
 */
public class ActActivityForumpostingServiceImpl implements ActActivityForumpostingService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActActivityForumpostingDao actActivityForumpostingDao;
    @Resource
    private ActActivityAdmirerDao actActivityAdmirerDao;
    @Resource
    private ActActivityReportDao actActivityReportDao;
    @Resource
    private ActActivityForumDao actActivityForumDao;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(论坛发贴记录分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActActivityForumpostingVo>
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    public PageDTO<ActActivityForumpostingVo> page(ActActivityForumpostingQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ActActivityForumpostingVo> rst = new PageDTO<>();
        List<ActActivityForumpostingVo> list = this.actActivityForumpostingDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actActivityForumpostingDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建论坛发贴记录)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Transactional
    @Log(operationObj = "论坛发贴记录", operationType = "创建", detail = "创建论坛发贴记录")
    public ResponseDTO create(ActActivityForumposting o, UserProp userProp) throws Exception {

        int temp = this.actActivityForumpostingDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "论坛发贴记录名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setAreaCode(userProp.getAreaCode());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.actActivityForumpostingDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新论坛发贴记录)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Transactional
    @Log(operationObj = "论坛发贴记录", operationType = "变更", detail = "变更论坛发贴记录")
    public ResponseDTO update(ActActivityForumposting o, UserProp userProp) throws Exception {

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.actActivityForumpostingDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取论坛发贴记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActActivityForumposting>
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Transactional
    public ResponseDTO<ActActivityForumpostingVo> getById(String id) throws Exception {
        ResponseDTO<ActActivityForumpostingVo> rst = new ResponseDTO<>();
        rst.setData(this.actActivityForumpostingDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除论坛发贴记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Log(operationObj = "论坛发贴记录", operationType = "删除", detail = "删除论坛发贴记录")
    public ResponseDTO deleteById(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        this.actActivityForumpostingDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除论坛发贴记录)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Transactional
    @Log(operationObj = "论坛发贴记录", operationType = "批量删除", detail = "批量删除论坛发贴记录")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        this.actActivityForumpostingDao.deleteByIds(ids);
        actActivityAdmirerDao.deleteByPostingIds(ids);
        actActivityForumDao.deleteByPostingIds(ids);
        actActivityReportDao.deleteByPostingIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * 审核, 修改状态
     *
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO updateStatus(String id, String status) throws Exception {
        this.actActivityForumpostingDao.updateStatus(id, status);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 获取发帖详情接口
     *
     * @param id     发帖纪录主键
     * @param userId 当前登录用户ID,可选
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO getForumPostingDetail(String id, String userId) {
        ActActivityForumpostingVo vo = this.actActivityForumpostingDao.getForumPostingDetail(id, userId);
        return new ResponseDTO(ResultCode.SUCCESS, "ok", vo);
    }


}
