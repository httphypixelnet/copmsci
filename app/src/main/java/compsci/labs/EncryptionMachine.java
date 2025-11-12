package compsci.labs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import compsci.Arguments;

public class EncryptionMachine {
    public static final String k_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 .,/'\"\\[]{}-=_+()?!<>`~@#$^&*:";

    public static String prompt(String prompt, Scanner scanner) {
        System.out.print(prompt + "\n > ");
        return scanner.nextLine();
    }

    public static char applyShift(char c, int shift) {
        int ind;
        if (Math.signum(shift) == 1) {
            ind = (k_ALPHABET.indexOf(c) + shift) % k_ALPHABET.length();
        } else {
            int tester = (k_ALPHABET.indexOf(c) + shift);
            ind = (tester > 0 ? tester : k_ALPHABET.length() + tester);
        }
        return ind == k_ALPHABET.length() ? k_ALPHABET.charAt(0) : k_ALPHABET.charAt(ind);
    }

    public static String encrypt(int shift, char[] old) {
        char[] newChars = new char[old.length];
        for (int i = 0; i < newChars.length; i++) {
            char oldChar = old[i];
            int index = k_ALPHABET.indexOf(oldChar);
            if (index == -1) {
                newChars[i] = ';';
            } else {
                newChars[i] = applyShift(oldChar, shift);
            }
        }
        return String.valueOf(newChars);
    }

    public static String decrypt(int shift, char[] encryped) {
        char[] decrypted = new char[encryped.length];
        for (int i = 0; i < decrypted.length; i++) {
            if (encryped[i] == ';') {
                decrypted[i] = ' ';
            } else {

                decrypted[i] = applyShift(encryped[i], -shift);
            }
        }
        return String.valueOf(decrypted);
    }

    public static void main(String[] args) {
        Arguments a = new Arguments(args, "cipherMachine.jar");
        if (a.shift != -1) {
            if (!(a.toEncrypt.equals(""))) {
                System.out.println(encrypt(a.shift, a.toEncrypt.toCharArray()));
            }
            if (!a.toDecrypt.equals("")) {
                System.out.println(decrypt(a.shift, a.toDecrypt.toCharArray()));
            }
        } else {
            Scanner sc = new Scanner(System.in);
            int shift = Integer
                    .parseInt(
                            prompt("Welcome to the encryption machine!\nPlease enter your preferred shift amount.", sc))
                    % (k_ALPHABET.length() - 1);
            char[] old = prompt("Enter the sentence to be encrypted.", sc).toCharArray();
            String encrypted = encrypt(shift, old);
            if (encrypted.contains(";")) {
                System.out.println(
                        "WARNING: Your input contains unsupported characters.\nTry using only alphanumeric characters.");
            }
            System.out.println(String.format("Encrypted string: %s", encrypted));
            String dc = prompt("Would you like to decrypt the sentance back into its original form? y/N", sc);
            if (dc.equalsIgnoreCase("y")) {
                System.out.println(decrypt(shift, encrypted.toCharArray()));
            }
        }

    }
}
