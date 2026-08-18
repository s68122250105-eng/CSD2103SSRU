import algorithms.CommandEditor;
import algorithms.SnapshotEditor;
import algorithms.TextEditor;

import java.util.Scanner;

/**
 * Main.java
 * Menu-driven demonstration of Group 3's Text Editor Undo/Redo system.
 * Lets the user pick Algorithm A (Snapshot) or Algorithm B (Command/Delta),
 * perform INSERT / DELETE / REPLACE / UNDO / REDO, and inspect stack state,
 * operation counts, and timing.
 */
public class Main {

    private static TextEditor editor = new CommandEditor();
    private static String currentAlgorithmName = "Algorithm B (Command/Delta)";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Group 3: Text Editor - Snapshot vs Command ===");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": chooseAlgorithm("A"); break;
                    case "2": chooseAlgorithm("B"); break;
                    case "3": doInsert(); break;
                    case "4": doDelete(); break;
                    case "5": doReplace(); break;
                    case "6": doUndo(); break;
                    case "7": doRedo(); break;
                    case "8": editor.displayState(); break;
                    case "9": showCounters(); break;
                    case "10": System.out.println("Current text: \"" + editor.getText() + "\""); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid choice. Please enter a number from the menu.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Goodbye.");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Active algorithm: " + currentAlgorithmName);
        System.out.println("Text: \"" + editor.getText() + "\"  (undo=" + editor.undoStackSize() +
                ", redo=" + editor.redoStackSize() + ")");
        System.out.println("1) Use Algorithm A - Snapshot");
        System.out.println("2) Use Algorithm B - Command/Delta");
        System.out.println("3) INSERT");
        System.out.println("4) DELETE");
        System.out.println("5) REPLACE");
        System.out.println("6) UNDO");
        System.out.println("7) REDO");
        System.out.println("8) Display Stack State");
        System.out.println("9) Show Operation Counters");
        System.out.println("10) Show Current Text");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    private static void chooseAlgorithm(String which) {
        String currentText = editor.getText();
        if ("A".equals(which)) {
            editor = new SnapshotEditor();
            currentAlgorithmName = "Algorithm A (Snapshot)";
        } else {
            editor = new CommandEditor();
            currentAlgorithmName = "Algorithm B (Command/Delta)";
        }
        if (!currentText.isEmpty()) {
            editor.insert(0, currentText);
        }
        System.out.println("Switched to " + currentAlgorithmName);
    }

    private static void doInsert() {
        System.out.print("Position: ");
        int pos = readInt();
        System.out.print("Text to insert: ");
        String text = sc.nextLine();
        if (text.isEmpty()) {
            System.out.println("Warning: inserted text is empty, no change made.");
            return;
        }
        editor.insert(pos, text);
        System.out.println("Inserted. Text is now: \"" + editor.getText() + "\"");
    }

    private static void doDelete() {
        if (editor.getText().isEmpty()) {
            System.out.println("Warning: document is empty, nothing to delete.");
            return;
        }
        System.out.print("Position: ");
        int pos = readInt();
        System.out.print("Length: ");
        int len = readInt();
        editor.delete(pos, len);
        System.out.println("Deleted. Text is now: \"" + editor.getText() + "\"");
    }

    private static void doReplace() {
        if (editor.getText().isEmpty()) {
            System.out.println("Warning: document is empty, nothing to replace.");
            return;
        }
        System.out.print("Position: ");
        int pos = readInt();
        System.out.print("Length to replace: ");
        int len = readInt();
        System.out.print("New text: ");
        String newText = sc.nextLine();
        editor.replace(pos, len, newText);
        System.out.println("Replaced. Text is now: \"" + editor.getText() + "\"");
    }

    private static void doUndo() {
        if (!editor.undo()) {
            System.out.println("Warning: undo stack is empty, nothing to undo.");
        } else {
            System.out.println("Undo done. Text is now: \"" + editor.getText() + "\"");
        }
    }

    private static void doRedo() {
        if (!editor.redo()) {
            System.out.println("Warning: redo stack is empty, nothing to redo.");
        } else {
            System.out.println("Redo done. Text is now: \"" + editor.getText() + "\"");
        }
    }

    private static void showCounters() {
        System.out.println("Push count: " + editor.getPushCount());
        System.out.println("Pop count: " + editor.getPopCount());
        System.out.println("Comparison count: " + editor.getComparisonCount());
        System.out.println("Approx. auxiliary characters stored: " + editor.getAuxiliaryCharCount());
    }

    private static int readInt() {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }
}
