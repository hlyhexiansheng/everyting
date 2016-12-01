package codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by noodles on 16/8/25 下午6:16.
 */
@Slf4j
public class PacketEncoder extends MessageToByteEncoder<PackResponse>{

    @Override
    protected void encode(ChannelHandlerContext ctx, PackResponse msg, ByteBuf out) throws Exception {
        final String jsonString = JSON.toJSONString(msg);
        out.writeBytes(jsonString.getBytes());
        log.info(jsonString);
    }
}
