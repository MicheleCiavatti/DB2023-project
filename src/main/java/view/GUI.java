package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GUI {
    
    private static final String TITLE = "D&D database";
    private static final int PROPORTION = 2;
    private final JFrame frame = new JFrame(TITLE);

    public GUI() {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        final JButton write = new JButton("Writing");
        left.add(write);
        final JButton read = new JButton("Reading");
        left.add(read);
        canvas.add(left, BorderLayout.WEST);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JLabel result = new JLabel();
        canvas.add(result, BorderLayout.CENTER);
        write.addActionListener(e -> result.setText("Hello"));
        read.addActionListener(e -> {
            System.out.println(result.getText());
            result.setText("");
        });
    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }
}
