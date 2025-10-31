package compsci.labs;
public class ASCII {
    private final static String A = 
                "   ___   _      _         _   _           CRLF" +
                "  / __| (_)___ | |__  ___| |_| |_ ___ _ _ CRLF" +
                " | (__  | (_-< | '_ \\/ -_)  _|  _/ -_) '_|CRLF" +
                "  \\___| |_/__/ |_.__/\\___|\\__|\\__\\___|_|  CRLF";
    private static void partA() {
        for (String line : A.split("CRLF")) {
            System.out.println(line);
        }
    }
    private static void partB() {
        SpaceNeedle.run();
    }
    public static void run() {
        partA();
        System.out.println();
        partB();
    }
}
