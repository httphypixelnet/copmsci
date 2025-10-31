package compsci.drawing;

// This is a simple drawing program that draws three versions of a particular
// subfigure on the screen.  In this unstructured version of the program
// all of the drawing commands appear in the main method.

import java.awt.*;

public class Draw1 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(500, 400);
        panel.setBackground(Color.CYAN);
        Graphics g = panel.getGraphics();
        // upper-left is (50, 50)
        // lower-right is (150, 150)
        g.setColor(Color.RED);
        g.fillRect(50, 50, 100, 100);
        g.setColor(Color.GREEN);
        g.fillOval(50, 50, 100, 100);
        g.setColor(Color.WHITE);
        g.drawRect(50, 50, 100, 100);
        g.drawOval(50, 50, 100, 100);
        g.drawLine(50, 50, 150, 150);
        g.drawLine(50, 150, 150, 50);

        // upper-left is (100, 200)
        // lower-right is (200, 300)
        /* YOUR CODE HERE*/

        // upper-left is (250, 150)
        // lower-right is (325, 225)
        /* YOUR CODE HERE*/
    }
}