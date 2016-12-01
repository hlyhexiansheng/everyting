package jsondecoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;

/**
 * Created by noodles on 16/8/25 下午5:48.
 */
public class JsonCodecServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup ioGroup = new NioEventLoopGroup(2);

        final EventLoopGroup workGroup = new NioEventLoopGroup(3);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,ioGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY,Boolean.TRUE)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new JsonObjectDecoder());
                        p.addLast(workGroup,new MessageHandler());
                    }
                });

        bootstrap.bind(8888).sync();

    }
}
