package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieViewController {

    @FXML
    private TableView oneMovieTableView;

    @FXML
    public void onReturnButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("userDashboard-view.fxml"));
        Stage registerStage = new Stage();
        registerStage.setTitle("Vartotojo langas");
        registerStage.setScene(new Scene(root, 1100, 700));
        registerStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
