package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.NoticeDao;
import com.huacainfo.ace.portal.model.Notice;
import com.huacainfo.ace.portal.service.NoticeService;
import com.huacainfo.ace.portal.vo.NoticeQVo;
import com.huacainfo.ace.portal.vo.NoticeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(通知公告)
 */
public class NoticeServiceImpl implements NoticeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private NoticeDao noticeDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<NoticeVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    public PageDTO<NoticeVo> page(NoticeQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<NoticeVo> rst = new PageDTO<>();
        List<NoticeVo> list = this.noticeDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.noticeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建通知公告)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "通知公告", operationType = "创建", detail = "创建通知公告")
    public ResponseDTO create(Notice o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getNoticeId())) {
            return new ResponseDTO(ResultCode.FAIL, "公告编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new ResponseDTO(ResultCode.FAIL, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new ResponseDTO(ResultCode.FAIL, "类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new ResponseDTO(ResultCode.FAIL, "内容不能为空！");
        }
        if (o.getDeadline() == null) {
            return new ResponseDTO(ResultCode.FAIL, "有效日期不能为空！");
        }

        int temp = this.noticeDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "已存在此标题的数据！");
        }
        o.setStatus("1");
        o.setPublisher(userProp.getUserId());
        o.setCreateTime(new Date());
        o.setDepartmentId(userProp.getCorpId());
        o.setSyid(userProp.getActiveSyId());
        this.noticeDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新通知公告)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "通知公告", operationType = "变更", detail = "变更通知公告")
    public ResponseDTO update(Notice o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getNoticeId())) {
            return new ResponseDTO(ResultCode.FAIL, "编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new ResponseDTO(ResultCode.FAIL, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new ResponseDTO(ResultCode.FAIL, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getPublisher())) {
            return new ResponseDTO(ResultCode.FAIL, "发布者不能为空！");
        }
        if (CommonUtils.isBlank(o.getTop())) {
            return new ResponseDTO(ResultCode.FAIL, "置顶否不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new ResponseDTO(ResultCode.FAIL, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new ResponseDTO(ResultCode.FAIL, "入库时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeadline())) {
            return new ResponseDTO(ResultCode.FAIL, "过期时间不能为空！");
        }

        this.noticeDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Notice>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    public ResponseDTO<NoticeVo> getById(String id) throws Exception {
        ResponseDTO<NoticeVo> rst = new ResponseDTO<>();
        rst.setData(this.noticeDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Log(operationObj = "通知公告", operationType = "删除", detail = "删除通知公告")
    public ResponseDTO deleteById(String id) throws Exception {
        this.noticeDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除通知公告)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "通知公告", operationType = "批量删除", detail = "批量删除通知公告")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.noticeDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
