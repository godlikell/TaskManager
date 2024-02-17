import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline {

    public static boolean deadlineValid(String deadlineStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate deadline = LocalDate.parse(deadlineStr, formatter);
            return false;
        } catch (DateTimeParseException e) {
            System.out.println("Неправильный формат даты. Попробуйте снова.");
            return true;

        }
    }
}

