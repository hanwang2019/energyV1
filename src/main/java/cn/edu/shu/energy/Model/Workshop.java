package cn.edu.shu.energy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Workshop {

    public Workshop() {
        super();
        ensureId();
    }

    public Workshop(String name, String description) {
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

    @JsonIgnore
    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL)
    private List<District> districts;

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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
