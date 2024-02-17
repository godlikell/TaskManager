public class Main {

    public static void main(String[] args) {
        CommandManager cm = new CommandManager(new ProjectManager());
        cm.readInput();
    }
}
