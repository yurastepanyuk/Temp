package day6.Exception;

import day6.Tanks.Direction;

/**
 * Created by stepanyuk on 16.06.2015.
 */
public class TestException {

    public static void main (String argm [])  {
        try {
            move(Direction.DOWN);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("I am not sleeping");
        } catch (TankException e){
            System.out.println("I am catched Exception");
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("finally code");
        }
    }

    public static void move(Direction direction) throws InterruptedException {
        Thread.sleep(1000);
        throw new TankException("not move");//Бросаю свой созданный TankException()
        //System.out.println("I am sleeping");
    }

}
