package serialzable;

import java.io.*;
import java.util.Arrays;

/**
 * Created by noodles on 16/10/24 下午11:26.
 */
public class ObjectInputOutputTest {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final byte[] bytes = testEncode();

        testDecode(bytes);
    }

    private static void testDecode(byte[] bytes) throws IOException, ClassNotFoundException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        final ObjectInput input = createInput(byteArrayInputStream);
        final String readUTF = input.readUTF();
        final String readUTF1 = input.readUTF();
        System.out.println(readUTF);

        final Object object = input.readObject();
        final Object object1 = input.readObject();

        System.out.println(object);
        System.out.println(object1);
    }

    private static byte[] testEncode() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutput output = createOutput(outputStream);
        output.writeUTF("fuck");
        output.writeUTF("shit");

        ModelBean bean = new ModelBean();
        bean.a = 100;
        bean.b = "asdfafsd";

        ModelBean2 bean2 = new ModelBean2();
        bean2.data = new String[]{"11111","33333"};

        output.writeObject(bean);
        output.writeObject(bean2);
        output.flush();
        byte[] body = outputStream.toByteArray();
        return body;
    }

    public static ObjectOutput createOutput(OutputStream outputStream) {
        try {
            return new ObjectOutputStream(outputStream);
        } catch (Exception e) {
            return null;
        }
    }

    public static ObjectInput createInput(InputStream in) {
        try {
            return new ObjectInputStream(in);
        } catch (Exception e) {
            return null;
        }
    }
}
class ModelBean implements Serializable{
    public int a;
    public String b;

    @Override
    public String toString() {
        return "ModelBean{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }
}
class ModelBean2 implements Serializable{
    public String[] data;

    @Override
    public String toString() {
        return "ModelBean2{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}

