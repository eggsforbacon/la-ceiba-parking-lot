package ui;

import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
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

    /**
     * Called when the image view on "main-view.fxml" is clicked on <br>
     */
    @FXML
    void carClicked(MouseEvent event) {

    }

    /**
     * Called when the option for clients is clicked on "main-view.fxml" <br>
     */
    @FXML
    void clientsClicked(ActionEvent event) {

    }

    /**
     * Called when the exit button is clicked on "main-view.fxml" <br>
     */
    @FXML
    void exitClicked(ActionEvent event) {

    }

    /**
     * Called when the login button is clicked on "main-view.fxml" <br>
     */
    @FXML
    void loginClicked(ActionEvent event) {

    }

    /**
     * Called when the option for map is clicked on "main-view.fxml" <br>
     */
    @FXML
    void mapClicked(ActionEvent event) {

    }

    /**
     * Called when the option for receipts is clicked on "main-view.fxml" <br>
     */
    @FXML
    void receiptsClicked(ActionEvent event) {

    }

    /**
     * Called when the option for reports is clicked on "main-view.fxml" <br>
     */
    @FXML
    void reportsClicked(ActionEvent event) {

    }

    /**
     * Called when the option for users is clicked on "main-view.fxml" <br>
     */
    @FXML
    void usersClicked(ActionEvent event) {

    }

    /**
     * Called when the option for vehicles is clicked on "main-view.fxml" <br>
     */
    @FXML
    void vehiclesClicked(ActionEvent event) {

    }
}
