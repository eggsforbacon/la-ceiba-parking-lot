package ui;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    MainGUI controller;
    private static final String SAVE_PATH = "data/data.1zj";

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
        LauncherImpl.launchApplication(Main.class, PreloaderGUI.class, args);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void start(Stage primaryStage) throws IOException {
        controller.setCURRENT_PREF_MIN(352);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main-view.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().addAll(String.valueOf(getClass().getResource("css/main.css")));
        primaryStage.setTitle("La Ceiba: Inicio");
        primaryStage.setMinHeight(860);
        primaryStage.setMinWidth(352);
        primaryStage.show();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void init() {
        int COUNT_LIMIT = 40000;
        for (int i = 0; i <= COUNT_LIMIT; i++) {
            double progress = (double) i/COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void stop() {
    }
}
