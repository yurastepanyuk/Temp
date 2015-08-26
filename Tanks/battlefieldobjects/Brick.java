package day9.Tanks.battlefieldobjects;

import day9.Tanks.ActionField;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by stepanyuk on 25.06.2015.
 */
public class Brick extends BattleFieldObjects {

    //private final static String IMAGE_NAME = "src\\day9\\Tanks\\Images\\brick.jpg";
    private final static String IMAGE_NAME = "src\\" + ActionField.class.getName().replace(".","\\").replace(ActionField.class.getSimpleName(),"") + "images\\bfBricks.jpg";

    public Brick(int x, int y) {
        super(x, y);
        //setColor(new Color(255,0,0));

        try{
            //image = ImageIO.read(new File(IMAGE_NAME));
            image = ImageIO.read(new File(IMAGE_NAME).getAbsoluteFile());
        }catch (IOException e){
            System.err.println("Can't find image: " + IMAGE_NAME);
        }

    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed()) {
            //g.setColor(getColor());
            //g.fillRect(getX(), getY(), 64, 64);
            g.drawImage(image, getX(), getY(),getX()+64, getY()+64, getX(), getY(),getX()+64, getY()+64, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(getX(), getY(), 64, 64);
        }
    }
}
