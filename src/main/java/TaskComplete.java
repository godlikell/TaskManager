import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskComplete {

    private static final String XML_FILE_PATH = "Task.xml";
    public static void doTaskComplete(Scanner sc) {

        int id;
        do {
            System.out.print("Введите Id: ");
            String idStr = sc.nextLine();

            id = Id.idValid(idStr);
        } while (id == -1);


        try {
            // Создаем фабрику для создания парсера
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Загружаем XML-файл
            Document document = builder.parse(XML_FILE_PATH);

            // Получаем список всех Task элементов
            NodeList taskList = document.getElementsByTagName("Task");

            // Переменная для проверки наличия задачи с заданным идентификатором
            boolean taskExists = false;

            // Перебираем все Task элементы
            for (int i = 0; i < taskList.getLength(); i++) {
                Element task = (Element) taskList.item(i);
                // Получаем значение идентификатора текущего Task элемента
                int taskId = Integer.parseInt(task.getElementsByTagName("id").item(0).getTextContent());

                if (taskId == id) {
                    // Проверяем текущее значение элемента status
                    String currentStatus = task.getElementsByTagName("status").item(0).getTextContent();
                    if (!currentStatus.equals("DONE")) {
                        // Если значение status не равно "DONE", то перезаписываем его
                        task.getElementsByTagName("status").item(0).setTextContent("DONE");
                        System.out.println("Перезаписали");

                        // Проверяем наличие элемента complete
                        NodeList completeList = task.getElementsByTagName("complete");
                        if (completeList.getLength() > 0) {
                            // Если элемент уже существует, перезаписываем его значение
                            Element completeElement = (Element) completeList.item(0);
                            completeElement.setTextContent(getCurrentDate());
                        } else {
                            // Если элемента complete нет, создаем новый элемент
                            Element completeElement = document.createElement("complete");
                            completeElement.setTextContent(getCurrentDate());
                            // Добавляем элемент complete к задаче (Task) элементу
                            task.appendChild(completeElement);
                        }
                    }
                    else {
                        System.out.println("Task уже DONE");
                    }

                    taskExists = true;
                    break;
                }
            }

            // Сохраняем изменения в XML-файле
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(XML_FILE_PATH);
            transformer.transform(source, result);

            if (!taskExists) {
                System.out.println("Задачи с ID " + id + " не существует.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().format(formatter);
    }

}
