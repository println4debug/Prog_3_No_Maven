/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.accessrandomfile.implementation;

import java.util.EventObject;

public class CMDAccessRandomFileEvent extends EventObject {

    public CMDAccessRandomFileEvent(Object source) {
        super(source);
    }
}
