import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Created by noodles on 16/10/10 下午5:57.
 */
public class luaj {

    public static void main(String[] args) {
        Globals globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.load("print 'hello, world'");
        chunk.call();
    }
}
