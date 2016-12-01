package hession.hessianserialize;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import hession.service.HessianObject;

import java.io.*;

/**
 * Created by Administrator on 2015/12/14.
 */
public class HessianSerializeTest {

    public static void main(String[] args) throws IOException {

//        test1();

        test2();

    }

    private static void test2() throws IOException {

        HessianObject object = new HessianObject();
        object.setId(1000);
        object.setName("123");
        object.setPassword("123456");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);

        ho.writeObject(object);

        final byte[] bytes = os.toByteArray();



        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        HessianInput hi = new HessianInput(is);
        HessianObject object1 = (HessianObject) hi.readObject(HessianObject.class);

        System.out.println(object1);
    }

    private static void test1() throws IOException {
        HessianObject object = new HessianObject();
        object.setId(1);
        StringBuffer s = new StringBuffer();

        for(int i = 0; i < 100000;i++){
            s.append(i);
        }
        object.setPassword(s.toString());
        object.setName(s.reverse().toString());

        OutputStream os = new FileOutputStream("/Users/noodles/hessian.obj");
        Hessian2Output out = new Hessian2Output(os);

        out.writeObject(object);
        out.flush();
        os.close();

        InputStream is = new FileInputStream("/Users/noodles/hessian.obj");
        Hessian2Input in = new Hessian2Input(is);

        Object obj = in.readObject(HessianObject.class);
        is.close();
        System.out.println(obj);
    }
}
