package ui;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ParkingLot;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    MainGUI controller;
    ParkingLot laCeiba;

    /**
     * Principal constructor for the class. <br>
     * */
    public Main() {
        controller = new MainGUI();
    }

    /**
     * @param args The arguments for the compiler and the JavaVM. <br>
     * */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void start(Stage primaryStage) throws IOException {
        controller.setCURRENT_PREF_MIN(352);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/preloader-t.fxml")));
        Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void stop() {
    }
}
