package day7.List_ClassRoom;

/**
 * Created by stepanyuk on 26.06.2015.
 */
public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
    }

    public Demo() {

        Classroom classroom = new Classroom();

        System.out.println("");
        classroom.enter(new Student("Yuriy","Stepanyuk"));
        classroom.enter(new Student("Pasha","Boychuk"));
        classroom.enter(new Student("Sasha","Nakazniy"));
        classroom.enter(new Student("Olya","Gvozdik"));
        classroom.enter(new Student("Matasha","Manyuk"));

        System.out.println("");
        classroom.leave(classroom.getStudent("Sasha", "Nakazniy"));

        System.out.println("");
        System.out.println("In classroom there are " + classroom.getStudentCount() + " student");

        System.out.println("");
        System.out.println("There is " + classroom.isPresent("Vova", "Marcenyuk") + " Vova Marcenyuk in the classroom");
        System.out.println("There is " + classroom.isPresent("Pasha", "Boychuk") + " Pasha Boychuk in the classroom");

        System.out.println("");
        classroom.printStudentInfo();

    }
}
