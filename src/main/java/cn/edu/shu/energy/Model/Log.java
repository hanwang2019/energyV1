package cn.edu.shu.energy.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
public class Log {

    public Log() {
        super();
        ensureId();
        this.event = "";
        this.operateTime = new Date();
        this.operator = new User();
    }

    public Log(String event, User operator) {
        super();
        ensureId();
        this.event = event;
        this.operateTime = new Date();
        this.operator = operator;
    }

    private void ensureId() {
        this.setId(UUID.randomUUID().toString());
    }

    @Id
    private String id;

    private String event;

    private Date operateTime;

    @ManyToOne
    @JoinColumn(name = "operator")
    private User operator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }
}
