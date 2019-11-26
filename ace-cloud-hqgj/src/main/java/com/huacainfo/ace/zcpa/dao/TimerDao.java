package com.huacainfo.ace.zcpa.dao;


import java.util.List;

public interface TimerDao {

   List findEndProjectId();

   int batchUpdate(List ids);
}
