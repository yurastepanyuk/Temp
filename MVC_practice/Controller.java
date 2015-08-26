package day9.MVC_practice;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by stepanyuk on 12.08.2015.
 */
public class Controller extends MouseAdapter implements InterceptionListener{

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view  = view;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("mouseMoved");
        view.getMouse().x = e.getX();
        view.getMouse().y = e.getY();
        view.repaint();
    }

    @Override
    public void processInterception() {
        model.processInterception();
        view.repaint();
    }
}
