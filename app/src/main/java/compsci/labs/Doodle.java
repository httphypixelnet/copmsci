package compsci.labs;

import compsci.drawing.DrawingPanel;
import java.awt.*;

public class Doodle {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(300, 300);
        Graphics g = panel.getGraphics();
        panel.setBackground(Color.WHITE);
        
        g.setColor(Color.BLUE);
        g.fillOval(50, 50, 200, 200);
        
        g.setColor(Color.YELLOW);
        g.fillOval(90, 100, 40, 40);
        g.fillOval(170, 100, 40, 40);
        
        g.setColor(Color.BLACK);
        g.drawArc(100, 120, 100, 80, 180, 180);
        
        g.setColor(Color.RED);
        g.fillRect(120, 30, 60, 30);
        
        g.setColor(Color.GREEN);
        for (int i = 0; i < 5; i++) {
            g.drawLine(30 + i * 10, 280, 30 + i * 10, 250);
        }
    }
}
