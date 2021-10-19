package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import listcell.CinemaListViewCell;
import model.CinemaModel;
import service.SqliteDb;
import service.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Cinemas implements Initializable {
    SqliteDb sqliteDb = new SqliteDb();
    Util util = new Util();

    ArrayList<CinemaModel> cinemaArray;
    @FXML
    private ListView<CinemaModel> cinemasListView;

    private ObservableList<CinemaModel> cinemasObserbaleList;

    public Cinemas(){
        ////////get name of all Cinemas from db
        cinemaArray = sqliteDb.getCinemaNames();
        cinemasObserbaleList = FXCollections.observableArrayList();
        for (CinemaModel c: cinemaArray) {
            cinemasObserbaleList.add(c);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //////////fill ListView with custom cells
        cinemasListView.setItems(cinemasObserbaleList);
        cinemasListView.setCellFactory(cinemaModelListView -> new CinemaListViewCell());
    }

    /////////handles when an item in ListView is clicked
    public void handleMouseClickOnList(MouseEvent event) throws IOException {
//        System.out.println("clicked on " + cinemasListView.getSelectionModel().getSelectedItem().getName());
        /////////show movies in selected cinema
        new HomeScene(3);
        /////////set variable to use later
        util.setCurrentCinema(cinemasListView.getSelectionModel().getSelectedItem().getName());
        changeScreen(event);
    }

    public void changeScreen(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        Scene scene = new Scene((Parent) root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    ////////used in subclass
    public void goBack(ActionEvent event) throws IOException {
        Parent signUpParent = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        Scene signUpScreen = new Scene((Parent) signUpParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpScreen);
        window.show();
    }
}
