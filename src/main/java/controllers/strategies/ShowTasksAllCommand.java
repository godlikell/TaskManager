package controllers.strategies;

import controllers.ReaderFromXml;
import controllers.strategy.Command;

public class ShowTasksAllCommand implements Command {
    @Override
    public void execute() {
        ReaderFromXml readerFromXml = new ReaderFromXml();
        readerFromXml.readerFromXML();
        if (readerFromXml.getToDoList().getTasks() != null) {
            System.out.println(readerFromXml.getToDoList().toString());
        }
    }
}
