/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.eventinfo.implementation;

import events.eventinfo.CMDEventInfoListener;

import java.util.LinkedList;
import java.util.List;

public class CMDEventInfoHandler {
    private List<CMDEventInfoListener> listenerList;

    public CMDEventInfoHandler() {
        this.listenerList = new LinkedList<>();
    }

    public void addListener(CMDEventInfoListener listener) {
        try {
            this.listenerList.add(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void removeListener(CMDEventInfoListener listener) {
        try {
            this.listenerList.remove(listener);
        } catch(Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void handle(CMDEventInfo event) {
        for(CMDEventInfoListener listener : this.listenerList) {
            listener.onCommandEvent(event);
        }
    }
}
