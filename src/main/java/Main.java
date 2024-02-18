import controllers.CommandManager;
import controllers.ProjectManager;

/**
 * The main class of the program.
 */
public class Main {
    public static void main(String[] args) {
        CommandManager cm = new CommandManager(new ProjectManager());
        cm.readInput();
    }
}
