/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package events.createuploader;

import events.createuploader.implementation.CMDCreateUploaderEvent;

public interface CMDCreateUploadertListener {
    void onCommandEvent(CMDCreateUploaderEvent event);
}
