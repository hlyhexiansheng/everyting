package rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by noodles on 16/9/6 下午3:59.
 */
@Data
public class RpcRequest implements Serializable {

    private long requestId;

    private byte[] data;

}
