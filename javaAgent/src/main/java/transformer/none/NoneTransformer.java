package transformer.none;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import transformer.Transformer;

/**
 * Created by noodles on 16/11/18 上午11:57.
 */
public class NoneTransformer implements Transformer{
    public byte[] transform(String className, byte[] sourceClassBytes) throws NotFoundException, CannotCompileException {
        return sourceClassBytes;
    }
}
