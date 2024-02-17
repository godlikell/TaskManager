import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class TaskAdd {


    public static void addNewTask(Scanner sc) {
        try {
            Document doc;
            File file = new File("Task.xml");

            if (file.exists()) {
                // Загрузка существующего XML файла
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(file);
            } else {
                // Создание нового документа XML
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.newDocument();
                Element rootElement = doc.createElement("ToDoList");
                doc.appendChild(rootElement);
            }

            // Получение корневого элемента
            Element rootElement = doc.getDocumentElement();

            // Получение пользовательского ввода
//            Scanner scanner = new Scanner(System.in);

            // Создание нового элемента <Task>
            Element taskElement = doc.createElement("Task");

            // Создание элементов <id>, <title>, <description>, <priority>, <deadline>, <status>, <complete>
            Element idElement = doc.createElement("id");
            int id = AutoIncrementId.getAutoIncrementId();
            idElement.appendChild(doc.createTextNode(String.valueOf(id)));

            Element titleElement = doc.createElement("title");
            String title;

            do {
                System.out.print("Введите title: ");
                title = sc.nextLine();
            } while (Title.titleValid(title));

            titleElement.appendChild(doc.createTextNode(title));

            Element descriptionElement = doc.createElement("description");
            System.out.print("Введите description: ");
            String description = sc.nextLine();
            descriptionElement.appendChild(doc.createTextNode(description));

            Element priorityElement = doc.createElement("priority");

            String priority;
//            boolean validPriority;
            do {
                System.out.print("Введите priority: ");
                priority = sc.nextLine();

//                validPriority = Priority.priorityValid(priority);
            } while (Priority.priorityValid(priority));


//            System.out.print("Введите priority: ");
//            int priority = Integer.parseInt(sc.nextLine());


            priorityElement.appendChild(doc.createTextNode(priority));

            Element deadlineElement = doc.createElement("deadline");

            String deadlineStr;
            do {
                System.out.println("Введите deadline (в формате ГГГГ-ММ-ДД): ");
                deadlineStr = sc.nextLine();

            } while (Deadline.deadlineValid(deadlineStr));

            deadlineElement.appendChild(doc.createTextNode(deadlineStr));

            Element statusElement = doc.createElement("status");
            TaskStatus status = TaskStatus.NEW;
            statusElement.appendChild(doc.createTextNode(String.valueOf(status)));

            // Добавление элементов в <Task>
            taskElement.appendChild(idElement);
            taskElement.appendChild(titleElement);
            taskElement.appendChild(descriptionElement);
            taskElement.appendChild(priorityElement);
            taskElement.appendChild(deadlineElement);
            taskElement.appendChild(statusElement);

            // Добавление <Task> в корневой элемент <ToDoList>
            rootElement.appendChild(taskElement);

            // Запись изменений в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no"); // Включаем форматирование
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
            DOMSource source = new DOMSource(doc);
            FileWriter writer = new FileWriter(file);
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            writer.close();


            System.out.println("Данные успешно добавлены в XML файл.");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
}

