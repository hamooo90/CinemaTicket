package service;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.MovieModel;

import java.io.IOException;
import java.net.URL;

public class Util {
    private Pane view;

    public static String currentCinema;
    public static String currentMovie;
    public static int currentHall;
    public static String currentMovieTime;
    public static MovieModel currentSelectedHall;


    public void setCurrentSelectedHall(MovieModel currentSelectedHall) {
        Util.currentSelectedHall = currentSelectedHall;
    }

    public MovieModel getCurrentSelectedHall() {
        return currentSelectedHall;
    }

    public void setCurrentMovieTime(String currentMovieTime) {
        Util.currentMovieTime = currentMovieTime;
    }

    public  String getCurrentMovieTime() {
        return currentMovieTime;
    }

    public int getCurrentHall() {
        return currentHall;
    }

    public void setCurrentHall(int currentHall) {
        Util.currentHall = currentHall;
    }

    public void setCurrentMovie(String currentMovie) {
        Util.currentMovie = currentMovie;
        int a=0;
    }

    public String getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentCinema(String currentCinema) {
        Util.currentCinema = currentCinema;
    }

    public String getCurrentCinema() {
        return currentCinema;
    }

    public Pane getPage(String fxml){

        try {
            URL fileUrl = getClass().getResource("/scenes/"+ fxml  );
            if (fileUrl == null){
                throw new java.io.FileNotFoundException("fxml cant be found");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }



}
