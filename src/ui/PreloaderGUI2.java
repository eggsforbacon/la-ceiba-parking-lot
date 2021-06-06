package ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.PreloaderBar;
import threads.PreloaderThread;
import java.net.URL;
import java.util.ResourceBundle;

public class PreloaderGUI2 implements Initializable {

    private final PreloaderBar bar;
    boolean isLoaded;

    @FXML
    private Rectangle pBarRCT;

    /**
     * The main constructor of the class. <br>
     * */
    public PreloaderGUI2() {
        bar = new PreloaderBar();
        isLoaded = false;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bar.setActive(true);
        new PreloaderThread(bar, this).start();
    }

    /**
     * Updates the rectangle bar's width, i.e. loads it. <br>
     * */
    public void loadBar() {
        double newWidth = bar.getBarWidth();
        pBarRCT.setWidth(newWidth);
        double percentage = (newWidth / bar.LOADED_WIDTH) * 100;
        if (percentage >= 50.0 && !isLoaded) {
            Platform.runLater(new Thread(this::sleepSleep));
            load(); // <- Might have a loading exception here
            isLoaded = true;
        }
    }

    /**
     * Loads everything to be loaded into the model object. <br>
     * */
    private void load() {
        //TODO: Load every model method in here
    }

    /**
     * Stops the bar loading and launches the app. <br>
     * */
    public void postLoaded() {
        bar.setActive(false);
        ((Stage) pBarRCT.getScene().getWindow()).close();
    }

    /**
     * Sleeps the thread for a specified amount of milliseconds. <br>  */
    public void sleepSleep() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
