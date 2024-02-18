package controllers.strategies;

import controllers.strategy.Command;

import java.util.LinkedHashMap;
import java.util.Map;

public class HelpCommand implements Command {

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
        commandPool.put("exit", "Ends the program. \n" );
    }

    @Override
    public void execute() {
        for (String command : commandPool.keySet()) {
            System.out.println(command + ": " + commandPool.get(command));
        }
    }
}
