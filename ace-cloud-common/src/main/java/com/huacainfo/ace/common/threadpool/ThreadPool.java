package com.huacainfo.ace.common.threadpool;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool extends Thread {
    public DataQueue dataQueue = new DataQueue();
    private Logger log = Logger.getLogger(ThreadPool.class);
    private ThreadProcess processClass = null;
    private boolean running = true;
    private int minSize = 0;//初始化数
    private int maxSize = 0;//最大数
    private long stuckMaxTime = 0;
    private List threadList = new ArrayList();

    public ThreadPool(ThreadProcess processClass, int minSize, int maxSize, long stuckMaxTime) {
        this.processClass = processClass;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.stuckMaxTime = stuckMaxTime;
        for (int i = 0; i < minSize; i++) {
            try {
                RunThread runThread = new RunThread(threadList.size(), this,
                        processClass);
                runThread.start();
                threadList.add(runThread);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭所有打的认证线程 description
     */
    public void close() {
        this.running = true;
        for (int i = 0; i < threadList.size(); i++) {
            RunThread rt = (RunThread) threadList.get(i);
            rt.close();
        }
    }

    /**
     * @return
     */
    public List getTimoutThreads() {
        List list = new ArrayList();
        if (stuckMaxTime > 0) {
            long tTime = System.currentTimeMillis();
            for (int i = 0; i < threadList.size(); i++) {
                RunThread rt = (RunThread) threadList.get(i);
                if (rt.runTime() >= stuckMaxTime) {
                    list.add(rt);
                }
            }
        }
        return list;
    }

    /**
     * 线程状态测试启动
     */
    public void run() {
        while (running) {
            try {
                this.sleep(60 * 1000);
                if (stuckMaxTime > 0) {
                    long tTime = System.currentTimeMillis();
                    for (int i = 0; i < threadList.size(); i++) {
                        RunThread rt = (RunThread) threadList.get(i);
                        if (rt.runTime() >= stuckMaxTime) {
                            this.processClass.timeOut(rt);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public long getStuckMaxTime() {
        return stuckMaxTime;
    }

    public List getThreadList() {
        return threadList;
    }

    /**
     * 向池中添加数据
     *
     * @param obj
     * @return
     */
    public boolean addData(Object obj) {
        dataQueue.put(obj);
        return true;
    }

    /**
     * 待处理数据长度
     *
     * @return
     */
    public int dataSize() {
        return dataQueue.size();
    }

    /**
     * 添加一个线程
     */
    public synchronized void addThread() {
        int num = this.dataSize() / 3;
        int aa = this.dataSize() % 3;
        if (aa > 0) num++;
        num = num - threadList.size();
        for (int i = 0; i < num && threadList.size() < maxSize; i++) {
            try {
                RunThread runThread = new RunThread(threadList.size(), this,
                        processClass);
                runThread.start();
                threadList.add(runThread);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从池中移处一个线程
     *
     * @param t
     */
    public synchronized void removeThread(RunThread t) {
        t.close();
        threadList.remove(t);
    }
}
