import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class TaskEdit {

    public static void editTaskById(Scanner sc) {

        int id;
        do {
            System.out.print("Введите Id: ");
            String idStr = sc.nextLine();

            id = Id.idValid(idStr);
        } while (id == -1);

        try {
            File xmlFile = new File("Task.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();
            NodeList taskList = document.getElementsByTagName("Task");

            boolean taskExists = false;

            for (int i = 0; i < taskList.getLength(); i++) {
                Node taskNode = taskList.item(i);
                if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskElement = (Element) taskNode;
                    int taskId = Integer.parseInt(taskElement.getElementsByTagName("id").item(0).getTextContent());

                    if (id == taskId) {

                        Task task = new Task();
                        task.setId(Integer.parseInt(taskElement.getElementsByTagName("id")
                                .item(0).getTextContent()));
                        task.setTitle(taskElement.getElementsByTagName("title")
                                .item(0).getTextContent());
                        task.setDescription(taskElement.getElementsByTagName("description")
                                .item(0).getTextContent());
                        task.setPriority(Integer.parseInt(taskElement.getElementsByTagName("priority")
                                .item(0).getTextContent()));
                        task.setDeadline(LocalDate.parse(taskElement.getElementsByTagName("deadline")
                                .item(0).getTextContent()));
                        task.setStatus(TaskStatus.valueOf(taskElement.getElementsByTagName("status")
                                .item(0).getTextContent()));
                        NodeList completeDateElements = taskElement.getElementsByTagName("complete");
                        if (completeDateElements.getLength() > 0) {
                            task.setCompleteDate(LocalDate.parse(completeDateElements
                                    .item(0).getTextContent()));
                        }

                        System.out.println(task);

                        // сделать проверку на ввод
                        String userInput;

//                        while (true) {
//                            System.out.println("Enter 'edit' to update data or 'cancel' to abort:");
//                            userInput = sc.nextLine().trim().toLowerCase();
//
//                            if (userInput.equals("cancel") || userInput.equals("edit"))
//
//                        }

                        while (true) {
                            System.out.println("Enter 'edit' to update data or 'cancel' to abort:");
                            userInput = sc.nextLine().trim().toLowerCase();

                            if (userInput.equals("cancel")) {
                                System.out.println("Operation canceled.");
                                return; // Завершаем метод
                            } else if (userInput.equals("edit")) {

                                String newTitle;

                                do {
                                    System.out.print("Введите title: ");
                                    newTitle = sc.nextLine();
                                } while (Title.titleValid(newTitle));

                                taskElement.getElementsByTagName("title").item(0).setTextContent(newTitle);

                                // Добавьте аналогичный код для остальных элементов (description, priority, deadline, status)

                                System.out.print("Введите description: ");
                                String description = sc.nextLine();
                                taskElement.getElementsByTagName("description").item(0).setTextContent(description);

                                String priority;
                                do {
                                    System.out.print("Введите priority: ");
                                    priority = sc.nextLine();

                                } while (Priority.priorityValid(priority));
                                taskElement.getElementsByTagName("priority").item(0)
                                        .setTextContent(String.valueOf(priority));

                                String deadline;
                                do {
                                    System.out.println("Введите deadline (в формате ГГГГ-ММ-ДД): ");
                                    deadline = sc.nextLine();

                                } while (Deadline.deadlineValid(deadline));
                                taskElement.getElementsByTagName("deadline").item(0).setTextContent(deadline);

                                String statusStr;
                                TaskStatus status = null;

                                do {
                                    System.out.println("Введите status (new или in_progress): ");
                                    statusStr = sc.nextLine().trim().toLowerCase();

                                    try {
                                        status = TaskStatus.getStatusFromInput(statusStr);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Некорректный ввод. Попробуйте ещё раз.");
                                    }
                                } while (status == null);

                                taskElement.getElementsByTagName("status").item(0)
                                        .setTextContent(String.valueOf(status));

                                taskExists = true;
                                break;

                            } else {
                                System.out.println("Invalid input. Please try again.");
                            }

                        }
                    }
                }
            }

            // Сохранение изменений в файл
            // Ваш код для сохранения измененного XML файла
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult("Task.xml");
            transformer.transform(source, result);

            if (!taskExists) {
                System.out.println("Задачи с ID " + id + " не существует.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
