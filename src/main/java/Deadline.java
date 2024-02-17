import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline {

    public static boolean deadlineValid(String deadlineStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate deadline = LocalDate.parse(deadlineStr, formatter);
            return false;
        } catch (DateTimeParseException e) {
            System.out.println("The date format is incorrect. Try again");
            return true;

        }
    }
}

