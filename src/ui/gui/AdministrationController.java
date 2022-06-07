/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package ui.gui;

import businesslogic.FileAdministration;
import events.accessfile.implementation.CMDAccessFileEvent;
import events.accessfile.implementation.CMDAccessFileHandler;
import events.createfile.implementation.CMDCreateFileHandler;
import events.createuploader.implementation.CMDCreateUploaderHandler;
import events.deletefile.implementation.CMDDeleteFileEvent;
import events.deletefile.implementation.CMDDeleteFileHandler;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mediaDB.Uploader;
import mediaDB.implementation.File;
import ui.gui.comperator.*;
import ui.gui.comperator.FileAccessCountComperator;

import java.io.IOException;
import java.util.Comparator;

import static javafx.scene.control.ContentDisplay.GRAPHIC_ONLY;

public class AdministrationController {

    @FXML private TextField txtFieldAddress;
    @FXML private TextField txtFieldUploader;

    @FXML private Text txtType;
    @FXML private Text txtAccesses;
    @FXML private Text txtTags;
    @FXML private Text txtBit;
    @FXML private Text txtLength;
    @FXML private Text txtSize;
    @FXML private Text txtUploadDate;

    @FXML private ListView<File> fileListView;
    @FXML private ListView<Uploader> userListView;

    @FXML private Button btnDelete;
    @FXML private Button btnAccess;

    private FileAdministration model;

    private CMDAccessFileHandler accessFileHandler;
    private CMDCreateUploaderHandler createUploaderHandler;
    private CMDCreateFileHandler createFileHandler;
    private CMDDeleteFileHandler deleteFileHandler;

    private File recentlyClicked;
    private ChangeListener updateListener; // triggers if access file or changes the address
    private SimpleStringProperty recentlyClickedUploaderNameProperty; // Because the interface is not allowing to add getProperty
    private ChangeListener modeSetListener; // triggers if User clicks on a sort menu item

    private SimpleIntegerProperty sortModeProperty;

    public AdministrationController(FileAdministration model) {
        if(model == null) {
            throw new NullPointerException("Model was null!");
        }

        this.txtFieldAddress = new TextField();
        this.txtFieldUploader = new TextField();

        this.txtType = new Text();
        this.txtAccesses = new Text();
        this.txtBit = new Text();
        this.txtTags = new Text();
        this.txtLength = new Text();
        this.txtSize = new Text();
        this.txtUploadDate = new Text();

        this.btnDelete = new Button();
        this.btnAccess = new Button();

        this.userListView = new ListView<>();
        this.fileListView = new ListView<>();

        this.model = model;

        this.recentlyClicked = null;
        this.updateListener = null;
        this.modeSetListener = null;

        this.recentlyClickedUploaderNameProperty = new SimpleStringProperty();
        this.sortModeProperty = new SimpleIntegerProperty(0);
    }

