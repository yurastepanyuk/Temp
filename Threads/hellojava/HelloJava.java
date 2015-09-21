package day12.hellojava;

public class HelloJava implements Runnable {

    public static void main(String[] args) {

        HelloJava threadHelloJava  = new HelloJava();

        new Thread(threadHelloJava).start();
        new Thread(threadHelloJava).start();
        new Thread(threadHelloJava).start();

        int i = 0;

    }

    @Override
    public synchronized void run() {

        System.out.print(Thread.currentThread().getName() + ": ");

        try {
            System.out.print("Hello, ");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Java");


    }
}
