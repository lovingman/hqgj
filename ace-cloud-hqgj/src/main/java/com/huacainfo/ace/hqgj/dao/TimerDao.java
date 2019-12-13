package com.huacainfo.ace.hqgj.dao;


import java.util.List;

public interface TimerDao {

   List<String> findEndId();

   int batchUpdate(List ids);
}
