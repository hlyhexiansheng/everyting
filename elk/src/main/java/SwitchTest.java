/**
 * Created by noodles on 16/10/14 下午4:01.
 */
public class SwitchTest {

    public static void main(String[] args) {

        switch (1){
            case 1:
            case 2:{
                System.out.println(12);
                break;
            }
            default:{
                System.out.println("fcuk");
            }
        }

    }
}
