package day10.genericpractica;

import java.util.ArrayList;

/**
 * Created by stepanyuk on 21.08.2015.
 */
public class BoxGeneric<T> {

    private ArrayList<T> listBox;

    public BoxGeneric() {
        listBox = new ArrayList<T>();
    }


    public void add(T object){
        listBox.add(object);
    }

    public void remove(T object){
        listBox.remove(object);
    }

    public T getByIndex(int i){
        int idx = 0;
        for (T object : listBox){

            if (idx == i) {
                return object;
            }

            idx++;
        }
        throw new IndexOutOfBoundsException("Index: "+i);
    }

    public static void main(String[] args) {
        BoxGeneric<String> cats = new BoxGeneric<>();
        System.out.println("BoxGeneric<String> cats = new BoxGeneric<>()");
        cats.add("Murka");
        System.out.println("cats.add(\"Murka\");");
        cats.add("Vaska");
        System.out.println("cats.add(\"Vaska\");");
        cats.add("Pirat");
        System.out.println("cats.add(\"Pirat\");");

        cats.remove("Vaska");
        System.out.println("cats.remove(\"Vaska\");  ");

        System.out.print(cats.getByIndex(1));
        System.out.println("   - cats.getByIndex(1)");


    }

}
