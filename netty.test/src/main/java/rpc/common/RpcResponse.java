package rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by noodles on 16/9/6 下午4:10.
 */
@Data
public class RpcResponse implements Serializable{

    private long requestId;

    private byte[] data;

}
