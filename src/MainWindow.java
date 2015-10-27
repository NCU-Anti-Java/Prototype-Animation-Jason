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
    private int lastState;
    private ImageIcon[][] img;

    private ArrayList<Integer> pressedKey;
    private boolean spacePressed;

    public MainWindow() {
        LoadImage();

        pressedKey = new ArrayList<>();

        contentPane.setFocusable(true);
        contentPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int key = getDirection(e); // Get direction of pressed key
                if (key == -1) return; // Ignore invalid key

                if (key != 4)
                    pressedKey.add(key); // Log current pressing key
                else
                    spacePressed = true; // Space key is pressed (should not be added into list)
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                int key = getDirection(e); // Get direction of released key
                if (key == -1) return; // Ignore invalid key

                if (key == 4) { // Handle space key released
                    spacePressed = false; // Just reset spacePressed
                    return;
                }

                // If key of current direction is released, direction will be changed
                if (pressedKey.get(0) == key) {
                    frame = 0; // Reset frame no. for changing to a new direction
                    lastState = key; // Keep direction before changing direction
                }

                pressedKey.removeIf(x -> x == key); // Remove released key from list
            }
        });

        // Timer for animation
        Timer timer = new Timer(100, e -> draw());
        timer.start();
    }

    private void LoadImage(){
        img = new ImageIcon[4][8];
        for (int i = 0; i <= 3; i++)
            for (int j = 0; j <= 7; j++)
                img[i][j] = new ImageIcon("image/" + Integer.toString(i) + "_" + Integer.toString(j) + ".gif");
    }

    private void draw() {
        // Handle animation
        if (pressedKey.size() != 0) {
            // Increase frame no.
            frame++;
            if (frame >= 8) frame = 0;

            if (spacePressed) frame = 0; // Space key is pressed! Freeze!
            labelMain.setIcon(img[pressedKey.get(0)][frame]);
        } else {
            labelMain.setIcon(img[lastState][frame]);
        }
    }

    private int getDirection(KeyEvent e) {
        // Return direction of pressed key in number

        // Left: 37. Up: 38. Right: 39. Down: 40. Space: 32
        // Remapped to: Left: 0. Up: 1. Right: 2. Down: 3. Space: 4
        int code = e.getKeyCode();
        if (code == 32) return 4;
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
