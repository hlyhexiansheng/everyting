package rpc.consumer.bio;

import lombok.extern.slf4j.Slf4j;
import rpc.common.RpcResponse;
import rpc.common.RpcResultHolder;

import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by noodles on 16/9/8 上午12:57.
 * BIO阻塞模式的接受者.(没有粘包处理版本.)
 */
@Slf4j
public class BIOResponseReceiver extends Thread {

    private Socket channel;

    public BIOResponseReceiver(Socket socket) {
        this.channel = socket;
        this.setName("BIOResponseReceiver");
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                byte[] bytes = new byte[1020];
                final int read = channel.getInputStream().read(bytes);
                final RpcResponse rpcResponse = decodePack(bytes, read);
                log.info("BIOResponseReceiver 收到消息.requestId={}",rpcResponse.getRequestId());
                RpcResultHolder.receive(rpcResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static RpcResponse decodePack(byte[] data, int length) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(length);
        byteBuffer.put(data, 0, length);

        byteBuffer.flip();

        RpcResponse response = new RpcResponse();


        response.setRequestId(byteBuffer.getLong());

        response.setData(new byte[length - 8]);

        byteBuffer.get(response.getData());

        return response;
    }

}
