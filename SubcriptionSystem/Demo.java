package day9.SubcriptionSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepanyuk on 11.08.2015.
 */
public class Demo {

    public List<String> users;

    //New product
    public NewsPaper  newsPaperUT;
    public NewsPaper  newsPaperJN;
    public Journal	  journForbes;

    public static void main(String[] args) {

        new Demo();
        
    }

    public Demo(){

        users = new ArrayList<String>();

        //New product
        newsPaperUT = new NewsPaper("Ukranian tomorrow");
        newsPaperJN = new NewsPaper("Java news");
        journForbes = new Journal("Forbes");


        users.add("Yura");
        users.add("Vova");
        users.add("Kolya");
        users.add("Oleg");

        for (String user : users){
            addNewSubscriber(user, newsPaperUT);
            addNewSubscriber(user, newsPaperJN);
            addNewSubscriber(user, journForbes);
        }

        informSubscribers();

    }

    //Adding subscriber
    public void addNewSubscriber(String subscriber, Object object){
        Observer obsSubscriber = new Subscriber(subscriber);

        if (object instanceof Journal) {
            ((Journal) object).addObserver(obsSubscriber);
                    //addObserver(obsSubscriber);
        }else if (object instanceof NewsPaper) {
            ((Delivery) object).addObserver(obsSubscriber);
        }

    }

    //Inforning subscribers
    public void informSubscribers(){
        newsPaperUT.notifyObservers();
        newsPaperJN.notifyObservers();
        journForbes.notifyObservers();
    }

}
