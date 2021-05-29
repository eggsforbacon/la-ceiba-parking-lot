package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    boolean sceneIsActive;

    SecondaryGUI secController;

    boolean isMaximized;

    double CURRENT_PREF_MIN;

    /**
     * Principal constructor of the class. <br>
     * */
    public MainGUI() {
        secController = new SecondaryGUI();
        sceneIsActive = false;
    }
    /**
     * {@inheritDoc}
     * */
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
     * */
    private void launchPane(String fxmlDocument, String title, double width) {
        try {
            isMaximized = ((Stage) mainPane.getScene().getWindow()).isMaximized();
            if (!isMaximized) {
                mainPane.getScene().getWindow().setWidth(width);
                CURRENT_PREF_MIN = width;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/" + fxmlDocument));
            fxmlLoader.setController(secController);
            Parent root = fxmlLoader.load();
            ((Stage) mainPane.getScene().getWindow()).setTitle("La Ceiba: " + title);
            ((Stage) mainPane.getScene().getWindow()).setMinWidth(width);
            currentScene.setCenter(root);
            currentScene.setVisible(true);
            currentScene.setDisable(false);
            sceneIsActive = true;
        } catch (IOException ignored) {}
    }

    /**
     * Called when the image view on "main-view.fxml" is clicked on <br>
     * */
    @FXML
    void carClicked(MouseEvent event) {
        if (!isMaximized) {
            if (mainPane.getScene().getWindow().getWidth() != 352.0 && sceneIsActive) {
                ((Stage) mainPane.getScene().getWindow()).setMinWidth(325.0);
                mainPane.getScene().getWindow().setWidth(352.0);
                currentScene.setVisible(false);
                currentScene.setDisable(true);
            } else if (sceneIsActive) {
                mainPane.getScene().getWindow().setWidth(CURRENT_PREF_MIN);
                mainPane.getScene().getWindow().setWidth(CURRENT_PREF_MIN);
                currentScene.setVisible(true);
                currentScene.setDisable(false);
            }
        }
    }

    /**
     * Called when the login button is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void loginClicked(ActionEvent event) {
        launchPane("login.fxml","Iniciar Sesión", 704);
    }

    /**
     * Called when the option for clients is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void clientsClicked(ActionEvent event) {
        launchPane("clients-view.fxml","Clientes", 1500);
    }

    /**
     * Called when the option for vehicles is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void vehiclesClicked(ActionEvent event) {
        launchPane("vehicles-view.fxml","Clientes", 1500);
    }

    /**
     * Called when the option for map is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void mapClicked(ActionEvent event) {
        launchPane("map-view.fxml","Mapa", 1500);
    }

    /**
     * Called when the option for users is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void usersClicked(ActionEvent event) {
        launchPane("user-view.fxml","Clientes", 1500);
    }

    /**
     * Called when the option for receipts is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void receiptsClicked(ActionEvent event) {
        launchPane("receipt-gen.fxml", "Facturación", 772);
    }

    /**
     * Called when the option for reports is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void reportsClicked(ActionEvent event) {
        launchPane("reports.fxml", "Reportes y Extractos", 1132);
    }

    /**
     * Called when the exit button is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void exitClicked(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).close();
    }

    /*Setters*/

    /**
     * @param CURRENT_PREF_MIN The new value
     * */
    public void setCURRENT_PREF_MIN(double CURRENT_PREF_MIN) {
        this.CURRENT_PREF_MIN = CURRENT_PREF_MIN;
    }
}
