package rpc.consumer.bio;

import lombok.extern.slf4j.Slf4j;
import rpc.common.RpcRequest;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by noodles on 16/9/8 上午12:33.
 */
@Slf4j
public class RequestSender extends Thread {

    private Socket channel;

    private BlockingQueue<RpcRequest> queue = new LinkedBlockingDeque<RpcRequest>(1024);

    public RequestSender(Socket socket) {
        this.channel = socket;
        this.setName("RequestSender");
    }

    @Override
    public void run() {
        for (; ; ) {
            RpcRequest request = null;
            try {
                request = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final byte[] bytes = encodePack(request);
            try {
                this.channel.getOutputStream().write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean send(RpcRequest request) {
        return queue.offer(request);
    }

    public static byte[] encodePack(RpcRequest request) {
        int packageLength = request.getData().length + 4 + 8;
        ByteBuffer byteBuffer = ByteBuffer.allocate(packageLength);
        byteBuffer.putInt(packageLength);
        byteBuffer.putLong(request.getRequestId());
        byteBuffer.put(request.getData());
        return byteBuffer.array();
    }


}
