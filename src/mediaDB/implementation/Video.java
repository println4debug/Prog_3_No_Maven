/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

public class Video extends File implements mediaDB.Video {
    private int resolution;

    public Video() {
        super();
    }

    public Video(Uploader u, String adress) {
        super(u,adress);
    }

    @Override
    public int getResolution() {
        return this.resolution;
    }
}
