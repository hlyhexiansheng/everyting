package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2015/12/10.
 */
public class JDKProxyTest {

    public static void main(String[] args){

//
//        Person person = new Person();

        Human human = (Human) Proxy.newProxyInstance(Person.class.getClassLoader(), Person.class.getInterfaces(), new Handler(new Person()));
        human.say();
    }

}

class Handler implements InvocationHandler{

    private Human target;

    public Handler(Human target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.target,args);
    }
}

class Person implements Human{
    @Override
    public void say() {
        System.out.println("haha");
    }
}

interface Human{
    void say();
}
