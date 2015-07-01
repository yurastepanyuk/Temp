package day7.List_ClassRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepanyuk on 26.06.2015.
 */
public class Classroom implements Ivents{

    private List<Student> studentList;

    public Classroom() {
        this.studentList =  new ArrayList<>();
        System.out.println("Created Classroom");
    }

    @Override
    public void enter(Student student) {
        studentList.add(student);
        System.out.println("Added student: " + student.getName() + " " + student.getSecondName());
    }

    @Override
    public void leave(Student student) {
        studentList.remove(student);
        System.out.println("Leave student: " + student.getName() + " " + student.getSecondName());
    }

    @Override
    public int getStudentCount() {
        return studentList.size();
    }

    @Override
    public boolean isPresent(String name, String secondName) {

//        boolean result = false;

//        for (Student student : studentList) {
//            if (student.getName().equals(name) && student.getSecondName().equals(secondName)){
//                return true;
//            }
//        }

//        return result;

        boolean chek = studentList.contains(new Student(name,secondName));

        return chek;

    }

    @Override
    public void printStudentInfo() {

        System.out.println("There are studenrs in the Classroom: ");

        for (Student student : studentList) {

            //System.out.println(student.getName() + " " + student.getSecondName());
            System.out.println(student.toString());

        }

    }

    @Override
    public Student getStudent(String name, String lastName) {

        for (Student student : studentList) {
            if (student.getName().equals(name) && student.getSecondName() .equals(lastName)){
                return student;
            }
        }

       return null;
    }
}
