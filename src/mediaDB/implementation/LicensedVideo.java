/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

public class LicensedVideo extends Video implements mediaDB.LicensedVideo {

    private String holder;

    public LicensedVideo() {
        super();
        this.holder = "NOT INITIALIZED";
    }

    public LicensedVideo(Uploader u, String address) {
        super(u,address);
    }

    @Override
    public String getHolder() {
        return this.holder;
    }

}
