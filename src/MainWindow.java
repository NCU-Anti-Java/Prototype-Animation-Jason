import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Jason on 2015/10/27.
 */
public class MainWindow {
    private JPanel contentPane;
    private JLabel labelMain;

    private int pressedKey;

    public MainWindow() {
        contentPane.setFocusable(true);
        contentPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int key = getDirection(e); // Get direction of pressed key
                if (key == -1) return; // Ignore invalid key
                pressedKey = key; // Log current pressing key
                labelMain.setText(Integer.toString(pressedKey));
            }
        });
    }

    private int getDirection(KeyEvent e) {
        // Return direction of pressed key in number

        // Left: 37. Up: 38. Right: 39. Down: 40.
        // Remapped to: Left: 0. Up: 1. Right: 2. Down: 3.
        int code = e.getKeyCode();
        return (code >= 37 && code <= 40) ? code - 37 : -1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
