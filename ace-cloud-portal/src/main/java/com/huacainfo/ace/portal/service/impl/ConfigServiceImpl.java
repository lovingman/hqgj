package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.ConfigDao;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.service.ConfigService;
import com.huacainfo.ace.portal.vo.ConfigQVo;
import com.huacainfo.ace.portal.vo.ConfigVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(系统参数配置)
 */
public class ConfigServiceImpl implements ConfigService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ConfigDao configDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(系统参数配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<ConfigVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    public PageDTO<ConfigVo> page(ConfigQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ConfigVo> rst = new PageDTO<>();
        List<ConfigVo> list = this.configDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.configDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建系统参数配置)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "系统参数配置", operationType = "创建", detail = "创建系统参数配置")
    public ResponseDTO create(Config o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getConfigKey())) {
            return new ResponseDTO(ResultCode.FAIL, " 配置key不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigName())) {
            return new ResponseDTO(ResultCode.FAIL, " 名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigValue())) {
            return new ResponseDTO(ResultCode.FAIL, "系统参数值不能为空！");
        }

        int temp = this.configDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "已存在此名称的数据！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateTime(new Date());
        o.setDeptId(userProp.getCorpId());
        o.setSyid(userProp.getActiveSyId());
        this.configDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新系统参数配置)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "系统参数配置", operationType = "变更", detail = "变更系统参数配置")
    public ResponseDTO update(Config o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigName())) {
            return new ResponseDTO(ResultCode.FAIL, "名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getConfigValue())) {
            return new ResponseDTO(ResultCode.FAIL, "系统参数值不能为空！");
        }

        o.setCreateTime(new Date());
        o.setDeptId(userProp.getCorpId());
        this.configDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取系统参数配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Config>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    public ResponseDTO<ConfigVo> getById(String id) throws Exception {
        ResponseDTO<ConfigVo> rst = new ResponseDTO<>();
        rst.setData(this.configDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除系统参数配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Log(operationObj = "系统参数配置", operationType = "删除", detail = "删除系统参数配置")
    public ResponseDTO deleteById(String id) throws Exception {
        this.configDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除系统参数配置)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "系统参数配置", operationType = "批量删除", detail = "批量删除系统参数配置")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.configDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * 根据key查找配置信息
     *
     * @param syId   系统标识
     * @param cfgKey key
     * @return ConfigVo
     */
    @Override
    public Config findByKey(String syId, String cfgKey) {
        return configDao.findByKey(syId, cfgKey);
    }
}
