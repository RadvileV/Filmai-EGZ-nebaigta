package com.example.filmai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    /*
    Repozitorijos sukūrimas: 1)Sukurti githube, nusikopijuoti linką 2)Gitbash desktope 3)git clone linkas 4)Nusikopijuoti .git failą i projekto folderį
    ,ištrinti folderį iš desktopo 5)Projekto folderyje gitbash 6)git add . 7)git commit -m "žinutė" 8)git push
    */

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Prisijungimo langas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}



