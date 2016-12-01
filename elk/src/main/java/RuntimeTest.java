import java.io.*;
import java.util.Random;

/**
 * Created by noodles on 16/10/12 下午3:29.
 */
public class RuntimeTest {

    private static Integer lastTotal = 0;
    private static Integer lastIdle = 0;
    private static Integer lastUsed = 0;


    public static void main(String[] args) throws IOException, InterruptedException {


        System.out.println(new Random().nextInt(100));

        while (true){
            doCollectCPU();
            doCollectMem();
            Thread.sleep(2000);
        }
    }

    private static void doCollectMem() throws IOException {
        final Process ls = Runtime.getRuntime().exec("cat /proc/meminfo");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ls.getInputStream()));
        final String memTotalLine = reader.readLine().trim();
        final String memFreeLine = reader.readLine().trim();


        ls.destroy();
        reader.close();

        Integer memTotal = Integer.parseInt(memTotalLine.split("\\s+")[1]);
        Integer memFree = Integer.parseInt(memFreeLine.split("\\s+")[1]);

        Integer used = memTotal - memFree;
        System.out.println("memery rate of utilization:" + format2HundredPersent((double) used/memTotal));
    }

    private static void doCollectCPU() throws IOException {
        final Process ls = Runtime.getRuntime().exec("cat /proc/stat");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ls.getInputStream()));
        final String s = reader.readLine().trim();

        ls.destroy();
        reader.close();

        final String[] split = s.split("\\s+");
        Integer total = 0;
        Integer idle = 0;
        Integer used = 0;

        for(int i = 1; i < split.length;i++){
            if(i == 4 || i == 5){
                idle += Integer.parseInt(split[i]);
            }
            total += Integer.parseInt(split[i]);
        }
        used = total - idle;

        int deltaTotal = total - lastTotal;
        int deltaUsed = used - lastUsed;
        int deltaIdle  = idle - lastIdle;

        lastTotal = total;
        lastUsed = used;
        lastIdle = idle;



        System.out.println("idle=" + format2HundredPersent((double) deltaIdle / deltaTotal)
                + ",used=" + format2HundredPersent((double) deltaUsed / deltaTotal));

    }

    protected static String format2HundredPersent(double number) {
        double v = number * 100;
        return String.format("%.2f%s", v, "%");
    }

}
