/**
 * Created by noodles on 16/9/19 上午10:33.
 */
public class PrintStar {

    public static void main(String[] args) {
//        printStar(1, '+');
//        printStart2(1,5,'+');

        int curNum = 1;
        int maxNum = 5;

        char op = '+';
        while (true){
            if(curNum == 0){
                break;
            }
            if(curNum == maxNum){
                op = '-';
            }
            if(op == '+'){
                print(curNum++,maxNum);
            }else {
                print(curNum--,maxNum);
            }

        }

    }

    private static void print(int curNum,final int maxNum){

        for (int i = 0; i < maxNum - curNum; i++) {
            System.out.print(' ');
        }
        for (int i = 0; i < 2 * curNum -1; i++){
            System.out.print('*');
        }
        System.out.print('\n');

    }

    private static int loopAdd(int i) {
        if(i > 0){
            return i + loopAdd(i - 1);
        }
        return 0;
    }


    public static void printStart2(int curNum, final int maxNum, char option) {
        if (curNum == 0) {
            return;
        }
        for (int i = 0; i < maxNum - curNum; i++) {
            System.out.print(' ');
        }
        for (int i = 0; i < 2 * curNum -1; i++){
            System.out.print('*');
        }
        System.out.print('\n');

        if(curNum == 5){
            option = '-';
        }
        if(option == '+'){
            printStart2(curNum + 1,maxNum,option);
        }else {
            printStart2(curNum - 1,maxNum,option);
        }


    }


    private static void printStar(int num, char option) {
        if (num == 0) {
            return;
        }

        for (int i = 0; i < num; i++) {
            System.out.print("*");
        }
        System.out.print("\n");

        if (num == 5) {
            option = '-';
        }
        if (option == '+') {
            printStar(num + 1, option);
        } else {
            printStar(num - 1, option);
        }
    }

}
