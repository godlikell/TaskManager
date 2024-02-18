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
import java.util.Scanner;

/**
 * A class that reads user input and deletes a task.
 */
public class RemoveTaskCommand implements Command {
    Scanner sc;
    public RemoveTaskCommand(Scanner sc) {
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
                    task.getParentNode().removeChild(task);
                    taskExists = true;
                    break;
                }
            }

            if (taskExists) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(XML_FILE_PATH);
                transformer.transform(source, result);

                System.out.println("The task with the Id " + id + " deleted successfully");
            } else {
                System.out.println("The task with the Id " + id + " does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
