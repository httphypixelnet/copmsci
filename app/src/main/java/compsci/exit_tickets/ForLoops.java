package compsci.exit_tickets;

public class ForLoops {
    private static void first() {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    private static void second() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(".");
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        first();
        System.out.println();
        second();
    }
}
