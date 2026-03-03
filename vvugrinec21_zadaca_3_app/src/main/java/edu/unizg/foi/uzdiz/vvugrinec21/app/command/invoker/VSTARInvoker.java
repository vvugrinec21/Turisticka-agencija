package edu.unizg.foi.uzdiz.vvugrinec21.app.command.invoker;

import edu.unizg.foi.uzdiz.vvugrinec21.app.command.VSTARCommand;

public class VSTARInvoker {

    private VSTARCommand command;

    public void setCommand(VSTARCommand command) {
        this.command = command;
    }

    public boolean execute() {
        return command != null && command.execute();
    }
}
