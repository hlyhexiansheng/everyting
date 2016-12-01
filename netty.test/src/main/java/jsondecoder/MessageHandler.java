package jsondecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by noodles on 16/8/26 上午10:24.
 */
@Slf4j
public class MessageHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        final ByteBuf json = (ByteBuf) msg;
        byte[] bytes = new byte[json.readableBytes()];
        json.readBytes(bytes);

        System.out.println(new String(bytes));

        System.out.println(json.writerIndex());
        json.discardReadBytes();
        json.writeBytes(bytes);
        System.out.println(json.writerIndex());

        ctx.writeAndFlush(json);
    }

}
