package compsci.exit_tickets;

public class ASCIISign {
    private static String line() {
        return "+--------+\n";
    }

    private static String top() {
        return "  ______\n" +
                " /      \\\n" +
                "/        \\\n";
    }

    private static String stop() {
        return "|  STOP  |\n";
    }

    private static String bottom() {
        return "\\        /\n" +
                " \\______/\n";
    }

    private static void empty() {
        System.out.print("\n");
    }

    private static void first() {
        System.out.print(
                top() +
                        bottom());
    }

    private static void second() {
        System.out.print(
                bottom() +
                        line());
    }

    private static void sign() {
        System.out.print(
                top() +
                        stop() +
                        bottom());
    }

    private static void third() {
        System.out.print(
                top() +
                        line());
    }

    public static void run() {
        first();
        empty();
        second();
        empty();
        sign();
        empty();
        third();
    }
}
