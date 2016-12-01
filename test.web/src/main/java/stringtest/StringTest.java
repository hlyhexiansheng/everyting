package stringtest;

/**
 * Created by Administrator on 2015/12/24.
 */
public class StringTest {

    public static void main(String[] args){
        String s = "a话";
        char c = s.charAt(4);

        System.out.println(isChinese(c));
        System.out.println(c);

        System.out.println(lengthOfString(s));
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }


        /**
         * 获取字符串的长度，如果有中文，则每个中文字符计为2位
         * @param value 指定的字符串
         * @return 字符串的长度
         */
        public static int lengthOfString(String value) {
            int valueLength = 0;
            String chinese = "[\u0391-\uFFE5]";
            for (int i = 0; i < value.length(); i++) {
                String temp = value.substring(i, i + 1);
                if (temp.matches(chinese)) {
                    valueLength += 2;
                } else {
                    valueLength += 1;
                }
            }
            return valueLength;
        }
}
