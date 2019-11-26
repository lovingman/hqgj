package com.huacainfo.ace.zcpa.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.zcpa.constant.ProjectCategory;
import com.huacainfo.ace.zcpa.dao.ActActivityForumpostingDao;
import com.huacainfo.ace.zcpa.model.ActActivityForumposting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.zcpa.dao.ActProjectActivityDao;
import com.huacainfo.ace.zcpa.model.ActProjectActivity;
import com.huacainfo.ace.zcpa.service.ActProjectActivityService;
import com.huacainfo.ace.zcpa.vo.ActProjectActivityVo;
import com.huacainfo.ace.zcpa.vo.ActProjectActivityQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 豆文国
 * @version: 2019-07-30
 * @Description: TODO(活动详情)
 */
public class ActProjectActivityServiceImpl implements ActProjectActivityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActProjectActivityDao actProjectActivityDao;
    @Resource
    private ActActivityForumpostingDao actActivityForumpostingDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(活动详情分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectActivityVo>
     * @author: 豆文国
     * @version: 2019-07-30
     */
    @Override
    public PageDTO
            <ActProjectActivityVo> page(ActProjectActivityQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ActProjectActivityVo> rst = new PageDTO<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!CommonUtils.isBlank(condition.getEndTime())) {
            String endTime = sdf.format(condition.getEndTime()) + " 23:59:59 ";
            condition.setEndTime(sdf2.parse(endTime));
        }
        if(!CommonUtils.isBlank(condition.getStartTime())) {
            String startTime = sdf.format(condition.getStartTime()) + " 00:00:00 ";
            condition.setStartTime(sdf2.parse(startTime));
        }
        List<ActProjectActivityVo> list = this.actProjectActivityDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actProjectActivityDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建活动详情)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-30
     */
    @Override
    @Transactional
    @Log(operationObj = "活动详情", operationType = "创建", detail = "创建活动详情")
    public ResponseDTO create(ActProjectActivity o, UserProp userProp) throws Exception {
        int temp = this.actProjectActivityDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "活动详情名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        this.actProjectActivityDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新活动详情)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-30
     */
    @Override
    @Transactional
    @Log(operationObj = "活动详情", operationType = "变更", detail = "变更活动详情")
    public ResponseDTO update(ActProjectActivity o, UserProp userProp) throws Exception {

        this.actProjectActivityDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取活动详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectActivity>
     * @author: 豆文国
     * @version: 2019-07-30
     */
    @Override
    @Transactional
    public ResponseDTO<ActProjectActivityVo> getById(String id) throws Exception {
        ResponseDTO<ActProjectActivityVo> rst = new ResponseDTO<>();
        rst.setData(this.actProjectActivityDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除活动详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-30
     */
    @Override
    @Log(operationObj = "活动详情", operationType = "删除", detail = "删除活动详情")
    public ResponseDTO deleteById(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        this.actProjectActivityDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除活动详情)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-30
     */
    @Override
    @Transactional
    @Log(operationObj = "活动详情", operationType = "批量删除", detail = "批量删除活动详情")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        this.actProjectActivityDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * 修改活动状态
     *
     * @param actProjectActivity
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO updateProjectState(ActProjectActivity actProjectActivity) throws Exception {
        if (CommonUtils.isBlank(actProjectActivity.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(actProjectActivity.getStatus())) {
            return new ResponseDTO(ResultCode.FAIL, "状态不能为空！");
        }
        this.actProjectActivityDao.updateProjectState(actProjectActivity);
        if(actProjectActivity.getStatus().equals( ProjectCategory.STATUS_ADOPT)) {
            if (CommonUtils.isBlank(actProjectActivity.getAreaCode())) {
                return new ResponseDTO(ResultCode.FAIL, "区域不能为空！");
            }
            if (CommonUtils.isBlank(actProjectActivity.getCreateUserId())) {
                return new ResponseDTO(ResultCode.FAIL, "发帖人主键不能为空！");
            }
            if (CommonUtils.isBlank(actProjectActivity.getProjectCategory())) {
                return new ResponseDTO(ResultCode.FAIL, "项目类型不能为空！");
            }
            if (!actProjectActivity.getProjectCategory().equals("1")) {
                ActActivityForumposting o = new ActActivityForumposting();
                o.setId(actProjectActivity.getId());
                int temp = actActivityForumpostingDao.isExist(o);
                if (temp > 0) {
                    return new ResponseDTO(ResultCode.FAIL, "数据重复！");
                }
                o.setProjectCode(actProjectActivity.getProjectCategory());
                o.setAreaCode(actProjectActivity.getAreaCode());
                o.setPostingId(actProjectActivity.getCreateUserId());
                o.setTitle(actProjectActivity.getActivityTitle());
                o.setContent(actProjectActivity.getContent());
                o.setStatus(actProjectActivity.getStatus());
                o.setCreateDate(new Date());
                o.setCreateUserId(actProjectActivity.getCreateUserId());
                o.setCreateUserName(actProjectActivity.getCreateUserName());
                actActivityForumpostingDao.insert(o);
            }
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功");
    }

}
