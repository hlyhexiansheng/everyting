package rpc.provider.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import rpc.common.RpcRequest;

import java.util.List;

/**
 * Created by noodles on 16/9/6 下午3:39.
 */
@Slf4j
public class RPCDecoderHandler extends ByteToMessageDecoder{

    public static final int PACK_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() > PACK_LENGTH) {
            in.markReaderIndex();
            int length = in.readInt();
            if (in.readableBytes() + PACK_LENGTH < length) {
                in.resetReaderIndex();
            } else {

                RpcRequest request = new RpcRequest();
                request.setRequestId(in.readLong());
                request.setData(new byte[length - PACK_LENGTH - 8]);

                in.readBytes(request.getData());

                out.add(request);

            }
        }
    }

}
