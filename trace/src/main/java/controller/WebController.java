package controller;

import commom.Request;
import commom.RequestFormat;
import lombok.extern.slf4j.Slf4j;
import service.RemoteAccountService;
import service.RemoteOrderService;

import java.util.Random;

/**
 * Created by noodles on 16/11/2 上午9:53.
 */
@Slf4j
public class WebController {

    private RemoteAccountService accountService = new RemoteAccountService();

    private RemoteOrderService orderService = new RemoteOrderService();

    public void requestUrl(){
        Request request = new Request();
        request.traceId = String.valueOf(new Random().nextLong());
        request.spanId = String.valueOf(new Random().nextLong());
        request.parentSpanId = "0";

        log.info(RequestFormat.format(request));

        accountService.queryAccount(request);

        orderService.newOrder(request);
    }

}
