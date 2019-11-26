package com.huacainfo.ace.zcpa.dao;

import com.huacainfo.ace.common.security.model.Users;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @ClassName RegisterDao
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 17:37
 */
public interface RegisterDao {
    int userIsExist(Users o);

    int isExist(Users o);

    int insertUser(Users o);

    int isExistByMobile(String mobile);

    int updatePassword(@Param("account") String account,
                       @Param("newPassword") String newPassword);

    Map<String, Object> homePage(String userId);

    int  updateUser(Users o);

    Map<String, Object> getAreaCode(@Param("userId") String userId,
                                    @Param("sysId") String sysId);

    int deleteByIds(String[] ids);


    int insertUserRole(@Param("userid") String userid,@Param("roleid") String roleid);

    int isUnionid(@Param("unionid") String  unionid);
}
