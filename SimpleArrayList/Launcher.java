package day7.SimpleArrayList;

/**
 * Created by stepanyuk on 09.07.2015.
 */
public class Launcher {

    public static void main(String[] args) {

        SimpleArrayList list = new SimpleArrayList();

        list.add("Cat");
        list.add("Dog");

        for (Object object : list) {
            System.out.println((String)object + " " + object);
        }

        list.remove("Cat");

        list.remove(9);


    }




}
