import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class AutoIncrementId {
    private static final String TXT_FILE_PATH = "Id.txt";

    public static int getAutoIncrementId(){

        int newId = 0;

        try {
            File idFile = new File(TXT_FILE_PATH);
            int maxId;
            if (idFile.exists()) {
                try (Scanner scanner = new Scanner(idFile)) {
                    maxId = scanner.nextInt();
                }
            } else {
                maxId = 1;
            }

            maxId++;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFile))) {
                writer.write(String.valueOf(maxId));
            }

            newId = maxId;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newId;
    }

}
