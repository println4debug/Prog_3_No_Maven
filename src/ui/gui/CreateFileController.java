/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package ui.gui;

import events.createfile.implementation.CMDCreateFileEvent;
import events.createfile.implementation.CMDCreateFileHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class CreateFileController {

    private CMDCreateFileHandler createFileHandler;

    @FXML private TextField txtFieldType;

    @FXML private TextField txtFieldUploader;

    public CreateFileController(CMDCreateFileHandler createFileHandler) {
        this.createFileHandler = createFileHandler;
        this.txtFieldType = new TextField();
        this.txtFieldUploader = new TextField();
    }

    @FXML
    void init() {

    }

    @FXML
    void clickedCreateFile() {
        if(this.txtFieldUploader.textProperty().get().length() != 0 && this.txtFieldType.textProperty().get().length() != 0) {
            CMDCreateFileEvent createFileEvent = new CMDCreateFileEvent(this,this.txtFieldType.textProperty().get(),this.txtFieldUploader.textProperty().get());
            createFileHandler.handle(createFileEvent);
            clickedCancel();
        }
    }
    @FXML
    void clickedCancel() {
        Window actual = txtFieldType.getScene().getWindow();
        actual.fireEvent(new WindowEvent(actual, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
