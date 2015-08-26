package day9.MVC_practice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 * Created by stepanyuk on 12.08.2015.
 */
public class View extends JPanel {

    private Model model;
    private Rectangle mouse;
    private InterceptionListener interceptionListener;

    public View(Model model) {
        this.model = model;
        mouse = new Rectangle(new Point(0,0), new Dimension(10, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(new Color(86, 142, 204));
        g2d.fillRect(0,0, getWidth(), getHeight());

        g2d.setPaint(new Color(200,50,0));
        g2d.fillOval(model.getPosition().x, model.getPosition().y, model.getDimension().width, model.getDimension().height);

        if (g2d.hit(mouse, model.getShape(), false)){
            interceptionListener.processInterception();
        }
    }

    public Rectangle getMouse() {
        return mouse;
    }

    public void setInterceptionListener(InterceptionListener interceptionListener){
        this.interceptionListener = interceptionListener;
    }

}
