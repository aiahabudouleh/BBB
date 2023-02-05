package bo.enums;

public enum BBBRole {
    VIEWER("viewer"), MODERATOR("moderator");
    private String name;
    BBBRole(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