    public AdministrationController(FileAdministration model, CMDAccessFileHandler accessHandler,
                                    CMDCreateUploaderHandler createUploaderHandler,
                                    CMDCreateFileHandler createFileHandler,
                                    CMDDeleteFileHandler deleteFileHandler) {
        this(model);
        if(accessHandler == null || createFileHandler == null || createUploaderHandler == null || deleteFileHandler == null) {
            throw new NullPointerException("Handler was null!");
        }
        this.accessFileHandler = accessHandler;
        this.createUploaderHandler = createUploaderHandler;
        this.createFileHandler = createFileHandler;
        this.deleteFileHandler = deleteFileHandler;

        this.modeSetListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                updateListItemsBySortMode();
            }
        };

        this.updateListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateListItemsBySortMode();
            }
        };
    }

    @FXML
    void initialize() {
        this.fileListView.setItems(this.model.getFileListPropery());
        this.userListView.setItems(this.model.getUploaderListPropery());
        this.model.getFileListPropery().addListener(new InvalidationListener() { // When an item gets deleted it needs to refresh the File-Info fields
            @Override
            public void invalidated(Observable observable) {
                updateFileInfoField();
            }
        });
        updateFileInfoField();
        initButtonImages();
    }

    @FXML
    void updateFileInfoField() {
        unbindTexts();
        if(fileListView.getItems().size() == 0) {
            setTextsToEmpty();
            return;
        }

        File clickedFile = fileListView.getSelectionModel().getSelectedItem();
        this.recentlyClicked = clickedFile;

        if(clickedFile == null) {
            clickedFile = fileListView.getItems().get(0);
            recentlyClicked = clickedFile;
        }

        this.sortModeProperty.addListener(modeSetListener);
        this.txtAccesses.textProperty().addListener(updateListener);
        this.txtFieldAddress.textProperty().addListener(updateListener);

        this.txtFieldAddress.textProperty().bindBidirectional(clickedFile.getAddressProperty()); //ToDo: check doubles
        this.recentlyClickedUploaderNameProperty.setValue(clickedFile.getUploader().getName());
        this.txtAccesses.textProperty().bind(clickedFile.getAccesscountProperty().asString());
        this.txtType.textProperty().bind(clickedFile.getTypeProperty());
        this.txtFieldUploader.textProperty().bind(recentlyClickedUploaderNameProperty);
        this.txtBit.textProperty().bind(clickedFile.getBitrateProperty().asString());
        this.txtSize.textProperty().bind(clickedFile.getSizeProperty().asString());
        this.txtLength.textProperty().bind(clickedFile.getLengthProperty().asString());
        this.txtTags.textProperty().bind(clickedFile.getTagsProperty().asString());
        this.txtUploadDate.textProperty().bind(clickedFile.getDateProperty().asString());
    }

    void unbindTexts() {
        if(this.recentlyClicked != null) {
            this.txtFieldAddress.textProperty().removeListener(this.updateListener);
            this.txtAccesses.textProperty().removeListener(this.updateListener);
            this.sortModeProperty.removeListener(modeSetListener);

            this.txtFieldAddress.textProperty().unbindBidirectional(recentlyClicked.getAddressProperty());
            this.txtAccesses.textProperty().unbind();
            this.txtType.textProperty().unbind();
            this.txtBit.textProperty().unbind();
            this.txtSize.textProperty().unbind();
            this.txtLength.textProperty().unbind();
            this.txtTags.textProperty().unbind();
            this.txtUploadDate.textProperty().unbind();
            this.txtFieldUploader.textProperty().unbind();
        }
    }
    void setTextsToEmpty() {
        this.txtFieldAddress.textProperty().setValue("EMPTY");
        this.txtAccesses.textProperty().setValue("EMPTY");
        this.txtType.textProperty().setValue("EMPTY");
        this.txtFieldUploader.textProperty().setValue("EMPTY");
        this.txtBit.textProperty().setValue("EMPTY");
        this.txtSize.textProperty().setValue("EMPTY");
        this.txtLength.textProperty().setValue("EMPTY");
        this.txtTags.textProperty().setValue("EMPTY");
        this.txtUploadDate.textProperty().setValue("EMPTY");
    }

    @FXML void clickedUnsorted() {
        this.sortModeProperty.set(0);
    }

    @FXML void clickedAddress() {
        this.sortModeProperty.set(1);
    }

    @FXML void clickedSortAccess() {
        this.sortModeProperty.set(2);
    }

    @FXML void clickedSortUploader() {
        this.sortModeProperty.set(3);
    }

    @FXML void clickedSortDate() {
        this.sortModeProperty.set(4);
    }

    void initButtonImages() {
        // https://edencoding.com/how-to-add-an-image-to-a-button/
        Image imgDelete = new Image("/resources/img/x-button.png");
        ImageView imgViewDelete = new ImageView(imgDelete);
        imgViewDelete.setFitHeight(80);
        imgViewDelete.setPreserveRatio(true);
        imgViewDelete.fitWidthProperty().bind(btnDelete.widthProperty().divide(1.5));
        imgViewDelete.fitHeightProperty().bind(btnDelete.heightProperty().divide(1.5));
        this.btnDelete.setGraphic(imgViewDelete);
        this.btnDelete.setContentDisplay(GRAPHIC_ONLY);

        Image imgAccess = new Image("/resources/img/play.png");
        ImageView imgViewAccess = new ImageView(imgAccess);
        imgViewAccess.setFitHeight(80);
        imgViewAccess.setPreserveRatio(true);
        imgViewAccess.fitWidthProperty().bind(btnAccess.widthProperty().divide(1.15));
        imgViewAccess.fitHeightProperty().bind(btnAccess.heightProperty().divide(1.15));
        this.btnAccess.setGraphic(imgViewAccess);
        this.btnAccess.setContentDisplay(GRAPHIC_ONLY);

    }

    @FXML
    void clickedAccess() {
        if(recentlyClicked != null) {
            CMDAccessFileEvent accessEvent = new CMDAccessFileEvent(this,recentlyClicked.getAddress());
            accessFileHandler.handle(accessEvent);
        } else {
            showAlert("No File selected!","Please select an Item from the ListView!");
        }
    }

    @FXML
    void clickedCreateUploader() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/createuser.fxml"));
        CreateUserController createUserController = new CreateUserController(createUploaderHandler);
        loader.setController(createUserController);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Create User");
        stage.setScene(new Scene(root, 200, 100));
        stage.show();
    }
    @FXML
    void clickedCreateFile() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/createfile.fxml"));
        CreateFileController createFileController = new CreateFileController(createFileHandler);
        loader.setController(createFileController);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Create File");
        stage.setScene(new Scene(root, 200, 100));
        stage.show();

    }
    @FXML
    void clickedDeleteFile() {
        File clickedFile = fileListView.getSelectionModel().getSelectedItem();
        this.recentlyClicked = clickedFile;
        if(clickedFile != null) {
            CMDDeleteFileEvent deleteFileEvent = new CMDDeleteFileEvent(this,clickedFile.getAddress());
            this.deleteFileHandler.handle(deleteFileEvent);
        } else {
            showAlert("No File selected!","Please select an Item from the ListView!");
        }
    }
    void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public Comparator<File> getComp(){
        switch(sortModeProperty.get()) {
            case 1: return new FileAddressComperator();
            case 2: return new FileAccessCountComperator();
            case 3: return new FileUploaderComperator();
            case 4: return new FileDateComperator();
            default: return null;
        }
    }

    public void updateListItemsBySortMode() {
        if(sortModeProperty.get() == 0) {
            fileListView.setItems(model.getFileListPropery().sorted(null));
        } else {
            fileListView.setItems(model.getFileListPropery().sorted(getComp()));
        }
    }
}
