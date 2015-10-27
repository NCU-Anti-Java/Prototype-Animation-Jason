import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

/**
 * Created by Jason on 2015/10/27.
 */
public class MainWindow {
    private JPanel contentPane;
    private JLabel labelMain;
    private int frame;

    private ArrayList<Integer> pressedKey;

    public MainWindow() {
        pressedKey = new ArrayList<>();

        contentPane.setFocusable(true);
        contentPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int key = getDirection(e); // Get direction of pressed key
                if (key == -1) return; // Ignore invalid key
                pressedKey.add(key); // Log current pressing key
                frame = 0; // Reset frame no. for changing direction
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                int key = getDirection(e); // Get direction of released key
                if (key == -1) return; // Ignore invalid key
                pressedKey.removeIf(x -> x == key); // Remove released key from list
            }
        });

        // Timer for animation
        Timer timer = new Timer(200, e -> {
            if (pressedKey.size() != 0) {
                // Increase frame no.
                frame++;
                if (frame >= 8) frame = 0;

                labelMain.setText(Integer.toString(pressedKey.get(0)) + "_" + Integer.toString(frame));
            }else {
                frame = 0; // Reset frame no. for standing animation
                labelMain.setText(Integer.toString(frame));
            }
        });
        timer.start();
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
