package day12.race;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RaceConditions {

    public static void main(String[] args) {

        Random random = new Random();

        long husband = 1122;
        long wife    = 2211;

        //Atm atm = new GoodAtm();
        //Atm atm = new BadAtm();
        Atm atm = new LegacyAtm();

        Set<Runnable> threads = new HashSet<>();

        for (int i = 0; i < 50; i++) {
            threads.add(createWithdrawalThread(atm, husband, random.nextInt(1000)));
            threads.add(createWithdrawalThread(atm, wife, random.nextInt(1000)));
        }

        for (Runnable runnable : threads){
            new Thread(runnable).start();
        }

    }

    private static Runnable createWithdrawalThread(final Atm atm, final long accountId, final int money){
        return new Runnable() {
            @Override
            public void run() {
                atm.checkBallance(accountId);
                atm.withdrawMoney(accountId, money);
            }
        };
    }


}
