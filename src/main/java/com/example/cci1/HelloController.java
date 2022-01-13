package com.example.cci1;

import com.example.cci1.DbConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

;

public class HelloController {


    String data1, data2,data3, data4,data5, data6;
DbConnection obj = new DbConnection();
    Connection conn = obj.connMethod();
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
    @FXML
    private TextField txt4;
    @FXML
    private TextField txt5;
    @FXML
    private TextField txt6;
    @FXML
    private GridPane gp;
    private ObservableList<ObservableList> data;
    @FXML
    private TableView tbl;

    //CONNECTION DATABASE



    public void handleButtonAction(ActionEvent event) {

        data1 = txt1.getText();
        data2 = txt2.getText();
        data3 = txt3.getText();
        data4 = txt4.getText();
        data5 = txt5.getText();
        data6 = txt6.getText();
        String query = "Insert into db_tb2(SSID,Studid,Firstname,Lastname,Section,Department) VALUES('" + data1 + "','" + data2 + "','" + data3 + "','" + data4 + "','" + data5 + "','" + data6 + "')";
        try {

            Statement statement = conn.createStatement();
            statement.execute(query);
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");
//            Alert a = new Alert(Alert.AlertType.INFORMATION);
//            a.setContentText("successfuly inserted");
//            a.showAndWait();
            // create a popup

            ProgressIndicator PI = new ProgressIndicator();
            //PI.setProgress(0.1);
            AnchorPane root = new AnchorPane();
            PI.setMinSize(300,300);
            root.getChildren().add(PI);
            gp.add(root, 2, 4);
System.out.println("succesfully inserted");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("inserion failed");
        }
    }
    public void updateButtonHandle(ActionEvent event) {

        data1 = txt1.getText();
        data2 = txt2.getText();
        data3 = txt3.getText();
        data4 = txt4.getText();
        data5 = txt5.getText();
        data6 = txt6.getText();
        String query ="update db_tb2 set Firstname='Kefiyalew' where Firstname='Elias' ";
        try {

            Statement statement = conn.createStatement();
            statement.execute(query);
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");
//            Alert a = new Alert(Alert.AlertType.INFORMATION);
//            a.setContentText("successfuly inserted");
//            a.showAndWait();
            // create a popup

            ProgressIndicator PI = new ProgressIndicator();
            //PI.setProgress(0.1);
            AnchorPane root = new AnchorPane();
            PI.setMinSize(300,300);
            root.getChildren().add(PI);
            gp.add(root, 2, 4);
            System.out.println("succesfully update");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to update");
        }
    }
    public void buildData() {
        DbConnection obj1;
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            obj1 = new DbConnection();
            c = obj1.connMethod();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from db_tb2";

            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            //ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //for (int i = 1; i < rsmd.getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                //TableColumn col = new TableColumn(rsmd.getColumnLabel(i));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tbl.getColumns().addAll(col);


            }


            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }

                data.add(row);

            }


            tbl.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
    }
    public void selectrow() {
        DbConnection obj1;
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            obj1 = new DbConnection();
            c = obj1.connMethod();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "select Department from db_tb2 where Firstname='Elias' and Section='SecA'";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            //ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //for (int i = 1; i < rsmd.getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                //TableColumn col = new TableColumn(rsmd.getColumnLabel(i));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tbl.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }

                data.add(row);

            }


            tbl.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
    }
    public void buildDistinictData() {
        DbConnection obj1;
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            obj1 = new DbConnection();
            c = obj1.connMethod();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "select distinct Section from db_tb2";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            //ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //for (int i = 1; i < rsmd.getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                //TableColumn col = new TableColumn(rsmd.getColumnLabel(i));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tbl.getColumns().addAll(col);


            }


            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }


            tbl.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
    }
    public void selectDistinictButtonHandle(ActionEvent event) {
buildDistinictData();
    }

    public void selectButtonAction(ActionEvent event) {
        buildData();
    }

    public void selectrowButtonHandle(ActionEvent event) {
selectrow();
    }
    
}

