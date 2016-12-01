package service;

import commom.Request;
import commom.RequestFormat;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by noodles on 16/11/2 上午10:09.
 */
@Slf4j
public class RemoteUserService {
    public void existUser(Request request) {
        request = request.copy();

        request.parentSpanId = request.spanId;
        request.spanId = String.valueOf(new Random().nextLong());

        log.info(RequestFormat.format(request));
    }
}
