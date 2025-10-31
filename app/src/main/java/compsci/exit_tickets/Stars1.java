package compsci.exit_tickets;

public class Stars1 {
    static final boolean k_PRINT = true;
    private static String draw(char c, int count, boolean newLine) {
        String s = String.valueOf(c).repeat(count) + (newLine ? "\n" : "");
        if (k_PRINT) {
            System.out.print(s);
        }
        return s;
    }
    private static String draw(char c) {
        return draw(c, 1, false);
    }
    private static String line(char c, int count) {
        return draw(c, count, true);
    }
    private static String box(char perim, int x, int y) {
        StringBuilder s = new StringBuilder(line(perim, x));
        for (int i = 0; i < y-2; i++) {
            s.append(draw(perim));
            s.append(draw(' ', x-2, false));
            s.append(draw(perim, 1, true));
        }
        s.append(line(perim, x));
        return s.toString();
    }
    public static void main(String[] args) {
        // // first line of 13
        // line('*', 13);
        // System.out.println();
        // // second line of 7
        // line('*', 7);
        // System.out.println();
        // // third line of 35
        // line('*', 35);
        // System.out.println();
        // // first box, 10 by 3
        // box('*', 10, 3);
        // System.out.println();
        // // second box, 5 by 4
        // box('*', 5, 4);
    }
}