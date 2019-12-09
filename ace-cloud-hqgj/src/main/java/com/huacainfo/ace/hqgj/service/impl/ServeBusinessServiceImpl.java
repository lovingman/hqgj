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
import com.huacainfo.ace.hqgj.dao.ServeBusinessDao;
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
    private ServeBusinessDao serveBusinessDao;

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
    public ResponseDTO create(ServeBusiness o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        int temp = this.serveBusinessDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "创业服务包名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveBusinessDao.insert(o);
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
    public ResponseDTO update(ServeBusiness o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveBusinessDao.updateByPrimaryKey(o);
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


}
