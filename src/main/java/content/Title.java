package content;

/**
 * Class Title with input validation.
 */
public class Title {
    public static boolean titleValid(String title) {

        if (title.length() > 50) {
            System.out.println("title must be under 50 char");
            return true;
        } else if (title.trim().equals("")) {
            System.out.println("title can't be empty");
            return true;
        } else {
            return false;
        }
    }
}
