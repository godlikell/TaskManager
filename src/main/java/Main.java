public class Main {
    public static void main(String[] args) {
//        Task task1 = new Task("Сделать работу", "решить все задачи", 2);
//        Task task2 = new Task("XXXXXXXX", "xxxxxxxxx", 5);
//        Task task3 = new Task("YYYYYYYYY", "yyyyyyyyyyy", 10);
//        System.out.println(task1);
//        System.out.println(task2);
//        System.out.println(task3);
        CommandManager cm = new CommandManager(new CollectionManager());
        cm.readInput();
//        XMLParser xmlParser = new XMLParser();
//        xmlParser.parseFromXML();

    }
}
