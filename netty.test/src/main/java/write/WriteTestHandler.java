package write;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by noodles on 16/8/29 下午5:59.
 */
public class WriteTestHandler extends ChannelInboundHandlerAdapter{

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for(int i = 0; i < 1; i++){
            executorService.submit(new WriteCommand(ctx));
        }

    }
}

class WriteCommand implements Runnable{

    private ChannelHandlerContext ctx;

    public WriteCommand(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }

    public void run() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeCharSequence("1234", Charset.forName("utf-8"));
        ctx.channel().writeAndFlush(byteBuf);
        System.out.println(Thread.currentThread().getName());
    }
}
