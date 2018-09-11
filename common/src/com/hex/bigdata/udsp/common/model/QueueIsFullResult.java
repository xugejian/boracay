package com.hex.bigdata.udsp.common.model;

import java.io.Serializable;

public class QueueIsFullResult implements Serializable {
    private boolean waitQueueIsFull = false; // 等待队列是否满了，满了返回true，没满返回false
    private String waitQueueTaskId; // 等待队列中的任务id

    public boolean isWaitQueueIsFull() {
        return waitQueueIsFull;
    }

    public void setWaitQueueIsFull(boolean waitQueueIsFull) {
        this.waitQueueIsFull = waitQueueIsFull;
    }

    public String getWaitQueueTaskId() {
        return waitQueueTaskId;
    }

    public void setWaitQueueTaskId(String waitQueueTaskId) {
        this.waitQueueTaskId = waitQueueTaskId;
    }
}
