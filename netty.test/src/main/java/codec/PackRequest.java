package codec;

import lombok.Data;

/**
 * Created by noodles on 16/8/25 下午5:58.
 */
@Data
public class PackRequest {

    public int length;

    public short cmd;

    public byte[] bytes;
}
