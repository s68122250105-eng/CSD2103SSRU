package algorithms;


public interface TextEditor {

    void insert(int position, String text);

    void delete(int position, int length);

    void replace(int position, int length, String newText);

    /** @return true if an undo was performed, false if the undo stack was empty */
    boolean undo();

    /** @return true if a redo was performed, false if the redo stack was empty */
    boolean redo();

    String getText();

    int undoStackSize();

    int redoStackSize();

    void displayState();

    // ---- operation / performance counters (required by section 3.7 & 3.8) ----
    long getPushCount();

    long getPopCount();

    long getComparisonCount();

    /** Approximate auxiliary memory currently held by the undo/redo structures, in characters. */
    long getAuxiliaryCharCount();

    void resetCounters();
}
