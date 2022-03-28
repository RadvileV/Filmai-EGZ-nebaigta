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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private Label usernameLabel, groupLabel;
    @FXML
    private TextField categoryIdField, categoryNameField;
    @FXML
    private Label categoryUpdateSuccessful, categoryUpdateFailed;
    @FXML
    private TableColumn categoryIdColumn, categoryNameColumn;
    @FXML
    private TableView categoriesTableView;
    @FXML
    private TextField movieIdField, movieTitleField, imdbRatingField;
    @FXML
    private ChoiceBox chooseCategory;
    @FXML
    private TextArea movieDescriptionArea;
    @FXML
    private TableColumn movieIdColumn, movieTitleColumn, movieRatingColumn,
            movieCategoryColumn, movieDescriptionColumn;
    @FXML
    private TableView movieTableView;
    @FXML
    private Label movieUpdateFailed, movieUpdateSuccessful;


    ObservableList<Category> list = FXCollections.observableArrayList();
    ObservableList<Movie> list2 = FXCollections.observableArrayList();

    @FXML
    public void onSearchCategoryButtonClick() {
        list.clear();
        String categoryNameField2 = categoryNameField.getText();

        List<Category> categoryList = CategoryDAO.searchByName(categoryNameField2);
        for (Category category : categoryList) {
            list.add(new Category(category.getCategoryId(), category.getCategoryName()));

            categoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
            categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
            categoriesTableView.setItems(list);
        }
        if (categoryList.isEmpty()) {
            categoryUpdateSuccessful.setText("");
            categoryUpdateFailed.setText("Tokios kategorijos nėra.");
        } else {
            categoryUpdateFailed.setText("");
            categoryUpdateSuccessful.setText("Paieška sėkminga.");
        }
    }

    @FXML
    public void onUpdateCategoryButtonClick() {
        String categoryIdField2 = categoryIdField.getText();
        String categoryNameField2 = categoryNameField.getText();
        if (!Validation.isValidId(categoryIdField2)) {
            categoryUpdateFailed.setText("Neteisingai įvestas ID");
        } else if (!Validation.isValidTitle(categoryNameField2)) {
            categoryUpdateFailed.setText("Neteisingai įvestas pavadinimas");
        } else {
            int idField3 = Integer.parseInt(categoryIdField.getText());
            Category category = new Category(idField3, categoryNameField2);
            CategoryDAO.update(category);
            categoryUpdateSuccessful.setText("Pavyko paredaguoti įrašą");
        }

    }

    @FXML
    public void onCreateCategoryButtonClick() {
        String categoryNameField2 = categoryNameField.getText();
        if (!Validation.isValidTitle(categoryNameField2)) {
            categoryUpdateFailed.setText("Neteisingai įvestas pavadinimas");
        } else {
            Category category = new Category(categoryNameField2);
            CategoryDAO.create(category);
            categoryUpdateSuccessful.setText("Sėkmingai sukurtas naujas įrašas.");
        }
    }

    @FXML
    public void onDeleteCategoryButtonClick() {
        String categoryIdField2 = categoryIdField.getText();
        if (!Validation.isValidId(categoryIdField2)) {
            categoryUpdateSuccessful.setText("");
            categoryUpdateFailed.setText("Neteisingai įvestas ID");
        } else {
            int idField3 = Integer.parseInt(categoryIdField.getText());
            CategoryDAO.deleteById(idField3);
            categoryUpdateFailed.setText("");
            categoryUpdateSuccessful.setText("Pavyko sėkmingai ištrinti kategoriją");
        }
    }

    @FXML
    public void onSearchMovieButtonClick() {
        list2.clear();
        String movieTitleField2 = movieTitleField.getText();

        List<Movie> movieList = MovieDAO.searchByName(movieTitleField2);

        for (Movie movie : movieList) {
            list2.add(new Movie(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getRating(), movie.getCategory()));
            movieIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            movieDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            movieRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
            movieCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

            movieTableView.setItems(list2);
        }
        if (movieList.isEmpty()) {
            movieUpdateSuccessful.setText("");
            movieUpdateFailed.setText("Nepavyko atlikti paieškos");
        } else {
            movieUpdateFailed.setText("");
            movieUpdateSuccessful.setText("Pavyko atlikti paiešką.");
        }
    }

    @FXML
    public void onUpdateMovieButtonClick() {
        String movieIdField2 = movieIdField.getText();
        String movieTitleField2 = movieTitleField.getText();
        String movieDescriptionArea2 = movieDescriptionArea.getText();
        String movieRatingField2 = imdbRatingField.getText();

        String category = "";
        if (!chooseCategory.getSelectionModel().isEmpty()) {
            category = chooseCategory.getSelectionModel().getSelectedItem().toString();
        }
        if (!Validation.isValidId(movieIdField2)) {
            movieUpdateSuccessful.setText("");
            movieUpdateFailed.setText("Neteisingai įvestas ID");
        } else if (!Validation.isValidTitle(movieTitleField2)) {
            movieUpdateSuccessful.setText("");
            movieUpdateFailed.setText("Neteisingai įvestas pavadinimas");
        } else if (!Validation.isValidRating(movieRatingField2)) {
            movieUpdateSuccessful.setText("");
            movieUpdateFailed.setText("Neteisingai įvestas reitingas");
        } else {
            int movieRatingField3 = (int) Double.parseDouble(imdbRatingField.getText());
            int movieIdField3 = Integer.parseInt(movieIdField.getText());

            Movie movie = new Movie(movieIdField3, movieTitleField2, movieDescriptionArea2, movieRatingField3, category);
            MovieDAO.update(movie);
            movieUpdateFailed.setText("");
            movieUpdateSuccessful.setText("Pavyko atnaujinti įrašą");
        }
    }

    @FXML
    public void onCreateMovieButtonClick() {
        String movieTitleField2 = movieTitleField.getText();
        String movieDescriptionArea2 = movieDescriptionArea.getText();
        String movieRatingField2 = imdbRatingField.getText();

        String category = "";
        if (!chooseCategory.getSelectionModel().isEmpty()) {
            category = chooseCategory.getSelectionModel().getSelectedItem().toString();
        }

        if (!Validation.isValidTitle(movieTitleField2)) {
            movieUpdateSuccessful.setText("");
            movieUpdateFailed.setText("Neteisingai įvestas pavadinimas");
        } else if (!Validation.isValidRating(movieRatingField2)) {
            movieUpdateSuccessful.setText("");
            movieUpdateFailed.setText("Neteisingai įvestas reitingas");
        } else {
            int movieRatingField3 = (int) Double.parseDouble(imdbRatingField.getText());

            Movie movie = new Movie(movieTitleField2, movieDescriptionArea2, movieRatingField3, category);
            MovieDAO.create(movie);
            movieUpdateFailed.setText("");
            movieUpdateSuccessful.setText("Pavyko sukurti filmą");
        }
    }

    @FXML
    public void onDeleteMovieButtonClick() {
        String movieIdField2 = movieIdField.getText();
        if (!Validation.isValidId(movieIdField2)) {
            movieUpdateFailed.setText("Neteisingai įvestas ID");
        } else {
            int movieIdField3 = Integer.parseInt(movieIdField.getText());
            MovieDAO.deleteById(movieIdField3);
            movieUpdateSuccessful.setText("Pavyko sėkmingai ištrinti filmą");
        }
    }

    @FXML
    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Prisijungimo langas");
        LoginStage.setScene(new Scene(root, 600, 400));
        LoginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseCategory.setItems(FXCollections.observableList(CategoryDAO.fullCategoryList()));

        String username = UserSingleton.getInstance().getUserName();
        usernameLabel.setText(username);

        String role = UserDAO.searchByUsername(username);
        groupLabel.setText(role);

    }
}



