package day6.Exception;

/**
 * Created by stepanyuk on 16.06.2015.
 */
public class TankException extends RuntimeException {

    public TankException(String s) {
        alarmException(s);
    }

    private void alarmException(String s) {

        if (s.equals("not move")){
            System.out.println("NOT MOVING");
        }

    }
}
