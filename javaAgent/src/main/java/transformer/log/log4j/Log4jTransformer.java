package transformer.log.log4j;

import common.ApmUtil;
import common.ConfigReader;
import common.Constants;
import javassist.*;
import transformer.Transformer;

import java.io.IOException;

/**
 * Created by noodles on 16/11/18 下午11:46.
 */
public class Log4jTransformer implements Transformer{

    public byte[] transform(String className, byte[] sourceClassBytes) throws NotFoundException, CannotCompileException, IOException {
        final CtClass ctClass = ClassPool.getDefault().get(className);

        final CtConstructor[] constructors = ctClass.getConstructors();

        for(CtConstructor constructor : constructors){
            final CtClass[] parameterTypes = constructor.getParameterTypes();
            if(parameterTypes.length > 1){

                StringBuilder sb = new StringBuilder();
                sb.append("try{");

                sb.append("String appname = ").append("\"").append(ConfigReader.instance.getStringValue("appName","noname")).append("\"").append(";");
                sb.append("String ipAddress = ").append("\"").append(ConfigReader.instance.getStringValue("IpAddress","127.0.0.1")).append("\"").append(";");
                sb.append("String level = level.toString().toUpperCase();");
                sb.append("StackTraceElement[] elements = Thread.currentThread().getStackTrace();");
                sb.append("String methodName = ").append("\"").append("noName").append("\"").append(";");
                sb.append("String lineNum = ").append("\"").append("0").append("\"").append(";");

                sb.append("for(int i = 3; i < elements.length;i++){")
                        .append("if(elements[i].getClassName().equals(this.categoryName)){")
                        .append("methodName = elements[i].getMethodName();")
                        .append("lineNum = String.valueOf(elements[i].getLineNumber());break;}}");

                sb.append("String flag = String.join(\" \",new String[]{appname,ipAddress,level,this.categoryName,methodName,lineNum});");
                sb.append("this.message = this.message").append(" + ").append("\"").append(Constants.LOG_SPLIT_KEY).append("\"").append(" + ").append("flag;");

                sb.append("}catch (Throwable e){}");

                constructor.insertAfter(sb.toString());

                break;

            }
        }

        ApmUtil.writeClass2File(ctClass);

        return ctClass.toBytecode();
    }
}
