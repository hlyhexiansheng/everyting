package datastruct.stack;

/**
 * Created by noodles on 16/9/19 下午1:56
 */
public class ListStack<T> {

    private Node<T> head;

    public void push(T value){
        if(head == null){
            head = new Node<T>(value,null);
        }else {
            Node newNode = new Node<T>(value,head);
            head = newNode;
        }
    }

    public T pop(){
        if(head == null){
            return null;
        }
        final T value = head.value;
        head = head.next;
        return value;
    }

}

class Node<T>{
    public Node next;
    public T value;
    public Node(T value,Node next){
        this.value = value;
        this.next = next;
    }
}
