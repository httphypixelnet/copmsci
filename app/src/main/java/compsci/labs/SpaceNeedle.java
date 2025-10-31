package compsci.labs;

public class SpaceNeedle {
    public static final int SIZE = 4;

    public static void run() {
        drawNeedle();
        drawTopDish();
        drawMiddleSection();
        drawNeedle();
        drawBody();
        drawTopDish();
    }
    public static void drawNeedle() {
        for (int line = 1; line <= SIZE; line++) {
            System.out.println(" ".repeat(SIZE * 3) + "||");
        }
    } 

    public static void drawTopDish() {
        for (int line = 0; line < SIZE; line++) {
            System.out.print(" ".repeat(Math.abs(SIZE-1) * 3 - line*3));
            System.out.print("__/");
            System.out.print(":".repeat(line * 3));
            System.out.print("||");
            System.out.print(":".repeat(line * 3));
            System.out.println("\\__");
        }
        System.out.println("|" + "\"".repeat(SIZE * 6) + "|");
    }

    public static void drawMiddleSection() {
        for (int line = 0; line < SIZE; line++) {
            System.out.print(" ".repeat(line*2));
            System.out.print("\\_/");
            System.out.print("\\/".repeat(SIZE*3-line*2 - 2));
            System.out.println("\\_/");
        }
    }

    public static void drawBody() {
        for (int line = 0; line < SIZE * SIZE; line++) {
            System.out.print(" ".repeat(SIZE * 2 + 1));
            System.out.print("|");
            System.out.print("%".repeat(SIZE - 2));
            System.out.print("||");
            System.out.print("%".repeat(SIZE - 2));
            System.out.println("|");
        }
    }
}
