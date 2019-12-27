package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.hqgj.dao.ServeFinanceItemDao;
import com.huacainfo.ace.hqgj.model.ServeFinanceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.hqgj.dao.ServeFinanceDao;
import com.huacainfo.ace.hqgj.model.ServeFinance;
import com.huacainfo.ace.hqgj.service.ServeFinanceService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-10
 * @Description: TODO(财税服务包)
 */
public class ServeFinanceServiceImpl implements ServeFinanceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ServeFinanceDao serveFinanceDao;
    @Resource
    private ServeFinanceItemDao serveFinanceItemDao;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(财税服务包分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceVo>
     * @author: 何双
     * @version: 2019-12-10
     */
    @Override
    public PageDTO
            <ServeFinanceVo> page(ServeFinanceQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ServeFinanceVo> rst = new PageDTO<>();
        if(!CommonUtils.isBlank(condition.getType())){
            condition.setTypes(condition.getType().split(","));
        }
        List<ServeFinanceVo> list = this.serveFinanceDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.serveFinanceDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建财税服务包)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务包", operationType = "创建", detail = "创建财税服务包")
    public ResponseDTO create(ServeFinance o, UserProp userProp) throws Exception {
        String serveFinanceId=GUIDUtil.getGUID();
        o.setId(serveFinanceId);
        if (CommonUtils.isBlank(o.getType())) {
            return new ResponseDTO(ResultCode.FAIL, "服务类型不能为空！");
        }
        int temp = this.serveFinanceDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "财税服务包名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("0");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setModifyDate(new Date());
        //财税管家多个项目价格
        if(!CommonUtils.isBlank(o.getFinanceItemList())){
            List<ServeFinanceItem> list=o.getFinanceItemList();
            for (ServeFinanceItem item :list){
                item.setId(GUIDUtil.getGUID());
                item.setFinanceId(serveFinanceId);
                item.setCreateDate(new Date());
                item.setStatus("1");
                item.setCreateUserName(userProp.getName());
                item.setCreateUserId(userProp.getUserId());
                item.setModifyDate(new Date());
               serveFinanceItemDao.insert(item);
            }
        }
        this.serveFinanceDao.insert(o);

        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新财税服务包)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务包", operationType = "变更", detail = "变更财税服务包")
    public ResponseDTO update(ServeFinance o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgId())) {
            return new ResponseDTO(ResultCode.FAIL, "服务机构ID（关联base_organization表id）不能为空！");
        }
        o.setModifyDate(new Date());
        o.setModifyUserName(userProp.getName());
        o.setModifyUserId(userProp.getUserId());
         //财税管家多个项目价格

        if(!CommonUtils.isBlank(o.getFinanceItemList())){
            serveFinanceItemDao.deleteByFinanceIds(o.getId().split(","));
            List<ServeFinanceItem> list=o.getFinanceItemList();
            for (ServeFinanceItem item :list){
                item.setId(GUIDUtil.getGUID());
                item.setFinanceId(o.getId());
                item.setCreateDate(new Date());
                item.setStatus("1");
                item.setCreateUserName(userProp.getName());
                item.setCreateUserId(userProp.getUserId());
                item.setModifyDate(new Date());
                serveFinanceItemDao.insert(item);
            }
        }
        this.serveFinanceDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinance>
     * @author: 何双
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    public ResponseDTO<ServeFinanceVo> getById(String id,String userId) throws Exception {
        ResponseDTO<ServeFinanceVo> rst = new ResponseDTO<>();
        rst.setData(this.serveFinanceDao.selectVoByPrimaryKey(id,userId));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除财税服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    @Override
    @Log(operationObj = "财税服务包", operationType = "删除", detail = "删除财税服务包")
    public ResponseDTO deleteById(String id) throws Exception {
        this.serveFinanceDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务包)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    @Override
    @Transactional
    @Log(operationObj = "财税服务包", operationType = "批量删除", detail = "批量删除财税服务包")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.serveFinanceDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 修改封面图片
     * @param id
     * @param coverUrl
     * @return
     */
    @Override
    public ResponseDTO updateCoverUrl(String id, String coverUrl) {

        int i = serveFinanceDao.updateCoverUrl(id, coverUrl);
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "数据更新失败");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "数据更新成功", coverUrl);
    }

    /**
     * 修改状态 0-待审核 1-审核通过 2-未通过 3-已上线 4-已下线',
     * @param id
     * @param status
     * @return
     */
    @Override
    public ResponseDTO updateStatus(String id, String status,String reason) {
        if(status.equals("2")){
            if(CommonUtils.isBlank(reason)){
                return new ResponseDTO(ResultCode.FAIL, "请输入审核不通过原因");
            }
        }
        int i=  serveFinanceDao.updateStatus(id,status,reason);
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "更新失败");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "更新成功", status);
    }


}
