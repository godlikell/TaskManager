import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CollectionManager {

    private final Map<String, String> commandPool = new LinkedHashMap<>();

    {
        commandPool.put("help", "Displays information on available commands.");
        commandPool.put("list", "Displays information about all elements of the collection.");
        commandPool.put("list -s new", "Displays information about new elements of the collection.");
        commandPool.put("list -s in_progress", "Displays information about in progress elements of the collection.");
        commandPool.put("list -s done", "Displays information about done elements of the collection.");
        commandPool.put("complete", "Mark the task completed");
        commandPool.put("new", "Create new task");
        commandPool.put("edit", "Edit task");
        commandPool.put("remove", "Remove task.");
        commandPool.put("exit", "Ends the program.");

    }
    public void help() {
        for (String com : commandPool.keySet()) {
            System.out.println(com + ": " + commandPool.get(com));
        }
    }

    public void listPrint() {
        XMLParser xmlP = new XMLParser();
        xmlP.parseFromXML();

    }

    public void listNewPrint() {

    }

    public void listInProgressPrint() {
    }

    public void listDonePrint() {
    }

    public void completeTask() {
    }

    public void newTask() {
    }

    public void editTask() {
    }

    public void removeTask() {
    }

    public void exitMenu() {
    }
}
