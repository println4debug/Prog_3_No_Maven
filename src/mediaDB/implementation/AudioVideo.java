/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

public class AudioVideo extends Video implements mediaDB.AudioVideo {

    private int samplingRate;

    public AudioVideo() {
        super();
        this.samplingRate = 0;
    }

    public AudioVideo(Uploader u, String address) {
        super(u,address);
    }

    @Override
    public int getSamplingRate() {
        return this.samplingRate;
    }

}
