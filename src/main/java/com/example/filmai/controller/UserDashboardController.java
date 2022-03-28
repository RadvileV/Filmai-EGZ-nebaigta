package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.*;
import com.example.filmai.utils.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserDashboardController implements Initializable {
    @FXML
    private Label usernameLabel, groupLabel, movieSearchFailed, movieSearchSuccessful, findCategoryFailed, findCategorySuccessful;
    @FXML
    private TableColumn movieIdColumn, movieTitleColumn, movieCategoryColumn, movieDescriptionColumn;
    @FXML
    private TableView moviesTableView;
    @FXML
    private TextField movieTitleField, movieIdField, categoryNameField;

    ObservableList<Movie> list = FXCollections.observableArrayList();

    @FXML
    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Prisijungimo langas");
        LoginStage.setScene(new Scene(root, 600, 400));
        LoginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void onSearchMovieButtonClick() {
        list.clear();
        String movieTitleField2 = movieTitleField.getText();

        List<Movie> movieList = MovieDAO.searchByName(movieTitleField2);

        for (Movie movie : movieList) {
            list.add(new Movie(movie.getId(), movie.getTitle(), movie.getCategory(), movie.getDescription()));
            movieIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            movieCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
            movieDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            moviesTableView.setItems(list);
        }
        if (movieList.isEmpty()) {
            movieSearchSuccessful.setText("");
            movieSearchFailed.setText("Nepavyko atlikti paieškos");
        } else {
            movieSearchFailed.setText("");
            movieSearchSuccessful.setText("Pavyko atlikti paiešką.");
        }
    }

    public void onSearchByCategoryButtonClick() {
        list.clear();
        String categoryNameField2 = categoryNameField.getText();

        List<Movie> movieList = MovieDAO.searchByCategory(categoryNameField2);
        for (Movie movie : movieList) {
            list.add(new Movie(movie.getId(), movie.getTitle(), movie.getCategory(), movie.getDescription()));
            movieIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            movieCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
            movieDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            moviesTableView.setItems(list);
        }
        if (movieList.isEmpty()) {
            findCategorySuccessful.setText("");
            findCategoryFailed.setText("Nepavyko atlikti paieškos");
        } else {
            findCategoryFailed.setText("");
            findCategorySuccessful.setText("Pavyko atlikti paiešką.");
        }
    }


    @FXML
    public void onMovieViewInfoButtonClick(ActionEvent event) throws IOException {
        list.clear();
        String movieIdField2 = movieIdField.getText();
        if (!Validation.isValidId(movieIdField2)) {
            findCategorySuccessful.setText("");
            findCategoryFailed.setText("Neteisingai įvestas ID");
        } else {
            int movieIdField3 = Integer.parseInt(movieIdField.getText());
            Movie movie = MovieDAO.searchById(movieIdField3);
            goToMovieView(event);
        }
    }

    @FXML
    public void goToMovieView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("movie-view.fxml"));
        Stage registerStage = new Stage();
        registerStage.setTitle("Filmo informacijos langas");
        registerStage.setScene(new Scene(root, 600, 400));
        registerStage.show();
        ((Node) (event.getSource())).getScene().getWindow();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username = UserSingleton.getInstance().getUserName();
        usernameLabel.setText(username);

        String role = UserDAO.searchByUsername(username);
        groupLabel.setText(role);
    }
}

