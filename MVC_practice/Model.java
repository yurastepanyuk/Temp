package day9.MVC_practice;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Created by stepanyuk on 12.08.2015.
 */
public class Model {

    private Point position;

    private final Dimension dimension;

    private Shape shape;

    private Random random;

    public Model() {
        position    = new Point(100, 100);

        this.dimension   = new Dimension(50, 50);

        shape       = new Ellipse2D.Float(position.x, position.y, dimension.width, dimension.height);

        random      = new Random();

    }

    public void processInterception(){
        int newX = random.nextInt(749)+1;
        int newY = random.nextInt(549)+1;

        position.x = newX;
        position.y = newY;

        shape = new Ellipse2D.Float(position.x, position.y, dimension.width, dimension.height);

    }

    public Point getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
