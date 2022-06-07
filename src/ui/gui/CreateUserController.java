/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package ui.gui;

import events.createuploader.implementation.CMDCreateUploaderEvent;
import events.createuploader.implementation.CMDCreateUploaderHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class CreateUserController {
    private CMDCreateUploaderHandler createUploaderHandler;

    @FXML private TextField txtFieldUploader;

    public CreateUserController(CMDCreateUploaderHandler createUploaderHandler) {
        this.createUploaderHandler = createUploaderHandler;
        this.txtFieldUploader = new TextField();
    }

    @FXML
    void init() {

    }

    @FXML
    void clickedCreateUser() {
        if(this.txtFieldUploader.textProperty().get().length() != 0) {
            CMDCreateUploaderEvent createUploaderEvent = new CMDCreateUploaderEvent(this,this.txtFieldUploader.textProperty().get());
            createUploaderHandler.handle(createUploaderEvent);
            clickedCancel();
        }
    }
    @FXML
    void clickedCancel() {
        Window actual = txtFieldUploader.getScene().getWindow();
        actual.fireEvent(new WindowEvent(actual, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
