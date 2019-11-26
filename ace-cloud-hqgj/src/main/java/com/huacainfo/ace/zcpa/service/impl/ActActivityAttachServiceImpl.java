package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActActivityAttachDao;
import com.huacainfo.ace.zcpa.model.ActActivityAttach;
import com.huacainfo.ace.zcpa.service.ActActivityAttachService;
import com.huacainfo.ace.zcpa.vo.ActActivityAttachQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityAttachVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
/**
 * @author: 豆文国
 * @version: 2019-07-31
 * @Description: TODO(活动附件)
 */
public class ActActivityAttachServiceImpl implements ActActivityAttachService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActActivityAttachDao actActivityAttachDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(活动附件分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActActivityAttachVo>
     * @author: 豆文国
     * @version: 2019-07-31
     */
    @Override
    public NewPageDTO
            <ActActivityAttachVo> page(ActActivityAttachQVo condition, int pageNum, int pageSize, String orderBy) throws Exception {
//        PageHelper.startPage(pageNum, pageSize);

        List<ActActivityAttachVo> list = this.actActivityAttachDao.findList(condition, pageNum, pageSize, orderBy);

        return new NewPageDTO<>(list);
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建活动附件)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-31
     */
    @Override
    @Transactional
    @Log(operationObj = "活动附件", operationType = "创建", detail = "创建活动附件")
    public ResponseDTO create(ActActivityAttach o, UserProp userProp) throws Exception {

        int temp = this.actActivityAttachDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "活动附件名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        this.actActivityAttachDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新活动附件)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-31
     */
    @Override
    @Transactional
    @Log(operationObj = "活动附件", operationType = "变更", detail = "变更活动附件")
    public ResponseDTO update(ActActivityAttach o, UserProp userProp) throws Exception {

        this.actActivityAttachDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取活动附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActActivityAttach>
     * @author: 豆文国
     * @version: 2019-07-31
     */
    @Override
    @Transactional
    public ResponseDTO<ActActivityAttachVo> getById(String id) throws Exception {
        ResponseDTO<ActActivityAttachVo> rst = new ResponseDTO<>();
        rst.setData(this.actActivityAttachDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除活动附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-31
     */
    @Override
    @Log(operationObj = "活动附件", operationType = "删除", detail = "删除活动附件")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actActivityAttachDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除活动附件)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-31
     */
    @Override
    @Transactional
    @Log(operationObj = "活动附件", operationType = "批量删除", detail = "批量删除活动附件")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actActivityAttachDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
