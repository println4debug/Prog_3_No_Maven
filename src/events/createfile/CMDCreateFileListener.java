/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.createfile;

import events.createfile.implementation.CMDCreateFileEvent;

public interface CMDCreateFileListener {
    void onCommandEvent(CMDCreateFileEvent event);
}
