import java.util.Date;

public class Task {
    private static int lastId = 0;
    private int id;
    private String title;
    private String description;
    private int priority;
    private Date deadline;
    private TaskStatus status;
    private Date completionDate;

    public Task(String title, String description, int priority) {
        this.id = ++lastId;
        this.title = title;
        this.description = description;
        this.priority = priority;
//        this.deadline = deadline;
        this.status = TaskStatus.NEW;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
