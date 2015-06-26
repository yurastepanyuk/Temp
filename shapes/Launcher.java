package day6.shapes;

/**
 * Created by stepanyuk on 17.06.2015.
 */
public class Launcher {

    public static void main(String [] args){

        Shape draws[] = new Shape[3];

        Paint paint = new Paint();

        Triangle triangle = new Triangle();
        Rectangle rectangle = new Rectangle();
        Circle circle = new Circle();

        circle.setR(30);
        circle.setX(50);
        circle.setY(50);
        draws[0] = circle;
        paint.painting(circle);

        //Override method in rectangle
        rectangle.setX(100);
        rectangle.setY(50);
        rectangle.setWidth(50);
        rectangle.setHight(100);
        draws[1] = rectangle;
        paint.painting(rectangle);

        //Override method in Circle
        triangle.setxCoordinats(new int []{200, 170, 230});
        triangle.setyCoordinates(new int[]{50,220,220});
        draws[2] = triangle;
        paint.painting(triangle);


    }

}
