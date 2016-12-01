package echoserver;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

/**
 * Created by noodles on 16/8/24 下午1:28.
 */
@Slf4j
public class EchoClient {

    public static void main(String[] args) throws IOException, InterruptedException {


        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Socket socket = new Socket("115.231.216.158", 1805);

                        int leng = 1024;
                        byte[] bytes = new byte[leng];
                        for (int i = 0; i < leng; i++) {
                            bytes[i] = 1;
                        }
                        String word = "hello" + new Random().nextLong() + "yy";
                        for (; ; ) {
                            socket.getOutputStream().write(word.getBytes());
                            final int read = socket.getInputStream().read(new byte[20000]);
                            System.out.println(read == word.getBytes().length);
                            if (read < word.getBytes().length) {
                                System.out.println(".................." + read);
                            }
                        }
                    } catch (Exception e) {

                    }

                }
            }).start();
        }
    }
}
