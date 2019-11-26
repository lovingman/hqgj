package com.huacainfo.ace.common.threadpool;


public interface ThreadProcess {
    /**
     * 业务处理回调方法
     *
     * @param rt
     * @param obj
     */
    public void execute(RunThread rt, Object obj);

    /**
     * 执行时间超过指定时间的回调方法
     *
     * @param rt
     */
    public void timeOut(RunThread rt);

}
