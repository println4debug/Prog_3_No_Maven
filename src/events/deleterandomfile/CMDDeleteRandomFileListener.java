/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.deleterandomfile;

import events.deleterandomfile.implementation.CMDDeleteRandomFileEvent;

public interface CMDDeleteRandomFileListener {
    void onCommandEvent(CMDDeleteRandomFileEvent event);
}
