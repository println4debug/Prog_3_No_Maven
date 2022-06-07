/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */

import businesslogic.FileAdministration;
import events.accessfile.implementation.CMDAccessFileListener;
import events.createfile.implementation.CMDCreateFileListener;
import events.createuploader.implementation.CMDCreateUploaderListener;
import events.deletefile.implementation.CMDDeleteFileListener;
import ui.Console;

public class MainApp {
    public static void main(String args[]) {

        Console console = new Console();
        FileAdministration administration = new FileAdministration();

        CMDCreateUploaderListener createUploaderListener = new CMDCreateUploaderListener(administration);
        CMDCreateFileListener createFileListener = new CMDCreateFileListener(administration);
        CMDDeleteFileListener deleteFileListener = new CMDDeleteFileListener(administration);
        CMDAccessFileListener accessListener = new CMDAccessFileListener(administration);

        console.getCreateUploadHandler().addListener(createUploaderListener);
        console.getCreateFileHandler().addListener(createFileListener);
        console.getDeleteFileHandler().addListener(deleteFileListener);
        console.getAccessHandler().addListener(accessListener);

        console.print("Type :i for commands. ");
        while(true) {
            console.readCommand();
        }
    }
}
