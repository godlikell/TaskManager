package controllers;

import java.util.Scanner;

/**
 * User interface of the program.
 * Reads user input.
 */
public class CommandManager {
    ProjectManager projectManager;
    public CommandManager(ProjectManager projectManager) {

        this.projectManager = projectManager;
    }

    public void readInput() {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter \"help\" to get information about available commands");
        while (true) {
            System.out.println("Enter a command:");
            String command = console.nextLine().trim();
            try {
                switch (command) {
                    case "help":
                        projectManager.help();
                        break;
                    case "list":
                        projectManager.listPrint();
                        break;
                    case "list -s new":
                        projectManager.listNewPrint();
                        break;
                    case "list -s in_progress":
                        projectManager.listInProgressPrint();
                        break;
                    case "list -s done":
                        projectManager.listDonePrint();
                        break;
                    case "complete":
                        projectManager.completeTask(console);
                        break;
                    case "new":
                        projectManager.newTask(console);
                        break;
                    case "edit":
                        projectManager.editTask(console);
                        break;
                    case "remove":
                        projectManager.removeTask(console);
                        break;
                    case "exit":
                        console.close();
                        System.exit(0);
                        break;
                    default:
                        throw new Exception("Incorrect command input. Enter \"help\" to get " +
                                "information about available commands.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
