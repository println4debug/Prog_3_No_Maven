/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.deleterandomfile.implementation;

import java.util.EventObject;

public class CMDDeleteRandomFileEvent extends EventObject {


    public CMDDeleteRandomFileEvent(Object source) {
        super(source);
    }
}
