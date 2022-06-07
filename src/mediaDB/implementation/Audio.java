/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

import mediaDB.Uploader;


public class Audio extends File implements mediaDB.Audio {

    private int samplingRate;

    public Audio() {
        super();
    }

    public Audio(Uploader u, String address) {
        super(u,address);
    }

    @Override
    public int getSamplingRate() {
        return this.samplingRate;
    }
}
