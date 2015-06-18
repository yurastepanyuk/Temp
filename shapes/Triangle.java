package day6.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by stepanyuk on 17.06.2015.
 */
public class Triangle extends Shape {

    private int [] xCoordinats;
    private int [] yCoordinates;

    public Triangle() {
        this.yCoordinates = new int[2];
        this.xCoordinats = new int[2];
    }

    @Override
    protected void draw(Graphics g) {
        System.out.println("It is drawing Triangle");

        g.setColor(new Color(255, 0, 0));
        g.drawPolygon(getxCoordinats(),getyCoordinates(),3);
    }

    public int[] getxCoordinats() {
        return xCoordinats;
    }

    public void setxCoordinats(int[] xCoordinats) {
        this.xCoordinats = xCoordinats;
    }

    public int[] getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(int[] yCoordinates) {
        this.yCoordinates = yCoordinates;
    }
}
