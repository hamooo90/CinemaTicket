package scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.SeatModel;
import service.SqliteDb;
import service.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Time implements Initializable {
    Util util = new Util();
    SqliteDb sqliteDb = new SqliteDb();
    @FXML
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,
            b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,
            b21,b22,b23,b24,b25,b26,b27,b28,b29,b30,
            b31,b32,b33,b34,b35,b36,b37,b38,b39,b40;
    @FXML
    private ImageView img;
    @FXML
    private Label name,time;
    @FXML
    private Button back;

    private boolean[] seatToReserve = new boolean[41];

    ////////handle click to multiple buttons
    final EventHandler<ActionEvent> handler = event -> {
        Button btn =(Button) event.getSource();
        int index = Integer.parseInt(btn.getText());
        if(!seatToReserve[index]){
            seatToReserve[index]=true;
            btn.setStyle("-fx-background-color:#608eab;");
        }else {
            seatToReserve[index]=false;
            btn.setStyle("");

        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SeatModel ssss;
        ssss = sqliteDb.getSeats();
        handleSeat();
        b1.setOnAction(handler);b2.setOnAction(handler);b3.setOnAction(handler);b4.setOnAction(handler);
        b5.setOnAction(handler);b6.setOnAction(handler);b7.setOnAction(handler);b8.setOnAction(handler);
        b9.setOnAction(handler);b10.setOnAction(handler);b11.setOnAction(handler);b12.setOnAction(handler);
        b13.setOnAction(handler);b14.setOnAction(handler);b15.setOnAction(handler);b16.setOnAction(handler);
        b17.setOnAction(handler);b18.setOnAction(handler);b19.setOnAction(handler);b20.setOnAction(handler);
        b21.setOnAction(handler);b22.setOnAction(handler);b23.setOnAction(handler);b24.setOnAction(handler);
        b25.setOnAction(handler);b26.setOnAction(handler);b27.setOnAction(handler);b28.setOnAction(handler);
        b29.setOnAction(handler);b30.setOnAction(handler);b31.setOnAction(handler);b32.setOnAction(handler);
        b33.setOnAction(handler);b34.setOnAction(handler);b35.setOnAction(handler);b36.setOnAction(handler);
        b37.setOnAction(handler);b38.setOnAction(handler);b39.setOnAction(handler);b40.setOnAction(handler);

        name.setText(util.getCurrentSelectedHall().getMovie());
        time.setText(util.getCurrentSelectedHall().getTime());
//        FileInputStream inputstream = null;
//        try {
//            inputstream = new FileInputStream("img/"+util.getCurrentSelectedHall().getImg()+".jpg");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Image image = new Image("img/"+util.getCurrentSelectedHall().getImg()+".jpg");
        img.setImage(image);
    }


    public void buy(ActionEvent event){
        String strSeat = "";
        for(int i = 1;i<41;i++) {
            if (seatToReserve[i]) {
                strSeat += i +",";
            }
        }
        ////////ask for confirmation before buy
        if(!strSeat.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to buy this ticket? \nadmin    \nmovie: "+
                    util.getCurrentMovie()+" \nCinema: "+util.getCurrentSelectedHall().getCinema()+"\nHall: "+
                    util.getCurrentSelectedHall().getHall()+"\nTime: "+util.getCurrentSelectedHall().getTime()+"\nseats: " + strSeat, ButtonType.YES, ButtonType.NO);

            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                updateTable(event);
            }
        }
    }

    public void updateTable(ActionEvent event){
        String tableName = "t" + util.getCurrentSelectedHall().getId();
        String strSeat = "";

        for(int i = 1;i<41;i++) {
            if(seatToReserve[i]) {
                try {
                    /////////update seat table for single movie in db
                    sqliteDb.updateReservedTime(tableName,i,1 );
                    strSeat += i;
                    strSeat += ", ";
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        try {
            /////insert bought ticket into history
            sqliteDb.insertToUserHistory(strSeat);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        strSeat="";
        handleSeat();
    }

    public void goBack(ActionEvent event) throws IOException {
//        System.out.println("kkk");
        new HomeScene(4);
        Parent signUpParent = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        Scene signUpScreen = new Scene((Parent) signUpParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpScreen);
        window.show();
    }

    //////disable seat if it is already bought
    private void handleSeat(){
        SeatModel ssss = sqliteDb.getSeats();
        boolean[] vv = ssss.isReserved();
        b1.setDisable(vv[0]);
        b2.setDisable(vv[1]);
        b3.setDisable(vv[2]);
        b4.setDisable(vv[3]);
        b5.setDisable(vv[4]);
        b6.setDisable(vv[5]);
        b7.setDisable(vv[6]);
        b8.setDisable(vv[7]);
        b9.setDisable(vv[8]);
        b10.setDisable(vv[9]);
        b11.setDisable(vv[10]);
        b12.setDisable(vv[11]);
        b13.setDisable(vv[12]);
        b14.setDisable(vv[13]);
        b15.setDisable(vv[14]);
        b16.setDisable(vv[15]);
        b17.setDisable(vv[16]);
        b18.setDisable(vv[17]);
        b19.setDisable(vv[18]);
        b20.setDisable(vv[19]);
        b21.setDisable(vv[20]);
        b22.setDisable(vv[21]);
        b23.setDisable(vv[22]);
        b24.setDisable(vv[23]);
        b25.setDisable(vv[24]);
        b26.setDisable(vv[25]);
        b27.setDisable(vv[26]);
        b28.setDisable(vv[27]);
        b29.setDisable(vv[28]);
        b30.setDisable(vv[29]);
        b31.setDisable(vv[30]);
        b32.setDisable(vv[31]);
        b33.setDisable(vv[32]);
        b34.setDisable(vv[33]);
        b35.setDisable(vv[34]);
        b36.setDisable(vv[35]);
        b37.setDisable(vv[36]);
        b38.setDisable(vv[37]);
        b39.setDisable(vv[38]);
        b40.setDisable(vv[39]);
    }
}
