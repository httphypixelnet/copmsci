package compsci;

import java.util.Scanner;

import compsci.exit_tickets.ASCIISign;

public class App {
    public static void main(String[] args) {
        // Lyrics.run();
        // ASCII.run();
        // ExitTicket.run();
        ASCIISign.run();
        Scanner sc = new Scanner(System.in);
        double rating = sc.nextDouble();
        System.out.println(
        Math.max((int) rating, (int) (rating+.5)));
    }
}
