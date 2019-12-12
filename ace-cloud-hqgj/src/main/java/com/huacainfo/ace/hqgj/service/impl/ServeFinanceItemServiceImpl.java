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
import com.huacainfo.ace.hqgj.dao.ServeFinanceItemDao;
import com.huacainfo.ace.hqgj.model.ServeFinanceItem;
import com.huacainfo.ace.hqgj.service.ServeFinanceItemService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceItemVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceItemQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-11
 * @Description: TODO(财税管家服务项目)
 */
public class ServeFinanceItemServiceImpl implements ServeFinanceItemService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeFinanceItemDao serveFinanceItemDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(财税管家服务项目分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceItemVo>
     * @author: 何双
     * @version: 2019-12-11
     */
    @Override
    public PageDTO
            <ServeFinanceItemVo> page(ServeFinanceItemQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeFinanceItemVo> rst = new PageDTO<>();
        List<ServeFinanceItemVo> list = this.serveFinanceItemDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveFinanceItemDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建财税管家服务项目)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-11
     */
    @Override
    @Transactional
    @Log(operationObj = "财税管家服务项目", operationType = "创建", detail = "创建财税管家服务项目")
    public ResponseDTO create(ServeFinanceItem o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getFinanceId())) {
            return new ResponseDTO(ResultCode.FAIL, "财税服务表ID（关联serve_finance表id）不能为空！");
        }

        int temp = this.serveFinanceItemDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "财税管家服务项目名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        this.serveFinanceItemDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新财税管家服务项目)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-11
     */
    @Override
    @Transactional
    @Log(operationObj = "财税管家服务项目", operationType = "变更", detail = "变更财税管家服务项目")
    public ResponseDTO update(ServeFinanceItem o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getFinanceId())) {
            return new ResponseDTO(ResultCode.FAIL, "财税服务表ID（关联serve_finance表id）不能为空！");
        }

        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
        this.serveFinanceItemDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税管家服务项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinanceItem>
     * @author: 何双
     * @version: 2019-12-11
     */
    @Override
    @Transactional
    public ResponseDTO<ServeFinanceItemVo> getById(String id) throws Exception {
        ResponseDTO<ServeFinanceItemVo> rst = new ResponseDTO<>();
        rst.setData(this.serveFinanceItemDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除财税管家服务项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-11
     */
    @Override
    @Log(operationObj = "财税管家服务项目", operationType = "删除", detail = "删除财税管家服务项目")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveFinanceItemDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税管家服务项目)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-11
     */
    @Override
    @Transactional
    @Log(operationObj = "财税管家服务项目", operationType = "批量删除", detail = "批量删除财税管家服务项目")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveFinanceItemDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
