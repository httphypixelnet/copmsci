package compsci.exit_tickets;
public class Sign {
    // class constant instead of arg parsing sighhh
    static final int k_SIZE = 9;
    public static void main(String[] args) {
        // configurable size
        drawShape(k_SIZE, k_SIZE*4);
    }
    public static String drawLine(int size, int width) {
        StringBuilder bufferLine = new StringBuilder();
        bufferLine.append("#");
        bufferLine.append("=".repeat(width));
        bufferLine.append("#");
        bufferLine.append("\n");
        return bufferLine.toString();
    }



    public static void drawShape(int size, int width) {
        StringBuilder outputBuilder = new StringBuilder(drawLine(size, width));
        for (int i = 0; i < size; i++) {
            int periodCount = i*4;
            int spaceCount = (width-periodCount-4)/2;
            appendLine(outputBuilder, spaceCount, periodCount);
            if (!(i == size-1)) {
                outputBuilder.append("\n");
            }
        }
        System.out.println(outputBuilder.toString() + "\n" + outputBuilder.reverse().toString().replaceAll("><", "<>"));
    }
    
    private static void appendLine(StringBuilder builder, int spaceCount, int periodCount) {
        builder.append("|");
        builder.append(" ".repeat(spaceCount));
        builder.append("<>");
        builder.append(".".repeat(periodCount));
        builder.append("<>");
        builder.append(" ".repeat(spaceCount));
        builder.append("|");
    }
}
