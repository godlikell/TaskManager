import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class TaskRemover {

    private static final String XML_FILE_PATH = "Task.xml";
    public static void removeTaskById(Scanner sc) {

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

                // Если идентификатор совпадает с заданным, удаляем элемент
                if (taskId == id) {
                    task.getParentNode().removeChild(task);
                    taskExists = true;
                    break;
                }
            }

            // Сохраняем изменения в XML-файле


            if (taskExists) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(XML_FILE_PATH);
                transformer.transform(source, result);
                System.out.println("Задача с ID " + id + " удалена успешно.");
            } else {
                System.out.println("Задачи с ID " + id + " не существует.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
