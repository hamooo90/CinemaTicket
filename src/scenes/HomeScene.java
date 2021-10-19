package scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.SqliteDb;
import service.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeScene implements Initializable {
    SqliteDb sqliteDb = new SqliteDb();
    Util util = new Util();
    @FXML
    private Label mainMessage;

    @FXML
    private BorderPane mainPane;


    public static int sceneNumber = 0;

    public HomeScene(){}

    //////////is used to change part of window from other classes
    public HomeScene(int scene){
        sceneNumber = scene;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainMessage.setText("Welcome " + sqliteDb.usersModel.getUsername());
        handleButton(sceneNumber);
    }

    public void changeScreen(ActionEvent event) throws IOException {
        Parent signUpParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene signUpScreen = new Scene((Parent) signUpParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpScreen);
        window.show();
    }

    /////handles which fxml should be load from other classes
    public void handleButton(int s){
        Util u = new Util();
        String str = "cinema";
        if(s == 0){
            str = "cinemas";
        } else if(s == 2){
            str = "time";
        }
        else if(s == 3){
            str = "movies";
        }else if (s == 4){
            str = "movieInfo";
        }
        Pane view = u.getPage(str+".fxml");
        mainPane.setCenter(view);
    }

    ////////handle left menu buttons
    public void handleButton1(){
        Pane view = util.getPage("cinemas.fxml");
        mainPane.setCenter(view);
    }
    public void handleButton2(){
        Pane view = util.getPage("search.fxml");
        mainPane.setCenter(view);
    }
    public void handleButton3(){
        Pane view = util.getPage("account.fxml");
        mainPane.setCenter(view);
    }

    ///////handle logout
    public void logout(ActionEvent event) throws IOException {
        sqliteDb.logout();
        util.setCurrentCinema("");
        util.setCurrentMovie(null);
        sceneNumber=0;

        ////////go back to login page
        changeScreen(event);
    }
}
