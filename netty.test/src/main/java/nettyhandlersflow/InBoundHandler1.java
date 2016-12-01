package nettyhandlersflow;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by noodles on 16/8/25 上午3:13.
 */
@Slf4j
public class InBoundHandler1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("[{}]",this.getClass().getSimpleName() + "-channelActive()");
        super.channelActive(ctx);
    }
}
