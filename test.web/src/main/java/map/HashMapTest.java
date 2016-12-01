package map;

import java.util.*;

/**
 * Created by Administrator on 2015/12/15.
 */
public class HashMapTest {

    private static ArrayList list = new ArrayList();

    public static void main(String[] args){
        for (int i = 0; i < 1000;i++){
            Key key = new Key(i);
            int hash = hash(key);
            if (!list.contains(hash)){
                list.add(hash);
            }
        }
        System.out.println(list.size());
    }


    public static int hash(Object k){
        int h = 0;
        h ^= k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

}

class Key{
    public Key(int id){
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        return id == key.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    public int id;
}

class Value{
    public Value(int value){
        this.value = value;
    }
    public int value;
}

