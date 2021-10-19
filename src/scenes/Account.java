package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import listcell.HistoryListViewCell;
import model.UserHistoryModel;
import service.SqliteDb;
import service.Util;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Account implements Initializable {
    SqliteDb sqliteDb = new SqliteDb();
    Util util = new Util();
    ArrayList<UserHistoryModel> historyModels;

    @FXML
    private ListView<UserHistoryModel> historyList;

    private ObservableList<UserHistoryModel> userHistoryModelObservableList;

    public Account(){
        ///////get history of bought ticket from db
        historyModels = sqliteDb.getUserHistory();
        userHistoryModelObservableList = FXCollections.observableArrayList();
        for (UserHistoryModel m:historyModels) {
            userHistoryModelObservableList.add(m);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        historyList.setItems(userHistoryModelObservableList);
        historyList.setCellFactory(userHistoryModelListView -> new HistoryListViewCell());
    }
}
