package rpc.consumer.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by noodles on 16/9/8 下午5:09.
 */
public class NioClient {



    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        SocketChannel channel = SocketChannel.open();

        channel.configureBlocking(false);

        channel.connect(new InetSocketAddress("localhost",8888));
    }

}
