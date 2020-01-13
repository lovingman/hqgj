package com.huacainfo.ace.hqgj.dao;

import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.hqgj.model.MapWechatSys;
import com.huacainfo.ace.hqgj.model.WechatConfig;
import com.huacainfo.ace.hqgj.vo.UsersVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RegisterDao
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 17:37
 */
public interface RegisterDao {
    int userIsExist(Users o);

    int idCardIsExist(Users o);

    int isExist(Users o);

    int insertUser(Users o);

    int isExistByMobile(String mobile);

    int updatePassword(@Param("account") String account,
                       @Param("newPassword") String newPassword);


    int  updateUser(Users o);

    Map<String, Object> getAreaCode(@Param("userId") String userId,
                                    @Param("sysId") String sysId);

    int deleteByIds(String[] ids);


    int insertUserRole(@Param("userid") String userid, @Param("roleid") String roleid);

    List<String> selectUserList();


    int isUnionid(@Param("unionid") String unionid);

    int updateUsers(Users o);

    UsersVo selectUserInfo(String userId);

    int updateUserType(@Param("userType") String userType,@Param("userId") String userId);

    int insertMapWechatSys(MapWechatSys mapWechatSys);

    int updateMapWechatSys(MapWechatSys mapWechatSys);

    WechatConfig findBySysId();
}
