package com.huacainfo.ace.zcpa.service.impl;

//import com.github.pagehelper.PageHelper;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActTopicVolunteerDao;
import com.huacainfo.ace.zcpa.model.ActTopicVolunteer;
import com.huacainfo.ace.zcpa.service.ActTopicVolunteerService;
import com.huacainfo.ace.zcpa.vo.ActTopicVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.ActTopicVolunteerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
/**
 * @author: 豆文国
 * @version: 2019-08-02
 * @Description: TODO(项目活动主题参与志愿者列表)
 */
public class ActTopicVolunteerServiceImpl implements ActTopicVolunteerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActTopicVolunteerDao actTopicVolunteerDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目活动主题参与志愿者列表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActTopicVolunteerVo>
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    public NewPageDTO<ActTopicVolunteerVo> page(ActTopicVolunteerQVo condition, int pageNum, int pageSize, String orderBy) throws Exception {
//        PageHelper.startPage(pageNum, pageSize);

        List<ActTopicVolunteerVo> list = this.actTopicVolunteerDao.findList(condition, pageNum, pageSize, orderBy);

        return new NewPageDTO<>(list);
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目活动主题参与志愿者列表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动主题参与志愿者列表", operationType = "创建", detail = "创建项目活动主题参与志愿者列表")
    public ResponseDTO create(ActTopicVolunteer o, UserProp userProp) throws Exception {

        int temp = this.actTopicVolunteerDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "项目活动主题参与志愿者列表名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        this.actTopicVolunteerDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目活动主题参与志愿者列表)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动主题参与志愿者列表", operationType = "变更", detail = "变更项目活动主题参与志愿者列表")
    public ResponseDTO update(ActTopicVolunteer o, UserProp userProp) throws Exception {

        this.actTopicVolunteerDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动主题参与志愿者列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActTopicVolunteer>
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Transactional
    public ResponseDTO<ActTopicVolunteerVo> getById(String id) throws Exception {
        ResponseDTO<ActTopicVolunteerVo> rst = new ResponseDTO<>();
        rst.setData(this.actTopicVolunteerDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目活动主题参与志愿者列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Log(operationObj = "项目活动主题参与志愿者列表", operationType = "删除", detail = "删除项目活动主题参与志愿者列表")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actTopicVolunteerDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动主题参与志愿者列表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动主题参与志愿者列表", operationType = "批量删除", detail = "批量删除项目活动主题参与志愿者列表")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actTopicVolunteerDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
