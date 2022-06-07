/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.createuploader.implementation;

import java.util.EventObject;

public class CMDCreateUploaderEvent extends EventObject {

    private String name;

    public CMDCreateUploaderEvent(Object source, String name) {
        super(source);
        if(name != null) {
            this.name = name;
        } else {
            throw new NullPointerException("Name was null!");
        }
    }

    public String getName() {
        return this.name;
    }

}
