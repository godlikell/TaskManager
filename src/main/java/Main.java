import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Task task1 = new Task("Сделать работу", "решить все задачи", 2);
//        Task task2 = new Task("XXXXXXXX", "xxxxxxxxx", 5);
//        Task task3 = new Task("YYYYYYYYY", "yyyyyyyyyyy", 10);
//        System.out.println(task1);
//        System.out.println(task2);
//        System.out.println(task3);
//        printMenu();
        CommandManager cm = new CommandManager(new CollectionManager());
        cm.readInput();
//        Scanner console = new Scanner(System.in);
//        System.out.println("Выберите действие:");
//        int menuNum = console.nextInt();
//
//        while (true) {
//            if (menuNum == 1) {
//
//            } else if (menuNum == 2) {
//
//            } else if (menuNum == 3) {
//
//            } else if (menuNum == 4) {
//
//            } else if (menuNum == 5) {
//
//            } else if (menuNum == 6) {
//
//            } else if (menuNum == 0) {
//                System.exit(0);
//            }
//            else {
//                System.out.println("wrong values, try again");
//            }
//
//            System.out.println("Выберите действие:");
//            menuNum = console.nextInt();
//        }
    }

    public static void printMenu() {
        System.out.println("Menu: \n" +
                "1. list\n" +
//                        "list -s new\n" +
//                        "list -s in_progress\n" +
//                        "list -s done\n" +
                "2. complete\n" +
                "3. new\n" +
                "4. edit\n" +
                "5. remove\n" +
                "6. help\n" +
                "0. exit");
    }
}
