package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.ProvinceDao;
import com.huacainfo.ace.portal.model.Province;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.service.ProvinceService;
import com.huacainfo.ace.portal.vo.ProvinceQVo;
import com.huacainfo.ace.portal.vo.ProvinceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(行政区划)
 */
public class ProvinceServiceImpl implements ProvinceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ProvinceDao provinceDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(行政区划分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<ProvinceVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    public PageDTO<ProvinceVo> page(ProvinceQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ProvinceVo> rst = new PageDTO<>();
        List<ProvinceVo> list = this.provinceDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.provinceDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建行政区划)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "行政区划", operationType = "创建", detail = "创建行政区划")
    public ResponseDTO create(Province o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getParentCode())) {
            return new ResponseDTO(ResultCode.FAIL, "上级辖区编号不能为空！");
        }

        int temp = this.provinceDao.isExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "辖区信息管理名称重复！");
        }
        this.provinceDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新行政区划)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "行政区划", operationType = "变更", detail = "变更行政区划")
    public ResponseDTO update(Province o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getCode())) {
            return new ResponseDTO(ResultCode.FAIL, "辖区编号不能为空！");
        }

        this.provinceDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取行政区划)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Province>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    public ResponseDTO<ProvinceVo> getById(String id) throws Exception {
        ResponseDTO<ProvinceVo> rst = new ResponseDTO<>();
        rst.setData(this.provinceDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除行政区划)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Log(operationObj = "行政区划", operationType = "删除", detail = "删除行政区划")
    public ResponseDTO deleteById(String id) throws Exception {
        this.provinceDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除行政区划)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @Override
    @Transactional
    @Log(operationObj = "行政区划", operationType = "批量删除", detail = "批量删除行政区划")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.provinceDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    @Override
    public ListDTO<Map<String,Object>> getListByPid(String pid) throws Exception{
        this.logger.info("{}",pid);
        ListDTO<Map<String,Object>> rst = new ListDTO<>();
        rst.setData(this.provinceDao.getListByPid(pid));
        return rst;
    }

    /**
     * execel导入接口
     *
     * @param list     文件流数组
     * @param userProp 当前登录用户
     * @return ResponseDTO
     * @throws Exception
     */
    @Log(operationObj = "资源文件管理", operationType = "批量导入", detail = "批量导入资源文件管理")
    @Transactional
    @Override
    public ResponseDTO importXls(List<Object> list, UserProp userProp) throws Exception{
        int i = 1;
        for (Object row : list) {
            Province o = new Province();
            CommonBeanUtils.copyProperties(o,row);
            this.logger.info(o.toString());
            if (CommonUtils.isBlank(o.getCode())) {
                return new ResponseDTO(ResultCode.FAIL, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getParentCode())) {
                return new ResponseDTO(ResultCode.FAIL, "行" + i + ",父编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new ResponseDTO(ResultCode.FAIL, "行" + i + ",名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getType())) {
                return new ResponseDTO(ResultCode.FAIL, "行" + i + ",类型不能为空！");
            }
            int t = provinceDao.isExist(o);
            if (t > 0) {
                provinceDao.updateByPrimaryKey(o);

            } else {
                provinceDao.insert(o);
            }
            i++;
        }

        return new ResponseDTO(ResultCode.SUCCESS, "资源导入完成！");
    }


}
