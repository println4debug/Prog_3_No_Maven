/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

public class LicensedAudioVideo extends Video implements mediaDB.LicensedAudioVideo {

    private int samplingRate;
    private String holder;

    public LicensedAudioVideo() {
       super();
       this.holder = "NOT INITIALIZED";
       this.samplingRate = 0;
    }

    public LicensedAudioVideo(Uploader u, String address) {
        super(u,address);
    }

    @Override
    public int getSamplingRate() {
        return this.samplingRate;
    }

    @Override
    public String getHolder() {
        return this.holder;
    }
}
