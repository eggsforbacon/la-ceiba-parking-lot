package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
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

    /*Main Pane*/

    @FXML
    private BorderPane mainPane = new BorderPane();

    @FXML
    private BorderPane currentScene = new BorderPane();

    boolean sceneActive;

    SecondaryGUI secController;

    public MainGUI() {
        secController = new SecondaryGUI();
        sceneActive = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = progress;
        progressBar = preloaderPBar;
        currentScene.prefHeightProperty().bind(mainPane.heightProperty());
        currentScene.prefWidthProperty().bind(mainPane.widthProperty());
    }

    /**
     * Launches placeholder pane <br>
     * @param fxmlDocument The fxml document that will be loaded. <br>
     */
    private void launchPane(String fxmlDocument, String title, String stylesheet) {
        try {
            mainPane.getScene().getWindow().setWidth(1500.0);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/" + fxmlDocument));
            fxmlLoader.setController(secController);
            Parent root = fxmlLoader.load();
            root.getStylesheets().addAll(String.valueOf(getClass().getResource("css/" + stylesheet)));
            ((Stage) mainPane.getScene().getWindow()).setTitle("La Ceiba: " + title);
            currentScene.setCenter(root);
            sceneActive = true;
        } catch (IOException ignored) {}
    }

    /**
     * Called when the image view on "main-view.fxml" is clicked on <br>
     */
    @FXML
    void carClicked(MouseEvent event) {
        if (mainPane.getScene().getWindow().getWidth() == 1500.0 && sceneActive) {
            mainPane.getScene().getWindow().setWidth(352.0);
        }
        else if (sceneActive) mainPane.getScene().getWindow().setWidth(1500.0);
    }

    /**
     * Called when the option for clients is clicked on "main-view.fxml" <br>
     */
    @FXML
    void clientsClicked(ActionEvent event) {
        launchPane("clients-view.fxml","Clientes","databases.css");
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
        launchPane("login.fxml","Iniciar Sesión","main.css");
    }

    /**
     * Called when the option for map is clicked on "main-view.fxml" <br>
     */
    @FXML
    void mapClicked(ActionEvent event) {
        launchPane("map-view.fxml","Mapa", "main.css");
    }

    /**
     * Called when the option for receipts is clicked on "main-view.fxml" <br>
     */
    @FXML
    void receiptsClicked(ActionEvent event) {
        launchPane("receipt-gen.fxml", "Facturación", "receipts.css");
    }

    /**
     * Called when the option for reports is clicked on "main-view.fxml" <br>
     */
    @FXML
    void reportsClicked(ActionEvent event) {
        launchPane("reports.fxml", "Reportes y Extractos", "main.css");
    }

    /**
     * Called when the option for users is clicked on "main-view.fxml" <br>
     */
    @FXML
    void usersClicked(ActionEvent event) {
        launchPane("user-view.fxml","Clientes","databases.css");
    }

    /**
     * Called when the option for vehicles is clicked on "main-view.fxml" <br>
     */
    @FXML
    void vehiclesClicked(ActionEvent event) {
        launchPane("vehicles-view.fxml","Clientes","databases.css");
    }
}
