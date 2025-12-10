package compsci.labs.babynames;

import compsci.drawing.DrawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import static compsci.Utils.prompt;

public class BabyNames {
    private static final int DECADES = 14;
    private static final int START_YEAR = 1880;
    private static final int YEARS_PER_DECADE = 10;
    private static final int DECADE_WIDTH = 70;
    private static final int GRAPH_TOP_MARGIN = 25;
    private static final int GRAPH_BOTTOM_MARGIN = 25;
    private static final int PLOT_HEIGHT = 500;
    private static final int PANEL_HEIGHT = GRAPH_TOP_MARGIN + PLOT_HEIGHT + GRAPH_BOTTOM_MARGIN;
    private static final int PANEL_WIDTH = DECADE_WIDTH * DECADES;
    private static final int LABEL_OFFSET_X = 2;
    private static final int LABEL_OFFSET_Y = 4;

    private static class BetterScanner {
        Scanner handler;
        BetterScanner(String source) {
            this.handler = new Scanner(source);
        }
        BetterScanner(File source) throws IOException {
            this.handler = new Scanner(source, StandardCharsets.UTF_8);
        }
        // type safety? i hardly know 'er
        private <T> T nextToken(Pattern pattern) {
            boolean failed = !handler.hasNext(pattern);
            if (failed) throw new NoSuchElementException();
            try {
                switch (pattern.pattern()) {
                    case "[A-Za-z]+" -> {
                        return (T) handler.next(pattern);
                    }
                    case "[MF]" -> {
                        return (T) String.valueOf(handler.next(pattern).charAt(0));
                    }
                    case "\\d+" -> {
                        return (T) Integer.valueOf(handler.next(pattern));
                    }
                    default -> throw new IllegalArgumentException("Unsupported pattern provided");
                }
            } catch (NumberFormatException | ClassCastException e) {
                throw new ClassCastException("Invalid pattern provided");
            } catch (NoSuchElementException ignored) {}
            throw new RuntimeException("This should never happen lol");
        }
    }

    private static class LineParseResult {
        // Jax M  0 	0 0 0	0  0 0	0 0 0 0  0 0 347
        String name;
        String gender;
        int[] counts;

        // added: serialize current values to a readable string
        public String serialize() {
            String countsStr = (counts == null) ? "null" : java.util.Arrays.toString(counts);
            return "LineParseResult{name=" + name + ", gender=" + gender + ", counts=" + countsStr + "}";
        }

        @Override
        public String toString() {
            return serialize();
        }
    }
    private static class FileParseResult {
        List<LineParseResult> maleLines;
        List<LineParseResult> femaleLines;

        public FileParseResult() {
            maleLines = new java.util.ArrayList<>();
            femaleLines = new java.util.ArrayList<>();
        }
        public List<LineParseResult> getFemaleLines() {
            return femaleLines;
        }
        public List<LineParseResult> getMaleLines() {
            return maleLines;
        }
        public void parseResult(LineParseResult result) {
            if (result.gender.equals("M")) {
                maleLines.add(result);
            } else {
                femaleLines.add(result);
            }
        }
        public FileParseResult merge(FileParseResult other) {
            maleLines.addAll(other.maleLines);
            femaleLines.addAll(other.femaleLines);
            return this;
        }
    }

