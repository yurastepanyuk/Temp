package day6.shapes;

import java.awt.Graphics;
//drawOval
//drawRect
//drawPolygon

//setColor

/**
 * Created by stepanyuk on 17.06.2015.
 */
public class Shape {

    private int x;
    private int y;

    //protected void draw(){
    protected void draw(Graphics g){
        System.out.println("It is drawing somesing");
        throw new IllegalStateException("I don't know yours Shape");
    }

    public void wipeOff(){
        System.out.println("Shape is wipe off");
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
