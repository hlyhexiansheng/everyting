package p;


import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Created by noodles on 16/12/4 下午1:01.
 */
public class ExceptionTest {

    public static void main(String[] args) {
        final ExceptionTest exceptionTest = new ExceptionTest();
        try {
            exceptionTest.method1();
        }catch (Exception e){
            e.printStackTrace();
            String fullStackTrace = ExceptionUtils.getFullStackTrace(e);

            final String stackTrace = ExceptionUtils.getStackTrace(e);

            System.out.println(stackTrace);

//            System.out.println(fullStackTrace);
            printCallStatck(e);
        }
    }

    public static void printCallStatck(Throwable ex) {

        System.out.println(ex.getMessage());
        Throwable cause = ex.getCause();
        while (true){
            if(cause == null){
                break;
            }
            System.out.println(cause.getMessage());
            cause = cause.getCause();
        }

        StackTraceElement[] stackElements = ex.getStackTrace();
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                System.out.print(stackElements[i].getClassName() + "\t");
                System.out.print(stackElements[i].getFileName() + "\t");
                System.out.print(stackElements[i].getLineNumber() + "\t");
                System.out.println(stackElements[i].getMethodName());
                System.out.println("-----------------------------------");
            }
        }
    }

    public void method1() {
        try {
            method2();
        } catch (Exception e) {
            throw new RuntimeException("method1",e);
        }

    }

    private void method2() {
        try {
            method3();
        } catch (Exception e) {
            throw new RuntimeException("method2",e);
        }
    }

    private void method3(){
        try {
            method4();
        } catch (Exception e) {
            throw new RuntimeException("method3",e);
        }
    }

    private void method4(){
        throw new RuntimeException("method4");
    }
}


