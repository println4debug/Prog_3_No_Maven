/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.accessrandomfile.implementation;

import events.accessrandomfile.CMDAccessRandomFileListener;

import java.util.LinkedList;
import java.util.List;

public class CMDAccessRandomFileHandler {
    private List<CMDAccessRandomFileListener> listenerList;

    public CMDAccessRandomFileHandler() {
        this.listenerList = new LinkedList<>();
    }

    public void addListener(CMDAccessRandomFileListener listener) {
        try {
            this.listenerList.add(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void removeListener(CMDAccessRandomFileListener listener) {
        try {
            this.listenerList.remove(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void handle(CMDAccessRandomFileEvent event) { // Datatype?
        for(CMDAccessRandomFileListener listener : this.listenerList) {
            listener.onCommandEvent(event);
        }
    }
}
