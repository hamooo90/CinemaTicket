package listcell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import model.MovieModel;

import java.io.IOException;

public class SearchListViewCell extends ListCell<MovieModel> {
    @FXML
    private Label movie,cinema,hall,seats,time;

    @FXML
    private HBox hbox;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(MovieModel movieModel, boolean empty) {
        super.updateItem(movieModel, empty);

        if(empty || movieModel == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("movieListViewCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            movie.setText(movieModel.getMovie());
            cinema.setText(movieModel.getCinema());
            hall.setText(movieModel.getHall());
            seats.setText(movieModel.getDirector());
            time.setText(movieModel.getTime());


            setText(null);
            setGraphic(hbox);
        }

    }
}
