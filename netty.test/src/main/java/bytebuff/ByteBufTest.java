package bytebuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * Created by noodles on 16/9/13 上午11:28.
 */
public class ByteBufTest {

    public static void main(String[] args) {
        final ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeInt(1);

        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println();

        byteBuf.readByte();
        byteBuf.skipBytes(1);

        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println();

        byteBuf.discardReadBytes();

        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println();

    }
}
