import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    public void parseFromXML(){
        ToDoList toDoList = new ToDoList();

        File file = new File("src/main/resources/Task.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.out.println("Open parsing error " + e.toString());
            return;
        }

        Node taskNode = doc.getFirstChild();
        if (taskNode == null) {
            System.out.println("Unlucky");
        }

        NodeList taskChildes = taskNode.getChildNodes();


        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskChildes.getLength(); i++){

            if (taskChildes.item(i).getNodeType() != Node.ELEMENT_NODE){
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

                if (elementsTask.item(j).getNodeType() != Node.ELEMENT_NODE){
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

            Task task = new Task(title, description, priority, deadline, status);
            task.setCompletionDate(complete);
            taskList.add(task);

        }

        toDoList.setTasks(taskList);
        System.out.println(toDoList.toString());

    }


}
