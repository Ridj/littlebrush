package littlebrush;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class MyBrush_ver3 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }

    private static void createGUI() {
        JFrame f = new JFrame("My sad little brushy");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyBrushPanel());
        f.pack();
        f.setVisible(true);
        f.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {
                    f.repaint();
                }
            }
        });
    }
}

class MyBrushPanel extends JPanel {

    private int squareX = 0;
    private int squareY = 0;
    private int squareW = 4;
    private int squareH = 4;

    public MyBrushPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });
    }

    private void moveSquare(int x, int y) {
        int OFFSET = 1;

        if ((squareX!=x) || (squareY!=y)) {
            squareX = x;
            squareY = y;
            repaint(squareX, squareY, squareW + OFFSET, squareH + OFFSET);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color aColor = new Color(0xFF0096);
        g.drawString("Some custom Panel", 10, 20);
        g.setColor(aColor);
        g.fillRect(squareX, squareY, squareW, squareH);
    }
}