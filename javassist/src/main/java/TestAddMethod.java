import common.ApmUtil;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Created by noodles on 16/12/4 下午4:59.
 */
public class TestAddMethod {

    public static void main(String[] args)  {
        try {
            final CtClass ctClass = ClassPool.getDefault().get("TestClass");

            CtMethod ctMethod = CtMethod.make("    public void print(){\n" +
                    "        System.out.println(\"-----\");\n" +
                    "    }",ctClass);

            ctClass.addMethod(ctMethod);

            StringBuilder builder = new StringBuilder();

            builder.append("public void logMessage0(String fqcn, Level level, Marker marker, Message message, Throwable t) {}");

            CtMethod ctMethod1 = CtMethod.make(builder.toString(),ctClass);

            ctClass.addMethod(ctMethod1);


            ApmUtil.writeClass2File(ctClass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
