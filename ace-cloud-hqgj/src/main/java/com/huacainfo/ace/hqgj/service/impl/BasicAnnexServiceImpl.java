package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;

import java.net.URL;
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
import com.huacainfo.ace.hqgj.dao.BasicAnnexDao;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.service.BasicAnnexService;
import com.huacainfo.ace.hqgj.vo.BasicAnnexVo;
import com.huacainfo.ace.hqgj.vo.BasicAnnexQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(附件)
 */
public class BasicAnnexServiceImpl implements BasicAnnexService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BasicAnnexDao basicAnnexDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(附件分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BasicAnnexVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    public PageDTO
            <BasicAnnexVo> page(BasicAnnexQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<BasicAnnexVo> rst = new PageDTO<>();
        List<BasicAnnexVo> list = this.basicAnnexDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.basicAnnexDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;

    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建附件)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "附件", operationType = "创建", detail = "创建附件")
    public ResponseDTO create(BasicAnnex o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new ResponseDTO(ResultCode.FAIL, "关联主键（关联相关模块的主键ID）不能为空！");
        }

        int temp = this.basicAnnexDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "附件名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.basicAnnexDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新附件)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "附件", operationType = "变更", detail = "变更附件")
    public ResponseDTO update(BasicAnnex o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRelationId())) {
            return new ResponseDTO(ResultCode.FAIL, "关联主键（关联相关模块的主键ID）不能为空！");
        }

        this.basicAnnexDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BasicAnnex>
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    public ResponseDTO<BasicAnnexVo> getById(String id) throws Exception {
        ResponseDTO<BasicAnnexVo> rst = new ResponseDTO<>();
        rst.setData(this.basicAnnexDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Log(operationObj = "附件", operationType = "删除", detail = "删除附件")
    public ResponseDTO deleteById(String id) throws Exception {
        this.basicAnnexDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除附件)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @Override
    @Transactional
    @Log(operationObj = "附件", operationType = "批量删除", detail = "批量删除附件")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.basicAnnexDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * 对应创业包下的所有附件
     * @param businessId
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO<String> businessFileUrlList(String businessId) throws Exception {
        if(CommonUtils.isBlank(businessId)){
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        List<String> urlList=basicAnnexDao.businessFileUrlList(businessId);
        String[] list=new String[urlList.size()];
        //多个图片下载地址
        for(int i=0;i<urlList.size();i++ ) {
            try {
                URL url = new URL(urlList.get(i));
                String base64 = CommonUtils.encodeImageToBase64(url);
                list[i] = base64;
            }catch (Exception e){
                return new ResponseDTO(ResultCode.FAIL, "图片转换失败！");
            }
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！",list);
    }


}
