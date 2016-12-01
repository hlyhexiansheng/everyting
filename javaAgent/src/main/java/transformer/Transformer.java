package transformer;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * Created by noodles on 16/11/18 上午11:19.
 */
public interface Transformer {

    byte[] transform(String className,byte[] sourceClassBytes) throws NotFoundException, CannotCompileException, IOException;

}
