package codec;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * Created by noodles on 16/8/26 上午10:40.
 */
@Slf4j
public class CodecClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1",8888);



        Scanner scanner = new Scanner(System.in);

        while (true){

            final String s = scanner.nextLine();
            System.out.println(s);
            final byte[] pack = wrapPack(s.getBytes(), (short) 1);

            socket.getOutputStream().write(mutileIt(pack,3));
            byte[] bytes = new byte[1020];
            Thread.sleep(100);
            socket.getInputStream().read(bytes);

            log.info(new String(bytes));
        }

    }

    public static byte[] wrapPack(byte[] data,short cmd){
        int packageLength = data.length + 4 + 2;
        ByteBuffer byteBuffer = ByteBuffer.allocate(packageLength);
        byteBuffer.putInt(packageLength);
        byteBuffer.putShort(cmd);
        byteBuffer.put(data);
        return byteBuffer.array();
    }

    /*翻倍*/
    public static byte[] mutileIt(byte[] bytes,int times){
        byte[] result = new byte[bytes.length * times];
        for(int i = 0; i < times;i++){
            for(int j = 0; j < bytes.length; j++){
                int index = (i * bytes.length) + j;
                result[index] = bytes[j];
            }
        }
        return result;
    }
}
