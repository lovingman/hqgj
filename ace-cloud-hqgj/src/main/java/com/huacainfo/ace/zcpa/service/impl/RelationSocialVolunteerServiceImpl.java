package com.huacainfo.ace.zcpa.service.impl;

//import com.github.pagehelper.PageHelper;
import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.zcpa.dao.RelationSocialVolunteerDao;
import com.huacainfo.ace.zcpa.model.RelationSocialVolunteer;
import com.huacainfo.ace.zcpa.service.RelationSocialVolunteerService;
import com.huacainfo.ace.zcpa.vo.RelationSocialVolunteerVo;
import com.huacainfo.ace.zcpa.vo.RelationSocialVolunteerQVo;
import javax.annotation.Resource;
@Service
/**
* @author: 豆文国
* @version: 2019-09-26
* @Description:  TODO(志愿者与社会组织关联)
*/
public class RelationSocialVolunteerServiceImpl implements RelationSocialVolunteerService {
Logger logger = LoggerFactory.getLogger(this.getClass());
@Resource
private RelationSocialVolunteerDao relationSocialVolunteerDao;

/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(志愿者与社会组织关联分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<RelationSocialVolunteerVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-09-26
    */
    @Override
    public PageDTO<RelationSocialVolunteerVo> page(RelationSocialVolunteerQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<RelationSocialVolunteerVo> rst = new PageDTO<>();
        List<RelationSocialVolunteerVo> list = this.relationSocialVolunteerDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.relationSocialVolunteerDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

            }

            /**
            *
            * @Title:create
            * @Description: TODO(创建志愿者与社会组织关联)
            * @param: @param o
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2019-09-26
            */
            @Override
            @Transactional
            @Log(operationObj = "志愿者与社会组织关联", operationType = "创建", detail = "创建志愿者与社会组织关联")
            public ResponseDTO create(RelationSocialVolunteer o, UserProp userProp) throws Exception {
              o.setId(GUIDUtil.getGUID());
            int temp = this.relationSocialVolunteerDao.isExist(o);
            if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "志愿者与社会组织关联名称重复！");
            }
            o.setVolunteerId(userProp.getUserId());
            o.setCreateDate(new Date());
            o.setAuditStatus("1");
            this.relationSocialVolunteerDao.insert(o);
            return new ResponseDTO(ResultCode.SUCCESS, "成功！");
            }

            /**
            *
            * @Title:update
            * @Description: TODO(更新志愿者与社会组织关联)
            * @param: @param o
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2019-09-26
            */
            @Override
            @Transactional
            @Log(operationObj = "志愿者与社会组织关联", operationType = "变更", detail = "变更志愿者与社会组织关联")
            public ResponseDTO update(RelationSocialVolunteer o, UserProp userProp) throws Exception {
            if (CommonUtils.isBlank(o.getId())) {
return new ResponseDTO(ResultCode.FAIL, "id主键不能为空！");
}

            this.relationSocialVolunteerDao.updateByPrimaryKey(o);
            return new ResponseDTO(ResultCode.SUCCESS, "成功！");
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取志愿者与社会组织关联)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<RelationSocialVolunteer>
            * @throws
            * @author: 豆文国
            * @version: 2019-09-26
            */
            @Override
            @Transactional
            public ResponseDTO<RelationSocialVolunteerVo> getById(String id) throws Exception {
                ResponseDTO<RelationSocialVolunteerVo> rst = new ResponseDTO<>();
                    rst.setData(this.relationSocialVolunteerDao.selectVoByPrimaryKey(id));
                    return rst;
                    }

                    /**
                    *
                    * @Title:deleteById
                    * @Description: TODO(删除志愿者与社会组织关联)
                    * @param: @param id
                    * @param: @throws Exception
                    * @return: ResponseDTO
                    * @throws
                    * @author: 豆文国
                    * @version: 2019-09-26
                    */
                    @Override
                    @Log(operationObj = "志愿者与社会组织关联", operationType = "删除", detail = "删除志愿者与社会组织关联")
                    public ResponseDTO deleteById(String id) throws Exception {
                    this.relationSocialVolunteerDao.deleteByPrimaryKey(id);
                    return new ResponseDTO(ResultCode.SUCCESS, "成功！");
                    }


                    /**
                    *
                    * @Title:deleteByIds
                    * @Description: TODO(批量删除志愿者与社会组织关联)
                    * @param: @param ids
                    * @param: @throws Exception
                    * @return: ResponseDTO
                    * @throws
                    * @author: 豆文国
                    * @version: 2019-09-26
                    */
                    @Override
                    @Transactional
                    @Log(operationObj = "志愿者与社会组织关联", operationType = "批量删除", detail = "批量删除志愿者与社会组织关联")
                    public ResponseDTO deleteByIds(String[] ids) throws Exception {
                    this.relationSocialVolunteerDao.deleteByIds(ids);
                    return new ResponseDTO(ResultCode.SUCCESS, "成功！");
                    }



                    }
