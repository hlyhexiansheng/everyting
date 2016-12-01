package transformer;

import common.ApmLoggerUtil;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by noodles on 16/11/18 上午10:59.
 */
public class TransformDispatcher implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        className = className.replace('/', '.');

        final Transformer transformer = TransformerFactory.getTransformer(className);

        try {
            return transformer.transform(className, classfileBuffer);
        }catch (Exception e){
            e.printStackTrace();
            ApmLoggerUtil.log("transform error " + e.getMessage());
            return classfileBuffer;
        }
    }

}
