/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package businesslogic;

import mediaDB.Uploader;
import mediaDB.implementation.Audio;
import mediaDB.implementation.File;

public class FileFactory {

    private FileAdministration administration;

    public FileFactory(FileAdministration administration) {
        this.administration = administration;
    }

    public File createFile(String type, Uploader uploader) {
        File file;
        switch (type) {
            case "Audio": file = new Audio(uploader,administration.createUniqueAddress());break;
            case "Video": file = new mediaDB.implementation.Video((mediaDB.implementation.Uploader) uploader,administration.createUniqueAddress());break;
            case "AudioVideo": file = new mediaDB.implementation.AudioVideo((mediaDB.implementation.Uploader) uploader,administration.createUniqueAddress());break;
            case "InteractiveVideo": file = new mediaDB.implementation.InteractiveVideo((mediaDB.implementation.Uploader) uploader,administration.createUniqueAddress());break;
            case "LicensedAudio": file = new mediaDB.implementation.LicensedAudio((mediaDB.implementation.Uploader) uploader,administration.createUniqueAddress());break;
            case "LicensedVideo": file = new mediaDB.implementation.LicensedVideo((mediaDB.implementation.Uploader) uploader,administration.createUniqueAddress());break;
            case "LicensedAudioVideo": file = new mediaDB.implementation.LicensedAudioVideo((mediaDB.implementation.Uploader) uploader,administration.createUniqueAddress());break;
            default: throw new IllegalArgumentException("Type unknown!");
        }
        return file;
    }
}
