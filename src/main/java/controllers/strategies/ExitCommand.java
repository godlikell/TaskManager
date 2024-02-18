package controllers.strategies;

import controllers.strategy.Command;

import java.util.Scanner;

public class ExitCommand implements Command {
    Scanner console;
    public ExitCommand(Scanner console) {
        this.console = console;
    }
    @Override
    public void execute() {
        console.close();
        System.exit(0);
    }
}
