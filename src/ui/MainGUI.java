package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGUI implements Initializable {

    /*Splash screen*/

    @FXML
    private BorderPane splashPane;

    @FXML
    private Label progress;

    public static Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = progress;
    }
}
