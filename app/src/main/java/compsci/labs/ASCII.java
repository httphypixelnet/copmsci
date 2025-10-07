package compsci.labs;
public class ASCII {
    private final static String A = 
                " _____              _       _          _   _            CRLF" +
                "/  __ \\ _     _    (_)     | |        | | | |           CRLF" +
                "| /  \\/| |_ _| |_   _ ___  | |__   ___| |_| |_ ___ _ __ CRLF" +
                "| |  |_   _|_   _| | / __| | '_ \\ / _ \\ __| __/ _ \\ '__|CRLF" +
                "| \\__/\\|_|   |_|   | \\__ \\ | |_) |  __/ |_| ||  __/ |   CRLF" +
                " \\____/            |_|___/ |_.__/ \\___|\\__|\\__\\___|_|   CRLF";
    private final static String B = "";
    private static void partA() {
        for (String line : A.split("CRLF")) {
            System.out.println(line);
        }
    }
    private static void partB() {
        
    }
    public static void main(String[] args) {
        partA();
        partB();
    }
}
