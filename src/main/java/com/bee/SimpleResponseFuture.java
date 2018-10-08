package com.bee;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleResponseFuture implements ResponseFuture {

    private static final Map<Long,SimpleResponseFuture> FUTURE_MAP = new ConcurrentHashMap<>();
    private long requestId;
    private ReentrantLock lock = new ReentrantLock();
    private Condition done = lock.newCondition();
    private SessionLayerMsg response;

    public SimpleResponseFuture(long requestId) {
        FUTURE_MAP.put(requestId,this);
    }

    @Override
    public SessionLayerMsg get() {
        if(null == response){
            lock.lock();
            try {
                done.await();
                return response;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        return null;
    }

    @Override
    public SessionLayerMsg get(long timeoutMills) {
        return null;
    }

    public static void handleReceived(SessionLayerMsg sessionLayerMsg) {
        SimpleResponseFuture future = FUTURE_MAP.remove(sessionLayerMsg.getRequestId());
        future.doReceived(sessionLayerMsg);
    }

    private void doReceived(SessionLayerMsg sessionLayerMsg) {
        lock.lock();
        try {
            long id = sessionLayerMsg.getRequestId();
            if(requestId == id) {
                response = sessionLayerMsg;
                done.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
