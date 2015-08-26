package day9.Tanks.battlefieldobjects;

import day9.Tanks.ActionField;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by stepanyuk on 25.06.2015.
 */
public class Water extends BattleFieldObjects {

    //private final static String IMAGE_NAME = "src\\day8\\Tanks\\Images\\water.jpg";
    private final static String IMAGE_NAME = "src\\" + ActionField.class.getName().replace(".","\\").replace(ActionField.class.getSimpleName(),"") + "images\\water.jpg";

    public Water(int x, int y) {
        super(x,y);
        //setColor(new Color(100,200,255));

        try{
            image = ImageIO.read(new File(IMAGE_NAME));
            //image = makeImageTranslucent(ImageIO.read(new File(IMAGE_NAME)), 0.5f);
        }catch (IOException e){
            System.err.println("Can't find image: " + IMAGE_NAME);
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d              = (Graphics2D)g;
        Composite currentComposite  = g2d.getComposite();

        Composite translicent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);

        g2d.setComposite(translicent);

        if (!isDestroyed()) {
            g.setColor(getColor());
            g.fillRect(getX(), getY(), 64, 64);
            g.drawImage(image, getX(), getY(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }

        g2d.setComposite(currentComposite);

    }

    public static BufferedImage makeImageTranslucent(BufferedImage source,
                                                     double alpha) {
        BufferedImage target = new BufferedImage(source.getWidth(),
                source.getHeight(), Transparency.TRANSLUCENT);
        // Get the images graphics
        Graphics2D g = target.createGraphics();
        // Set the Graphics composite to Alpha
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC,
                (float) alpha));
        // Draw the image into the prepared reciver image
        g.drawImage(source, null, 0, 0);
        // let go of all system resources in this Graphics
        g.dispose();
        // Return the image
        return target;
    }

}
