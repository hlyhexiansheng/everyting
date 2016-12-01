package rpc.common;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by noodles on 16/9/8 上午12:38.
 */
public class RpcResultHolder {

    private final static ConcurrentHashMap<Long,RpcFuture> MAP = new ConcurrentHashMap<Long, RpcFuture>();

    public static void addRpcResult(Long requestId,RpcFuture result){
        MAP.put(requestId,result);
    }

    public static void receive(RpcResponse response){
        final RpcFuture rpcFuture = MAP.remove(response.getRequestId());
        rpcFuture.doReceive(response);
    }


}
