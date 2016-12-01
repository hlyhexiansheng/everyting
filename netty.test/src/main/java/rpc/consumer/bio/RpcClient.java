package rpc.consumer.bio;

import rpc.common.RpcFuture;
import rpc.common.RpcRequest;
import rpc.common.RpcResponse;
import rpc.exception.RpcException;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by noodles on 16/9/8 上午1:03.
 */
public class RpcClient {

    private RequestSender sender;

    private BIOResponseReceiver2 receiver;

    public void init(){
        try {
            Socket socket = new Socket("127.0.0.1",8888);

            sender = new RequestSender(socket);
            sender.start();

            receiver = new BIOResponseReceiver2(socket);
            receiver.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RpcResponse send(RpcRequest request,int timeout) throws RpcException {
        RpcFuture future = new RpcFuture(timeout,null,request);
        this.sender.send(request);
        return (RpcResponse) future.get();
    }

}
