package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.MyDb;
import util.CrudUtil;
import util.UILoader;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myPage {
    public TextField input01;
    public TextField input2;
    public TextField input3;

    public TableView<MyDb> table;
    public TableColumn sampleCol;
    public TableColumn sample2Col;
    public TableColumn sample3Col;
    public AnchorPane secondMainContext;

    public void initialize() {
        sampleCol.setCellValueFactory(new PropertyValueFactory<>("Sample1"));
        sample2Col.setCellValueFactory(new PropertyValueFactory<>("Sample3"));
        sample3Col.setCellValueFactory(new PropertyValueFactory<>("Sample2"));

        try {
            loadAllSampleData();
        } catch (SQLException | ClassNotFoundException ignored) {
        }

//        saveOnAction.setOnMouseClicked(event -> {
//            saveOnAction();
//        });

    }

    private void loadAllSampleData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM mydb");
        ObservableList<MyDb> obList = FXCollections.observableArrayList();

        while (result.next()) {
            obList.add(new MyDb(result.getString("Sample1"), result.getString("Sample2"), result.getString("Sample3")));
        }
        table.setItems(obList);
        table.refresh();
    }

    public void saveOnAction(ActionEvent actionEvent) {
        try {
            if (CrudUtil.execute("INSERT INTO mydb VALUES (?,?,?)", input01.getText(), input2.getText(), input3.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
                loadAllSampleData();
            }
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
            MyDb myDb = new MyDb(input01.getText(), input2.getText(), input3.getText());
            boolean isUpdated2 = CrudUtil.execute("UPDATE mydb SET Sample2=? ," + " Sample3=?  WHERE Sample1=?", myDb.getSample3(), myDb.getSample2(), myDb.getSample1());
            if (isUpdated2) {

                new Alert(Alert.AlertType.CONFIRMATION, "Update Confirmed!").show();
                loadAllSampleData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException ignored) {
        }
    }


    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            if (CrudUtil.execute("DELETE FROM mydb WHERE Sample1=?", input01.getText())) {
                loadAllSampleData();
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                loadAllSampleData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException ignored) {
        }
    }

    public void exitOnAction(ActionEvent actionEvent) throws IOException {
        UILoader.SetUi("mainPage", secondMainContext);
    }
}
