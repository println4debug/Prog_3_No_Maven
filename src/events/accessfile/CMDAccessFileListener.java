/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.accessfile;

import events.accessfile.implementation.CMDAccessFileEvent;

public interface CMDAccessFileListener {
    void onCommandEvent(CMDAccessFileEvent event);
}
