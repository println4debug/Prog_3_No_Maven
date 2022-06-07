/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

import mediaDB.LicensedAudioVideo;

public class LicensedAudio extends Audio implements mediaDB.LicensedAudio {

    private String holder;

    public LicensedAudio() {
        super();
        this.holder = "NOT INITIALIZED";
    }

    public LicensedAudio(Uploader u, String address) {
        super(u,address);
    }
    @Override
    public String getHolder() {
        return this.holder;
    }
}
