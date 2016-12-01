package stringtest;

/**
 * Created by noodles on 16/8/23 上午10:56.
 */
public class MoveStringtest extends BasicTest{

    public static void main(String[] args) {
        char[] str = new char[10];
        for(int i = 0; i < str.length;i++){
            str[i] = (char) ('A' + i);
        }

        trace(str);

        move2Tail(2,str);

        trace(str);
    }

    private static void move2Tail(int index, char[] str) {
        while (index-- != 0){
            leftMoveOne(str);
        }
    }

    private static void leftMoveOne(char[] str){
        char t = str[0];
        for(int i = 1; i < str.length; i++){
            str[i-1] = str[i];
        }
        str[str.length-1] = t;
    }
}
