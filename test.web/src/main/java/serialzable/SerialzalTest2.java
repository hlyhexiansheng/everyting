package serialzable;

import java.io.*;

/**
 * Created by noodles on 2017/11/14 16:24.
 */
public class SerialzalTest2 {

    public static void main(String[] args) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/tmp/a.obj"));
        TE t = new TE("1");
        out.writeObject(t);
        out.flush();
        out.close();
    }
}

class TE implements Serializable {
    public String aaaaaaaaaaa;

    TE(String a) {
        this.aaaaaaaaaaa = a;
    }
}
