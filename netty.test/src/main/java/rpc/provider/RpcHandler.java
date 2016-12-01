package rpc.provider;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import rpc.common.RpcRequest;
import rpc.common.RpcResponse;

/**
 * Created by noodles on 16/9/6 下午4:16.
 */
@Slf4j
@ChannelHandler.Sharable
public class RpcHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        RpcRequest request = (RpcRequest)msg;

        log.info("requsetId={},data={}",request.getRequestId(),new String(request.getData()));

        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        response.setData((new String(request.getData()) + new String(request.getData())).getBytes());
        ctx.writeAndFlush(response);

    }
}
