package rpc.common;

import rpc.exception.RpcException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by noodles on 16/9/8 上午12:37.
 */
public class RpcFuture {

    private int timeOut;

    private ResponseCallBack callBack;

    private final Lock lock = new ReentrantLock();

    private final Condition done = lock.newCondition();

    private static final AtomicLong REQUEST_ID = new AtomicLong(0);

    private static final int DEFAULT_TIMEOUT = 1000;

    public Object result;

    public RpcFuture(int timeOut,ResponseCallBack callBack,RpcRequest request){
        this.timeOut = timeOut <= 0 ? DEFAULT_TIMEOUT : timeOut;
        this.callBack = callBack;
        request.setRequestId(nextRequestId());
        RpcResultHolder.addRpcResult(request.getRequestId(),this);
    }

    public Object get() throws RpcException {
        return get(timeOut);
    }

    public Object get(int timeOut) throws RpcException {
        this.timeOut = timeOut <= 0 ? DEFAULT_TIMEOUT : timeOut;
        if (!isDone()) {
            long start = System.currentTimeMillis();
            lock.lock();
            try {
                while (! isDone()){
                    done.await(timeOut, TimeUnit.MILLISECONDS);
                    if (isDone() || System.currentTimeMillis() - start > timeOut) {
                        break;
                    }
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        }
        if(!isDone()){
            throw new RpcException("request timeout, response is Null.");
        }
        return result;
    }

    public void doReceive(RpcResponse response){

        lock.lock();
        try {
            this.result = response;
            if (done != null) {
                done.signal();
            }
        } finally {
            lock.unlock();
        }

        if (callBack != null) {
            invokeCallback(callBack);
        }

    }

    private void invokeCallback(ResponseCallBack callBack) {

    }

    private static long nextRequestId(){
        return REQUEST_ID.getAndIncrement();
    }

    public boolean isDone() {
        return result != null;
    }
}
