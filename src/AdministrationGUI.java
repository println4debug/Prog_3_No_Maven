/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */

import businesslogic.FileAdministration;
import events.accessfile.implementation.CMDAccessFileHandler;
import events.accessfile.implementation.CMDAccessFileListener;
import events.createfile.implementation.CMDCreateFileHandler;
import events.createfile.implementation.CMDCreateFileListener;
import events.createuploader.implementation.CMDCreateUploaderHandler;
import events.createuploader.implementation.CMDCreateUploaderListener;
import events.deletefile.implementation.CMDDeleteFileHandler;
import events.deletefile.implementation.CMDDeleteFileListener;
import events.eventinfo.CMDEventInfoListener;
import events.eventinfo.implementation.CMDEventInfoHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sim.RemoveMediaContentSimulation;
import sim.UploadMediaSimulation;
import ui.Console;
import ui.gui.AdministrationController;

public class AdministrationGUI extends Application {

    @Override public void start(Stage primaryStage) throws Exception{

        FileAdministration administration = new FileAdministration();

        boolean example = true;

        if(example) {
            administration.createUploader("Jan");
            administration.createUploader("Alex");
            administration.createUploader("Marlon");
            administration.createUploader("Eddy");

            administration.addUpload("Audio","Alex");
            administration.addUpload("Audio","Alex");
            administration.addUpload("Audio","Alex");

            administration.addUpload("Video","Jan");
            administration.addUpload("Video","Jan");
            administration.addUpload("Video","Jan");

            administration.addUpload("LicensedVideo","Marlon");
            administration.addUpload("LicensedVideo","Marlon");
            administration.addUpload("LicensedVideo","Marlon");

            administration.addUpload("LicensedAudioVideo","Eddy");
            administration.addUpload("LicensedAudioVideo","Eddy");
            administration.addUpload("LicensedAudioVideo","Eddy");

            administration.addUpload("AudioVideo","Alex");
            administration.addUpload("AudioVideo","Alex");
            administration.addUpload("AudioVideo","Alex");
            administration.addUpload("AudioVideo","Alex");
        }

        CMDCreateUploaderListener createUploaderListener = new CMDCreateUploaderListener(administration);
        CMDCreateFileListener createFileListener = new CMDCreateFileListener(administration);
        CMDDeleteFileListener deleteFileListener = new CMDDeleteFileListener(administration);
        CMDAccessFileListener accessListener = new CMDAccessFileListener(administration);

        CMDAccessFileHandler accessHandler = new CMDAccessFileHandler();
        CMDCreateUploaderHandler createUploaderHandler = new CMDCreateUploaderHandler();
        CMDCreateFileHandler createFileHandler = new CMDCreateFileHandler();
        CMDDeleteFileHandler deleteFileHandler = new CMDDeleteFileHandler();

        accessHandler.addListener(accessListener);
        createUploaderHandler.addListener(createUploaderListener);
        createFileHandler.addListener(createFileListener);
        deleteFileHandler.addListener(deleteFileListener);

        Console console = new Console(createUploaderHandler,createFileHandler,deleteFileHandler,accessHandler);
        CMDEventInfoListener eventInfoListener = new events.eventinfo.implementation.CMDEventInfoListener(console);
        CMDEventInfoHandler eventInfoHandler = new CMDEventInfoHandler();
        eventInfoHandler.addListener(eventInfoListener);

        AdministrationController controller = new AdministrationController(administration,accessHandler,
                createUploaderHandler,createFileHandler,deleteFileHandler);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/administration.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("Administration");
        Image applicationIcon = new Image("/resources/img/administration.png");
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setScene(new Scene(root, 800, 360));
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(800);
        primaryStage.show();


    }
    public static void main(String[] args) {launch(args);}

}
