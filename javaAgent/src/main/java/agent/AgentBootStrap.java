package agent;

import common.ConfigReader;
import transformer.TransformDispatcher;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * Created by noodles on 16/11/4 上午9:54.
 */
public class AgentBootStrap {

    public static void premain(String agentArs, Instrumentation inst) throws IOException {
        System.out.println("Java Agent Is Running.....\n\n\n");

        ConfigReader.instance.loadConfig("/Users/noodles/Documents/bppe-2.0/workspace/test/javaAgent/src/main/resources/apm.properties");

//        inst.addTransformer(new ClazzTransformer());
        inst.addTransformer(new TransformDispatcher());
    }

}
