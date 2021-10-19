package model;

public class UserHistoryModel {
    private int id;
    private String movie,time,cinema,hall,seat;

    public int getId() {
        return id;
    }

    public String getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }

    public String getCinema() {
        return cinema;
    }

    public String getHall() {
        return hall;
    }

    public String getSeat() {
        return seat;
    }

    public UserHistoryModel(int id, String movie, String time, String cinema, String hall, String seat) {
        this.id = id;
        this.movie = movie;
        this.time = time;
        this.cinema = cinema;
        this.hall = hall;
        this.seat = seat;
    }
}
