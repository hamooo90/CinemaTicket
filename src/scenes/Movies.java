package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import listcell.MoviesListViewCell;
import model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Movies extends Cinemas implements Initializable {
    ArrayList<MovieModel> moviesArray;
    @FXML
    private ListView<MovieModel> moviesListView;

    private ObservableList<MovieModel> moviesObservableList;

    public Movies()  {
        ////////get name of all movies in selected cinema
        moviesArray = sqliteDb.getMovies();
        moviesObservableList = FXCollections.observableArrayList();

        ///////////add movie to observable
        ///////////movie list will get data from this observable
        for (MovieModel c: moviesArray) {
            boolean chk=true;
            for(int i = 0; i< moviesObservableList.size(); i++){
                /////////check for movie name duplication
                if(c.getMovie().equals(moviesObservableList.get(i).getMovie())){
                    chk = false;
                }
            }
            ////////if no duplication add movie to observable
            if(chk){
                moviesObservableList.add(c);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //////////fill ListView with custom cells
        moviesListView.setItems(moviesObservableList);
        moviesListView.setCellFactory(movieModelListView -> new MoviesListViewCell());
    }

    /////////handles when an item in ListView is clicked
    public void handleMouseClickOnList(MouseEvent event) throws IOException {
//        System.out.println("clicked on " + moviesListView.getSelectionModel().getSelectedItem().getMovie());
        new HomeScene(4);
        util.setCurrentMovie(moviesListView.getSelectionModel().getSelectedItem().getMovie());
        changeScreen(event);
    }

    @Override
    public void goBack(ActionEvent event) throws IOException {
        new HomeScene(0);
        super.goBack(event);
    }
}
