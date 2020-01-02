package cn.edu.shu.energy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "device")
public class Device {

    public Device() {
        super();
        ensureId();
        this.name = "";
        this.channel = "";
        this.deviceName = "";
        this.manufacture = "";
    }

    public Device(String name, String manufacture, String channel, String deviceName) {
        super();
        ensureId();
        this.name = name;
        this.channel = channel;
        this.deviceName = deviceName;
        this.manufacture = manufacture;
    }

    private void ensureId() {
        this.setId(UUID.randomUUID().toString());
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacture")
    private String manufacture;

    @Column(name = "channel")
    private String channel;

    @Column(name = "device_name")
    private String deviceName;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Line line;

    @JsonIgnore
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Record> records;

    @JsonIgnore
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Cache> caches;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Cache> getCaches() {
        return caches;
    }

    public void setCaches(List<Cache> caches) {
        this.caches = caches;
    }
}
