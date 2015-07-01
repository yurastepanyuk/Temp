package day7.List_ClassRoom;

/**
 * Created by stepanyuk on 26.06.2015.
 */
public class Student {

    private String name;
    private String SecondName;

    public Student(String name, String secondName) {
        this.name = name;
        this.SecondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    @Override
    public String toString() {
        return getName() + " " + getSecondName();
    }

    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if (obj instanceof Student){
            Student student = (Student)obj;
            if (getName() != null && getName().equals(student.getName())
                    && getSecondName() != null && getSecondName().equals(student.getSecondName())){
                return true;
            }
        }

        return result;
    }
}
