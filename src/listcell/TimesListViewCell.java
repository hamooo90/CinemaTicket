package listcell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.MovieModel;

import java.io.IOException;

public class TimesListViewCell extends ListCell<MovieModel> {

    @FXML
    private Label hallName;

    @FXML
    private Label time;

    @FXML
    private VBox vbox;
    @FXML
    private Pane tCell;
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(MovieModel movieModel, boolean empty) {
        super.updateItem(movieModel, empty);

        if(empty || movieModel == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("timeListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            hallName.setText(movieModel.getHall());
            time.setText(movieModel.getTime());

            setText(null);
            setGraphic(tCell);
        }

    }

}
