package day12.notifications;

import javax.swing.*;
import java.awt.*;

public class StarGate extends JPanel{

    private static final int WIDTH = 400;

    private Ship ship;
    private Gate gates;

    public static void main(String[] args) throws InterruptedException {
        new StarGate();
    }

    public StarGate() throws InterruptedException {

        JFrame frame = new JFrame("STARGATE");
        frame.setLocation(450,100);
        frame.setMinimumSize(new Dimension(WIDTH, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.getContentPane().add(this);
        frame.setVisible(true);

        ship  = new Ship();
        gates = new Gate(10,170,5, Color.blue);

        turnOnGates();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    moveShip();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        while (true){
            repaint();
            sleep(1000 / 60);
        }

    }

    private class Ship{

        public int x;
        public int y;
        public int radius;

        public Color color;

        public Ship() {
            this.radius = 10;
            this.x      = 0;
            this.y      = 170;
        }
    }

    private class Gate{

        public int topX;
        public int topY;
        public int topWidth;
        public int topHight;

        public int bottomX;
        public int bottomY;
        public int bottomWidth;
        public int bottomHight;

        public int openTopY;
        public int closeTopY;
        public int openBottomY;
        public int closeBottomY;

        public Color color;

        public boolean isOpen;

        public Gate(int width, int hight,int xxx, Color color) {

            this.topX       = 320;
            this.topY       = 170 - hight/2 +10;
            this.topWidth   = width;
            this.topHight   = hight/2;
            this.openTopY   = this.topY - 20;
            this.closeTopY  = this.topY;

            this.bottomX     = 320;
            this.bottomY     = hight+10;
            this.bottomWidth = width;
            this.bottomHight = hight/2;
            this.openBottomY = this.bottomY + 20;
            this.closeBottomY= this.bottomY;

            this.color  = color;

            this.isOpen = false;

        }
    }

    private boolean isShipInRange(){
        return (ship.x > (gates.topX - ship.radius*3)) && (ship.x < gates.topX);
    }

    private void turnOnGates(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    synchronized (gates){
                        System.out.println("Mothership: waiting for the ship.");
                        gates.wait();
                    }
                    while (!gates.isOpen){
                        animationGates();
                    }
                    synchronized (ship){
                        ship.notify();
                    }

                    while (gates.isOpen){
                        animationGates();
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void animationGates() throws InterruptedException {
        if (!gates.isOpen){

            if (gates.topY > gates.openTopY){
                gates.topY--;
            }

            if (gates.bottomY < gates.openBottomY){
                gates.bottomY++;
            }

            if (gates.topY == gates.openTopY){
                gates.bottomY = gates.openBottomY;
                gates.isOpen = true;
                System.out.println("Mothersip: Gates opened, please come in");
                synchronized (ship){
                    ship.notify();
                }
            }

            sleep(300);

        } else if (gates.isOpen){

            if(gates.topY < gates.closeTopY){
                gates.topY++;
                gates.bottomY--;
            }

            if (gates.topY == gates.closeTopY){
                gates.bottomY = gates.closeBottomY;
                gates.isOpen = false;
                synchronized (gates){
                    gates.wait();
                }
            }

            sleep(300);

        } else {
            sleep(15);
        }
    }

    private void moveShip() throws InterruptedException {

        while (ship.x < WIDTH - ship.radius*4){

            if (!gates.isOpen && isShipInRange()){

                synchronized (gates){
                    gates.notify();
                    System.out.print("Ship: asked permission");
                }

                synchronized (ship){
                    System.out.println(" and waiting gates to open");
                    ship.wait();
                }

            } else if(ship.x >= WIDTH - ship.radius*4){

                synchronized (gates){
                    System.out.println("Mothersip: Gates closing");
                    gates.notify();
                }
            }

            ship.x++;
            sleep(5);
        }

        System.out.println("Ship: back home, waiting a new task ");
    }

    private void sleep(int timeout){
        try{
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(ship.color);
        g.fillOval(ship.x, ship.y, ship.radius*2, ship.radius*2);

        g.setColor(gates.color);
        g.fillRect(gates.topX, gates.topY, gates.topWidth, gates.topHight);
        g.fillRect(gates.bottomX, gates.bottomY, gates.bottomWidth, gates.bottomHight);
    }
}
