package by.prus.springssoserver.entity;

import jakarta.persistence.*;

@Entity
public class IpCustomUser {

    /**
     * IP belongs only for one uesr
     * One user can have several available IPs
     */
    @Id
    @Column(nullable = false, unique = true)
    private String ip;

    public IpCustomUser() {}
    private Integer userId;
    public String getIp() {return ip;}
    public void setIp(String ip) {this.ip = ip;}
    public Integer getUserId() {return userId;}
    public void setUserId(Integer userId) {this.userId = userId;}
}
