package day7.List_ClassRoom;

/**
 * Created by stepanyuk on 26.06.2015.
 */
public interface Ivents {

    public static final String className = "11-A";

    void enter(Student student);
    void leave(Student student);
    int getStudentCount();
    boolean isPresent(String name, String secondName);
    void printStudentInfo();
    Student getStudent(String name, String lastName);

}
