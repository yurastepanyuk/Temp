package day10.wildcards;

import day10.domain.AutoParts;
import day10.domain.Oil;
import day10.domain.Tire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by stepanyuk on 21.08.2015.
 */
public class BoxGenericAndWidcard<T extends AutoParts> {

    private ArrayList<T> autoParts;

    public BoxGenericAndWidcard() {
        autoParts = new ArrayList<T>();
    }

    public void add(T object){
        autoParts.add(object);
    }

    public void remove(T object){
        autoParts.remove(object);
    }

    public T getByIndex(int i){
        int idx = 0;
        for (T object : autoParts){

            if (idx == i) {
                return object;
            }

            idx++;
        }
        throw new IndexOutOfBoundsException("Index: "+i);
    }

    public static void main(String[] args) {
        startDemo();
    }

    public List<T> getAvtoParts(){
        return new ArrayList<>(autoParts);
    }

    private static void startDemo() {

        BoxGenericAndWidcard<AutoParts> products = new BoxGenericAndWidcard<>();
        System.out.println("BoxGenericAndWidcard<AutoParts> products = new BoxGenericAndWidcard<>()");

        Tire tire = new Tire();
        tire.setId(1);
        tire.setName("Tire 195/60R15 88V SP Sport 01 Dunlop");
        tire.setPrice(1250D);
        tire.setSize("195/60R15");
        products.add(tire);
        System.out.println(tire.toString());

        Oil oil = new Oil();
        oil.setId(0);
        oil.setName("Motor oil Castrol 5W40");
        oil.setPrice(750D);
        oil.setCapacity(4);
        products.add(oil);
        System.out.println(oil.toString());

        //products.remove(tire);
        //System.out.println("products.remove(tire)  ");

        System.out.print(products.getByIndex(0));
        System.out.println("   - products.getByIndex(0)");

        System.out.println("");
        System.out.println("Print AutoParts after sorted");
        products.sortByName();
        for (AutoParts product : products.getAvtoParts()) {
            System.out.println(product.toString());
        }

    }

    public void sortByName() {
        Collections.sort(autoParts, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                AutoParts object1 = (AutoParts)o1;
                AutoParts object2 = (AutoParts)o2;

                String nameObject1 = object1.getName();
                String nameObject2 = object2.getName();

                int result = nameObject1.compareToIgnoreCase(nameObject2);

                if (result > 0) {
                    return 1;
                } else if (result < 0){
                    return -1;
                }

                return 0;

            }
        });
    }
}
