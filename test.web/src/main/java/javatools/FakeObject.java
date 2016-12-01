package javatools;

import lombok.Data;

/**
 * Created by noodles on 16/9/7 上午10:00.
 */
@Data
public class FakeObject {
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private String data5;
    private String data6;
    private String data7;
    private String data8;
    private String data9;
    private String data10;
    private String data11;
    private byte[] bytes;
    public FakeObject(){
        this.bytes = new byte[1024*1024*100];
    }
}
