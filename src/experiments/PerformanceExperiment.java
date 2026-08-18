package experiments;

import algorithms.CommandEditor;
import algorithms.SnapshotEditor;
import algorithms.TextEditor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class PerformanceExperiment {

    private static final int[] TEXT_LENGTHS = {100, 1_000, 10_000, 100_000};
    private static final int[] ACTION_COUNTS = {100, 1_000, 10_000};
    private static final int REPETITIONS = 5;

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("results/experiment_results.csv"))) {
            out.println("TextLength,ActionCount,Algorithm,AvgTimeNs,AvgPush,AvgPop,AvgComparisons,AvgAuxChars");
            for (int textLen : TEXT_LENGTHS) {
                for (int actionCount : ACTION_COUNTS) {
                    runTrial(textLen, actionCount, "A", out);
                    runTrial(textLen, actionCount, "B", out);
                }
            }
        }
        System.out.println("Done. Results written to results/experiment_results.csv");
    }

    private static void runTrial(int textLen, int actionCount, String algo, PrintWriter out) {
        long totalTime = 0, totalPush = 0, totalPop = 0, totalCmp = 0, totalAux = 0;
        for (int r = 0; r < REPETITIONS; r++) {
            TextEditor editor = algo.equals("A") ? new SnapshotEditor() : new CommandEditor();
            String seed = randomString(textLen);
            editor.insert(0, seed);
            editor.resetCounters();

            Random rnd = new Random(42 + r);
            long start = System.nanoTime();
            for (int i = 0; i < actionCount; i++) {
                applyRandomAction(editor, rnd);
            }
            for (int i = 0; i < actionCount; i++) {
                editor.undo();
            }
            for (int i = 0; i < actionCount; i++) {
                editor.redo();
            }
            long end = System.nanoTime();

            totalTime += (end - start);
            totalPush += editor.getPushCount();
            totalPop += editor.getPopCount();
            totalCmp += editor.getComparisonCount();
            totalAux += editor.getAuxiliaryCharCount();
        }
        out.printf("%d,%d,%s,%d,%d,%d,%d,%d%n",
                textLen, actionCount, algo,
                totalTime / REPETITIONS,
                totalPush / REPETITIONS,
                totalPop / REPETITIONS,
                totalCmp / REPETITIONS,
                totalAux / REPETITIONS);
    }

    private static void applyRandomAction(TextEditor editor, Random rnd) {
        int len = editor.getText().length();
        if (len == 0) {
            editor.insert(0, "x");
            return;
        }
        int type = rnd.nextInt(3);
        int pos = rnd.nextInt(len);
        if (type == 0) {
            editor.insert(pos, "x");
        } else if (type == 1 && len > 0) {
            int maxLen = Math.min(3, len - pos);
            editor.delete(pos, Math.max(1, maxLen));
        } else {
            int maxLen = Math.min(3, len - pos);
            editor.replace(pos, Math.max(1, maxLen), "yz");
        }
    }

    private static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random rnd = new Random(1);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + rnd.nextInt(26)));
        }
        return sb.toString();
    }
}
