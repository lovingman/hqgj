package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.JwtTokenUtil;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.portal.dao.SystemDao;
import com.huacainfo.ace.portal.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CacheConfig(cacheNames = {"system"})
@Service
public class SystemServiceImpl implements SystemService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SystemDao systemDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public ListDTO<Tree> getSysTreeMenu(String userId) throws Exception {
        logger.info("SystemServiceImpl -> getSysTreeMenu {}", userId);

        List<Map<String, Object>> list = this.systemDao.selectResourcesByUserId(userId);
        CommonTreeUtils tool = new CommonTreeUtils(list);
        ListDTO<Tree> rst = new ListDTO();
        rst.setData(tool.getTreeList("0"));
        return rst;
    }

    @Override
    public ListDTO<Map<String, String>> getSysButtons(String userId) throws Exception {
        ListDTO<Map<String, String>> rst = new ListDTO();
        List<Map<String, String>> list = this.systemDao.selectButtonsByUserId(userId);
        rst.setData(list);
        return rst;
    }

    @Cacheable
    @Override
    public ListDTO<Tree> getAreaTree(String pid, Integer type, String hasSelf) throws Exception {
        List<Map<String, Object>> list = this.systemDao.getAreaTree(pid, type);

        return parseAreaTree(pid, hasSelf, list);
    }

    private ListDTO<Tree> parseAreaTree(String pid, String hasSelf, List<Map<String, Object>> list) {
        CommonTreeUtils tool = new CommonTreeUtils(list);
        ListDTO<Tree> rst = new ListDTO();
        if (StringUtils.isEmpty(hasSelf)) {
            rst.setData(tool.getTreeList(pid));
        } else {
            rst.setData(tool.getTreeListCaseSelf(pid));
        }

        return rst;
    }

    /**
     * pid 顶级行政区化编码
     * type 数据展示层级 5 省 4市 3县区 2 乡镇 1村;多级别请用，隔开
     * hasSelf 是否包含顶级行政区化
     *
     * @param pid     顶级行政区化编码
     * @param type    数据展示层级 5 省 4市 3县区 2 乡镇 1村;多级别请用，隔开
     * @param hasSelf 是否包含顶级行政区化
     */
    @Cacheable
    @Override
    public ListDTO<Tree> getNewAreaTree(String pid, String type, String hasSelf) {
        List<Map<String, Object>> list = this.systemDao.getNewAreaTree(pid, type.split(","));

        return parseAreaTree(pid, hasSelf, list);
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
    @Override
    public ResponseDTO<String[]> getPAreaCode(String areaCode) throws Exception {
        ResponseDTO<String[]> rst= new ResponseDTO();
        rst.setData(this.systemDao.getPAreaCode(areaCode));
        return rst;
    }


}
