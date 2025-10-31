package compsci.labs;

import java.awt.*;
import compsci.drawing.DrawingPanel;

public class CafeWall {
    public static final int MORTAR = 2;
    
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(650, 400);
        Graphics g = panel.getGraphics();
        panel.setBackground(Color.GRAY);
        
        drawRow(g, 0, 0, 4, 20);
        drawRow(g, 50, 70, 5, 30);
        
        drawGrid(g, 10, 150, 4, 25, 0);
        drawGrid(g, 250, 200, 3, 25, 10);
        drawGrid(g, 425, 180, 5, 20, 10);
        drawGrid(g, 400, 20, 2, 35, 35);
    }

    public static void drawRow(Graphics g, int x, int y, int pairs, int size) {
        for (int i = 0; i < pairs; i++) {
            g.setColor(Color.BLACK);
            g.fillRect(x + i * 2 * size, y, size, size);
            
            g.setColor(Color.BLUE);
            g.drawLine(x + i * 2 * size, y, 
                      x + i * 2 * size + size, y + size);
            g.drawLine(x + i * 2 * size + size, y, 
                      x + i * 2 * size, y + size);
            g.setColor(Color.WHITE);
            g.fillRect(x + i * 2 * size + size, y, size, size);
        }
    }
    
    public static void drawGrid(Graphics g, int x, int y, int pairs, int size, int offset) {
        for (int row = 0; row < pairs * 2; row++) {
            int currentOffset = (row % 2 == 0) ? 0 : offset;
            int currentY = y + row * (size + MORTAR);
            drawRow(g, x + currentOffset, currentY, pairs, size);
        }
    }
}
