/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.eventinfo;


import events.eventinfo.implementation.CMDEventInfo;

public interface CMDEventInfoListener {
    void onCommandEvent(CMDEventInfo event);
}
