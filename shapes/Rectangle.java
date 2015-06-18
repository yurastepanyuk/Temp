package day6.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by stepanyuk on 17.06.2015.
 */
public class Rectangle extends Shape {

    private int width;
    private int hight;

    @Override
    protected void draw(Graphics g) {
        System.out.println("It is draving Rectangle");

        g.setColor(new Color(255, 0, 255));
        g.drawRect(getX(), getY(), getWidth(), getHight());
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
