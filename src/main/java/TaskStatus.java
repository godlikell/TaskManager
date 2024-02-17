public enum TaskStatus {
    NEW,
    IN_PROGRESS,
    DONE;

    public static TaskStatus getStatusFromInput(String userInput) {
        String input = userInput.trim().toLowerCase();
        switch (input) {
            case "new":
                return NEW;
            case "in_progress":
                return IN_PROGRESS;
            default:
                throw new IllegalArgumentException("Некорректный статус: " + userInput);
        }
    }

}
