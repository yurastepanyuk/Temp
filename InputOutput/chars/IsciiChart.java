package day11.chars;

public class IsciiChart {

    public static void main(String[] args) {

        int start  = 27;
        int finish = 127;

        for (int i = start; i <= finish; i++) {
            System.out.write(i);
            if (i % 7 == 0) {
                System.out.println();
            } else {
                System.out.write('\t');
            }

        }


    }

}
