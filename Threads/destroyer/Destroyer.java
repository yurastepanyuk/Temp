package day12.destroyer;

public class Destroyer extends Thread {

    private volatile boolean killThread;

    public Destroyer() {
        this.killThread = false;
    }

    public void killThisThread(){
        this.killThread = true;
    }


    @Override
    public void run() {
        System.out.print(isInterrupted());
        while (!killThread || isInterrupted()){

            System.out.println("Enjoying life...");
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Life is so cool aaaa (Dying...)");
    }

}
