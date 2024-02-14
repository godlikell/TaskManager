import java.util.List;

public class ToDoList {
    private List<Task> tasks;

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "tasks=" + tasks +
                '}';
    }
}
