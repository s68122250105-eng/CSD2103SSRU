package algorithms;

import java.util.ArrayDeque;
import java.util.Deque;
import models.Action;
import models.Action.ActionType;

/* Algorithm B: Command (Delta) Method.*/

public class CommandEditor implements TextEditor {

    private StringBuilder document = new StringBuilder();
    private final Deque<Action> undoStack = new ArrayDeque<>();
    private final Deque<Action> redoStack = new ArrayDeque<>();

    private long pushCount = 0;
    private long popCount = 0;
    private long comparisonCount = 0;

    private void recordAndClearRedo(Action action) {
        undoStack.push(action);
        pushCount++;
        comparisonCount++; // checking redoStack.isEmpty()
        if (!redoStack.isEmpty()) {
            redoStack.clear();
        }
    }

    @Override
    public void insert(int position, String text) {
        comparisonCount++;
        if (position < 0 || position > document.length()) {
            throw new IllegalArgumentException("Invalid insert position");
        }
        document.insert(position, text);
        recordAndClearRedo(new Action(ActionType.INSERT, position, "", text));
    }

    @Override
    public void delete(int position, int length) {
        comparisonCount++;
        if (position < 0 || length < 0 || position + length > document.length()) {
            throw new IllegalArgumentException("Invalid delete range");
        }
        String removed = document.substring(position, position + length);
        document.delete(position, position + length);
        recordAndClearRedo(new Action(ActionType.DELETE, position, removed, ""));
    }

    @Override
    public void replace(int position, int length, String newText) {
        comparisonCount++;
        if (position < 0 || length < 0 || position + length > document.length()) {
            throw new IllegalArgumentException("Invalid replace range");
        }
        String old = document.substring(position, position + length);
        document.replace(position, position + length, newText);
        recordAndClearRedo(new Action(ActionType.REPLACE, position, old, newText));
    }
    private void applyInverse(Action a) {
        comparisonCount++;
        switch (a.getActionType()) {
            case INSERT:
                document.delete(a.getPosition(), a.getPosition() + a.getNewText().length());
                break;
            case DELETE:
                document.insert(a.getPosition(), a.getOldText());
                break;
            case REPLACE:
                document.replace(a.getPosition(), a.getPosition() + a.getNewText().length(), a.getOldText());
                break;
        }
    }

    private void applyForward(Action a) {
        comparisonCount++;
        switch (a.getActionType()) {
            case INSERT:
                document.insert(a.getPosition(), a.getNewText());
                break;
            case DELETE:
                document.delete(a.getPosition(), a.getPosition() + a.getOldText().length());
                break;
            case REPLACE:
                document.replace(a.getPosition(), a.getPosition() + a.getOldText().length(), a.getNewText());
                break;
        }
    }

    @Override
    public boolean undo() {
        comparisonCount++;
        if (undoStack.isEmpty()) {
            return false;
        }
        Action a = undoStack.pop();
        popCount++;
        applyInverse(a);
        redoStack.push(a);
        pushCount++;
        return true;
    }

    @Override
    public boolean redo() {
        comparisonCount++;
        if (redoStack.isEmpty()) {
            return false;
        }
        Action a = redoStack.pop();
        popCount++;
        applyForward(a);
        undoStack.push(a);
        pushCount++;
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
        System.out.println("Undo stack size=" + undoStack.size() + " top=" +
                (undoStack.isEmpty() ? "-" : undoStack.peek()));
        System.out.println("Redo stack size=" + redoStack.size() + " top=" +
                (redoStack.isEmpty() ? "-" : redoStack.peek()));
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
        for (Action a : undoStack) total += a.getOldText().length() + a.getNewText().length();
        for (Action a : redoStack) total += a.getOldText().length() + a.getNewText().length();
        return total;
    }

    @Override
    public void resetCounters() {
        pushCount = 0;
        popCount = 0;
        comparisonCount = 0;
    }
}
