package com.huacainfo.ace.zcpa.service.impl;

//import com.github.pagehelper.PageHelper;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActAdmirerForumDao;
import com.huacainfo.ace.zcpa.model.ActAdmirerForum;
import com.huacainfo.ace.zcpa.service.ActAdmirerForumService;
import com.huacainfo.ace.zcpa.vo.ActAdmirerForumQVo;
import com.huacainfo.ace.zcpa.vo.ActAdmirerForumVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-08-13
 * @Description: TODO(点赞回帖)
 */
public class ActAdmirerForumServiceImpl implements ActAdmirerForumService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActAdmirerForumDao actAdmirerForumDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(点赞回帖分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActAdmirerForumVo>
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    @Override
    public NewPageDTO<ActAdmirerForumVo> page(ActAdmirerForumQVo condition, int pageNum, int pageSize, String orderBy) throws Exception {
//        PageHelper.startPage(pageNum, pageSize);

        List<ActAdmirerForumVo> list = this.actAdmirerForumDao.findList(condition, pageNum, pageSize, orderBy);

        return new NewPageDTO<>(list);
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建点赞回帖)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    @Override
    @Transactional
    @Log(operationObj = "点赞回帖", operationType = "创建", detail = "创建点赞回帖")
    public ResponseDTO create(ActAdmirerForum o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserid())) {
            return new ResponseDTO(ResultCode.FAIL, "点赞人主键不能为空！");
        }

        int temp = this.actAdmirerForumDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "点赞回帖名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        this.actAdmirerForumDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新点赞回帖)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    @Override
    @Transactional
    @Log(operationObj = "点赞回帖", operationType = "变更", detail = "变更点赞回帖")
    public ResponseDTO update(ActAdmirerForum o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserid())) {
            return new ResponseDTO(ResultCode.FAIL, "点赞人主键不能为空！");
        }

        this.actAdmirerForumDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取点赞回帖)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActAdmirerForum>
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    @Override
    @Transactional
    public ResponseDTO<ActAdmirerForumVo> getById(String id) throws Exception {
        ResponseDTO<ActAdmirerForumVo> rst = new ResponseDTO<>();
        rst.setData(this.actAdmirerForumDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除点赞回帖)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    @Override
    @Log(operationObj = "点赞回帖", operationType = "删除", detail = "删除点赞回帖")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actAdmirerForumDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除点赞回帖)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    @Override
    @Transactional
    @Log(operationObj = "点赞回帖", operationType = "批量删除", detail = "批量删除点赞回帖")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actAdmirerForumDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
