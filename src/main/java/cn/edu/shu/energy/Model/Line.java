package cn.edu.shu.energy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Line {

    public Line() {
        super();
        ensureId();
    }

    public Line(String name, String description) {
        super();
        ensureId();
        this.name = name;
        this.description = description;
    }

    private void ensureId() {
        this.setId(UUID.randomUUID().toString());
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private District district;

    @JsonIgnore
    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
    private List<Device> devices;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
