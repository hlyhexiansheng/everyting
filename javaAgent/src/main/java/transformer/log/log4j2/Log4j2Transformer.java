package transformer.log.log4j2;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import transformer.Transformer;

import java.io.IOException;

/**
 * Created by noodles on 16/11/19 上午12:39.
 */
public class Log4j2Transformer implements Transformer {

    public byte[] transform(String className, byte[] sourceClassBytes) throws NotFoundException, CannotCompileException, IOException {
        return sourceClassBytes;
    }
}
