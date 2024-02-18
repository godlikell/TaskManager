package content;

/**
 * Class Priority with input validation.
 */
public class Priority {

    public static boolean priorityValid(String priorityStr) {

        try {
            int priority = Integer.parseInt(priorityStr);
            if (priority >= 0 && priority <= 10) {
                return false;
            } else {
                System.out.println("Priority must be between 0 and 10. Try again");
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("The wrong priority. Try again");
            return true;
        }
    }
}
