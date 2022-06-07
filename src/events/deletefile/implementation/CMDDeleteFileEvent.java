/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.deletefile.implementation;

import mediaDB.implementation.File;

import java.util.EventObject;

public class CMDDeleteFileEvent extends EventObject {

    private String address;

    public CMDDeleteFileEvent(Object source, String address) {
        super(source);
        if(address != null) {
            this.address = address;
        } else {
            throw new NullPointerException("Address or name was null!");
        }
    }

    public String getAddress() {
        return this.address;
    }
}
