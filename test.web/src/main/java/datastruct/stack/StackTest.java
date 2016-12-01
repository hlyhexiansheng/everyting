package datastruct.stack;

/**
 * Created by noodles on 16/9/19 下午1:54.
 */
public class StackTest {

    public static void main(String[] args) {
//        ArrayStack<Integer> stack = new ArrayStack<>();
        ListStack<Integer> stack = new ListStack<>();
        stack.push(10);
        stack.push(11);
        stack.push(12);
        stack.push(13);
        Integer value = null;
        while ((value = stack.pop()) != null){
            System.out.println(value);
        }
    }
}
