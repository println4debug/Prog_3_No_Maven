/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.createfile.implementation;

import events.createfile.CMDCreateFileListener;

import java.util.LinkedList;
import java.util.List;

public class CMDCreateFileHandler {

    private List<CMDCreateFileListener> listenerList;

    public CMDCreateFileHandler() {
        this.listenerList = new LinkedList<>();
    }

    public void addListener(CMDCreateFileListener listener) {
        try {
            this.listenerList.add(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void removeListener(CMDCreateFileListener listener) {
        try {
            this.listenerList.remove(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void handle(CMDCreateFileEvent event) {
        for(CMDCreateFileListener listener : this.listenerList) {
            listener.onCommandEvent(event);
        }
    }
}
