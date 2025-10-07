package compsci;

public class S {
    private static void p(String text) {
        System.out.println(text);
    }

    private static void refrain() {
        p("I don't know why it wouldn't compile,");
        p("My TA just smiled.");
        p("");
    }

    private static void codingLine() {
        p("I added System.out.println(\"I <3 coding\"),");
    }

    private static void escapeLine() {
        p("I added a backslash to escape the quotes,");
    }

    private static void methodLine() {
        p("I added a main method with its String[] args,");
    }

    private static void classLine() {
        p("I added a public class and called it Scum,");
    }

    private static void first() {
        p("I once wrote a program that wouldn't compile");
        refrain();
    }

    private static void second() {
        p("My program did nothing");
        p("So I started typing.");
        codingLine();
        refrain();
    }

    private static void third() {
        p("\"Parse error,\" cried the compiler");
        p("Luckily I'm such a code baller.");
        escapeLine();
        codingLine();
        refrain();
    }

    private static void fourth() {
        p("Now the compiler wanted an identifier");
        p("And I thought the situation was getting dire.");
        methodLine();
        escapeLine();
        codingLine();
        refrain();
    }

    private static void fifth() {
        p("Java complained it expected an enum");
        p("Boy, these computers really are dumb!");
        classLine();
        methodLine();
        escapeLine();
        codingLine();
        refrain();
    }

    public static void main(String args[]) {
        first();
        second();
        third();
        fourth();
        fifth();
    }
}
