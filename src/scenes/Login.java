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
import service.Util;

import java.io.IOException;
import java.sql.SQLException;

public class Login {

    public SqliteDb sqliteDb = new SqliteDb();
    public Util util = new Util();

    @FXML
    private Label errorLabel;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    public void changeScreen() throws IOException {
        Stage primaryStage = (Stage) txtPassword.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(newRoot);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void login(ActionEvent event) throws IOException {
        //////////check if user and pass is valid
        FormValidator formValidator = new FormValidator();
        String validateResult = formValidator.validatelogin(txtUsername.getText(), txtPassword.getText());
        if(validateResult.equals("")) {
            try {
                ///////////if valid try to login
                if (sqliteDb.login(txtUsername.getText(), txtPassword.getText())) {
//                    System.out.println("correct");

                    //////////show home window if login successfully
                    Parent signUpParent = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
                    Scene signUpScreen = new Scene((Parent) signUpParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(signUpScreen);
//                    window.setHeight(600);
//                    window.setWidth(1100);
//                    window.setMaximized(true);
                    window.setResizable(false);
                    window.show();

                    /////////////Center Window in screen
                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
                    window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
                } else {
//                    System.out.println("not Correct");
                    //////////show error message
                    errorLabel.setText("User not found.");
                }
            } catch (SQLException throwables) {
//                System.out.println("error!");
                //////////show error message
                errorLabel.setText("Something went wrong!");
                throwables.printStackTrace();
            }
        } else {
            //////////show error message
            errorLabel.setText(validateResult);
        }
    }

}
