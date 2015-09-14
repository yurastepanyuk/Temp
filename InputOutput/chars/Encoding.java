package day11.chars;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Encoding {

    public static void main(String[] args) throws UnsupportedEncodingException {

        char ch = 'æ';
        String str = new String(new char[]{'æ'});

        System.out.println(ch);
        System.out.println(str);
        System.out.println();

        System.out.println("Code Point: " + (int) ch);

        System.out.println();
        System.out.println("cp1251 " + Arrays.toString(str.getBytes("cp1251")) );

        System.out.println();
        System.out.println("UTF-8 " + Arrays.toString(str.getBytes(StandardCharsets.UTF_8)) );

        System.out.println();
        System.out.println("UTF-16BE " + Arrays.toString(str.getBytes(StandardCharsets.UTF_16BE)));
        System.out.println("UTF-16LE" + Arrays.toString(str.getBytes(StandardCharsets.UTF_16LE)));

        System.out.println();
        System.out.println("UTF-32 " + Arrays.toString(str.getBytes("UTF-32")) );

    }


}
