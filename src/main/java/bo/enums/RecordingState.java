package bo.enums;

public enum RecordingState {
    PUBLISHED("published"), UNPUBLISHED("unpublished");
    String state;
    RecordingState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
