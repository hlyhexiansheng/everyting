package common;

import javassist.CtClass;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by noodles on 16/11/4 上午11:41.
 */
public class ApmUtil {

    public static void log(String className, String methodName, long elapsedTime) {
        System.out.println(String.format("[agent log] %s.%s,elapsedTime=[%s]",className,methodName,elapsedTime));
    }

    public static void writeClass2File(CtClass ctClass){
        try {
            File file = new File("/Users/noodles/Desktop/" + ctClass.getName() + ".class");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(ctClass.toBytecode());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
