package day12.createthread;

public class MyThread extends Thread {


    @Override
    public void run() {
        System.out.println("Running Thread <class MyThread extends Thread> " + Thread.currentThread().getName() + " " + this.getClass().getSimpleName());
    }

}
