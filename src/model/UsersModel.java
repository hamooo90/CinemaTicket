package model;

public class UsersModel {
     int id;
     String username,password;
    public void UsersModel(String username, String password){
        this.username = username;
        this.password = password;
    }
    public void UsersModel(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void clear() {
        id = 0;
        username = "";
        password = "";
    }
}
