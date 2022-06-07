/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */

import businesslogic.FileAdministration;
import events.createfile.implementation.CMDCreateFileListener;
import events.createuploader.implementation.CMDCreateUploaderListener;
import events.deletefile.implementation.CMDDeleteFileListener;
import events.deleterandomfile.implementation.CMDDeleteRandomFileListener;
import events.eventinfo.implementation.CMDEventInfoListener;
import sim.RemoveMediaContentSimulation;
import sim.UploadMediaSimulation;
import ui.Console;

public class SimulationOne {
    public static void main(String[] args) {
        Console console = new Console();
        FileAdministration administration = new FileAdministration();

        CMDCreateUploaderListener createUploaderListener = new CMDCreateUploaderListener(administration);
        CMDCreateFileListener createFileListener = new CMDCreateFileListener(administration);
        CMDDeleteFileListener deleteFileListener = new CMDDeleteFileListener(administration);
        CMDDeleteRandomFileListener deleteRndListener = new CMDDeleteRandomFileListener(administration);
        CMDEventInfoListener infoListener = new CMDEventInfoListener(console);

        console.getCreateUploadHandler().addListener(createUploaderListener);
        console.getCreateFileHandler().addListener(createFileListener);
        console.getDeleteFileHandler().addListener(deleteFileListener);
        console.getDeleteRandomFileHandler().addListener(deleteRndListener);
        console.getInfoHandler().addListener(infoListener);

        Runnable addSimulation = new UploadMediaSimulation(console);
        Thread addSimulationThread = new Thread(addSimulation);
        addSimulationThread.start();

        Runnable removeSimulation = new RemoveMediaContentSimulation(console);
        Thread removeSimulationThread = new Thread(removeSimulation);
        removeSimulationThread.start();
    }
}
