package nettyhandlersflow;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by noodles on 16/8/25 上午3:14.
 */
@Slf4j
public class InAndOutHandler extends ChannelDuplexHandler {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("[{}]",this.getClass().getSimpleName() + "-channelActive()");
        super.channelActive(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("[{}]",this.getClass().getSimpleName() + "-write()");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("[{}]",this.getClass().getSimpleName() + "-channelRead()");

        ByteBuf byteBuf = (ByteBuf)msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        log.info(new String(bytes));

        ctx.writeAndFlush(((ByteBuf)msg).writeBytes("Respose Message".getBytes()));
        ctx.channel().writeAndFlush(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("[{}]",this.getClass().getSimpleName() + "-channelReadComplete()");
    }
}
