package by.prus.springssoserver.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "CUSTOMUSERS")
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "customusers_authorities", joinColumns = {
            @JoinColumn(name = "customusers_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "authorities_id", referencedColumnName = "id") })
    private Set<Authority> authorities;

    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate birthdate;

    public CustomUser() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public Set<Authority> getAuthorities() {return authorities;}
    public void setAuthorities(Set<Authority> authorities) {this.authorities = authorities;}
    public Boolean getAccountNonExpired() {return accountNonExpired;}
    public void setAccountNonExpired(Boolean accountNonExpired) {this.accountNonExpired = accountNonExpired;}
    public Boolean getAccountNonLocked() {return accountNonLocked;}
    public void setAccountNonLocked(Boolean accountNonLocked) {this.accountNonLocked = accountNonLocked;}
    public Boolean getCredentialsNonExpired() {return credentialsNonExpired;}
    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {this.credentialsNonExpired = credentialsNonExpired;}
    public Boolean getEnabled() {return enabled;}
    public void setEnabled(Boolean enabled) {this.enabled = enabled;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getEmailAddress() {return emailAddress;}
    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}
    public LocalDate getBirthdate() {return birthdate;}
    public void setBirthdate(LocalDate birthdate) {this.birthdate = birthdate;}
}
