package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.ServeBusinessOtherDao;
import com.huacainfo.ace.hqgj.model.ServeBusinessOther;
import com.huacainfo.ace.hqgj.service.ServeBusinessOtherService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessOtherVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessOtherQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务资料清单其它资料)
 */
public class ServeBusinessOtherServiceImpl implements ServeBusinessOtherService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeBusinessOtherDao serveBusinessOtherDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(创业服务资料清单其它资料分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessOtherVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO
            <ServeBusinessOtherVo> page(ServeBusinessOtherQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeBusinessOtherVo> rst = new PageDTO<>();
        List<ServeBusinessOtherVo> list = this.serveBusinessOtherDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveBusinessOtherDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建创业服务资料清单其它资料)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料清单其它资料", operationType = "创建", detail = "创建创业服务资料清单其它资料")
    public ResponseDTO create(ServeBusinessOther o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new ResponseDTO(ResultCode.FAIL, "创业服务表ID（关联serve_business表ID）不能为空！");
        }

        int temp = this.serveBusinessOtherDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "创业服务资料清单其它资料名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveBusinessOtherDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新创业服务资料清单其它资料)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料清单其它资料", operationType = "变更", detail = "变更创业服务资料清单其它资料")
    public ResponseDTO update(ServeBusinessOther o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getBusinessId())) {
            return new ResponseDTO(ResultCode.FAIL, "创业服务表ID（关联serve_business表ID）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveBusinessOtherDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务资料清单其它资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessOther>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<ServeBusinessOtherVo> getById(String id) throws Exception {
        ResponseDTO<ServeBusinessOtherVo> rst = new ResponseDTO<>();
        rst.setData(this.serveBusinessOtherDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除创业服务资料清单其它资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "创业服务资料清单其它资料", operationType = "删除", detail = "删除创业服务资料清单其它资料")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveBusinessOtherDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务资料清单其它资料)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "创业服务资料清单其它资料", operationType = "批量删除", detail = "批量删除创业服务资料清单其它资料")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveBusinessOtherDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
