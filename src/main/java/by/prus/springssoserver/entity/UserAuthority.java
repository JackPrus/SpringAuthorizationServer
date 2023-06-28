package by.prus.springssoserver.entity;

public enum UserAuthority {

    USER("USER"),
    ADMIN("ADMIN");

    private String authority;

    private UserAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
