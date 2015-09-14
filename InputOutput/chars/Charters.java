package day11.chars;

import java.util.Arrays;

public class Charters {

    public static void main(String[] args) {
        char aChar = 169;
        System.out.println(aChar);
        System.out.println();

        aChar = '\u00A9';
        System.out.println(aChar);
        System.out.println();

        printCodePointInfo(100);
        printCodePointInfo(500);
        printCodePointInfo(1000);
        printCodePointInfo(50000);
        printCodePointInfo(65600);
        printCodePointInfo(100000);

    }

    private static void printCodePointInfo(int codePoint){

        char[] chars    = Character.toChars(codePoint);
        String strChars = new String(chars);

        System.out.println("Code Point: " + codePoint);
        System.out.println("Char array: " + Arrays.toString(chars));
        System.out.println("Res string: " + strChars);
        System.out.println("String len: " + strChars.length());
        System.out.println("Code count: " + strChars.codePointCount(0, strChars.length()));
        System.out.println();

    }

}
