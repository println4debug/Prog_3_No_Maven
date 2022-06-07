/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.deletefile;

import events.deletefile.implementation.CMDDeleteFileEvent;

public interface CMDDeleteFileListener {
    void onCommandEvent(CMDDeleteFileEvent event);
}
