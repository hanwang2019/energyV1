package cn.edu.shu.energy.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "record")
@Inheritance(strategy = InheritanceType.JOINED)
public class Record {

    public Record() {
        super();
        ensureId();
        this.time = new Date();
        this.voltageA = 0d;
        this.voltageB = 0d;
        this.voltageC = 0d;
        this.currentA = 0d;
        this.currentB = 0d;
        this.currentC = 0d;
        this.power = 0d;
        this.energy = 0d;
    }

    public Record(Date time) {
        super();
        ensureId();
        this.time = time;
        this.voltageA = 0d;
        this.voltageB = 0d;
        this.voltageC = 0d;
        this.currentA = 0d;
        this.currentB = 0d;
        this.currentC = 0d;
        this.power = 0d;
        this.energy = 0d;
    }

    public Record(Date time, double ua, double ub, double uc, double ia, double ib, double ic, double p, double e, Device device) {
        super();
        ensureId();
        this.time = time;
        this.voltageA = ua;
        this.voltageB = ub;
        this.voltageC = uc;
        this.currentA = ia;
        this.currentB = ib;
        this.currentC = ic;
        this.power = p;
        this.energy = e;
        this.device = device;
    }

    private void ensureId() {
        this.setId(UUID.randomUUID().toString());
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "time")
    private Date time;

    @Column(name = "voltage_a")
    private double voltageA;

    @Column(name = "voltage_b")
    private double voltageB;

    @Column(name = "voltage_c")
    private double voltageC;

    @Column(name = "current_a")
    private double currentA;

    @Column(name = "current_b")
    private double currentB;

    @Column(name = "current_c")
    private double currentC;

    @Column(name = "power")
    private double power;

    @Column(name = "energy")
    private double energy;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Device device;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getVoltageA() {
        return voltageA;
    }

    public void setVoltageA(double voltageA) {
        this.voltageA = voltageA;
    }

    public double getVoltageB() {
        return voltageB;
    }

    public void setVoltageB(double voltageB) {
        this.voltageB = voltageB;
    }

    public double getVoltageC() {
        return voltageC;
    }

    public void setVoltageC(double voltageC) {
        this.voltageC = voltageC;
    }

    public double getCurrentA() {
        return currentA;
    }

    public void setCurrentA(double currentA) {
        this.currentA = currentA;
    }

    public double getCurrentB() {
        return currentB;
    }

    public void setCurrentB(double currentB) {
        this.currentB = currentB;
    }

    public double getCurrentC() {
        return currentC;
    }

    public void setCurrentC(double currentC) {
        this.currentC = currentC;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getUsage() {
        return energy;
    }

    public void setUsage(double energy) {
        this.energy = energy;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void addValue(String key, String valueString) {
        double value = Double.valueOf(valueString);
        if (key.equals("CurrentA")) {
            this.currentA = value;
        } else if (key.equals("CurrentB")) {
            this.currentB = value;
        } else if (key.equals("CurrentC")) {
            this.currentC = value;
        } else if (key.equals("VoltageA")) {
            this.voltageA = value;
        } else if (key.equals("VoltageB")) {
            this.voltageB = value;
        } else if (key.equals("VoltageC")) {
            this.voltageC = value;
        } else if (key.equals("Power")) {
            this.power = value;
        } else if (key.equals("Energy")) {
            this.energy = value;
        } else if (key.equals("PowerUsage")) {
            this.energy = value;
        }
        return;
    }

    public void addValue(String key, double value) {
        if (key.equals("CurrentA")) {
            this.currentA = value;
        } else if (key.equals("CurrentB")) {
            this.currentB = value;
        } else if (key.equals("CurrentC")) {
            this.currentC = value;
        } else if (key.equals("VoltageA")) {
            this.voltageA = value;
        } else if (key.equals("VoltageB")) {
            this.voltageB = value;
        } else if (key.equals("VoltageC")) {
            this.voltageC = value;
        } else if (key.equals("Power")) {
            this.power = value;
        } else if (key.equals("Energy")) {
            this.energy = value;
        } else if (key.equals("PowerUsage")) {
            this.energy = value;
        }
        return;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", voltageA=" + voltageA +
                ", voltageB=" + voltageB +
                ", voltageC=" + voltageC +
                ", currentA=" + currentA +
                ", currentB=" + currentB +
                ", currentC=" + currentC +
                ", power=" + power +
                ", energy=" + energy +
                ", device=" + device.getId() +
                '}';
    }
}
