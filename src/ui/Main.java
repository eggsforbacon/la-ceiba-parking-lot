package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/splash-screen.fxml")));
        Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void stop() {

        Platform.exit();
    }
}
