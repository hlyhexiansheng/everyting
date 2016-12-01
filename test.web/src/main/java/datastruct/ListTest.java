package datastruct;

/**
 * Created by noodles on 16/8/23 下午12:22.
 */
public class ListTest {

    public static void main(String[] args) {
        final Node head = createList(10);
//        trace(head);
//
//        final Node newList = reverseList(head);
//        trace(newList);

        Node newHead = reverseByK(head,2);
        trace(newHead);
    }

    /*链表翻转。给出一个链表和一个数k，比如，链表为1→2→3→4→5→6，k=2，则翻转后2→1→6→5→4→3，若k=3，翻转后3→2→1→6→5→4，若k=4，翻转后4→3→2→1→6→5*/
    public static Node reverseByK(Node head, int k) {
        Node head1 = head;
        Node head2 = null;
        for (int i = 0; i < k - 1; i++){
            head = head.next;
        }

        head2 = head.next;
        head.next = null;

        Node newHead1 = reverseList(head1);
        Node newHead2 = reverseList(head2);


        Node tmp = newHead1;
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = newHead2;

        return newHead1;
    }


    /*反转*/
    public static Node reverseList(Node head) {

        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;

            head.next = pre;
            pre = head;

            head = next;
        }
        return pre;
    }

    public static Node createList(int n) {

        Node head = new Node();
        head.value = 0;

        Node prev = head;
        for (int i = 1; i < n; i++) {
            Node node = new Node();
            node.value = i;
            prev.next = node;
            prev = node;
        }
        return head;
    }


    public static void trace(Node head) {
        for (Node node = head; node != null; node = node.next) {
            System.out.println(node.value);
        }
    }


}

class Node {
    public int value;
    public Node next;
}
