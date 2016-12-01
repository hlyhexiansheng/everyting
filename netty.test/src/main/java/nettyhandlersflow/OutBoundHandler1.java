package nettyhandlersflow;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by noodles on 16/8/25 上午3:14.
 */
@Slf4j
public class OutBoundHandler1 extends ChannelOutboundHandlerAdapter{

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("[{}]",this.getClass().getSimpleName() + "-write()");
        super.write(ctx,msg,promise);
    }
}
