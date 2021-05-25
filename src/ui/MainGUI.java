package ui;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGUI implements Initializable {

    /*Splash screen*/

    @FXML
    private BorderPane splashPane;

    @FXML
    private Label progress;

    @FXML
    private ProgressBar preloaderPBar;

    public static Label label =  new Label();

    public static ProgressBar progressBar = new ProgressBar();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = progress;
        progressBar = preloaderPBar;
    }
}
