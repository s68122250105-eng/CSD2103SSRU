package models;

public class Action {

    public enum ActionType {
        INSERT, DELETE, REPLACE
    }

    private static int counter = 0;

    private final int actionId;
    private final ActionType actionType;
    private final int position;
    private final String oldText;   // text that existed before the action (empty for INSERT)
    private final String newText;   // text that exists after the action (empty for DELETE)
    private final long timestamp;

    public Action(ActionType actionType, int position, String oldText, String newText) {
        this.actionId = ++counter;
        this.actionType = actionType;
        this.position = position;
        this.oldText = oldText == null ? "" : oldText;
        this.newText = newText == null ? "" : newText;
        this.timestamp = System.currentTimeMillis();
    }

    public int getActionId() { return actionId; }
    public ActionType getActionType() { return actionType; }
    public int getPosition() { return position; }
    public String getOldText() { return oldText; }
    public String getNewText() { return newText; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("Action#%d[%s pos=%d old=\"%s\" new=\"%s\"]",
                actionId, actionType, position, oldText, newText);
    }
}
