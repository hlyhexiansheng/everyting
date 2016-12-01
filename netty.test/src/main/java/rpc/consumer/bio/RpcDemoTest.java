package rpc.consumer.bio;

import lombok.extern.slf4j.Slf4j;
import rpc.common.RpcRequest;
import rpc.common.RpcResponse;
import rpc.exception.RpcException;

/**
 * Created by noodles on 16/9/8 上午1:09.
 */
@Slf4j
public class RpcDemoTest {

    public static void main(String[] args) throws InterruptedException {
        final RpcClient client = new RpcClient();
        client.init();

        RpcRequest request = new RpcRequest();
        request.setData("hello".getBytes());

        try {
            for(int i = 0; i < 1000;i++){
                final RpcResponse response = client.send(request,200000);
                log.info("requestId={},data={}",response.getRequestId(),new String(response.getData()));
            }

        } catch (RpcException e) {
            e.printStackTrace();
        }


    }
}
