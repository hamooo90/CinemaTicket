package model;

public class MovieModel {
    private int id;
    private String movie,cinema,hall,time,about,
            director,actor1,actor2,actor3,genre,img;

    public String getAbout() {
        return about;
    }

    public String getDirector() {
        return director;
    }

    public String getActor1() {
        return actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public String getActor3() {
        return actor3;
    }

    public String getGenre() {
        return genre;
    }

    public String getImg() {
        return img;
    }

    public MovieModel(int id, String movie, String cinema, String hall,
                      String time, String about, String director, String actor1,
                      String actor2, String actor3, String genre, String img) {
        this.id = id;
        this.movie = movie;
        this.cinema = cinema;
        this.hall = hall;
        this.time = time;
        this.about = about;
        this.director = director;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
        this.genre = genre;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getMovie() {
        return movie;
    }

    public String getCinema() {
        return cinema;
    }

    public String getHall() {
        return hall;
    }

    public String getTime() {
        return time;
    }

}
