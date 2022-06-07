/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package ui.gui.comperator;

import mediaDB.implementation.File;

import java.util.Comparator;

public class FileAccessCountComperator implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        if(o1.getAccessCount() == o2.getAccessCount()) { return 0; }
        else if(o1.getAccessCount() < o2.getAccessCount()) {
            return 1;
        } else {
            return -1;
        }
    }
}
