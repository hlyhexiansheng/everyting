package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

class ActChannelInitializer extends ChannelInitializer<Channel> {
    ActChannelInitializer() {
    }

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        System.out.println("init channel");
        pipeline.addLast(new ChannelHandler[]{new SystemHandler()});
    }
}

