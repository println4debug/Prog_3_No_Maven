/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package sim;

import businesslogic.FileAdministration;
import events.accessrandomfile.implementation.CMDAccessRandomFileEvent;
import ui.Console;


public class AccessFileSimulation implements Runnable{
    private FileAdministration admin;
    private Console console;

    public AccessFileSimulation(Console console) {
        if(console == null) {
            throw new IllegalArgumentException("Console was null!");
        }
        this.console = console;
    }
    @Override
    public void run() {
        boolean run = true;
        while(run) {
            try {
                CMDAccessRandomFileEvent event = new CMDAccessRandomFileEvent(this);
                console.getAccessRandomFileHandler().handle(event);
            } catch (Exception e) {
                console.print(e.getMessage());
            }
        }
    }
}
