package algorithms;

import java.util.ArrayDeque;
import java.util.Deque;

/*Algorithm A: Snapshot Method.*/

public class SnapshotEditor implements TextEditor {

    private StringBuilder document = new StringBuilder();
    private final Deque<String> undoStack = new ArrayDeque<>();
    private final Deque<String> redoStack = new ArrayDeque<>();

    private long pushCount = 0;
    private long popCount = 0;
    private long comparisonCount = 0;

    private void snapshotBeforeEdit() {
        undoStack.push(document.toString());
        pushCount++;
        comparisonCount++; 
        if (!redoStack.isEmpty()) {
            redoStack.clear();
        }
    }

    @Override
    public void insert(int position, String text) {
        validatePosition(position, document.length());
        snapshotBeforeEdit();
        document.insert(position, text);
    }

    @Override
    public void delete(int position, int length) {
        comparisonCount++;
        if (position < 0 || length < 0 || position + length > document.length()) {
            throw new IllegalArgumentException("Invalid delete range");
        }
        snapshotBeforeEdit();
        document.delete(position, position + length);
    }

    @Override
    public void replace(int position, int length, String newText) {
        comparisonCount++;
        if (position < 0 || length < 0 || position + length > document.length()) {
            throw new IllegalArgumentException("Invalid replace range");
        }
        snapshotBeforeEdit();
        document.replace(position, position + length, newText);
    }

    @Override
    public boolean undo() {
        comparisonCount++;
        if (undoStack.isEmpty()) {
            return false;
        }
        redoStack.push(document.toString());
        pushCount++;
        String previous = undoStack.pop();
        popCount++;
        document = new StringBuilder(previous);
        return true;
    }

    @Override
    public boolean redo() {
        comparisonCount++;
        if (redoStack.isEmpty()) {
            return false;
        }
        undoStack.push(document.toString());
        pushCount++;
        String next = redoStack.pop();
        popCount++;
        document = new StringBuilder(next);
        return true;
    }

    @Override
    public String getText() {
        return document.toString();
    }

    @Override
    public int undoStackSize() {
        return undoStack.size();
    }

    @Override
    public int redoStackSize() {
        return redoStack.size();
    }

    @Override
    public void displayState() {
        System.out.println("Document: \"" + document + "\"");
        System.out.println("Undo stack (top->bottom, most recent first), size=" + undoStack.size());
        System.out.println("Redo stack size=" + redoStack.size());
    }

    @Override
    public long getPushCount() { return pushCount; }

    @Override
    public long getPopCount() { return popCount; }

    @Override
    public long getComparisonCount() { return comparisonCount; }

    @Override
    public long getAuxiliaryCharCount() {
        long total = 0;
        for (String s : undoStack) total += s.length();
        for (String s : redoStack) total += s.length();
        return total;
    }

    @Override
    public void resetCounters() {
        pushCount = 0;
        popCount = 0;
        comparisonCount = 0;
    }

    private void validatePosition(int position, int length) {
        comparisonCount++;
        if (position < 0 || position > length) {
            throw new IllegalArgumentException("Invalid insert position");
        }
    }
}
