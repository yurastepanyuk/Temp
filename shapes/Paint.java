package day6.shapes;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Created by stepanyuk on 17.06.2015.
 */
public class Paint extends JPanel {

    public Shape [] shapes;
    private static int qtyShapes = -1;

    public Paint() {
        JFrame frame = new JFrame("Shapes");
        frame.setLocation(500, 100);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        shapes = new Shape[10];

        //repaint();
    }

    public void painting(Shape figure){

        this.shapes[qtyShapes + 1] = figure;
        this.qtyShapes = qtyShapes + 1;

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Shape shape : shapes){
            if (shape == null){
                continue;
            }

            shape.draw(g);
        }

    }

}
