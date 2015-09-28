package day12.returningthetank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ReturnTank extends JPanel {

    private final static int WIDTH = 500;

    private Tanchik tank;
    private Gates   gate;

    public static void main(String[] args) {
        new ReturnTank();
    }

    public class Tanchik{

        public int x;
        public int y;
        public int width;
        public int hight;

        public Image image;

        private final String IMAGE_NAME = "src\\" + ReturnTank.class.getName().replace(".","\\").replace(ReturnTank.class.getSimpleName(),"") + "t34up.jpg";

        public Tanchik() {
            this.x      = 0;
            this.y      = 150;
            this.width  = 50;
            this.hight  = 50;

            try{
                this.image = ImageIO.read(new File(IMAGE_NAME));
                rotateImage(90);
            } catch (IOException e) {
                System.out.println("Can't find image: " + IMAGE_NAME);
                e.printStackTrace();
            }

        }

        public void rotateImage(int rotate){

            double degress = 0;

            ImageIcon icon = new ImageIcon(this.image);
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
            //graphics2D.setComposite();

            degress = rotate;

            graphics2D.rotate(Math.toRadians(degress), icon.getIconWidth()/2, icon.getIconHeight()/2);
            graphics2D.drawImage(this.image,0,0,new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
            this.image = bufferedImage;
        }

        public void parkingTank() {
            this.x      = -100;
            this.y      = -100;
            this.width  = 50;
            this.hight  = 50;
        }
    }

    public class Gates{

        public int   x;
        public int   y;
        public int   hight;
        public int   openGates = 100;
        public int   closeGate = 360;

        public ActionGate actionGate;

        public boolean isOpen;

        public Gates() {
            this.x          = 350;
            this.y          = 0;
            this.hight      = 360;
            this.actionGate = ActionGate.NONE;
            this.isOpen     = false;
        }

    }

    public enum ActionGate{
        NONE, OPEN, CLOSE
    }

    public ReturnTank() {

        tank = new Tanchik();
        gate = new Gates();

        JFrame frame = new JFrame("ReturnTank");
        frame.setLocation(350,150);
        frame.setMinimumSize(new Dimension(WIDTH,400));
        setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        turnGate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                moveTank();
            }
        }).start();

        while (true){
            sleep(1000/60);
            repaint();
        }

    }

    private void turnGate(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){

                try{
                    synchronized (gate){
                        System.out.println("Gate: waiting the tank");
                        gate.wait();
                    }
                    gate.actionGate = ActionGate.OPEN;
                    while (!gate.isOpen){
                        animationGate();
                    }
                    synchronized (tank){
                        tank.notify();
                    }
                    synchronized (gate){
                        System.out.println("Gate: gates opened, please come in");
                        gate.wait();
                    }
                    gate.actionGate = ActionGate.CLOSE;
                    while (gate.isOpen){
                        animationGate();
                    }
                    synchronized (tank){
                        tank.notify();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }


            }
        }).start();
    }

    public void animationGate(){

        if (gate.actionGate == ActionGate.OPEN){
            while (gate.hight > gate.openGates){
                gate.hight--;
                sleep(20);
            }

            if (gate.hight <= gate.openGates){
                gate.isOpen = true;
            }

        } else if (gate.actionGate == ActionGate.CLOSE){
            while (gate.hight <= gate.closeGate){
                gate.hight++;
                sleep(20);
            }

            if (gate.hight >= gate.closeGate){
                gate.isOpen = false;

                nextTankReturn();

            }

        }

    }

    private void nextTankReturn() {
        tank = new Tanchik();
        new Thread(new Runnable() {
            @Override
            public void run() {
                moveTank();
            }
        }).start();
        //moveTank();
    }

    public void moveTank(){
        while (tank.x <= WIDTH-80){

            if (isTankInRange() && !gate.isOpen){
                synchronized (gate){
                    gate.notify();
                }
                synchronized (tank){
                    try {
                        System.out.println("Tank: I request permission to drive into");
                        System.out.println("Gate: openingthe gate");
                        tank.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            tank.x++;

            sleep(20);
        }

        if (tank.x >= WIDTH-80){

            gate.actionGate = ActionGate.CLOSE;
            synchronized (gate){
                System.out.println("Tank: back home, waiting a new task ");
                System.out.println("Gate: closing the gate");
                gate.notify();
            }

            int i = 180;
            while (i > 0){
                tank.rotateImage(-45);
                repaint();
                i -=45;
                sleep(750);
           }

            tank.parkingTank();

        }
    }

    public boolean isTankInRange(){
        return (tank.x > gate.x-80) && (tank.x < gate.x);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(tank.image, tank.x, tank.y, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });

        g.setColor(Color.red);
        g.fillRect(gate.x, gate.y, 10, gate.hight);

    }

    public void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
