package com.huacainfo.ace.zcpa.service.impl;


import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.zcpa.dao.ActActivityReportDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.zcpa.dao.ActActivityForumDao;
import com.huacainfo.ace.zcpa.model.ActActivityForum;
import com.huacainfo.ace.zcpa.service.ActActivityForumService;
import com.huacainfo.ace.zcpa.vo.ActActivityForumVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 豆文国
 * @version: 2019-08-02
 * @Description: TODO(项目活动论坛回帖情况)
 */
public class ActActivityForumServiceImpl implements ActActivityForumService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActActivityForumDao actActivityForumDao;
    @Resource
    private ActActivityReportDao actActivityReportDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目活动论坛回帖情况分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActActivityForumVo>
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    public PageDTO<ActActivityForumVo> page(ActActivityForumQVo condition, int start, int limit, String orderBy) throws Exception {

        PageDTO<ActActivityForumVo> rst = new PageDTO<>();
        List<ActActivityForumVo> list = this.actActivityForumDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actActivityForumDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目活动论坛回帖情况)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动论坛回帖情况", operationType = "创建", detail = "创建项目活动论坛回帖情况")
    public ResponseDTO create(ActActivityForum o, UserProp userProp) throws Exception {
      if(CommonUtils.isBlank(o.getPostingId())){
        return new ResponseDTO(ResultCode.FAIL, "发帖纪录ID不能为空！");
      }
        o.setId(GUIDUtil.getGUID());
        o.setActivityid(o.getPostingId());
        o.setStatus("1");
        this.actActivityForumDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目活动论坛回帖情况)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动论坛回帖情况", operationType = "变更", detail = "变更项目活动论坛回帖情况")
    public ResponseDTO update(ActActivityForum o, UserProp userProp) throws Exception {

        this.actActivityForumDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动论坛回帖情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActActivityForum>
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Transactional
    public ResponseDTO<ActActivityForumVo> getById(String id) throws Exception {
        ResponseDTO<ActActivityForumVo> rst = new ResponseDTO<>();
        rst.setData(this.actActivityForumDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目活动论坛回帖情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Log(operationObj = "项目活动论坛回帖情况", operationType = "删除", detail = "删除项目活动论坛回帖情况")
    public ResponseDTO deleteById(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        this.actActivityForumDao.deleteByPrimaryKey(id);
        actActivityReportDao.deleteByPostingIds(id.split(","));
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动论坛回帖情况)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动论坛回帖情况", operationType = "批量删除", detail = "批量删除项目活动论坛回帖情况")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
       int i= this.actActivityForumDao.deleteByIds(ids);
              actActivityReportDao.deleteByPostingIds(ids);
        if(i<1){
            return new ResponseDTO(ResultCode.FAIL, "失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 修改活动状态
     *
     * @param actActivityForum
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO updateState(ActActivityForum actActivityForum) throws Exception {
        this.actActivityForumDao.updateState(actActivityForum);
        return new ResponseDTO(ResultCode.SUCCESS, "成功");
    }


}
