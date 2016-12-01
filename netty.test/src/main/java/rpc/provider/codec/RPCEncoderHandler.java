package rpc.provider.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import rpc.common.RpcResponse;

/**
 * Created by noodles on 16/9/6 下午3:39.
 */
public class RPCEncoderHandler extends MessageToByteEncoder<RpcResponse> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcResponse msg, ByteBuf out) throws Exception {
        out.writeInt(8 + msg.getData().length);  //包体长度
        out.writeLong(msg.getRequestId());
        out.writeBytes(msg.getData());
    }
}
