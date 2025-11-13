package compsci.labs;

import java.util.*;

import compsci.Utils;

public class EncryptionMachine {
    public static final String k_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 .,/'\"\\[]{}-=_+()?!<>`~@#$^&*:";



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

    public static String decrypt(int shift, char[] encrypted) {
        char[] decrypted = new char[encrypted.length];
        for (int i = 0; i < decrypted.length; i++) {
            if (encrypted[i] == ';') {
                decrypted[i] = ' ';
            } else {

                decrypted[i] = applyShift(encrypted[i], -shift);
            }
        }
        return String.valueOf(decrypted);
    }

    public static void main(String[] args) {
        Arguments a = new Arguments(args, "cipherMachine.jar");
        if (a.shift != -1) {
            if (!(a.toEncrypt.isEmpty())) {
                System.out.println(encrypt(a.shift, a.toEncrypt.toCharArray()));
            }
            if (!a.toDecrypt.isEmpty()) {
                System.out.println(decrypt(a.shift, a.toDecrypt.toCharArray()));
            }
        } else {
            Scanner sc = new Scanner(System.in);
            int shift = Integer
                    .parseInt(
                            Utils.prompt("Welcome to the encryption machine!\nPlease enter your preferred shift amount.", sc))
                    % (k_ALPHABET.length() - 1);
            char[] old = Utils.prompt("Enter the sentence to be encrypted.", sc).toCharArray();
            String encrypted = encrypt(shift, old);
            if (encrypted.contains(";")) {
                System.out.println(
                        "WARNING: Your input contains unsupported characters.\nThey will be replaced by a space in their decrypted form.");
            }
            System.out.printf("Encrypted string: %s%n", encrypted);
            String dc = Utils.prompt("Would you like to decrypt the sentence back into its original form? y/N", sc);
            if (dc.equalsIgnoreCase("y")) {
                System.out.println(decrypt(shift, encrypted.toCharArray()));
            }
        }

    }

    public static class Arguments {
            public String toEncrypt = "";
            public String toDecrypt = "";
            public int shift = -1;
            /**
             * Get a typed representation of the program arguments. Also does input validation on most keys.
             * @param args the program arguments
             */
            public Arguments(String[] args, String programName) {
                Map<String, String> allowedArgs = new HashMap<String, String>();

                allowedArgs.put("--shift", "shift to use in encryption/decryption");
                allowedArgs.put("--encrypt", "Encrypt string");
                allowedArgs.put("--decrypt", "Decrypt string");
                allowedArgs.put("--help", "Display this help command");

                for (int i = 0; i < args.length; i++) {
                    String arg = args[i].strip();

                    if (allowedArgs.containsKey(arg)) {
                        if (i == args.length - 1 || allowedArgs.containsKey(args[i + 1])) {
                            // cannot have a value
                            if (!arg.equals("--help")) {
                                throw new IllegalArgumentException(String.format("Missing value for argument %s", arg));
                            }
                        }
                        arg = arg.replace("--", "");
                        switch (arg) {
                            case "help": {
                                System.out.printf("Usage: %s [options...]%n", programName);
                                try {
                                    Iterator<Map.Entry<String, String>> iterator = allowedArgs.entrySet().iterator();
                                    while (allowedArgs.entrySet().iterator().hasNext()) {
                                        Map.Entry<String, String> entry = iterator.next();
                                        System.out.println(Utils.pad(" " + entry.getKey(), 15) + entry.getValue());
                                    }
                                } catch (Exception e) {
                                    if (!(e instanceof NoSuchElementException)) {
                                        System.err.println(e.getStackTrace());
                                    }
                                }

                                System.exit(0);
                            }
                            case "encrypt": {
                                this.toEncrypt = args[i+1];
                                break;
                            }
                            case "decrypt": {
                                this.toDecrypt = args[i+1];
                                break;
                            }
                            case "shift": {
                                this.shift = Integer.parseInt(args[i+1]);
                                break;
                            }
                        }
                        // Only increment if we haven't already consumed an extra argument
                        if (!arg.equals("debug") || i == args.length - 1 || args[i + 1].startsWith("--")) {
                            i++;
                        }
                    } else {
                        throw new IllegalArgumentException("Unknown argument: " + args[i]);
                    }
                }
            }
        }
}
