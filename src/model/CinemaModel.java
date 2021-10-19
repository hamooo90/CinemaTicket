package model;

public class CinemaModel {
    private int id;
    private String name,address,img;

    public CinemaModel(int id, String name, String address, String img) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public String getImg() {
        return img;
    }

    public void CinemaModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void clear(){
        id = 0;
        name = "";
        address = "";
        img = "";
    }
}
