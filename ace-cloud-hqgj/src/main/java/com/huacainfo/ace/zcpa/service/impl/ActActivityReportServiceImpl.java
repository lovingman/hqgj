package com.huacainfo.ace.zcpa.service.impl;

//import com.github.pagehelper.PageHelper;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActActivityReportDao;
import com.huacainfo.ace.zcpa.model.ActActivityReport;
import com.huacainfo.ace.zcpa.service.ActActivityReportService;
import com.huacainfo.ace.zcpa.vo.ActActivityReportQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityReportVo;
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
 * @Description: TODO(项目活动论坛举报情况)
 */
public class ActActivityReportServiceImpl implements ActActivityReportService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActActivityReportDao actActivityReportDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目活动论坛举报情况分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActActivityReportVo>
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    public NewPageDTO<ActActivityReportVo> page(ActActivityReportQVo condition, int pageNum, int pageSize, String orderBy) throws Exception {
//        PageHelper.startPage(pageNum, pageSize);

        List<ActActivityReportVo> list = this.actActivityReportDao.findList(condition, pageNum, pageSize, orderBy);

        return new NewPageDTO<>(list);
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目活动论坛举报情况)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动论坛举报情况", operationType = "创建", detail = "创建项目活动论坛举报情况")
    public ResponseDTO create(ActActivityReport o, UserProp userProp) throws Exception {

        int temp = this.actActivityReportDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "已经举报过了！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.actActivityReportDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目活动论坛举报情况)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动论坛举报情况", operationType = "变更", detail = "变更项目活动论坛举报情况")
    public ResponseDTO update(ActActivityReport o, UserProp userProp) throws Exception {
        this.actActivityReportDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动论坛举报情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActActivityReport>
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Transactional
    public ResponseDTO<ActActivityReportVo> getById(String id) throws Exception {
        ResponseDTO<ActActivityReportVo> rst = new ResponseDTO<>();
        rst.setData(this.actActivityReportDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目活动论坛举报情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Log(operationObj = "项目活动论坛举报情况", operationType = "删除", detail = "删除项目活动论坛举报情况")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actActivityReportDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动论坛举报情况)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动论坛举报情况", operationType = "批量删除", detail = "批量删除项目活动论坛举报情况")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actActivityReportDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
