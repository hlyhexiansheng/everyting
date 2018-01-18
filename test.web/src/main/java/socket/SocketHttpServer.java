package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

/**
 * Created by noodles on 16/9/12 下午11:25.
 */
public class SocketHttpServer {

    public static void main(String[] args) throws Exception {

        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);


        while (true) {

            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            byte[] bytes = new byte[100000];
            byte b;
            int i = 0;
            while (true) {
                b = (byte) clientSocket.getInputStream().read();
                if (b == -1) {
                    break;
                }
                bytes[i] = b;
                i++;
            }
            final String s = new String(bytes, 0, i);

            System.out.println("origin--" + s);
            System.out.println(URLDecoder.decode(s, "utf-8"));

            out.write("HTTP/1.0 200 OK\r\n");
            out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
            out.write("Server: Apache/0.8.4\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write("Content-Length: 59\r\n");
            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
            out.write("\r\n");
            out.write("<TITLE>Exemple</TITLE>");
            out.write("<P>Ceci est une page d'exemple.</P>");

            System.err.println("Connexion avec le client terminée");
            out.close();
            in.close();
            clientSocket.close();
        }
    }
}
