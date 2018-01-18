import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by noodles on 2017/11/30 16:42.
 */
public class Game2 {

    private static ArrayList<String> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        loadFile("/Users/noodles/Desktop/file.txt");

        waitForInput();

    }

    private static void waitForInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String inputWord = scanner.nextLine();
            inputWord = inputWord.trim().toLowerCase();

            int count = 0;
            for (String w : words) {
                if (w.startsWith(inputWord)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    private static void loadFile(String fileName) throws IOException {
        final FileInputStream inputStream = new FileInputStream(fileName);

        int index = 0;
        byte[] bytes = new byte[10000];

        while (true) {

            final int c = inputStream.read();
            if (c == -1) {
                System.out.println("--");
                break;
            }
            if (c == 32 || c == 10 || c == 13 || c == 9) {
                if (index > 0) {
//                    System.out.println(new String(bytes, 0, index));
                    words.add(new String(bytes, 0, index).toLowerCase());
                    index = 0;
                }
            } else {
                bytes[index] = (byte) c;
                index++;
            }
        }
    }
}
