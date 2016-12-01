package nettyhandlersflow;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by noodles on 16/8/25 上午3:24.
 */
@Slf4j
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1",8888);

        socket.getOutputStream().write("hello,world".getBytes());


        byte[] bytes = new byte[1020];
        socket.getInputStream().read(bytes);

        log.info(new String(bytes));

        Thread.sleep(1000000);
    }
}
