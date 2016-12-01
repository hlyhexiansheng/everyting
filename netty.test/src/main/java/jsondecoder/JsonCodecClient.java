package jsondecoder;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by noodles on 16/8/26 上午10:40.
 */
@Slf4j
public class JsonCodecClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1",8888);



        Scanner scanner = new Scanner(System.in);

        while (true){

            final String s = scanner.nextLine();

            JsonObj jsonObj = new JsonObj(s);

            socket.getOutputStream().write(JSON.toJSONString(jsonObj).getBytes());
            byte[] bytes = new byte[1020];

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

@Data
class JsonObj{
    private int id;
    private String name;
    private String pwd;
    public JsonObj(){}
    public JsonObj(String name){
        this.id = new Random().nextInt();
        this.name = name;
        this.pwd = name + ":" + id;
    }
}
