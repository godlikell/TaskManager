package controllers.strategies;

import content.Status;
import controllers.ReaderFromXml;
import controllers.strategy.Command;

public class ShowTasksDoneCommand implements Command {
    @Override
    public void execute() {
        ReaderFromXml readerFromXml = new ReaderFromXml();
        readerFromXml.readerFromXML();
        if (readerFromXml.getToDoList().getTasks() != null) {
            System.out.println("Done tasks: ");
            readerFromXml.getToDoList().getTasks().stream().filter(e -> e.getStatus() == Status.DONE)
                    .forEach(System.out::println);

        }
    }
}
