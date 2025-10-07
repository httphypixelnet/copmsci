package compsci.labs;

public class Lyrics {

    private static void blank() {
        System.out.println("");
    }

    private static void open(String animal, String punctuation) {
        System.out.println("There was an old woman who swallowed a " + animal + punctuation);
    }

    private static void closing() {
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.");
        blank();
    }

    private static void spider() {
        System.out.println("She swallowed the spider to catch the fly,");
    }

    private static void chainFromBird() {
        System.out.println("She swallowed the bird to catch the spider,");
        spider();
        closing();
    }

    private static void chainFromCat() {
        System.out.println("She swallowed the cat to catch the bird,");
        chainFromBird();
    }

    private static void dogChainAfterHogLine() {
        System.out.println("She swallowed the dog to catch the cat,");
        System.out.println("She swallowed the cat to catch the bird,");
        System.out.println("She swallowed the bird to catch the spider,");
        spider();
        closing();
    }

    private static void firstVerse() {
        open("fly", ".");
        closing();
    }

    private static void secondVerse() {
        open("spider", ",");
        System.out.println("That wriggled and iggled and jiggled inside her.");
        spider();
        closing();
    }

    private static void thirdVerse() {
        open("bird", ",");
        System.out.println("How absurd to swallow a bird.");
        chainFromBird();
    }

    private static void fourthVerse() {
        open("cat", ",");
        System.out.println("Imagine that to swallow a cat.");
        chainFromCat();
    }

    private static void fifthVerse() {
        open("dog", ",");
        System.out.println("What a hog to swallow a dog.");
        dogChainAfterHogLine();
    }

    private static void sixthVerse() {
        open("tiger", ",");
        System.out.println("She swallowed the tiger to catch the dog,");
        System.out.println("What a hog to swallow a dog.");
        dogChainAfterHogLine();
    }

    private static void seventhVerse() {
        open("horse", ",");
        System.out.println("She died of course.");
        blank();
    }

    public static void run() {
        firstVerse();
        secondVerse();
        thirdVerse();
        fourthVerse();
        fifthVerse();
        sixthVerse();
        seventhVerse();
    }
}
