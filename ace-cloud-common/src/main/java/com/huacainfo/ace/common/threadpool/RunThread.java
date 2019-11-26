package com.huacainfo.ace.common.threadpool;

import org.apache.log4j.Logger;

import java.util.Date;

public class RunThread extends Thread {
    private static Logger log = Logger.getLogger(RunThread.class);
    private boolean busy = true;
    private Date beginTime = null;
    private int threadNo = 0;
    private boolean running = true;
    private long callNum = 0;
    private ThreadPool threadPool = null;
    private ThreadProcess processClass = null;
    private Object data = null;

    public RunThread(int threadNo, ThreadPool threadPool, ThreadProcess processClass) {
        this.threadNo = threadNo;
        this.threadPool = threadPool;
        this.processClass = processClass;
    }

    public boolean isBusy() {
        return this.busy;
    }

    public void close() {
        this.running = false;
    }

    public long runTime() {
        if (!busy) return -1;
        return System.currentTimeMillis() - beginTime.getTime();
    }

    public void run() {
        log.debug(processClass.getClass().getName() + "  " + this.threadNo + " is start...");
        while (running) {
            try {
                this.busy = false;
                while (threadPool.dataQueue.size() == 0) {
                    if (this.threadNo >= threadPool.getMinSize() &&
                            threadPool.getThreadList().size() > threadPool.getMinSize()) {
                        threadPool.removeThread(this);
                        running = false;
                    }
                    if (!running) break;
                    synchronized (threadPool.dataQueue) {
                        threadPool.dataQueue.wait();
                    }
                    continue;
                }
                if (!running) break;
                data = threadPool.dataQueue.pop();
                threadPool.addThread();
                this.beginTime = new Date();
                this.busy = true;
                this.processClass.execute(this, data);
                threadPool.dataQueue.remove(data);
                this.callNum++;
            } catch (Exception e) {
                e.printStackTrace();
                log.error(this.threadNo + "  [data]" + data + " exception " + e);
            }
        }
        log.debug(processClass.getClass().getName() + "  " + this.threadNo + " is stop.......");
    }

    public int getThreadNo() {
        return threadNo;
    }

    public Object getData() {
        return data;
    }

    public long getCallNum() {
        return callNum;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

}
