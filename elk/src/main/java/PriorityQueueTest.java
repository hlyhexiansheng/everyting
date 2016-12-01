import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by noodles on 16/11/23 下午5:42.
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Entity> priorityQueue = new PriorityQueue<>(new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                if(o1.getCreateTime() > o2.getCreateTime()){
                    return 1;
                }
                return -1;
            }
        });


        priorityQueue.add(new Entity(1));

        priorityQueue.add(new Entity(3));

        priorityQueue.add(new Entity(5));

        priorityQueue.add(new Entity(7));
        priorityQueue.add(new Entity(2));
        priorityQueue.add(new Entity(6));
        priorityQueue.add(new Entity(4));

        while (true){
            final Entity peek = priorityQueue.peek();
            System.out.println(peek);
            final Entity poll = priorityQueue.poll();
            if(poll == null){
                break;
            }
            System.out.println(poll);
        }

    }
}

@Data
class Entity{
    private long createTime;
    private String msg;
    public Entity(long createTime){
        this.createTime = createTime;
        this.msg = String.valueOf(createTime);
    }

}
