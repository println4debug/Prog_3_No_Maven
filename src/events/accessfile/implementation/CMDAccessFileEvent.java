/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.accessfile.implementation;

import java.util.EventObject;

public class CMDAccessFileEvent extends EventObject {
    private String address;

    public CMDAccessFileEvent(Object source, String address) {
        super(source);
        if(address != null) {
            this.address = address;
        } else {
            throw new NullPointerException("Name was null!");
        }
    }

    public String getAddress() {
        return this.address;
    }
}
