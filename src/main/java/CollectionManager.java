import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

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
        XMLReader xmlP = new XMLReader();
        xmlP.readerFromXML();
        if (xmlP.getToDoList().getTasks() != null) {
            System.out.println(xmlP.getToDoList().toString());
        }

    }

    public void listNewPrint() {
        XMLReader xmlP = new XMLReader();
        xmlP.readerFromXML();
        if (xmlP.getToDoList().getTasks() != null) {
            System.out.println("New tasks: ");
            xmlP.getToDoList().getTasks().stream().filter(e -> e.getStatus() == TaskStatus.NEW)
                    .forEach(System.out::println);

        }
    }

    public void listInProgressPrint() {
        XMLReader xmlP = new XMLReader();
        xmlP.readerFromXML();
        if (xmlP.getToDoList().getTasks() != null) {
            System.out.println("In progress tasks: ");
            xmlP.getToDoList().getTasks().stream().filter(e -> e.getStatus() == TaskStatus.IN_PROGRESS)
                    .forEach(System.out::println);

        }
    }

    public void listDonePrint() {
        XMLReader xmlP = new XMLReader();
        xmlP.readerFromXML();
        if (xmlP.getToDoList().getTasks() != null) {
            System.out.println("Done tasks: ");
            xmlP.getToDoList().getTasks().stream().filter(e -> e.getStatus() == TaskStatus.DONE)
                    .forEach(System.out::println);

        }
    }

    public void completeTask(Scanner sc) {

        TaskComplete.doTaskComplete(sc);
    }

    public void newTask(Scanner sc) {

        TaskAdd.addNewTask(sc);
    }


    public void editTask(Scanner sc) {

        TaskEdit.editTaskById(sc);
    }

    public void removeTask(Scanner sc) {

        TaskRemover.removeTaskById(sc);
    }
}
