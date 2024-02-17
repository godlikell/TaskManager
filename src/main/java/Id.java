public class Id {

    public static int idValid(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            if (id > 0) {
                return id;
            } else {
                System.out.println("Неправильный id. Попробуйте снова.");
                return -1; // or any other default value to indicate invalid id
            }
        } catch (NumberFormatException e) {
            System.out.println("Неправильный id. Попробуйте снова.");
            return -1; // or any other default value to indicate invalid id
        }
    }
}
