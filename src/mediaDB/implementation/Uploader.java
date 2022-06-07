/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

import javafx.beans.property.SimpleStringProperty;

public class Uploader implements mediaDB.Uploader {

    private SimpleStringProperty name;

    public Uploader(String name) {
        if(name == null) { throw new IllegalArgumentException("Name was null!"); }
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public String getName() {
        return this.name.get();
    }

    public SimpleStringProperty getNameProperty() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
