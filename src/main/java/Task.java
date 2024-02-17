import java.time.LocalDate;


public class Task {

    private int id;
    private String title;
    private String description;
    private int priority;
    private LocalDate deadline;
    private TaskStatus status;
    private LocalDate complete;

    public Task(int id, String title, String description, int priority, LocalDate deadline, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
    }

    public Task() {}

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

    public LocalDate getCompleteDate() {
        return complete;
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

    public void setCompleteDate(LocalDate complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Task{")
                .append("id=").append(id)
                .append(", title='").append(title).append('\'')
                .append(", description='").append(description).append('\'')
                .append(", priority=").append(priority)
                .append(", deadline=").append(deadline)
                .append(", status=").append(status);

        if (complete != null) {
            sb.append(", complete=").append(complete);
        }

        sb.append('}');

        return sb.toString();
    }
}
