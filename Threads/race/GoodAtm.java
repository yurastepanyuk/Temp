package day12.race;

public class GoodAtm implements Atm {

    private Integer balance = 1000;

    public GoodAtm() {
    }

    @Override
    public void checkBallance(long accauntId) {
        System.out.println(accauntId + " goint to withdraw some money");
    }

    @Override
    public void withdrawMoney(long accountId, int amount) {

        if (allowWithdrowal(accountId, amount)) {
            updateBallans(accountId, amount, TransactionType.WITHDROWAL);
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
        balance -= amount;
        System.out.println("Succesful " + type + " account: " + account + " amount: " + amount);
    }

    enum TransactionType{
        DEPOSIT, WITHDROWAL;
    }

}
