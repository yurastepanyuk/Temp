package day12.race;

public class LegacyAtm implements Atm {

    private Integer balance = 10000;

    @Override
    public void checkBallance(long accauntId) {
        System.out.println(accauntId + " goint to withdraw some money");
    }

    @Override
    public synchronized void withdrawMoney(long accountId, int amount) {

        try {
            //Thread.sleep(500);
            if (allowWithdrowal(accountId, amount)) {
                updateBallans(accountId, amount, TransactionType.WITHDROWAL);
            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {

        }

    }

    public boolean allowWithdrowal(long accountId, int amount){
        if (balance >= amount) {
            System.out.println("allowWithdrowal = true");
            return true;
        } else {
            System.out.println("allowWithdrowal = falce");
            return false;
        }

    }

    private void updateBallans(long account, int amount, TransactionType type){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance -= amount;
        System.out.println("Succesful " + type + " account: " + account + " amount: " + amount);
    }

    enum TransactionType{
        DEPOSIT, WITHDROWAL;
    }
}
