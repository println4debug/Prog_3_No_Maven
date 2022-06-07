/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package sim;

import events.deleterandomfile.implementation.CMDDeleteRandomFileEvent;
import ui.Console;


public class RemoveMediaContentSimulation implements Runnable{
        private Console console;

        public RemoveMediaContentSimulation(Console console) {
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
                                CMDDeleteRandomFileEvent deletionEvent = new CMDDeleteRandomFileEvent(this);
                                this.console.getDeleteRandomFileHandler().handle(deletionEvent);
                        } catch (Exception e) {
                                console.print(e.getMessage());
                        }
                }
        }
}
