/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package sim;

import events.createfile.implementation.CMDCreateFileEvent;
import events.createuploader.implementation.CMDCreateUploaderEvent;
import ui.Console;

import java.util.Random;

public class UploadMediaSimulation implements Runnable{
    private Console console;

    public UploadMediaSimulation(Console console) {
        if(console == null) {
            throw new IllegalArgumentException("Console null!");
        }
        this.console = console;
    }
    @Override
    public void run() {
        boolean run = true;
        Random rnd = new Random();
        String filetype = "Unknown";
        setUpSimUploaders();
        while(run) {
            try {
                int type = rnd.nextInt(6);
                switch(type) {
                    case 0: filetype = "Audio"; break;
                    case 1: filetype = "Video"; break;
                    case 2: filetype = "AudioVideo"; break;
                    case 3: filetype = "InteractiveVideo"; break;
                    case 4: filetype = "LicensedAudio"; break;
                    case 5: filetype = "LicensedAudioVideo"; break;
                    case 6: filetype = "LicensedVideo"; break;
                    default: filetype = "Unkown"; break;
                }
                int user = rnd.nextInt(100);
                CMDCreateFileEvent creationEvent = new CMDCreateFileEvent(this,filetype,"Simulation-Uploader-"+user);
                console.getCreateFileHandler().handle(creationEvent);
            } catch (Exception e) {
                console.print(e.getMessage());
            }
        }
    }
    public void setUpSimUploaders() {
        for(int i = 0; i < 100; i++) {
            try {
                CMDCreateUploaderEvent addUserEvent = new CMDCreateUploaderEvent(this,"Simulation-Uploader-"+i);
                console.getCreateUploadHandler().handle(addUserEvent);
            } catch (Exception e) {
                console.print(e.getMessage());
            }
        }
    }
}
