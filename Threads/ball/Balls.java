package day12.ball;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Balls extends JPanel {

    private static final int WIDTH = 400;

    private Ball ball1;
    private Ball ball2;

    List<Ball> balls;

    public static void main(String[] args) {
        new Balls();
    }

    public Balls() {

        JFrame frame = new JFrame("Balls");
        frame.setLocation(450,150);
        frame.setMinimumSize(new Dimension(WIDTH, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        createBalls();

        createControllers();

//        ball1 = new Ball();
//        ball2 = new Ball();
//        ball2.setY(ball2.getY() + 50);

//        Controller controller = new Controller();
//        controller.setBall(ball1);
//        new Thread(controller).start();
//
//        controller = new Controller();
//        controller.setBall(ball2);
//        new Thread(controller).start();

        while (true){
            System.out.println("Runnable " + Thread.currentThread().getName());
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createControllers() {

        for (Ball curBall : balls){

            Controller curController = new Controller();
            curController.setBall(curBall);

            new Thread(curController).start();

        }

    }

    private void createBalls() {

        balls = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Ball curentBall = new Ball();
            curentBall.setY(curentBall.getY() + i * 20);
            curentBall.setSpead(curentBall.getSpead() - i * 5);

            balls.add(curentBall);

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Ball curBall : balls){
            curBall.draw(g);
        }

//        ball1.draw(g);
//        ball2.draw(g);

    }


    private class Ball {

        private int     radius;
        private int     x;
        private int     y;

        private int     spead;

        public Action getAction() {
            return action;
        }

        public void setAction(Action action) {
            this.action = action;
        }

        private Action  action;

        private static final int STEP = 5;

        public Ball() {
            this.radius = 10;
            this.x      = 0;
            this.y      = 30;
            this.action = Action.RIGHT;
            this.spead  = 50;
        }

        protected void draw(Graphics g) {

            Graphics2D graphics2D = (Graphics2D)g;

            g.setColor(new Color(255, 0, 0));
            g.fillOval(getX(), getY(), getRadius(), getRadius());

        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getSpead() {
            return spead;
        }

        public void setSpead(int spead) {
            this.spead = spead;
        }

    }

    private class Controller implements Runnable{

        private Ball ball;

        public Controller() {

        }

        private void runBall(){
            while (true){

                System.out.println("Runnable " + Thread.currentThread().getName());

                if (ball.action == Action.RIGHT) {
                    ball.setX((1) * ball.STEP + ball.getX());
                } else if (ball.action == Action.LEFT){
                    ball.setX((-1) * ball.STEP + ball.getX());
                }

                if (ball.getX()+ball.getRadius()*2+5 >= WIDTH) {
                    ball.setAction(Action.LEFT);
                } else if (ball.getX() <= 0){
                    ball.setAction(Action.RIGHT);
                }

                repaint();

                try {
                    Thread.sleep(ball.getSpead());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setBall(Ball ball) {
            this.ball = ball;
        }

        @Override
        public void run() {
            runBall();
        }
    }

    public enum Action{
        LEFT, RIGHT;
    }


}
