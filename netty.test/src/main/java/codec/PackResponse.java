package codec;

import lombok.Data;

/**
 * Created by noodles on 16/8/29 上午11:27.
 */
@Data
public class PackResponse {
    public short cmd;
    public String data;
}
