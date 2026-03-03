package edu.unizg.foi.uzdiz.vvugrinec21.app.command.invoker;

import edu.unizg.foi.uzdiz.vvugrinec21.app.command.PSTARCommand;

public class PSTARInvoker {

    private PSTARCommand command;

    public void setCommand(PSTARCommand command) {
        this.command = command;
    }

    public void execute() {
        if (command != null) {
            command.execute();
        }
    }
}
