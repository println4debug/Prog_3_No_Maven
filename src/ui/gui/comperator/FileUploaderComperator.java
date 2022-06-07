/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package ui.gui.comperator;

import mediaDB.implementation.File;

import java.util.Comparator;

public class FileUploaderComperator implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        return o1.getUploader().getName().compareTo(o2.getUploader().getName());
    }
}
