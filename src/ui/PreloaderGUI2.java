package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.PreloaderBar;
import java.net.URL;
import java.util.ResourceBundle;

public class PreloaderGUI2 implements Initializable {

    private final int COUNT_LIMIT = 40000;
    private final PreloaderBar bar;
    private Stage preloaderStage;
    private Scene scene;

    @FXML
    private Rectangle pBarRCT;

    /**
     * The main constructor of the class. <br>
     * */
    public PreloaderGUI2() {
        bar = new PreloaderBar();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bar.setActive(true);

    }

    /**
     * Stops the bar loading and launches the app. <br>
     * */
    public void postLoaded() {
        bar.setActive(false);
    }

    /**
     * Updates the rectangle bar's width, i.e. loads it. <br>
     * */
    public void loadBar() {
        pBarRCT.setWidth(bar.getBarWidth());
    }
}
