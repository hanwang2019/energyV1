package cn.edu.shu.energy.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "cache")
public class Cache extends Record {

    public Cache() {
        super();
        this.type = Type.UPLOADED;
    }

    public Cache(Date time) {
        super(time);
        this.type = Type.UPLOADED;
    }

    public static enum Type {
        UPLOADED,
        CALCULATED,
    }

    @Column(name = "type")
    public Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
