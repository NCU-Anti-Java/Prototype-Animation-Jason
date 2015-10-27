import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Jason on 2015/10/27.
 */
public class MainWindow {
    private JPanel contentPane;
    private JLabel labelMain;

    public MainWindow() {
        contentPane.setFocusable(true);
        contentPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                labelMain.setText(Integer.toString(e.getKeyCode()));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
