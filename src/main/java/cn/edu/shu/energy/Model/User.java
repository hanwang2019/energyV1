package cn.edu.shu.energy.Model;

import cn.edu.shu.energy.Util.MD5Encode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

    public static String CURRENT_USER = "current_user";

    public User() {
        super();
        ensureId();
        this.username = "";
        this.password = "";
        this.name = "";
        this.department = "";
        this.lastLogin = new Date(0);
    }

    public User(String username, String password, String name, String department) {
        super();
        ensureId();
        this.username = username;
        this.password = encryptPassword(password);
        this.name = name;
        this.department = department;
        this.lastLogin = new Date(0);
    }

    private void ensureId() {
        this.setId(UUID.randomUUID().toString());
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "last_login")
    private Date lastLogin;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "operator")
    private List<Log> logs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(MD5Encode.encode(password).replace("0", "") + MD5Encode.encode(password).replace("4", ""));
    }

    public static String encryptPassword(String password) {
        return MD5Encode.encode(password).replace("0", "") + MD5Encode.encode(password).replace("4", "");
    }

    public User clearId() {
        this.id = "";
        return this;
    }
}
