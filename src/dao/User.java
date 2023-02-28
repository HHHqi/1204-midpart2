package dao;

public class User extends AdminAndUserMiddle{
    private boolean role=false;

    public User() {
    }

    public User(int id, String username, String password, boolean role) {
        super(id, username, password);
        this.role = role;
    }
    public User(String username, String password){
        super(username,password);
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

}
