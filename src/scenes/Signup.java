package scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import service.FormValidator;
import service.SqliteDb;

import java.io.IOException;
import java.sql.SQLException;

public class Signup {
    SqliteDb sqliteDb = new SqliteDb();

    @FXML
    private Label errorLabel;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    public void changeScreen(ActionEvent event) throws IOException {
        Parent logInParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene logInScreen = new Scene(logInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(logInScreen);
        window.show();
    }

    public void signup(ActionEvent event) throws IOException {
        //////////check if user and pass is valid
        FormValidator formValidator = new FormValidator();
        String validateResult = formValidator.validateSignup(txtUsername.getText(), txtPassword.getText());
        if (validateResult.equals("")){
            try {
                ///////////if valid try to login
                if (sqliteDb.signup(txtUsername.getText(), txtPassword.getText())) {
                    //////////show home window
                    Parent signUpParent = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
                    Scene signUpScreen = new Scene((Parent) signUpParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(signUpScreen);
                    window.setResizable(false);
                    window.show();

                    /////////////Center Window in screen
                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
                    window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
//                    System.out.println("signup successful");
                } else {
//                    System.out.println("signup Not successful");
                    //////////show error message
                    errorLabel.setText("Something went wrong!");
                }
            } catch (SQLException throwables) {
//                System.out.println("signup Not successful");
                //////////show error message
                errorLabel.setText("Something went wrong!");
                throwables.printStackTrace();
            }
        } else{
            //////////show error message
            errorLabel.setText(validateResult);
        }
    }

}
