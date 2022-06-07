/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */

import businesslogic.FileAdministration;
import events.accessrandomfile.CMDAccessRandomFileListener;
import events.createfile.implementation.CMDCreateFileListener;
import events.createuploader.implementation.CMDCreateUploaderListener;
import events.deletefile.implementation.CMDDeleteFileListener;
import events.deleterandomfile.implementation.CMDDeleteRandomFileListener;
import events.eventinfo.implementation.CMDEventInfoListener;
import sim.AccessFileSimulation;
import sim.RemoveMediaContentSimulation;
import sim.UploadMediaSimulation;
import ui.Console;

public class SimulationTwo {
    public static void main(String[] args) {

        // Hiermit bin ich zeitlich leider nicht fertig geworden, werde es aber zur nächsten Abgabe fertig haben

        Console console = new Console();
        FileAdministration administration = new FileAdministration();

        CMDCreateUploaderListener createUploaderListener = new CMDCreateUploaderListener(administration);
        CMDCreateFileListener createFileListener = new CMDCreateFileListener(administration);
        CMDDeleteFileListener deleteFileListener = new CMDDeleteFileListener(administration);
        CMDEventInfoListener infoListener = new CMDEventInfoListener(console);
        CMDDeleteRandomFileListener deleteRndListener = new CMDDeleteRandomFileListener(administration);
        CMDAccessRandomFileListener accessListener = new events.accessrandomfile.implementation.CMDAccessRandomFileListener(administration);

        console.getCreateUploadHandler().addListener(createUploaderListener);
        console.getCreateFileHandler().addListener(createFileListener);
        console.getDeleteFileHandler().addListener(deleteFileListener);
        console.getInfoHandler().addListener(infoListener);
        console.getAccessRandomFileHandler().addListener(accessListener);
        console.getDeleteRandomFileHandler().addListener(deleteRndListener);

        Runnable addSimulation = new UploadMediaSimulation(console);
        Thread addSimulationThread = new Thread(addSimulation);
        addSimulationThread.start();

        Runnable removeSimulation = new RemoveMediaContentSimulation(console);
        Thread removeSimulationThread = new Thread(removeSimulation);
        removeSimulationThread.start();

        Runnable accessSimulation = new AccessFileSimulation(console);
        Thread accessThread = new Thread(accessSimulation);
        accessThread.start();
    }
}
