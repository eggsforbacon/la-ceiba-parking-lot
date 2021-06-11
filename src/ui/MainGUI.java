package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ParkingLot;
import threads.LoginThread;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGUI implements Initializable {

    /*-------------------------- JAVAFX FIELDS -------------------------*/

    /*Main Pane*/

    @FXML
    private BorderPane mainPane = new BorderPane();

    @FXML
    private BorderPane currentScene = new BorderPane();

    @FXML
    private Button mgLoginButton = new Button();

    @FXML
    private Button mgClientsButton = new Button();

    @FXML
    private Button mgVehiclesButton = new Button();

    @FXML
    private Button mgMapButton = new Button();

    @FXML
    private Button mgUsersButton = new Button();

    @FXML
    private Button mgReceiptButton = new Button();

    @FXML
    private Button mgReportsButton = new Button();

    @FXML
    private Button mgExitButton = new Button();

    /*------------------------ CLASS ATTRIBUTES ------------------------*/

    CurrentSceneGUI currentSceneController;
    boolean sceneIsActive;
    boolean isMaximized;
    double CURRENT_PREF_MIN;
    ParkingLot laCeiba;

    /*---------------------------- METHODS -----------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer, Constructors and General*/

    /**
     * Principal constructor of the class. <br>
     * @param laCeiba The object in which the apps info will be stored. <br>
     * */
    public MainGUI(ParkingLot laCeiba) {
        this.laCeiba = laCeiba;
        currentSceneController = new CurrentSceneGUI(laCeiba,this);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentScene.prefHeightProperty().bind(mainPane.heightProperty());
        currentScene.prefWidthProperty().bind(mainPane.widthProperty());
        if(laCeiba.checkFirstTime()){
            toggleButtons(true);
            laCeiba.setFirstTime(false);
            login();
        }
        else{
            toggleButtons(false);
        }
    }

    public void login(){
        new LoginThread(this).start();
    }

    /**
     Disable or enable the main menu buttons <br>
     <b> pre: </b><br>
     <b> post: </b>Disables or enables the buttons<br>
     @param state a boolean that indicate if they have to be enabled or disabled
     */
    public void toggleButtons(boolean state) {
        mgUsersButton.setDisable(state);
        mgReceiptButton.setDisable(state);
        mgMapButton.setDisable(state);
        mgReportsButton.setDisable(state);
        mgClientsButton.setDisable(state);
        mgVehiclesButton.setDisable(state);
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
                CURRENT_PREF_MIN = width + 5;
            }
            currentSceneController.setCurrentScene(title);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/" + fxmlDocument));
            fxmlLoader.setController(currentSceneController);
            Parent root = fxmlLoader.load();
            ((Stage) mainPane.getScene().getWindow()).setTitle("La Ceiba: " + title);
            ((Stage) mainPane.getScene().getWindow()).setMinWidth(width);
            currentScene.setCenter(root);
            currentScene.setVisible(true);
            currentScene.setDisable(false);
            sceneIsActive = true;
        } catch (IOException ignored) {}
    }

    /*Menu Options*/

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
        ((Stage) mainPane.getScene().getWindow()).setResizable(true);
        launchPane("login.fxml","Iniciar Sesion", 704);
    }

    /**
     * Called when the option for clients is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void clientsClicked(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).setResizable(true);
        launchPane("clients-view.fxml","Clientes", 1500);
    }

    /**
     * Called when the option for vehicles is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void vehiclesClicked(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).setResizable(true);
        launchPane("vehicles-view.fxml","Vehiculos", 1500);
        //currentSceneController.initVehiclesDB();
    }

    /**
     * Called when the option for map is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void mapClicked(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).setResizable(false);
        ((Stage) mainPane.getScene().getWindow()).setMaximized(true);
        launchPane("map-view.fxml","Mapa", 1500);
    }

    /**
     * Called when the option for users is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void usersClicked(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).setResizable(true);
        launchPane("user-view.fxml","Usuarios", 1500);
    }

    /**
     * Called when the option for receipts is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void receiptsClicked(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).setResizable(true);
        launchPane("receipt-gen.fxml", "Facturacion", 772);
    }

    /**
     * Called when the option for reports is clicked on "main-view.fxml" <br>
     * */
    @FXML
    void reportsClicked(ActionEvent event) {
        ((Stage) mainPane.getScene().getWindow()).setResizable(true);
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

    /**
     * @return The object of the secondary fxml controller class. <br>
     * */
    public CurrentSceneGUI getCurrentSceneController() {
        return currentSceneController;
    }
}
