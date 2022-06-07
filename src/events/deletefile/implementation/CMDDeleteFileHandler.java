/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.deletefile.implementation;

import java.util.LinkedList;
import java.util.List;

public class CMDDeleteFileHandler {

    private List<CMDDeleteFileListener> listenerList;

    public CMDDeleteFileHandler() {
        this.listenerList = new LinkedList<>();
    }

    public void addListener(CMDDeleteFileListener listener) {
        try {
            this.listenerList.add(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void removeListener(CMDDeleteFileListener listener) {
        try {
            this.listenerList.remove(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void handle(CMDDeleteFileEvent event) { // Datatype?
        for(CMDDeleteFileListener listener : this.listenerList) {
            listener.onCommandEvent(event);
        }
    }
}
