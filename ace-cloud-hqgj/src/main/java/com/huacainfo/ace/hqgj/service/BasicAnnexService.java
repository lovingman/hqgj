package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.vo.BasicAnnexVo;
import com.huacainfo.ace.hqgj.vo.BasicAnnexQVo;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(附件)
 */
public interface BasicAnnexService {
    /**
     * @throws
     * @Title:page
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
    PageDTO
            <BasicAnnexVo> page(BasicAnnexQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建附件)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO create(BasicAnnex o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新附件)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(BasicAnnex o, UserProp userProp) throws Exception;

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
    ResponseDTO<BasicAnnexVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBasicAnnexById
     * @Description: TODO(删除附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteById(String id) throws Exception;


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
    ResponseDTO deleteByIds(String[] ids) throws Exception;


    /**
     * 对应创业服务包下所有附件
     * @param businessId
     * @return
     * @throws Exception
     */
    ResponseDTO<String> businessFileUrlList(String businessId)throws Exception;

}
