package ui;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreloaderGUI extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    /**
     * {@inheritDoc}
     * */
    @Override
    public void start(Stage primaryStage) {
        preloaderStage = primaryStage;
        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.TRANSPARENT);
        Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
        primaryStage.getIcons().add(icon);
        preloaderStage.show();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/splash-screen.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        if (type == StateChangeNotification.Type.BEFORE_START) preloaderStage.hide();
    }
}