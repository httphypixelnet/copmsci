package compsci.exit_tickets;

public class ExitTicket {

    private static void first() {
        System.out.println("This program prints a");

        System.out.println(
                "quote from the Gettysburg Address.");
        System.out.println(
                "\"Four score and seven years ago,");
        System.out.println(
                "our 'fore fathers' brought forth on");
        System.out.println(
                "this continent a new nation.\"");
        System.out.println(
                " ");
        System.out.println(
                "A \"quoted\" String is");
        System.out.println(
                "'much' better if you learn");
        System.out.println(
                "the rules of \"escape sequences.\"");
        System.out.println(
                " ");
        System.out.println(
                "Also, \"\" represents an empty String.");
        System.out.println(
                "Don't forget: use \\\" instead of \" !");
        System.out.println(
                "'' is not the same as \"");
    }

    // method run because this is not the main method of the program
    public static void run() {
        first();
    }
}
