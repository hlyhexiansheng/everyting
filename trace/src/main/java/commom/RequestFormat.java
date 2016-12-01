package commom;

/**
 * Created by noodles on 16/11/2 上午10:17.
 */
public class RequestFormat {

    public static String format(Request request){
        return String.format("tranceId=%s,spanId=%s,parentSpanId=%s",request.traceId,request.spanId,request.parentSpanId);
    }

}
