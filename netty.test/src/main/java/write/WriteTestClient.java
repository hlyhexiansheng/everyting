package write;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by noodles on 16/8/29 下午5:55.
 */
@Slf4j
public class WriteTestClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("10.32.1.243",9501);

        while (true){
            byte[] bytes = new byte[100];
            final int read = socket.getInputStream().read(bytes);
            log.info(new String(Arrays.copyOf(bytes,read)));
        }

    }

}
