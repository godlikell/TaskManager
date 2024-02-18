package controllers;

import controllers.strategies.*;
import controllers.strategy.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * User interface of the program.
 * Reads user input.
 */
public class CommandManager {
    private Scanner console = new Scanner(System.in);
    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandManager() {
        commandMap.put("help", new HelpCommand());
        commandMap.put("list", new ShowTasksAllCommand());
        commandMap.put("list -s new", new ShowTasksNewCommand());
        commandMap.put("list -s in_progress", new ShowTasksInProgressCommand());
        commandMap.put("list -s done", new ShowTasksDoneCommand());
        commandMap.put("complete", new CompleteTaskCommand(console));
        commandMap.put("new", new NewTaskCommand(console));
        commandMap.put("edit", new EditTaskCommand(console));
        commandMap.put("remove", new RemoveTaskCommand(console));
        commandMap.put("exit", new ExitCommand(console));

    }

    public void readInput() {
        System.out.println("Enter \"help\" to get information about available commands");
        while (true) {
            System.out.println("Enter a command:");
            String commandStr = console.nextLine().trim().toLowerCase();
            Command cmd = commandMap.get(commandStr);
            if (cmd != null) {
                try {
                    cmd.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                    System.out.println("Incorrect command input. Enter \"help\"" +
                            " to get information about available commands.");
                }
            }
        }
    }


