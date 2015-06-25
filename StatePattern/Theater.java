package day6.StatePattern;

/**
 * Created by stepanyuk on 23.06.2015.
 */
public class Theater {

    Actor actor;

    public static void main(String[] args) {
        Theater theater = new Theater();
        theater.actor = new HappyActor();  

        theater.actor.act();
        
        theater.intermission();

        theater.actor.act();

    }

    private void intermission() {
        getSalary();
    }

    private void getSalary() {
        actor = new SadActor();
    }

}
