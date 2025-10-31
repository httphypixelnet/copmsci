package compsci;

import compsci.labs.ASCII;
import compsci.labs.CafeWall;
import compsci.labs.Doodle;
import compsci.labs.Lyrics;

public class App {
    public static void main(String[] args) {
        Lyrics.run();
        ASCII.run();
        // thread because drawingpanel is blocking
        new Thread(() -> CafeWall.run()).start();
        new Thread(() -> Doodle.run()).start();
    }
}
