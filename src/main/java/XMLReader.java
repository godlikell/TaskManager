import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    private static final String XML_FILE_PATH = "Task.xml";

    private ToDoList toDoList = new ToDoList();

    public void readerFromXML() {

        File file = new File(XML_FILE_PATH);

        if (file.length() == 0) {
            System.out.println("There are no tasks");
            return;
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.out.println("Open parsing error " + e.toString());
            return;
        }

        Node taskNode = doc.getDocumentElement();

        NodeList taskChildes = taskNode.getChildNodes();

        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < taskChildes.getLength(); i++) {

            if (taskChildes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (!taskChildes.item(i).getNodeName().equals("Task")) {
                continue;
            }

            int id = 0;
            String title = "";
            String description = "";
            int priority = 0;
            LocalDate deadline = null;
            TaskStatus status = null;
            LocalDate complete = null;

            NodeList elementsTask = taskChildes.item(i).getChildNodes();

            for (int j = 0; j < elementsTask.getLength(); j++) {

                if (elementsTask.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (elementsTask.item(j).getNodeName()) {
                    case "id": {
                        id = Integer.parseInt(elementsTask.item(j).getTextContent());
                        break;
                    }
                    case "title": {
                        title = elementsTask.item(j).getTextContent();
                        break;
                    }
                    case "description": {
                        description = elementsTask.item(j).getTextContent();
                        break;
                    }
                    case "priority": {
                        priority = Integer.parseInt(elementsTask.item(j).getTextContent());
                        break;
                    }
                    case "deadline": {
                        deadline = LocalDate.parse(elementsTask.item(j).getTextContent());
                        break;
                    }
                    case "status": {
                        status = TaskStatus.valueOf(elementsTask.item(j).getTextContent());
                        break;
                    }
                    case "complete": {
                        complete = LocalDate.parse(elementsTask.item(j).getTextContent());
                        break;
                    }

                }
            }

            Task task = new Task(id, title, description, priority, deadline, status);
            task.setCompleteDate(complete);

            taskList.add(task);

        }

        toDoList.setTasks(taskList);

    }

    public ToDoList getToDoList() {
        return toDoList;

    }
}
