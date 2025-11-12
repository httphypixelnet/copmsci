package compsci;

public class Utils {
    /** 
     * Left pad a string with spaces
     * @param input the string to pad
     * @param totalLength the total length of the padded string
     * @return the padded string
    */
    public static String pad(String input, int totalLength) {
        return pad(input, totalLength, ' ');
    }
    /**
         * Left pad a string using the padding param
         * @param input the string to pad using the padding param
         * @param totalLength the total length of the padded string
         * @param padding the padding to use in the padded string
         * @return the padded string
         */
        public static String pad(String input, int totalLength, char padding) {
            if (input.length() >= totalLength) {
                return input;
            }
            // stop using StringBuilder and inefficient for loop
            return input + String.valueOf(padding).repeat(totalLength - input.length());
        }
}
