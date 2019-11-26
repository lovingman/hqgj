package com.huacainfo.ace.zcpa.constant.synchro;

public interface KitUrl {
    //生产
    String PRODIP="http://10.0.0.4:8080";
    //测试
    String TESTIP="http://59.231.66.130:8088";

    // 1、登录用户信息
    String SEARCHUSER_URl=PRODIP+"/sysadmin/searchUserListData/getUsersInfoByData.action";

    //2、被服务学校信息
    String SEARCHSCHOOL_URL=PRODIP+"/baseinfo/searchSchool/getSchoolByParams.action";

    //3、特殊人群信息
    String SPECIALPERSON_URL=PRODIP+"/baseInfo/specialPersonManage/getSpecialPersons.action";

    //4、社会组织
    String NEWSOCIETYORGANIZATION_URL=PRODIP+"/baseinfo/newSocietyOrganizationsManage/getSocietyOrganizations.action";

    //5、平安单位
    String SAFETYUNIT_URL=PRODIP+"/baseinfo/safetyUnitManage/getSafetyUnits.action";




}
