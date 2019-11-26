package com.huacainfo.ace.portal.service.impl;



import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.vo.CorpVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huacainfo.ace.common.log.annotation.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.LogsDao;
import com.huacainfo.ace.portal.model.Logs;
import com.huacainfo.ace.portal.service.LogsService;
import com.huacainfo.ace.portal.vo.LogsVo;
import com.huacainfo.ace.portal.vo.LogsQVo;

import javax.annotation.Resource;

@Service
/**
 * @author: wangen
 * @version: 2019-06-01
 * @Description: TODO(操作日志)
 */
public class LogsServiceImpl implements LogsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private LogsDao logsDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(操作日志分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <LogsVo>
     * @author: wangen
     * @version: 2019-06-01
     */
    @Override
    public PageDTO<LogsVo> page(LogsQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<LogsVo> rst = new PageDTO<>();
        List<LogsVo> list = this.logsDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.logsDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }
    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取操作日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Logs>
     * @author: wangen
     * @version: 2019-06-01
     */
    @Override
    @Transactional
    public ResponseDTO<LogsVo> getById(String id) throws Exception {
        ResponseDTO<LogsVo> rst = new ResponseDTO<>();
        rst.setData(this.logsDao.selectVoByPrimaryKey(id));
        return rst;
    }
}
