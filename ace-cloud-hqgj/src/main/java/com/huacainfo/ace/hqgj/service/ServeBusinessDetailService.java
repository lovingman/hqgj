package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeBusinessDetail;
import com.huacainfo.ace.hqgj.vo.AnnexVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessDetailVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessDetailQVo;

import java.util.List;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务资料清单人员表)
 */
public interface ServeBusinessDetailService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(创业服务资料清单人员表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessMemberVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    PageDTO
            <ServeBusinessDetailVo> page(ServeBusinessDetailQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建创业服务资料清单人员表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO create(String jsons, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新创业服务资料清单人员表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(ServeBusinessDetail o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务资料清单人员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessMember>
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO<ServeBusinessDetailVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteServeBusinessMemberById
     * @Description: TODO(删除创业服务资料清单人员表)
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
     * @Description: TODO(批量删除创业服务资料清单人员表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;


    /**
     * 查询创业服务附件
     * @param id 主键
     * @return
     */
    ResponseDTO<AnnexVo> annexList(String id);

    ResponseDTO updateState(ServeBusinessDetail serveBusinessDetail) throws Exception;


    ResponseDTO previewImg(String id,String type)throws Exception;
}
