package login;

public class Member {
    private String id;
    private String password;
    private String nickname;

    Member() {}
    Member(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }

    void setId(String id) { this.id = id; }
    void setPassword(String password) { this.password = password; }
    void setNickname(String nickname) { this.nickname = nickname; }
    String getId() { return id;}
    String getPassword() { return password;}
    String getNickname() { return nickname;}
}