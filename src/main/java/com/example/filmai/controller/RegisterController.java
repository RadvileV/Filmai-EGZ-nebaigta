package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.User;
import com.example.filmai.model.UserDAO;
import com.example.filmai.utils.BCryptPassword;
import com.example.filmai.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private Label registrationStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private CheckBox isAdminCheckBox;


    @FXML
    public void onRegisterButtonClick (ActionEvent event) throws IOException {
        String username2 = username.getText();
        String password2 = password.getText();
        String email2 = email.getText();
        if (Validation.isValidUsername(username2) && Validation.isValidPassword(password2) && Validation.isValidEmail(email2)) {
            registrationStatus.setText("Duomenys įvesti teisingai");
            String passwordBCrypt = BCryptPassword.hashPassword(password2);
            boolean isAdmin = isAdminCheckBox.isSelected();
            User user = new User(username2, passwordBCrypt, email2, isAdmin);
            UserDAO.create(user);
            Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if (!Validation.isValidEmail(email2)) registrationStatus.setText("Blogai įvestas el. pašto adresas");
        if (!Validation.isValidPassword(password2)) registrationStatus.setText("Blogai įvestas slaptažodis");
        if (!Validation.isValidUsername(username2)) registrationStatus.setText("Blogai įvestas vartotojo vardas");
    }
}
