package controllers.strategies;

import content.Status;
import controllers.ReaderFromXml;
import controllers.strategy.Command;

public class ShowTasksNewCommand implements Command {
    @Override
    public void execute() {
        ReaderFromXml readerFromXml = new ReaderFromXml();
        readerFromXml.readerFromXML();
        if (readerFromXml.getToDoList().getTasks() != null) {
            System.out.println("New tasks: ");
            readerFromXml.getToDoList().getTasks().stream().filter(e -> e.getStatus() == Status.NEW)
                    .forEach(System.out::println);

        }
    }
}
