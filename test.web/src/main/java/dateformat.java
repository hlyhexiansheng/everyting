import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by noodles on 16/4/20.
 */
public class dateformat {

    public static void main(String[] args){
        System.out.println(new SimpleDateFormat("ddHHmm").format(new Date()));
    }
}
