/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.eventinfo.implementation;

import ui.Console;

public class CMDEventInfoListener implements events.eventinfo.CMDEventInfoListener {
    private Console model;

    public CMDEventInfoListener(Console model) {
        if(model != null) {
            this.model = model;
        } else {
            throw new NullPointerException("Model was null!");
        }
    }

    public void onCommandEvent(CMDEventInfo event) {
        model.print(event.getMessage());
    }
}
