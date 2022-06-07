/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

public class InteractiveVideo extends Video implements mediaDB.InteractiveVideo {

    private String type;

    public InteractiveVideo() {
        super();
        this.type = "NOT INITIALIZED";
    }
    public InteractiveVideo(Uploader u, String address) {
        super(u,address);
    }
    @Override
    public String getType() {
        return null;
    }
}
