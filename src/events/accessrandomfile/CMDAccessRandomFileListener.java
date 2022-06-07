/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.accessrandomfile;

import events.accessrandomfile.implementation.CMDAccessRandomFileEvent;

public interface CMDAccessRandomFileListener {
    void onCommandEvent(CMDAccessRandomFileEvent event);
}
