package day8.MagicSquare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by 3ai4ik on 02.08.2015.
 */
public class MagicSquare extends JPanel {

    Color colorSquare = Color.BLACK;

    public MagicSquare()  {

        JFrame frame = new JFrame();
        frame.setTitle("Magic square");
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setSize(new Dimension(300, 300));
        frame.setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JButton button = new JButton("New Color");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorSquare = getRundomColor();
                repaint();
            }
        });

        add(button, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LAST_LINE_END, GridBagConstraints.NONE, new Insets(150, 0, 0, 0), 100, 0));


        frame.getContentPane().add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private Color getRundomColor() {

        Random random = new Random();

        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        return color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(colorSquare);
        g.fillRect(100, 50, 100, 100);


    }

    public static void main(String[] args) {
        new MagicSquare();
    }
}
