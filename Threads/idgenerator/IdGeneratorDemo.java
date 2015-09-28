package day12.idgenerator;

import TEMP.threads.threadforinnerclass.ThreadForInnerClass;

public class IdGeneratorDemo {

    final static IdGenerator idGenerator  = new IdGenerator();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (; ;) {
                        System.out.println(Thread.currentThread().getName() + " " + idGenerator.getNextId());

                        sleep(1000);
                    }
                }
            }).start();

        }

    }

    private static void sleep(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
