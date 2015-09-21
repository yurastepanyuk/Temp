package day12.createthread;

public class LauncherDemo {

    public static void main(String[] args) {

        System.out.println("Running Thread <main(String[] args)> " + Thread.currentThread().getName());

        new MyThread().start();

        new Thread(new MyRunnable()).start();

    }

}
