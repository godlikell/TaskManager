public class Priority {

    public static boolean priorityValid(String priorityStr) {

        try {
            int priority = Integer.parseInt(priorityStr);
            if (priority >= 0 && priority <= 10) {
                return false;
            } else {
                System.out.println("Priority должен быть от 0 до 10. Попробуйте снова.");
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Неправильный priority. Попробуйте снова.");
            return true;
        }
    }
}
