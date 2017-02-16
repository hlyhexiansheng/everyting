package simplehttpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by noodles on 17/1/10 下午5:29.
 */
public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            try {

                final URI requestURI = t.getRequestURI();
                final String query = requestURI.getQuery();

                Map m = new HashMap();

                java.lang.String response;

                for (int i = 0; i < 1000; i++) {
                    m.put(i+"", "中文");
                }
                response = com.alibaba.fastjson.JSON.toJSONString(m);
                t.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}
