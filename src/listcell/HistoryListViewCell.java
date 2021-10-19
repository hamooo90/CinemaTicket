package listcell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import model.UserHistoryModel;

import java.io.IOException;

public class HistoryListViewCell extends ListCell<UserHistoryModel> {
    @FXML
    private Label movie,cinema,hall,seats,time;

    @FXML
    private HBox hbox;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(UserHistoryModel historyModel, boolean empty) {
        super.updateItem(historyModel, empty);
        if(empty || historyModel == null) {

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

            movie.setText(historyModel.getMovie());
            cinema.setText(historyModel.getCinema());
            hall.setText(historyModel.getHall());
            seats.setText(historyModel.getSeat());
            time.setText(historyModel.getTime());

            setText(null);
            setGraphic(hbox);
        }
    }
}
