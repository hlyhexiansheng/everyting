package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by noodles on 16/8/25 下午5:51.
 */
@Slf4j
public class PacketDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        log.info("readbleBytes={}",in.readableBytes());

        if (in.readableBytes() > 4) {
            in.markReaderIndex();
            int length = in.readInt();
            if (in.readableBytes() + 4 < length) {
                in.resetReaderIndex();
            } else {
                PackRequest request = new PackRequest();
                request.length = length;
                request.cmd = in.readShort();
                request.bytes = new byte[length - 4 - 2];
                in.readBytes(request.bytes);

                out.add(request);

                log.info(request.toString());
            }
        }
        log.info("readbleBytes={}",in.readableBytes());

    }
}
