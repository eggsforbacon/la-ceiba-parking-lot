package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

    /*Map view*/

    @FXML
    private HBox topHBOX = new HBox();

    @FXML
    private HBox bottomHBOX = new HBox();

    @FXML
    private VBox sideVBOX = new VBox();

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
    ParkingLot laCeiba;

    /*---------------------------- METHODS -----------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer, Constructors and General*/

    /**
     * Principal constructor of the class. <br>
     * @param laCeiba The object in which the apps info will be stored. <br>
     * */
    public CurrentSceneGUI(ParkingLot laCeiba) {
        this.laCeiba = laCeiba;
        emergentWindowsController = new EmergentWindowsGUI(laCeiba);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dummy = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        receiptVehicleTypeCHB.setItems(dummy);
        reportTypeCHB.setItems(dummy);
        initMap();
    }

    /**
     * Launches an fxml file. <br>
     * @param fxml The name of the fxml file. @NotNull.<br>
     * @param title The title of the window to be launched. @NotEmpty.<br>
     * */
    private void launchFXML(String fxml, String title) {
        Parent root = loadFxml(fxml);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Loads an fxml file into a Parent object. <br>
     * @param fxml The title of the window to be launched. @NotEmpty @NotNull.<br>*/
    private Parent loadFxml(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/" + fxml));
            fxmlLoader.setController(emergentWindowsController);
            return fxmlLoader.load();
        } catch (Exception e) {
            System.out.println("Can't load requested document right now.\nRequested document: \"" + fxml + "\"");
            throw new NullPointerException("Document is null");
        }
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

    void initMap() {
        for (int i = 10; i > -11; i--) {
            if (i == 1) i -= 2;
            emergentWindowsController.loadSlot(i, "upper");
            Parent slot = loadFxml("parking-slot.fxml");
            if (i == 2) {
                Separator sep = new Separator(Orientation.HORIZONTAL);
                sep.setOpacity(1);
                topHBOX.getChildren().add(sep);
            }
            topHBOX.getChildren().add(slot);
        }

        topHBOX.getChildren().remove(topHBOX.getChildren().size() - 1);

        for (int i = 9; i < 16; i++) {
            emergentWindowsController.loadSlot(i, "mid");
            Parent slot = loadFxml("parking-slot.fxml");
            sideVBOX.getChildren().add(slot);
        }

        sideVBOX.getChildren().remove(sideVBOX.getChildren().size() - 1);

        for (int i = 15; i < 30; i++) {
            emergentWindowsController.loadSlot(i, "mid");
            Parent slot = loadFxml("parking-slot.fxml");
            bottomHBOX.getChildren().add(slot);
        }

        bottomHBOX.getChildren().remove(bottomHBOX.getChildren().size() - 1);
    }

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
