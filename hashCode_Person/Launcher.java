package day7.hashCode_Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stepanyuk on 03.07.2015.
 */
public class Launcher {

    public static void main(String[] args) {

        Map<String,Person> personHashMap= new HashMap<String,Person>();

        Person person;
        Adress adress;

        person = new Person("Yura", 17);
        adress = new Adress("Kiev","Raskovoj", 60);
        person.setAdress(adress);
        personHashMap.put(person.name, person);

        person = new Person("Sveta", 31);
        adress = new Adress("Vasilkov", "Naberezhanaya", 7);
        person.setAdress(adress);
        personHashMap.put(person.name, person);

        person = new Person("Vovka", 35);
        adress = new Adress("Sevastopol", "Ostapa Bendera", 12);
        person.setAdress(adress);
        personHashMap.put(person.name, person);

        for (String key : personHashMap.keySet() ){
            System.out.println("Key " + key);
        }

        System.out.println("Get value by key: " + personHashMap.get("Yura").toString());
        System.out.println("Get value by key: " + personHashMap.get("Sveta").toString());
        System.out.println("Get value by key: " + personHashMap.get("Vovka").toString());

        for (Person valuePerson : personHashMap.values()) {
            System.out.println("Person: " + valuePerson.toString());
        }



    }


}
