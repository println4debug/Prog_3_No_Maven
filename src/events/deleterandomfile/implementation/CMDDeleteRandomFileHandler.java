/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.deleterandomfile.implementation;

import java.util.LinkedList;
import java.util.List;

public class CMDDeleteRandomFileHandler {

    private List<CMDDeleteRandomFileListener> listenerList;

    public CMDDeleteRandomFileHandler() {
        this.listenerList = new LinkedList<>();
    }

    public void addListener(CMDDeleteRandomFileListener listener) {
        try {
            this.listenerList.add(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeListener(CMDDeleteRandomFileListener listener) {
        try {
            this.listenerList.remove(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handle(CMDDeleteRandomFileEvent event) {
        for(CMDDeleteRandomFileListener listener : this.listenerList) {
            listener.onCommandEvent(event);
        }
    }
}
