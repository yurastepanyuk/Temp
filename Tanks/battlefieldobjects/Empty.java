package day9.Tanks.battlefieldobjects;

import day9.Tanks.ActionField;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by stepanyuk on 25.06.2015.
 */
public class Empty extends BattleFieldObjects {

    //private final static String IMAGE_NAME = "src\\day8\\Tanks\\Images\\empty.jpg";
    private final static String IMAGE_NAME = "src\\" + ActionField.class.getName().replace(".","\\").replace(ActionField.class.getSimpleName(),"") + "images\\empty.jpg";

    public Empty(int x, int y) {
        super(x, y);
        setColor(new Color(0, 0, 0));

        try{
            image = ImageIO.read(new File(IMAGE_NAME));
        } catch (IOException e){
            System.err.println("Cann't find image: " + IMAGE_NAME);
        }

    }

    @Override
    public void draw(Graphics g) {
//        if (!isDestroyed()) {
////            g.setColor(getColor());
////            g.fillRect(getX(), getY(), 64, 64);
////            g.drawImage(iEmpty, getX(), getY(), new ImageObserver() {
////                @Override
////                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
////                    return false;
////                }
////            });
//        } else{
//            g.setColor(getColor());
//            g.fillRect(getX(), getY(), 64, 64);
//        }
        g.setColor(getColor());
        g.fillRect(getX(), getY(), 64, 64);

    }

}
