public enum Status {
    NEW,
    IN_PROGRESS,
    DONE;

    public static Status getStatusFromInput(String userInput) {
        String input = userInput.trim().toLowerCase();
        switch (input) {
            case "new":
                return NEW;
            case "in_progress":
                return IN_PROGRESS;
            default:
                throw new IllegalArgumentException("Incorrect status: " + userInput);
        }
    }

}
