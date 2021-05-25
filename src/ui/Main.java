package ui;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    MainGUI controller;
    private static final String SAVE_PATH = "data/data.1zj";

    public Main() {
        controller = new MainGUI();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, PreloaderGUI.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main-view.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().addAll(String.valueOf(getClass().getResource("css/main.css")));
        primaryStage.setTitle("La Ceiba: Inicio");
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    @Override
    public void init() {
        int COUNT_LIMIT = 30000;
        for (int i = 0; i <= COUNT_LIMIT; i++) {
            double progress = (double) i/COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }

    @Override
    public void stop() {
    }
}
