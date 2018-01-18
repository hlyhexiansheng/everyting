package direbuf;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by noodles on 2017/11/17 15:41.
 */
public class DirectBufClient {


    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(true);
        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), 8888);
        channel.connect(isa);
//        final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        final long startTime = System.currentTimeMillis();
        for (long i = 0; i < 1024 * 1024 * 10 ; i++) {
            byteBuffer.put(("aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaaaaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaaaaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-aaaaaaaa-" + i).getBytes());
            byteBuffer.flip();
            channel.write(byteBuffer);
            byteBuffer.clear();
        }
        System.out.println("cost:" + (System.currentTimeMillis() - startTime));

    }
}
