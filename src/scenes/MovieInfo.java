package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import listcell.TimesListViewCell;
import model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieInfo extends Cinemas implements Initializable {

    MovieModel movieModel;
    ArrayList<MovieModel> movieTime;
    private ObservableList<MovieModel> movieTimeModelObservableList;

    @FXML
    private Label movieName;
    @FXML
    private Label director;
    @FXML
    private Label genre;
    @FXML
    private Label actor1;
    @FXML
    private Label actor2;
    @FXML
    private Label actor3;
    @FXML
    private ImageView img;
    @FXML
    private Text about;
    @FXML
    private ListView<MovieModel> timeList;
//
    public MovieInfo(){//////
        /////////get selected movie info and time from db
        movieModel = sqliteDb.getMovieInfo();
        movieTime = sqliteDb.getMovieTime();
        movieTimeModelObservableList = FXCollections.observableArrayList();
        for(int i=0;i<movieTime.size();i++)
        {
            movieTimeModelObservableList.add(movieTime.get(i));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieName.setText(movieModel.getMovie());
        director.setText(movieModel.getDirector());
        about.setText(movieModel.getAbout());
        genre.setText(movieModel.getGenre());
        actor1.setText(movieModel.getActor1());
        actor2.setText(movieModel.getActor2());
        actor3.setText(movieModel.getActor3());
//        FileInputStream inputstream = null;
//        try {
//            inputstream = new FileInputStream("img/"+movieModel.getImg()+".jpg");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Image image = new Image("img/"+movieModel.getImg()+".jpg");
        img.setImage(image);
        /////////
        /////////fill time list with custom cell
        timeList.setItems(movieTimeModelObservableList);
        timeList.setCellFactory(movieTimeModelListView -> new TimesListViewCell());

    }

    public void handleMouseClickOnList(MouseEvent event) throws IOException {
        System.out.println("clicked on " + timeList.getSelectionModel().getSelectedItem().getId());
        new HomeScene(2);
        /////// set variable to use later
        util.setCurrentSelectedHall(timeList.getSelectionModel().getSelectedItem());
        changeScreen(event);
    }

    @Override
    public void goBack(ActionEvent event) throws IOException {
        new HomeScene(3);
        super.goBack(event);
    }

}
