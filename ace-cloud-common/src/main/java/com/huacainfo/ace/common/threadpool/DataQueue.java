package com.huacainfo.ace.common.threadpool;

import java.util.ArrayList;
import java.util.List;

public class DataQueue {
    public final static int PRIORITY_HIGH = 3;
    public final static int PRIORITY_NORMAL = 2;
    public final static int PRIORITY_LOW = 1;

    private List<Object> procDataList = new ArrayList<Object>();

    private List<Object> dataList3 = null;
    private List<Object> dataList2 = null;
    private List<Object> dataList1 = null;

    public DataQueue() {
        dataList3 = new ArrayList<Object>();
        dataList2 = new ArrayList<Object>();
        dataList1 = new ArrayList<Object>();
    }

    /**
     * @param obj
     */
    public synchronized void put(Object obj) {
        put(obj, PRIORITY_NORMAL);
    }

    /**
     * 添加数据
     *
     * @param obj
     * @param priority
     */
    public synchronized void put(Object obj, int priority) {
        if (priority == 3) {
            dataList3.add(obj);
        } else if (priority == 2) {
            dataList2.add(obj);
        } else if (priority == 1) {
            dataList1.add(obj);
        } else {
            dataList2.add(obj);
        }
        this.notifyAll();
    }

    /**
     * 取数据
     *
     * @return
     */
    public synchronized Object pop() {
        Object obj = null;
        while (dataList3.isEmpty() && dataList2.isEmpty() && dataList1.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        if (dataList3.size() > 0) {
            obj = dataList3.remove(0);
        } else if (dataList2.size() > 0) {
            obj = dataList2.remove(0);
        } else {
            obj = dataList1.remove(0);
        }
        procDataList.add(obj);
        return obj;
    }

    public void remove(Object obj) {
        synchronized (procDataList) {
            procDataList.remove(obj);
        }
    }

    /**
     * 大小
     *
     * @return
     */
    public int size() {
        return dataList3.size() + dataList2.size() + dataList1.size();
    }

    public int procSize() {
        return procDataList.size();
    }

    /**
     * 是否已存在
     *
     * @param obj
     * @return
     */
    public boolean contains(Object obj) {
        if (dataList3.contains(obj)) {
            return true;
        } else if (dataList2.contains(obj)) {
            return true;
        } else if (dataList1.contains(obj)) {
            return true;
        } else if (procDataList.contains(obj)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */
    public String toString() {
        return dataList3.toString() + dataList2.toString() + dataList1.toString();
    }
}
