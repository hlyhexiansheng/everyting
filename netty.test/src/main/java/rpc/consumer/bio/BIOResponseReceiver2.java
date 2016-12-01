package rpc.consumer.bio;

import lombok.extern.slf4j.Slf4j;
import rpc.common.RpcResponse;
import rpc.common.RpcResultHolder;

import java.io.BufferedInputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by noodles on 16/9/8 上午12:57.
 * BIO阻塞模式的接受者.(处理粘包.)
 */
@Slf4j
public class BIOResponseReceiver2 extends Thread {

    private Socket channel;

    public BIOResponseReceiver2(Socket socket) {
        this.channel = socket;
        this.setName("BIOResponseReceiver2");
    }

    @Override
    public void run() {
        try {
            BufferedInputStream in = new BufferedInputStream(channel.getInputStream());
            for ( ; ; ) {
                byte[] header = new byte[4];

                in.mark(0);
                final int read = in.read(header);
                if(read < 4){
                    in.reset();
                    continue;
                }

                final int bodyLength = byteArrayToInt(header);

                byte[] body = new byte[bodyLength];
                int l = in.read(body);

                if( l < bodyLength ){
                    in.reset();
                    continue;
                }

                final RpcResponse rpcResponse = decodePack(body);
                RpcResultHolder.receive(rpcResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static int byteArrayToInt(byte[] b)
    {
        return   b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a)
    {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static RpcResponse decodePack(byte[] data) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
        byteBuffer.put(data, 0, data.length);

        byteBuffer.flip();

        RpcResponse response = new RpcResponse();

        response.setRequestId(byteBuffer.getLong());

        response.setData(new byte[data.length - 8]);

        byteBuffer.get(response.getData());

        return response;
    }

}
