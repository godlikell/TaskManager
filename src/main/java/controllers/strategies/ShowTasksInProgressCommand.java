package controllers.strategies;

import content.Status;
import controllers.ReaderFromXml;
import controllers.strategy.Command;

public class ShowTasksInProgressCommand implements Command {
    @Override
    public void execute() {
        ReaderFromXml readerFromXml = new ReaderFromXml();
        readerFromXml.readerFromXML();
        if (readerFromXml.getToDoList().getTasks() != null) {
            System.out.println("In progress tasks: ");
            readerFromXml.getToDoList().getTasks().stream().filter(e -> e.getStatus() == Status.IN_PROGRESS)
                    .forEach(System.out::println);

        }
    }
}
