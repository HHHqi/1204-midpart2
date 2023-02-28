package dao;

public class Admin extends AdminAndUserMiddle{
    private boolean role=true;

    public Admin() {
    }

    public Admin(int id, String username, String password, boolean role) {
        super(id, username, password);
        this.role = role;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
