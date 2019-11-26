package com.huacainfo.ace.zcpa.service.impl;

//import com.github.pagehelper.PageHelper;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActProjectVolunteerSignoutDao;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteerSignout;
import com.huacainfo.ace.zcpa.service.ActProjectVolunteerSignoutService;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerSignoutQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerSignoutVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: heshuang
 * @version: 2019-08-22
 * @Description: TODO(项目志愿者退出列表)
 */
public class ActProjectVolunteerSignoutServiceImpl implements ActProjectVolunteerSignoutService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActProjectVolunteerSignoutDao actProjectVolunteerSignoutDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目志愿者退出列表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectVolunteerSignoutVo>
     * @author: heshuang
     * @version: 2019-08-22
     */
    @Override
    public NewPageDTO<ActProjectVolunteerSignoutVo> page(ActProjectVolunteerSignoutQVo condition, int pageNum, int pageSize, String orderBy) throws Exception {
//        PageHelper.startPage(pageNum, pageSize);

        List<ActProjectVolunteerSignoutVo> list = this.actProjectVolunteerSignoutDao.findList(condition, pageNum, pageSize, orderBy);

        return new NewPageDTO<>(list);
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目志愿者退出列表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者退出列表", operationType = "创建", detail = "创建项目志愿者退出列表")
    public ResponseDTO create(ActProjectVolunteerSignout o, UserProp userProp) throws Exception {

        int temp = this.actProjectVolunteerSignoutDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "项目志愿者退出列表名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.actProjectVolunteerSignoutDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目志愿者退出列表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者退出列表", operationType = "变更", detail = "变更项目志愿者退出列表")
    public ResponseDTO update(ActProjectVolunteerSignout o, UserProp userProp) throws Exception {

        this.actProjectVolunteerSignoutDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目志愿者退出列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectVolunteerSignout>
     * @author: heshuang
     * @version: 2019-08-22
     */
    @Override
    @Transactional
    public ResponseDTO<ActProjectVolunteerSignoutVo> getById(String id) throws Exception {
        ResponseDTO<ActProjectVolunteerSignoutVo> rst = new ResponseDTO<>();
        rst.setData(this.actProjectVolunteerSignoutDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目志愿者退出列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    @Override
    @Log(operationObj = "项目志愿者退出列表", operationType = "删除", detail = "删除项目志愿者退出列表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actProjectVolunteerSignoutDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目志愿者退出列表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者退出列表", operationType = "批量删除", detail = "批量删除项目志愿者退出列表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actProjectVolunteerSignoutDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 删除志愿者退出
     *
     * @param projectId
     * @param volunteerId
     * @return
     */
    @Override
    public ResponseDTO deleteVolunteer(String projectId, String volunteerId) {
        this.actProjectVolunteerSignoutDao.deleteVolunteer(projectId, volunteerId);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
