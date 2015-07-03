package day7.hashCode_Person;

/**
 * Created by stepanyuk on 03.07.2015.
 */
public class Adress {
    String city;
    String street;
    int house;

    public Adress(String city, String street, int house) {

        if (city == null || street == null || house == 0){
            throw new IllegalStateException("Parameters should not contains null values and house should be > 0");
        }

        this.city = city;
        this.house = house;
        this.street = street;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Adress){

            Adress adress = (Adress) obj;

            if (city.equals(adress.getCity()) && street.equals(adress.getStreet()) && house == adress.getHouse()){
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = 25;
        result = 37 * result + city.hashCode();
        result = 37 * result + street.hashCode();
        result = 37 * result + house;

        return result;
    }

    @Override
    public String toString() {
        return "Adress: city " + getCity() + ", street " + getStreet() + ", house " + getHouse();
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getHouse() {
        return house;
    }

}
