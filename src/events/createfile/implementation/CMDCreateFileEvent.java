/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.createfile.implementation;

import mediaDB.Uploader;

import java.util.EventObject;

public class CMDCreateFileEvent extends EventObject {

    private String type;
    private String uploader;

    public CMDCreateFileEvent(Object source, String name, String uploader) {
        super(source);
        if(name != null && uploader != null) {
            this.type = name;
            this.uploader = uploader;
        } else {
            throw new NullPointerException("Type or name was null!");
        }
    }

    public String getType() {
        return this.type;
    }

    public String getUploader() {
        return this.uploader;
    }
}
