package compsci.labs;

import java.io.*;
import java.util.*;

public class MadLibs {
    public static void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the game of Mad Libs.");
        System.out.println("I will ask you to provide various words");
        System.out.println("and phrases to fill in a story.");
        System.out.println("The result will be written to an output file.");
        System.out.println();

        boolean done = false;
        while (!done) {
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            String choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "C":
                    createMadLib(sc);
                    break;
                case "V":
                    viewMadLib(sc);
                    break;
                case "Q":
                    done = true;
                    break;
                
            }
        }
    }

    /**
     * @param sc the Scanner for reading user input
     */
    public static void createMadLib(Scanner sc) {
        File inputFile = getInputFile(sc);
        System.out.print("Output file name: ");
        String outputFileName = sc.nextLine();
        File outputFile = new File(outputFileName);

        try {
            assert inputFile != null;
            Scanner fileScanner = new Scanner(inputFile);
            PrintStream output = new PrintStream(outputFile);

            processFile(sc, fileScanner, output);

            fileScanner.close();
            output.close();

            System.out.println("Your mad-lib has been created!");
        } catch (FileNotFoundException e) {
            System.out.println("Error processing file.");
        }
    }

    /**
     * @param sc the Scanner for reading user input
     * @return the File object for the valid input file
     */
    public static File getInputFile(Scanner sc) {
        System.out.print("Input file name: ");
        String fileName = sc.nextLine();
        File file = new File(fileName);

        while (!file.exists()) {
            System.out.print("File not found. Try again: ");
            fileName = sc.nextLine();
            if (fileName.equals("q") || fileName.equals("quit")) {
                return null;
            }
            file = new File(fileName);
        }

        return file;
    }
    public static void processFile(Scanner sc, Scanner fileScanner, PrintStream output) {
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Scanner lineScanner = new Scanner(line);

            while (lineScanner.hasNext()) {
                String token = lineScanner.next();

                if (isPlaceholder(token)) {
                    String replacement = getPlaceholderReplacement(sc, token);
                    output.print(replacement + " ");
                } else {
                    output.print(token + " ");
                }
            }

            output.println();
            lineScanner.close();
        }
    }

    /**
     * Determines if a token is a placeholder (starts with < and ends with >).
     *
     * @param token the token to check
     * @return true if the token is a placeholder, false otherwise
     */
    public static boolean isPlaceholder(String token) {
        return token.startsWith("<") && token.endsWith(">");
    }

    /**
     * @param sc the Scanner for reading user input
     * @param placeholder the placeholder token including < and >
     * @return the user's replacement text
     */
    public static String getPlaceholderReplacement(Scanner sc, String placeholder) {
        
        String placeholderText = placeholder.substring(1, placeholder.length() - 1);

        
        placeholderText = placeholderText.replace('-', ' ');

        
        String article = startsWithVowel(placeholderText) ? "an" : "a";

        System.out.print("Please type " + article + " " + placeholderText + ": ");
        return sc.nextLine();
    }

    /**
     * @param text the text to check
     * @return true if the text starts with a vowel, false otherwise
     */
    public static boolean startsWithVowel(String text) {
        if (text.isEmpty()) {
            return false;
        }

        char firstChar = Character.toLowerCase(text.charAt(0));
        return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' ||
               firstChar == 'o' || firstChar == 'u';
    }

    /**
     * Views a previously created mad lib by reading and displaying the contents of a file.
     *
     * @param sc the Scanner for reading user input
     */
    public static void viewMadLib(Scanner sc) {
        File file = getInputFile(sc);

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
        }
    }
}
