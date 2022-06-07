/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package mediaDB.implementation;

import javafx.beans.property.*;
import mediaDB.MediaContent;
import mediaDB.Tag;
import mediaDB.Uploadable;
import mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class File implements Uploadable, MediaContent {

    private int samplingRate;
    private SimpleStringProperty address;
    private ObjectProperty<Collection<Tag>> tags;
    private SimpleLongProperty acesscount;
    private ObjectProperty<BigDecimal> bitrate;
    private ObjectProperty<Duration> length;
    private ObjectProperty<BigDecimal> size;
    private Uploader uploader;
    private ObjectProperty<Date> date;
    private SimpleStringProperty type;

    public File() {
        this.acesscount = new SimpleLongProperty(0);
        this.address = new SimpleStringProperty("NOT INITIALIZED");
        this.uploader = new mediaDB.implementation.Uploader("NOT INITIALIZED");
        this.bitrate = new SimpleObjectProperty<>(new BigDecimal(100));

        this.date = new SimpleObjectProperty<>(new Date());
        this.size = new SimpleObjectProperty<>(new BigDecimal(100));
        this.tags = new SimpleObjectProperty<>(new LinkedList<>());
        this.type = new SimpleStringProperty(this.getClass().getSimpleName());
        this.length = new SimpleObjectProperty<>();
    }

    public File(Uploader u, String address) {
        this();
        this.address.setValue(address);
        this.uploader = u;
    }

    public File(Uploader u, String address,BigDecimal bitrate, BigDecimal size, Collection<Tag> tags) {
        this(u,address);
        this.bitrate.set(bitrate);
        this.size.set(size);
        this.tags.set(tags);
    }

    @Override
    public String getAddress() {
        return this.address.get();
    }

    public SimpleStringProperty getAddressProperty() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address.setValue(address);
    }

    @Override
    public Collection<Tag> getTags() {
        return this.tags.get();
    }

    public ObjectProperty<Collection<Tag>> getTagsProperty() {
        return this.tags;
    }

    public void setSamplingRate(int samplingRate) {
        this.samplingRate = samplingRate;
    }

    public void setAcesscount(long acesscount) {
        this.acesscount.set(acesscount);
    }

    public void setBitrate(BigDecimal bitrate) {
        this.bitrate.set(bitrate);
    }

    public void setLength(Duration length) {
        this.length.set(length);
    }

    public void setSize(BigDecimal size) {
        this.size.set(size);
    }

    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.set(tags);
    }

    @Override
    public long getAccessCount() {
        return this.acesscount.get();
    }

    public SimpleLongProperty getAccesscountProperty() {
        return this.acesscount;
    }

    @Override
    public BigDecimal getBitrate() {
        return this.bitrate.get();
    }

    public ObjectProperty<BigDecimal> getBitrateProperty() {
        return this.bitrate;
    }

    @Override
    public Duration getLength() {
        return this.length.get();
    }

    public ObjectProperty<Duration> getLengthProperty() {
        return this.length;
    }

    @Override
    public BigDecimal getSize() {
        return this.size.get();
    }

    public ObjectProperty<BigDecimal> getSizeProperty() {
        return this.size;
    }

    @Override
    public Uploader getUploader() {
        return this.uploader;
    }

    @Override
    public Date getUploadDate() {
        return this.date.get();
    }

    public ObjectProperty<Date> getDateProperty(){
        return this.date;
    }

    public void increaseAccess() {
        this.acesscount.set(this.getAccessCount()+1);
    }

    public String getType() {
        return this.type.get();
    }

    public SimpleStringProperty getTypeProperty() {
        return this.type;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "-File, Address: " + this.getAddress();
    }
}
