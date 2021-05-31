package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CurrentSceneGUI implements Initializable {
    /*------------------------- JAVAFX FIELDS --------------------------*/

    /*Login*/

    @FXML
    private TextField loginUserTF = new TextField();

    @FXML
    private PasswordField loginPassPWF =  new PasswordField();

    /*Clients DB*/

    @FXML
    private TableView<Client> clientsTBV = new TableView<>();

    @FXML
    private TableColumn<Client, String> clientsNameCOL = new TableColumn<>();

    @FXML
    private TableColumn<Client, String> clientsIDCOL = new TableColumn<>();

    @FXML
    private TableColumn<Client, String> clientsPhoneCOL = new TableColumn<>();

    @FXML
    private TableColumn<Client, String> clientEnabledCOL = new TableColumn<>();

    @FXML
    private Button clientEditBTN = new Button();

    @FXML
    private Button clientDeleteBTN = new Button();

    /*Vehicles DB*/

    @FXML
    private TableView<Vehicle> vehiclesTBV = new TableView<>();

    @FXML
    private TableColumn<Vehicle, String> vehicleTypeCOL = new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> vehicleStayCOL = new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> vehiclePlateCOL = new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> vehicleSlotCOL = new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> vehicleEnabledCOL = new TableColumn<>();

    @FXML
    private Button vehicleEditBTN = new Button();

    @FXML
    private Button vehicleDeleteBTN = new Button();

    /*Map view (Throwing this one under the rug for a little while)*/

    /*Users DB*/

    @FXML
    private TableView<Employee> usersTBV = new TableView<>();

    @FXML
    private TableColumn<Employee, String> userNameCOL = new TableColumn<>();

    @FXML
    private TableColumn<Employee, String> userUsernameCOL = new TableColumn<>();

    @FXML
    private TableColumn<Employee, String> userIDCOL = new TableColumn<>();

    @FXML
    private Button userDeleteBTN = new Button();

    @FXML
    private Button userEditBTN = new Button();

    /*Receipt Generation*/

    @FXML
    private CheckBox receiptToggle = new CheckBox();

    @FXML
    private VBox monthlyVBox = new VBox();

    @FXML
    private Label receiptCodeMonthlyLBL = new Label();

    @FXML
    private TextField receiptCityMonthlyTF = new TextField();

    @FXML
    private Label receiptPaidValueMonthlyLBL = new Label();

    @FXML
    private DatePicker receiptDateMonthlyDTP = new DatePicker();

    @FXML
    private TextField receiptClientNameMonthlyTF = new TextField();

    @FXML
    private Label receiptVehicleMonthlyLBL = new Label();

    @FXML
    private Label receiptDaysMonthlyLBL = new Label();

    @FXML
    private VBox dailyVBox = new VBox();

    @FXML
    private ChoiceBox<String> receiptVehicleTypeCHB = new ChoiceBox<>();

    @FXML
    private DatePicker receiptDateDailyDTP = new DatePicker();

    @FXML
    private Label receiptPaidValueDailyLBL = new Label();

    @FXML
    private TextField receiptPlateDailyTF = new TextField();

    @FXML
    private TextField receiptHoursDailyTF = new TextField();

    /*Report Generation*/

    @FXML
    private BorderPane reportPane = new BorderPane();

    @FXML
    private DatePicker reportFromDTP = new DatePicker();

    @FXML
    private DatePicker reportToDTP = new DatePicker();

    @FXML
    private TextField reportFromHourTF = new TextField();

    @FXML
    private TextField reportToHourTF = new TextField();

    @FXML
    private ChoiceBox<String> reportTypeCHB = new ChoiceBox<>();

    /*------------------------ CLASS ATTRIBUTES ------------------------*/

    EmergentWindowsGUI emergentWindowsController;

    /*---------------------------- METHODS -----------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer, Constructors and General*/

    /**
     * Principal constructor of the class. <br>
     * */
    public CurrentSceneGUI() {
        emergentWindowsController = new EmergentWindowsGUI();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dummy = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        receiptVehicleTypeCHB.setItems(dummy);
        reportTypeCHB.setItems(dummy);
    }

    /**
     * Launches an fxml file. <br>
     * @param fxml The name of the fxml file. @NotNull.<br>
     * @param title The title of the window to be launched. @NotEmpty.<br>
     * */
    private void launchFXML(String fxml, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/" + fxml));
            fxmlLoader.setController(emergentWindowsController);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ignored) {}
    }

    /*Login*/

    /**
     * Takes the user and password and validates their login credentials. <br>
     * */
    @FXML
    void loginClicked(ActionEvent event) {
        emergentWindowsController.setDialMessageLBL("Mensaje de la ventana de dialogo.");
        launchFXML("dialogue.fxml", "Ventana de dialogo");
    }

    /**
     * Prompts the user with a registration window. <br>
     * */
    @FXML
    void registerClicked(ActionEvent event) {
        launchFXML("create-user.fxml","Nuevo Usuario");
    }

    /*Clients DB*/

    /**
     * Prompts the user to add a client with its vehicle to the program. <br>
     * */
    @FXML
    void addClient(ActionEvent event) {
        launchFXML("create-client-vehicle.fxml", "Nuevo Cliente");
    }

    /**
     * Deletes the selected client. <br>
     * */
    @FXML
    void deleteClient(ActionEvent event) {
        emergentWindowsController.setDialMessageLBL("Mensaje de la ventana de dialogo.");
        launchFXML("dialogue.fxml", "Ventana de dialogo");
    }

    /**
     * Prompts the user to edit the selected client. <br>
     * */
    @FXML
    void editClient(ActionEvent event) {
        launchFXML("edit-client.fxml", "Editar Cliente");
    }

    /*Vehicles DB*/

    /**
     * Deletes the selected vehicle. <br>
     * */
    @FXML
    void deleteVehicle(ActionEvent event) {
        emergentWindowsController.setDialMessageLBL("Mensaje de la ventana de dialogo.");
        launchFXML("dialogue.fxml", "Ventana de dialogo");
    }

    /**
     * Prompts the user to edit the selected vehicle. <br>
     * */
    @FXML
    void editVehicle(ActionEvent event) {
        launchFXML("edit-vehicle.fxml", "Editar Vehículo");
    }

    /*Map view (Throwing this one under the rug for a little while)*/

    /*Users DB*/

    /**
     * Prompts the user to add another user to the program. <br>
     * The current user MUST have administrative privileges for this action to be effective. <br>
     * */
    @FXML
    void addUser(ActionEvent event) {
        launchFXML("create-user.fxml", "Nuevo Usuario");
    }

    /**
     * Deletes the selected user. <br>
     * The current user MUST have administrative privileges for this action to be effective. <br>
     * */
    @FXML
    void deleteUser(ActionEvent event) {
        emergentWindowsController.setDialMessageLBL("Mensaje de la ventana de dialogo.");
        launchFXML("dialogue.fxml", "Ventana de dialogo");
    }

    /**
     * Prompts the user to edit the selected user. <br>
     * The current user MUST have administrative privileges for this action to be effective. <br>
     * */
    @FXML
    void editUser(ActionEvent event) {
        launchFXML("edit-user.fxml", "Editar Usuario");
    }

    /*Receipt Generation*/

    /**
     * Toggles monthly or daily receipt format accordingly. <br>
     * */
    @FXML
    void toggleMonthly(ActionEvent event) {
        if (receiptToggle.isSelected()) {
            dailyVBox.setVisible(false);
            dailyVBox.setPrefHeight(0);
            monthlyVBox.setVisible(true);
            monthlyVBox.setPrefHeight(429);
        } else {
            monthlyVBox.setVisible(false);
            monthlyVBox.setPrefHeight(0);
            dailyVBox.setVisible(true);
            dailyVBox.setPrefHeight(429);
        }
    }

    /**
     * Generates a receipt according to specifications of the user.<br>
     * */
    @FXML
    void generateReceipt(ActionEvent event) {
        emergentWindowsController.setDialMessageLBL("Mensaje de la ventana de dialogo.");
        launchFXML("dialogue.fxml", "Ventana de dialogo");
    }

    /*Report Generation*/

    /**
     * Generates a report according to the specifications of the user. <br>
     * */
    @FXML
    void generateReport(ActionEvent event) {
        emergentWindowsController.setDialMessageLBL("Mensaje de la ventana de dialogo.");
        launchFXML("dialogue.fxml", "Ventana de dialogo");
    }
}
