import java.util.Scanner;

public class CommandManager {
    CollectionManager collectionManager;

    public CommandManager(CollectionManager collectionManager) {

        this.collectionManager = collectionManager;
    }

    public void readInput() {
        Scanner console = new Scanner(System.in);
        System.out.println("Please, enter a command. (Enter \"help\" to get information about available commands)");
        while (true) {
            String command = console.nextLine().trim();
            try {
                switch (command) {
                    case "help":
                        collectionManager.help();
                        break;
                    case "list":
                        collectionManager.listPrint();
                        break;
                    case "list -s new":
                        collectionManager.listNewPrint();
                        break;
                    case "list -s in_progress":
                        collectionManager.listInProgressPrint();
                        break;
                    case "list -s done":
                        collectionManager.listDonePrint();
                        break;
                    case "complete":
                        collectionManager.completeTask(console);
                        break;
                    case "new":
                        collectionManager.newTask(console);
                        break;
                    case "edit":
                        collectionManager.editTask(console);
                        break;
                    case "remove":
                        collectionManager.removeTask(console);
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
