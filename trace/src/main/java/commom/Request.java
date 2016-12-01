package commom;

/**
 * Created by noodles on 16/11/2 上午9:55.
 */
public class Request {
    public String traceId;
    public String parentSpanId;
    public String spanId;

    public Request copy(){
        Request request = new Request();
        request.traceId = this.traceId;
        request.parentSpanId = this.parentSpanId;
        request.spanId = this.spanId;
        return request;
    }
}
