import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by noodles on 17/1/10 下午5:47.
 */
public class RunOpenFalconAgent {

    public static void main(String[] args) throws IOException {

        final Process ls = Runtime.getRuntime().exec("control start");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ls.getInputStream()));
        while (true){
            final String line = reader.readLine();
            if(line == null || line.equals("")){
                break;
            }
            System.out.println(line);
        }
    }
}
