package day12.destroyer;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {
        Destroyer destroyer = new Destroyer();
        destroyer.start();

        Thread.currentThread().sleep(1500);

        //destroyer.killThisThread();
        destroyer.interrupt();

    }

}
