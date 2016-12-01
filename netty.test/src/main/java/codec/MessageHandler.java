package codec;

import io.netty.channel.ChannelFuture;
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
        byte[] data = ((PackRequest)msg).bytes;
        String content = new String(data);

        log.info(content);

        PackResponse response = new PackResponse();
        response.setCmd(((PackRequest) msg).cmd);
        response.setData("resp:"+content);

        final ChannelFuture channelFuture = ctx.writeAndFlush(response);


    }


}
