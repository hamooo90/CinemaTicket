package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import listcell.SearchListViewCell;
import model.MovieModel;
import service.SqliteDb;
import service.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Search implements Initializable {
    SqliteDb sqliteDb = new SqliteDb();
    Util util = new Util();

    ArrayList<MovieModel> movieModels;

    @FXML
    private ListView<MovieModel> searchList;

    private ObservableList<MovieModel> modelObservableList;

    @FXML
    private TextField searchText;


    public Search(){
    }

    public void Search(){
        movieModels = sqliteDb.searchMovie(searchText.getText());
        modelObservableList = FXCollections.observableArrayList();
        for(MovieModel m:movieModels){
            modelObservableList.add(m);
        }
        searchList.setItems(modelObservableList);
        searchList.setCellFactory(movieModelListView -> new SearchListViewCell());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        searchList.setItems(modelObservableList);
//        searchList.setCellFactory(movieModelListView -> new SearchListViewCell());
    }

    /////////handles when an item in ListView is clicked
    public void handleMouseClickOnList(MouseEvent event) throws IOException {
        System.out.println("clicked on " + searchList.getSelectionModel().getSelectedItem().getMovie());

        new HomeScene(4);
        util.setCurrentMovie(searchList.getSelectionModel().getSelectedItem().getMovie());
        util.setCurrentCinema(searchList.getSelectionModel().getSelectedItem().getCinema());
        changeScreen(event);
    }

    public void changeScreen(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        Scene scene = new Scene((Parent) root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
