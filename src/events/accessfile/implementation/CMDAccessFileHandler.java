/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.accessfile.implementation;

import events.accessfile.CMDAccessFileListener;

import java.util.LinkedList;
import java.util.List;

public class CMDAccessFileHandler {
    private List<CMDAccessFileListener> listenerList;

    public CMDAccessFileHandler() {
        this.listenerList = new LinkedList<>();
    }

    public void addListener(CMDAccessFileListener listener) {
        try {
            this.listenerList.add(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void removeListener(CMDAccessFileListener listener) {
        try {
            this.listenerList.remove(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void handle(CMDAccessFileEvent event) { // Datatype?
        for(CMDAccessFileListener listener : this.listenerList) {
            listener.onCommandEvent(event);
        }
    }
}
