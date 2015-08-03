package day8.AngryFriend;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Created by 3ai4ik on 02.08.2015.
 */
public class AngryFriend {

    Integer [] gridBagConstraints;
    int idxCurrentgridBagConstraints;
    String [] borderLayout;
    int currentBorderLayout;

    JFrame frame;

    JButton button;

    JPanel jpBound;

    //JPanel jpButton;

    Random random;

    public AngryFriend() {

        gridBagConstraints = new Integer[7];
        gridBagConstraints[0] = GridBagConstraints.SOUTH;
        gridBagConstraints[1] = GridBagConstraints.EAST;
        gridBagConstraints[2] = GridBagConstraints.WEST;
        gridBagConstraints[3] = GridBagConstraints.CENTER;
        gridBagConstraints[4] = GridBagConstraints.NORTH;
        gridBagConstraints[5] = GridBagConstraints.EAST;
        gridBagConstraints[6] = GridBagConstraints.WEST;

        borderLayout = new String[7];
        borderLayout[0] = BorderLayout.SOUTH;
        borderLayout[1] = BorderLayout.NORTH;
        borderLayout[2] = BorderLayout.EAST;
        borderLayout[3] = BorderLayout.WEST;
        borderLayout[4] = BorderLayout.NORTH;
        borderLayout[5] = BorderLayout.WEST;
        borderLayout[6] = BorderLayout.EAST;

        frame = new JFrame(){
            @Override
            public void paintComponents(Graphics g) {
                super.paintComponents(g);
            }
        };
        frame.setTitle("Angry friend");
        frame.setSize(new Dimension(250,250));
        frame.setMinimumSize(new Dimension(250,250));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setForeground(Color.white);

        frame.setLayout(new GridLayout(2, 0));

        JPanel panelLabel = new JPanel();
        panelLabel.setMaximumSize(new Dimension(250,30));
        JLabel label = new JLabel();
        label.setText("Click on the ball");
        label.setFont(new Font("Courier New", Font.ITALIC, 26));
        panelLabel.add(label);

        jpBound = new JPanel(new BorderLayout());

        random = new Random();

        button = new JButton();
        button.setIcon(new ImageIcon("src\\day8\\AngryFriend\\001d.png"));
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        button.setDoubleBuffered(true);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(60,60));

        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("mouseEntered");
                jpBound.remove(button);
                frame.remove(jpBound);
                idxCurrentgridBagConstraints++;
                if (idxCurrentgridBagConstraints >= 6) idxCurrentgridBagConstraints = 0;
                currentBorderLayout++;
                if (currentBorderLayout >= 6) currentBorderLayout = 0;
                jpBound.add(button, borderLayout[currentBorderLayout]);
                frame.getContentPane().add(jpBound);
                //jpBound.add(button, BorderLayout.CENTER);
                //frame.add(jpBound);
                //jpBound.add(button, BorderLayout.CENTER);
                //jpBound.add(button, new GridBagConstraints(0, 0, 1, 1, 0, 0, gridBagConstraints[idxCurrentgridBagConstraints], GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                //frame.repaint();
                frame.setVisible(true);
                frame.repaint();

                jpBound.add(button, BorderLayout.SOUTH);
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        jpBound.add(button, BorderLayout.EAST);
        frame.add(jpBound);

        frame.getContentPane().add(panelLabel, BorderLayout.BEFORE_LINE_BEGINS);
        frame.getContentPane().add(jpBound);

        frame.pack();
        frame.setVisible(true);

    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//    }

    public static void main(String[] args) {
        new AngryFriend();
    }


}
