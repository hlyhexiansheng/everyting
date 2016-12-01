package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Administrator on 2015/12/5.
 */
public class ServerSk {


    static OutputStream outputStream;

    static InputStream inputStream;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();

        while (true) {
            int data = -1;
            try {
                data = inputStream.read();
            }catch (IOException ex){
                System.out.println("lost connection.....");
            }
            if (data != -1) {
                System.out.println("get a command" + data);
                try{
                    outputStream.write(data + 1);
                }catch (SocketException e){
                    System.out.println("writeFail...");
                }
                Thread.sleep(2000);
            }
        }
    }

    public static void loop(){

    }
}
