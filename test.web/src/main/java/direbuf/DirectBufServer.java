package direbuf;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by noodles on 2017/11/17 15:36.
 */
public class DirectBufServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        final Socket accept = serverSocket.accept();
        final InputStream inputStream = accept.getInputStream();
        long count = 0;
        while (true) {
            final byte[] bytes = new byte[1024 * 1024];
            final int read = inputStream.read(bytes);
            if (read > 0) {
                count += read;
            } else {
                System.out.println("count=" + count);
                break;
            }
        }
    }

}
