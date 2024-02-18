package controllers;

import content.Status;
import controllers.utillity.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A class that process user input.
 */
public class ProjectManager {

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
        for (String command : commandPool.keySet()) {
            System.out.println(command + ": " + commandPool.get(command));
        }
    }

    public void listPrint() {
        TaskReader taskReader = new TaskReader();
        taskReader.readerFromXML();
        if (taskReader.getToDoList().getTasks() != null) {
            System.out.println(taskReader.getToDoList().toString());
        }

    }

    public void listNewPrint() {
        TaskReader taskReader = new TaskReader();
        taskReader.readerFromXML();
        if (taskReader.getToDoList().getTasks() != null) {
            System.out.println("New tasks: ");
            taskReader.getToDoList().getTasks().stream().filter(e -> e.getStatus() == Status.NEW)
                    .forEach(System.out::println);

        }
    }

    public void listInProgressPrint() {
        TaskReader taskReader = new TaskReader();
        taskReader.readerFromXML();
        if (taskReader.getToDoList().getTasks() != null) {
            System.out.println("In progress tasks: ");
            taskReader.getToDoList().getTasks().stream().filter(e -> e.getStatus() == Status.IN_PROGRESS)
                    .forEach(System.out::println);

        }
    }

    public void listDonePrint() {
        TaskReader taskReader = new TaskReader();
        taskReader.readerFromXML();
        if (taskReader.getToDoList().getTasks() != null) {
            System.out.println("Done tasks: ");
            taskReader.getToDoList().getTasks().stream().filter(e -> e.getStatus() == Status.DONE)
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
