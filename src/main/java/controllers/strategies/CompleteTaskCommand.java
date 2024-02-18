package controllers.strategies;

import content.Id;
import controllers.strategy.Command;
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

/**
 * A class that reads user input and changes the status of the Task.
 */
public class CompleteTaskCommand implements Command {
    Scanner sc;
    public CompleteTaskCommand(Scanner sc) {
        this.sc = sc;
    }
    private static final String XML_FILE_PATH = "Tasks.xml";

    @Override
    public void execute() {
        int id;
        do {
            System.out.print("Enter the task Id: ");
            String idStr = sc.nextLine();

            id = Id.idValid(idStr);
        } while (id == -1);


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(XML_FILE_PATH);

            NodeList taskList = document.getElementsByTagName("Task");

            boolean taskExists = false;

            for (int i = 0; i < taskList.getLength(); i++) {
                Element task = (Element) taskList.item(i);

                int taskId = Integer.parseInt(task.getElementsByTagName("id").item(0).getTextContent());

                if (taskId == id) {
                    String currentStatus = task.getElementsByTagName("status").item(0).getTextContent();
                    if (!currentStatus.equals("DONE")) {
                        task.getElementsByTagName("status").item(0).setTextContent("DONE");
                        System.out.println("Changed the status to done");

                        NodeList completeList = task.getElementsByTagName("complete");
                        if (completeList.getLength() > 0) {
                            Element completeElement = (Element) completeList.item(0);
                            completeElement.setTextContent(getCurrentDate());
                        } else {
                            Element completeElement = document.createElement("complete");
                            completeElement.setTextContent(getCurrentDate());
                            task.appendChild(completeElement);
                        }
                    }
                    else {
                        System.out.println("Task already done");
                    }

                    taskExists = true;
                    break;
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(XML_FILE_PATH);
            transformer.transform(source, result);

            if (!taskExists) {
                System.out.println("The task with Id " + id + " does not exist");
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
