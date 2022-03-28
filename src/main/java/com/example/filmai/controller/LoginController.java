package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.UserDAO;
import com.example.filmai.model.UserSingleton;
import com.example.filmai.utils.BCryptPassword;
import com.example.filmai.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label loginStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        String username2 = username.getText();
        String password2 = password.getText();
        if (Validation.isValidUsername(username2) && Validation.isValidPassword(password2)) {
            String passwordDb = UserDAO.getBCryptPassword(username2);
            if (passwordDb.equals("")) {
                //Duomenys įvesti pagal validaciją, bet neranda atitikmens
                loginStatus.setText("Patikrinkite įvestus duomenis.");
            } else {
                boolean isValidPassword = BCryptPassword.checkPassword(password2, passwordDb);
                if (isValidPassword && UserDAO.userIsAdmin(username2, passwordDb)) {
                    UserSingleton userSingleton = UserSingleton.getInstance();
                    userSingleton.setUserName(username2);
                    goToAdminDashboard(event);
                } else if (isValidPassword && UserDAO.userIsNotAdmin(username2, passwordDb)) {
                    UserSingleton userSingleton = UserSingleton.getInstance();
                    userSingleton.setUserName(username2);
                    goToUserDashboard(event);
                } else {
                    //Neteisingai įvestas slaptažodis
                    loginStatus.setText("Patikrinkite įvestus duomenis.");
                }
            }
        } else {
            loginStatus.setText("Neteisingai įvesti duomenys.");
        }
    }


    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("register-view.fxml"));
        Stage registerStage = new Stage();
        registerStage.setTitle("Registracijos langas");
        registerStage.setScene(new Scene(root, 600, 400));
        registerStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void goToAdminDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("adminDashboard-view.fxml"));
        Stage registerStage = new Stage();
        registerStage.setTitle("Administracijos langas");
        registerStage.setScene(new Scene(root, 1100, 800));
        registerStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void goToUserDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("userDashboard-view.fxml"));
        Stage registerStage = new Stage();
        registerStage.setTitle("Vartotojo langas");
        registerStage.setScene(new Scene(root, 1100, 700));
        registerStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}