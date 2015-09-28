package day11.simplelist;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {

        SimpleList<String> list = new SimpleList<String> ();

        list.add("Cat");
        list.add("Dog");

        list.add("Bunny");

        list.remove(1);
        list.remove(0);

        list.add("Chiken");

        for (Object object : list) {
            System.out.println((String)object);
        }


    }




}
