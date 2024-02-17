public class Id {

    public static int idValid(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            if (id > 0) {
                return id;
            } else {
                System.out.println("Incorrect id. Try again");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect id. Try again");
            return -1;
        }
    }
}
