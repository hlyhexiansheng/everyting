package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2015/12/5.
 */
public class ClentSk {

    public static void main(String[] args ) throws IOException, InterruptedException {

        Socket socket = new Socket("127.1.0.1",8888);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        int count = 0;
        while(true){
            int availableDataLength = inputStream.available();
            if(availableDataLength % 10 == 0){
                for(int i = 0; i < availableDataLength;i++){
                    int data = inputStream.read();
                    if(data == -1){
                        break;
                    }
                    System.out.println(data);
                }
            }
            outputStream.write(1000 + count++);
            Thread.sleep(2000);
        }
    }
}