    private static LineParseResult parseLine(String line) {

        LineParseResult result = new LineParseResult();
        BetterScanner sc = new BetterScanner(line);
        try {
            result.name = sc.nextToken(Pattern.compile("[A-Za-z]+"));
            result.gender = sc.nextToken(Pattern.compile("[MF]"));
            result.counts = new int[DECADES];
            for (int i = 0; i < DECADES; i++) {
                result.counts[i] = sc.nextToken(Pattern.compile("\\d+"));
            }
        }
        catch (NoSuchElementException ignored) {
            System.out.println("Invalid line: " + line);
            System.out.println(result.serialize());
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        // load files into memory
        FileParseResult fileParseResult = parseFile("names.txt");
//        fileParseResult.merge(parseFile("names2.txt"));
        Scanner userInput = new Scanner(System.in);
        String name = prompt("What name would you like to search?", userInput).trim();
        if (name.isEmpty()) {
            System.out.println("Name is required to search.");
            return;
        }
        String genderAnswer = prompt("What gender would you like to search?", userInput).trim();
        if (genderAnswer.isEmpty()) {
            System.out.println("Gender is required to search.");
            return;
        }
        char gender = Character.toUpperCase(genderAnswer.charAt(0));
        if (gender != 'M' && gender != 'F') {
            System.out.println("Please enter gender as M or F.");
            return;
        }

        LineParseResult match = findLine(fileParseResult, name, gender);
        if (match == null) {
            System.out.println("No data for " + name + " " + gender + ".");
            return;
        }

        drawGraph(match);
        System.out.println("Graphed " + match.name + " " + match.gender + ".");
    }

    private static LineParseResult findLine(FileParseResult data, String name, char gender) {
        Stream<LineParseResult> stream = (gender == 'M') ? data.getMaleLines().stream() : data.getFemaleLines().stream();
        return stream
                .filter(line -> line.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private static void drawGraph(LineParseResult line) {
        DrawingPanel panel = new DrawingPanel(PANEL_WIDTH, PANEL_HEIGHT);
        Graphics g = panel.getGraphics();
        drawGrid(g);
        drawPlot(g, line);
    }

    private static void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        int topY = GRAPH_TOP_MARGIN;
        int bottomY = PANEL_HEIGHT - GRAPH_BOTTOM_MARGIN;
        g.drawLine(0, topY, PANEL_WIDTH, topY);
        g.drawLine(0, bottomY, PANEL_WIDTH, bottomY);
        for (int i = 0; i < DECADES; i++) {
            int x = i * DECADE_WIDTH;
            g.drawLine(x, 0, x, PANEL_HEIGHT);
            int labelYear = START_YEAR + (i * YEARS_PER_DECADE);
            g.drawString(String.valueOf(labelYear), x + LABEL_OFFSET_X, PANEL_HEIGHT - 1);
        }
    }

    private static void drawPlot(Graphics g, LineParseResult line) {
        g.setColor(Color.RED);
        int prevX = 0;
        int prevY = rankToY(line.counts[0]);
        drawPointLabel(g, line, 0, prevX, prevY);
        for (int i = 1; i < line.counts.length; i++) {
            int x = i * DECADE_WIDTH;
            int y = rankToY(line.counts[i]);
            g.drawLine(prevX, prevY, x, y);
            drawPointLabel(g, line, i, x, y);
            prevX = x;
            prevY = y;
        }
    }

    private static void drawPointLabel(Graphics g, LineParseResult line, int decadeIndex, int x, int y) {
        String rankText = String.valueOf(line.counts[decadeIndex]);
        String label = line.name + " " + line.gender + " " + rankText;
        int labelY = Math.max(GRAPH_TOP_MARGIN, y - LABEL_OFFSET_Y);
        g.drawString(label, x + LABEL_OFFSET_X, labelY);
    }

    private static int rankToY(int rank) {
        if (rank <= 0) {
            return GRAPH_TOP_MARGIN + PLOT_HEIGHT;
        }
        int offset = (rank - 1) / 2;
        return GRAPH_TOP_MARGIN + offset;
    }

    private static FileParseResult parseFile(String fileName) throws IOException {
        BetterScanner sc = new BetterScanner(new File(fileName));
        FileParseResult fileParseResult = new FileParseResult();
        int loops = 0;
        while (sc.handler.hasNextLine()) {
            loops++;
            String nextLine = sc.handler.nextLine();
            if (nextLine.isBlank())  {System.out.println(loops);break;}
            fileParseResult.parseResult(parseLine(nextLine));
        }
        sc.handler.close();
        return fileParseResult;
    }
}