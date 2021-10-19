package listcell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.CinemaModel;

import java.io.IOException;

public class CinemaListViewCell extends ListCell<CinemaModel> {
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
    protected void updateItem(CinemaModel cinemaModel, boolean empty) {
        super.updateItem(cinemaModel, empty);

        if(empty || cinemaModel == null) {

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

            label1.setText(cinemaModel.getName());
            label2.setText("");
//            label2.setText(cinemaModel.getAddress());

//            FileInputStream inputstream = null;
//            try {
//                inputstream = new FileInputStream("img/"+cinemaModel.getImg()+".jpg");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
            Image image = new Image("img/"+cinemaModel.getImg()+".jpg");
            img1.setImage(image);


            setText(null);
            setGraphic(gridPane);
        }

    }


}
