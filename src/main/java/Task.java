import java.time.LocalDate;


public class Task {
    private static int lastId = 0;
    private int id;
    private String title;
    private String description;
    private int priority;
    private LocalDate deadline;
    private TaskStatus status;
    private LocalDate completionDate;

    public Task(int id, String title, String description, int priority, LocalDate deadline, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", deadline=" + deadline +
                ", status=" + status +
                ", completionDate=" + completionDate +
                '}';
    }
}
