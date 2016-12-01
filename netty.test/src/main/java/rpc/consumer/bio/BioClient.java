package rpc.consumer.bio;

import lombok.extern.slf4j.Slf4j;
import rpc.common.RpcResponse;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by noodles on 16/9/6 下午4:23.
 */
@Slf4j
public class BioClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1",8888);

        while (true){

            final byte[] requestBytes = encodePack("hello,world".getBytes());
            socket.getOutputStream().write(requestBytes);

            byte[] bytes = new byte[1020];
            final int read = socket.getInputStream().read(bytes);
            log.info("{},{}",read,requestBytes.length);
            final RpcResponse rpcResponse = decodePack(bytes, read);

            Thread.sleep(5000);
        }
    }

    public static byte[] encodePack(byte[] data){
        int packageLength = data.length + 4 + 8;
        ByteBuffer byteBuffer = ByteBuffer.allocate(packageLength);
        byteBuffer.putInt(packageLength);
        byteBuffer.putLong(System.currentTimeMillis());
        byteBuffer.put(data);
        return byteBuffer.array();
    }

    public static RpcResponse decodePack(byte[] data,int length){

        ByteBuffer byteBuffer = ByteBuffer.allocate(length);
        byteBuffer.put(data,0,length);

        byteBuffer.flip();

        RpcResponse response = new RpcResponse();

        log.info("pos:{},limit:{}",byteBuffer.position(),byteBuffer.limit());
        response.setRequestId(byteBuffer.getLong());

        log.info("pos:{},limit:{}",byteBuffer.position(),byteBuffer.limit());
        response.setData(new byte[length - 8]);

        byteBuffer.get(response.getData());

        log.info("requestId={},data={}",response.getRequestId(),new String(response.getData()));
        return response;
    }

}
