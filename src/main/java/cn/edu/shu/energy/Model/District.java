package cn.edu.shu.energy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class District {

    public District() {
        super();
        ensureId();
    }

    public District(String name, String description) {
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
    private Workshop workshop;

    @JsonIgnore
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Line> lines;

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

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
