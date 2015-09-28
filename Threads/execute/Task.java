package day12.execute;

import java.util.Random;
import java.util.concurrent.Callable;

public class Task implements Callable {

    @Override
    public Object call() throws Exception {

        Integer result = 0;

        Random random = new Random();

        for (int i = 0; i < random.nextInt(Integer.MAX_VALUE); i++) {
            result++;
        }

        return result;
    }
}
