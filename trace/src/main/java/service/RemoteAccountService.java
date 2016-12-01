package service;

import commom.Request;
import commom.RequestFormat;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by noodles on 16/11/2 上午9:58.
 */
@Slf4j
public class RemoteAccountService {


    private RemoteUserService userService = new RemoteUserService();

    public void queryAccount(Request request) {
        request = request.copy();
        request.parentSpanId = request.spanId;
        request.spanId = String.valueOf(new Random().nextLong());

        log.info(RequestFormat.format(request));

        userService.existUser(request);
    };
}
