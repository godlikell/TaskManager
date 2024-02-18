package content;

import java.util.List;

/**
 * Class TodoList, which contains a list of Task objects.
 */
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
        StringBuilder sb = new StringBuilder();
        sb.append("All tasks:\n");
        for (Task task : tasks) {
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }

}
