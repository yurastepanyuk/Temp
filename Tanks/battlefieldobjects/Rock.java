package day9.Tanks.battlefieldobjects;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by stepanyuk on 25.06.2015.
 */
public class Rock extends BattleFieldObjects {

    private final static String IMAGE_NAME = "src\\day8\\Tanks\\Images\\rock.jpg";
   // private final static String IMAGE_NAME = "src\\" + ActionField.class.getName().replace(".","\\").replace(ActionField.class.getSimpleName(),"") + "images\\rock.jpg";

    //private Image iRock;

    public Rock(int x, int y) {
        super(x, y);
        setColor(new Color(100,100,100));

        try {
            image = ImageIO.read(new File(IMAGE_NAME));
        } catch (IOException e){
            System.err.println("Can't find image: " + IMAGE_NAME);
        }
    }

    @Override
    public void destroy() {

    }

//    @Override
//    public void draw(Graphics g) {
//        if (!isDestroyed()) {
//            g.setColor(getColor());
//            g.fillRect(getX(), getY(), 64, 64);
//            g.drawImage(iRock, getX(), getY(), new ImageObserver() {
//                @Override
//                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
//                    return false;
//                }
//            });
//        }
//    }

}
