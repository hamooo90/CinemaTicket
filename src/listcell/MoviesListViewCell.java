package listcell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.MovieModel;

import java.io.IOException;

public class MoviesListViewCell extends ListCell<MovieModel> {

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView img1;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(MovieModel movieModel, boolean empty) {
        super.updateItem(movieModel, empty);
        if(empty || movieModel == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("listCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            label1.setText(movieModel.getMovie());
            label2.setText(movieModel.getDirector());

//            FileInputStream inputstream = null;
//            try {
//                inputstream = new FileInputStream("img/"+movieModel.getImg()+".jpg");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
            Image image = new Image("img/"+movieModel.getImg()+".jpg");
            img1.setImage(image);

            setText(null);
            setGraphic(gridPane);
        }

    }

}
