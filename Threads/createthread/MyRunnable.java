package day12.createthread;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Running Thread <class MyRunnable implements Runnable> " + Thread.currentThread().getName() + " " + this.getClass().getSimpleName());
    }
}
