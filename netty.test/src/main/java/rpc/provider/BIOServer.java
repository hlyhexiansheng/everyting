package rpc.provider;

import rpc.common.RpcResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by noodles on 16/9/8 下午1:52.
 * 测试
 */
public class BIOServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){
            final Socket socket = serverSocket.accept();

            socket.setTcpNoDelay(true);

            new Thread(new Runnable() {
                public void run() {
                    while (true){
                        try {
                            byte[] receiveData = new byte[1024];

                            final int read = socket.getInputStream().read(receiveData);
                            if(read == -1){
                                continue;
                            }

                            final RpcResponse response = decodePack(receiveData, read);

                            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);

                            final byte[] body = "abcdefghij".getBytes();

                            byteBuffer.putInt(body.length + 8);
                            byteBuffer.putLong(response.getRequestId());
                            byteBuffer.put(body);

                            byteBuffer.flip();
                            byte[] bytes = new byte[22];
                            byteBuffer.get(bytes);
                            for(int i = 0; i < bytes.length;i++){
                                socket.getOutputStream().write(new byte[]{bytes[i]});//故意模拟半包.
                                Thread.sleep(100);
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
        }




    }

    public static RpcResponse decodePack(byte[] data, int length) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(length);
        byteBuffer.put(data, 0, length);

        byteBuffer.flip();

        RpcResponse response = new RpcResponse();

        byteBuffer.getInt();//丢掉头...
        response.setRequestId(byteBuffer.getLong());

        response.setData(new byte[length - 8 - 4]);

        byteBuffer.get(response.getData());

        return response;
    }


}
