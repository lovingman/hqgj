package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.ResponseDTO;

public interface SynchronizationService {

     ResponseDTO syncSchoolData(String srartTime);

     ResponseDTO syncPersonData(String srartTime);

     ResponseDTO syncSocietyOrganizationData(String srartTime);

     ResponseDTO syncUserData(String srartTime);

     ResponseDTO syncUnitData(String srartTime);
}
