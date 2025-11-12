package compsci;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
public class Arguments {
        /**
         * Get a typed representation of the program arguments. Also does input validation on most keys.
         * @param args the program arguments
         */
        public String toEncrypt = "";
        public String toDecrypt = "";
        public int shift = -1;
        public Arguments(String[] args, String programName) {
            Map<String, String> allowedArgs = new HashMap<String, String>();

            allowedArgs.put("--shift", "shift to use in encryption/decryption");
            allowedArgs.put("--encrypt", "Encrypt string");
            allowedArgs.put("--decrypt", "Decrypt string");
            allowedArgs.put("--help", "Display this help command");

            for (int i = 0; i < args.length; i++) {
                String arg = args[i].strip();
                if (args.length == 0) {
                    System.exit(1);
                }

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
                            System.out.println(String.format("Usage: %s [options...]", programName));
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
