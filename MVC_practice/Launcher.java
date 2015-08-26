package day9.MVC_practice;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Created by stepanyuk on 12.08.2015.
 */
public class Launcher {

    public static void main(String[] args) {

        JFrame frame = new JFrame("MVC Practice");
        frame.setMinimumSize(new Dimension(800,600));
        frame.setLocation(300,100);

        Model model = new Model();

        View view = new View(model);
        frame.getContentPane().add(view);

        Controller controller = new Controller(model, view);
        view.addMouseMotionListener(controller);
        view.setInterceptionListener(controller);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
