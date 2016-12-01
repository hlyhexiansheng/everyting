package datastruct.stack;

/**
 * Created by noodles on 16/9/19 下午1:29.
 * 数组实现stack
 */
public class ArrayStack<T> {

    private int top = -1;

    private Object[] elements = new Object[100];

    public void push(T t){
        this.elements[++top] = t;
    }

    public T pop(){
        if(top == -1){
            return null;
        }
        return (T) this.elements[top--];
    }
}


