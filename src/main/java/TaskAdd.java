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
import java.util.Scanner;

public class TaskAdd {

    private static final String XML_FILE_PATH = "Task.xml";
    public static void addNewTask(Scanner sc) {
        try {
            Document doc;
            File file = new File(XML_FILE_PATH);

            if (file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(file);
            } else {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.newDocument();
                Element rootElement = doc.createElement("ToDoList");
                doc.appendChild(rootElement);
            }

            Element rootElement = doc.getDocumentElement();

            Element taskElement = doc.createElement("Task");

            Element idElement = doc.createElement("id");
            int id = AutoIncrementId.getAutoIncrementId();
            idElement.appendChild(doc.createTextNode(String.valueOf(id)));

            Element titleElement = doc.createElement("title");
            String title;

            do {
                System.out.print("Enter the title: ");
                title = sc.nextLine();
            } while (Title.titleValid(title));

            titleElement.appendChild(doc.createTextNode(title));

            Element descriptionElement = doc.createElement("description");
            System.out.print("Enter a description: ");
            String description = sc.nextLine();
            descriptionElement.appendChild(doc.createTextNode(description));

            Element priorityElement = doc.createElement("priority");

            String priority;

            do {
                System.out.print("Enter priority: ");
                priority = sc.nextLine();

            } while (Priority.priorityValid(priority));

            priorityElement.appendChild(doc.createTextNode(priority));

            Element deadlineElement = doc.createElement("deadline");

            String deadlineStr;
            do {
                System.out.println("Enter the deadline (in the format YYYY-MM-DD): ");
                deadlineStr = sc.nextLine();

            } while (Deadline.deadlineValid(deadlineStr));

            deadlineElement.appendChild(doc.createTextNode(deadlineStr));

            Element statusElement = doc.createElement("status");
            Status status = Status.NEW;
            statusElement.appendChild(doc.createTextNode(String.valueOf(status)));

            taskElement.appendChild(idElement);
            taskElement.appendChild(titleElement);
            taskElement.appendChild(descriptionElement);
            taskElement.appendChild(priorityElement);
            taskElement.appendChild(deadlineElement);
            taskElement.appendChild(statusElement);

            rootElement.appendChild(taskElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no"); // Включаем форматирование
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
            DOMSource source = new DOMSource(doc);
            FileWriter writer = new FileWriter(file);
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            writer.close();


            System.out.println("The data has been successfully added to the XML file");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
}

