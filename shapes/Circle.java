package day6.shapes;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Created by stepanyuk on 17.06.2015.
 */
public class Circle extends Shape {

    private int r;//radius

    @Override
    protected void draw(Graphics g) {
        System.out.println("It is drawing Circle");

        g.setColor(new Color(255, 0, 0));
        g.drawOval(getX(),getY(),getR(),getR());

    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
