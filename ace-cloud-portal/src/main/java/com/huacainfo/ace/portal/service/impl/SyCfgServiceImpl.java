package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.ConfigDao;
import com.huacainfo.ace.portal.dao.SyCfgDao;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.service.SyCfgService;
import com.huacainfo.ace.portal.vo.SyCfgQVo;
import com.huacainfo.ace.portal.vo.SyCfgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(系统配置)
 */
public class SyCfgServiceImpl implements SyCfgService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SyCfgDao syCfgDao;

    @Resource
    private ConfigDao configDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(系统配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<SyCfgVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    public PageDTO<SyCfgVo> page(SyCfgQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<SyCfgVo> rst = new PageDTO<>();
        List<SyCfgVo> list = this.syCfgDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.syCfgDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建系统配置)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "系统配置", operationType = "创建", detail = "创建系统配置")
    public ResponseDTO create(SyCfg o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "系统编号不能为空！");
        }

        int temp = this.syCfgDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "系统配置名称重复！");
        }
        o.setId(GUIDUtil.getGUID());
        this.syCfgDao.insert(o);
        this.initConfig(o.getId(),userProp.getCorpId(),o.getName());
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新系统配置)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "系统配置", operationType = "变更", detail = "变更系统配置")
    public ResponseDTO update(SyCfg o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "系统编号不能为空！");
        }

        this.syCfgDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取系统配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<SyCfg>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    public ResponseDTO<SyCfgVo> getById(String id) throws Exception {
        ResponseDTO<SyCfgVo> rst = new ResponseDTO<>();
        rst.setData(this.syCfgDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除系统配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Log(operationObj = "系统配置", operationType = "删除", detail = "删除系统配置")
    public ResponseDTO deleteById(String id) throws Exception {
        this.syCfgDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除系统配置)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @Override
    @Transactional
    @Log(operationObj = "系统配置", operationType = "批量删除", detail = "批量删除系统配置")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.syCfgDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    @Override
    public ListDTO<Map<String, Object>> selectSyCfgByUser(UserProp userProp) {
        ListDTO<Map<String, Object>> rst = new ListDTO<>();
        List<Map<String, Object>> value = this.syCfgDao.selectSyCfgByUser(userProp.getSyid());
        rst.setData(value);
        return rst;
    }

    private void initConfig(String syid,String deptId,String syName){
        Config cfg1=new Config();
        cfg1.setCreateTime(new Date());
        cfg1.setId(UUID.randomUUID().toString());
        cfg1.setDeptId(deptId);
        cfg1.setConfigKey("sys_login_bg_img");
        cfg1.setCategory("02");
        cfg1.setSyid(syid);
        cfg1.setConfigName("登录背景图片");
        cfg1.setConfigValue("/content/portal/images/LOGIN2880-760.png");
        this.configDao.insert(cfg1);

        Config cfg2=new Config();
        cfg2.setCreateTime(new Date());
        cfg2.setId(UUID.randomUUID().toString());
        cfg2.setDeptId(deptId);
        cfg2.setConfigKey("sys_name");
        cfg2.setCategory("02");
        cfg2.setSyid(syid);
        cfg2.setConfigName("系统名称");
        cfg2.setConfigValue(syName);
        this.configDao.insert(cfg2);


        Config cfg3=new Config();
        cfg3.setCreateTime(new Date());
        cfg3.setId(UUID.randomUUID().toString());
        cfg3.setDeptId(deptId);
        cfg3.setConfigKey("sys_unit");
        cfg3.setCategory("02");
        cfg3.setSyid(syid);
        cfg3.setConfigName("使用单位名称");
        cfg3.setConfigValue("XX单位");
        this.configDao.insert(cfg3);


        Config cfg4=new Config();
        cfg4.setCreateTime(new Date());
        cfg4.setId(UUID.randomUUID().toString());
        cfg4.setDeptId(deptId);
        cfg4.setConfigKey("version");
        cfg4.setCategory("02");
        cfg4.setSyid(syid);
        cfg4.setConfigName("系统版本");
        cfg4.setConfigValue("V1.0");
        this.configDao.insert(cfg4);


    }
}
