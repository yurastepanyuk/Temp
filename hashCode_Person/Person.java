package day7.hashCode_Person;

/**
 * Created by stepanyuk on 03.07.2015.
 */
public class Person {

    String name;
    int age;
    long salary;
    Adress adress;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
        this.salary = (long)1000;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Person){
            Person person = (Person) obj;
            if (name != null && name.equals(person.name) && age == person.age && salary == person.salary
                    && ((adress == null && person.adress == null) ||(adress.equals(person.adress)))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {

        int result = 25;
        result = 37 * result + name.hashCode();
        result = 37 * result + age;
        result = 37 * result + (int)new Long(salary).hashCode();
        if (adress != null){
          result = 37 * result + adress.hashCode();
        }

        //result = 37 * result * ((int)(value ^ (value >>> 32)));

        return result;
    }

    @Override
    public String toString() {
        return "Name " + name + ", age " + age + ", salary " + salary + " " +adress.toString();
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
