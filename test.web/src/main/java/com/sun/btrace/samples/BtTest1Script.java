package com.sun.btrace.samples;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

import static com.sun.btrace.BTraceUtils.Reflective.printFields;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;

/**
 * Created by noodles on 2017/11/21 14:52.
 */
@BTrace
public class BtTest1Script {
    @OnMethod(
            clazz = "btracetest.BTTest1",
            method = "test"
    )
    public static void startExecute(@ProbeClassName
                                            String cn, @ProbeMethodName
                                            String mn, AnyType[] args) {
        println(cn);
        println(mn);

        printFields(args[0]);

        println("-----");
    }


}
