package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.AttachDao;
import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.service.AttachService;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(通知公告)
 */
public class AttachServiceImpl implements AttachService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AttachDao attachDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<AttachVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    public PageDTO<AttachVo> page(AttachQVo condition) throws Exception {
        PageDTO<AttachVo> rst = new PageDTO<>();
        List<AttachVo> list = this.attachDao.findList(condition);
        rst.setRows(list);

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
    public ResponseDTO create(Attach o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getAttachId())) {
            return new ResponseDTO(ResultCode.FAIL, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new ResponseDTO(ResultCode.FAIL, "类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getNoticeId())) {
            return new ResponseDTO(ResultCode.FAIL, "公告编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getFileUrl())) {
            return new ResponseDTO(ResultCode.FAIL, "地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getFileSize())) {
            return new ResponseDTO(ResultCode.FAIL, "附件大小不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new ResponseDTO(ResultCode.FAIL, "入库时间不能为空！");
        }

        int temp = this.attachDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "通知公告名称重复！");
        }
        this.attachDao.insert(o);
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
    public ResponseDTO update(Attach o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getAttachId())) {
            return new ResponseDTO(ResultCode.FAIL, "序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new ResponseDTO(ResultCode.FAIL, "类别不能为空！");
        }
        if (CommonUtils.isBlank(o.getNoticeId())) {
            return new ResponseDTO(ResultCode.FAIL, "公告编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getFileUrl())) {
            return new ResponseDTO(ResultCode.FAIL, "地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getFileSize())) {
            return new ResponseDTO(ResultCode.FAIL, "附件大小不能为空！");
        }
        if (CommonUtils.isBlank(o.getCreateTime())) {
            return new ResponseDTO(ResultCode.FAIL, "入库时间不能为空！");
        }

        this.attachDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Attach>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    public ResponseDTO<AttachVo> getById(String id) throws Exception {
        ResponseDTO<AttachVo> rst = new ResponseDTO<>();
        rst.setData(this.attachDao.selectVoByPrimaryKey(id));
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
        this.attachDao.deleteByPrimaryKey(id);
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
        this.attachDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
