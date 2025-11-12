package compsci;

public class Utils {
    /** 
     * Left pad a string with spaces
     * @param input
     * @param totalLength
     * @return the padded string
    */
    public static String pad(String input, int totalLength) {
        return pad(input, totalLength, ' ');
    }
    /**
         * Left pad a string using the padding param
         * @param input
         * @param totalLength
         * @param padding the 
         * @return the padded string
         */
        public static String pad(String input, int totalLength, char padding) {
            if (input.length() >= totalLength) {
                return input;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(input);
            for (int i = 0; i < totalLength - input.length(); i++) {
                sb.append(padding);
            }
            return sb.toString();
        }

}
