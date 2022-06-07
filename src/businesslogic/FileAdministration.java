/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package businesslogic;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import mediaDB.MediaContent;
import mediaDB.Tag;
import mediaDB.Uploadable;
import mediaDB.Uploader;
import mediaDB.implementation.File;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FileAdministration implements Serializable {

    private ObservableList<Uploader> uploaderList;
    private ObservableList<File> uploadedContentList;

    private FileFactory fileFactory;

    public static BigDecimal MAX_FILE_SIZE = new BigDecimal(100); // Max Filesize
    private static BigDecimal totalMemory = new BigDecimal(100000000); // Max File-capacity for all files

    public FileAdministration() {
        this.uploaderList = FXCollections.observableList(new LinkedList<>());
        this.uploadedContentList = FXCollections.observableList(new LinkedList<>());
        this.fileFactory = new FileFactory(this);
    }

    public synchronized boolean createUploader(String name) {
        if(name == null || name.equals("")) { return false; }
        if (checkUploaderAlreadyExists(name)) { return false; }
        Uploader toAdd = new mediaDB.implementation.Uploader(name);
        try {
            return uploaderList.add(toAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public synchronized boolean createUploader(Uploader uploader) {
        if (checkUploaderAlreadyExists(uploader.getName())) { return false; }
        try {
            return uploaderList.add(uploader);
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized boolean deleteUploader(String name) {
        for (Uploader u : this.uploaderList) {
            if (name.equals(u.getName())) {
                try {
                    return uploaderList.remove(u);
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    public synchronized boolean deleteUploader(Uploader uploader) {
        try {
            return uploaderList.remove(uploader);
        } catch (Exception e) {
            return false;
        }
    }

    private synchronized boolean checkUploaderAlreadyExists(String name) {
        for (Uploader u : this.uploaderList) {
            if (u.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFileOwner(String name, File item) {
        return item.getUploader().getName().equals(name);
    }

    public synchronized String getAllUploadersWithUploadCount() {
        StringBuilder uploaderList = new StringBuilder();
        for (Uploader uploader : this.uploaderList) {
            uploaderList.append(uploader.getName())
                    .append(" Uploads: ").append(getTotalUploadsCount(uploader.getName()))
                    .append(System.lineSeparator());
        }
        return uploaderList.toString();
    }

    public synchronized int getTotalUploadsCount(String name) throws IllegalArgumentException{
        if (checkUploaderAlreadyExists(name)) {
            int counter = 0;
            for (File item : uploadedContentList) {
                if (isFileOwner(name, item)) {
                    counter++;
                }
            }
            return counter;
        } else {
            throw new IllegalArgumentException("Uploader not found!");
        }
    }

    public synchronized int getTotalFilesCount() {
        return this.uploadedContentList.size();
    }

    public synchronized boolean addUpload(String type, Uploader uploader) throws IllegalArgumentException{
        if(!checkUploaderAlreadyExists(uploader.getName())) { return false; }
        try {
            return uploadedContentList.add(fileFactory.createFile(type, uploader));
        } catch(Exception e) {
            return false;
        }

    }

    public synchronized boolean addUpload(String type, String uploader) {
        for(Uploader uploaderFromList : this.uploaderList) {
            if(uploaderFromList.getName().equals(uploader)) {
                File fileToAdd = fileFactory.createFile(type,uploaderFromList);
                //Todo: DELETE later!
                insertRandomValuesToFile(fileToAdd);
                uploadedContentList.add(fileToAdd);
                return true;
            }
        }
        return false;
    }

    private void insertRandomValuesToFile(File fileToEdit) {
        Random rand = new Random();
        fileToEdit.setBitrate(BigDecimal.valueOf(rand.nextInt(10000)));
        Collection<Tag> tags= new LinkedList<>();
        if(rand.nextInt(4) < 2) {
            tags.add(Tag.News);
        }
        if(rand.nextInt(4) < 2) {
            tags.add(Tag.Lifestyle);
        }
        if(rand.nextInt(4) < 2) {
            tags.add(Tag.Animal);
        }
        if(rand.nextInt(4) < 2) {
            tags.add(Tag.Tutorial);
        }
        fileToEdit.setTags(tags);
        fileToEdit.setSize(BigDecimal.valueOf(rand.nextInt(10000)));
    }

    public synchronized boolean addUpload(File fileToAdd)throws IllegalArgumentException {
        if (!isUploadable(fileToAdd)) {
            throw new IllegalArgumentException("File is not uploadable!");
        }
        if (fileToAdd.getAddress().equals("NOT INITIALIZED")) {
            throw new IllegalArgumentException("File data have not been initalizied.");
        }
        if (!checkUploaderAlreadyExists(fileToAdd.getUploader().getName())) {
            throw new IllegalArgumentException("Uploader does not exist!");
        }
        if (fileToAdd.getSize().compareTo(MAX_FILE_SIZE) > 0) {
            throw new IllegalArgumentException("File is too big!");
        }
        try {
            return this.uploadedContentList.add(fileToAdd);
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized File getFile(String adress) {
        for(File file: this.uploadedContentList) {
            if(file.getAddress().equals(adress)) {
                return file;
            }
        }
        return null;
    }

    public synchronized File getRandomFile() {
        Random rnd = new Random();
        int filenumber = rnd.nextInt(this.uploadedContentList.size());
        int i = 0;
        for(File file : uploadedContentList) {
            if(i == filenumber) {
                return file;
            }
            i++;
        }
        return null;
    }

    private boolean isUploadable(File toUpload) {
        return toUpload instanceof Uploadable && toUpload instanceof MediaContent;
    }

    public synchronized boolean removeUpload(File file) {
        if(file == null) { return false; }
        try {
            return uploadedContentList.remove(file);
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized boolean removeUpload(String address) throws NullPointerException{
        if(address == null) { return false; }
        for (File item : uploadedContentList) {
            if (item.getAddress().equals(address)) {
                try {
                    return this.uploadedContentList.remove(item);
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }
    public synchronized boolean removeRandomUpload() throws NullPointerException{
        File toDelete = getRandomFile();
        try {
            return uploadedContentList.remove(toDelete);
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized String getContentListAsString() {
        StringBuilder contentList = new StringBuilder();
        for (File item : uploadedContentList) {
            contentList.append("Address: ").append(item.getAddress())
                    .append(" Uploaded by: ").append(item.getUploader().getName())
                    .append(" Upload-date: ").append(item.getUploadDate())
                    .append(" Type: ").append(item.getClass().getName())
                    .append(System.lineSeparator());
        }
        return contentList.toString();
    }

    public synchronized String createUniqueAddress() {
        // Mit Hilfe von:https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rd = new Random();
        StringBuilder address = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            int variant = ThreadLocalRandom.current().nextInt(0, 1 + 1);
            if (variant < 1) {
                address.append(alphabet.charAt(rd.nextInt(alphabet.length())));
            } else {
                address.append(ThreadLocalRandom.current().nextInt(0, 9 + 1));
            }
        }
        if (addressAlreadyExist(address.toString())) {
            return createUniqueAddress();
        } else {
            return address.toString();
        }
    }

    private synchronized boolean addressAlreadyExist(String address) {
        for (File item : uploadedContentList) {
            if (item.getAddress().equals(address)) {
                return true;
            }
        }
        return false;
    }

    private synchronized boolean validateAddress(File fileToCheck) {
        return !addressAlreadyExist(fileToCheck.getAddress())
                && !fileToCheck.getAddress().equals("")
                && !fileToCheck.getAddress().equals("NOT INITIALIZED")
                && fileToCheck.getAddress()!=null;
    }

    public synchronized void increaseAccessCount(File file) throws NullPointerException{
        if(file == null) {
            throw new NullPointerException("File was null!");
        }
        file.increaseAccess();
    }

    public synchronized ObservableList<Uploader> getUploaderListPropery() {
        return this.uploaderList;
    }
    public synchronized ObservableList<File> getFileListPropery() {
        return this.uploadedContentList;
    }
}
