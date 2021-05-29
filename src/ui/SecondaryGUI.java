package ui;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;

public class SecondaryGUI {
    /*------------------------ JAVAFX FIELDS ------------------------*/

    /*Login*/

    @FXML
    private TextField loginUserTF;

    @FXML
    private PasswordField loginPassPWF;

    /*Clients DB*/

    @FXML
    private TableView<Client> clientsTBV;

    @FXML
    private TableColumn<Client, String> clientsNameCOL;

    @FXML
    private TableColumn<Client, String> clientsIDCOL;

    @FXML
    private TableColumn<Client, String> clientsPhoneCOL;

    @FXML
    private TableColumn<Client, String> clientEnabledCOL;

    @FXML
    private Button clientDeleteBTN;

    /*Vehicles DB*/

    @FXML
    private TableView<Vehicle> vechiclesTBV;

    @FXML
    private TableColumn<Vehicle, String> vehicleTypeCOL;

    @FXML
    private TableColumn<Vehicle, String> vehicleStayCOL;

    @FXML
    private TableColumn<Vehicle, String> vehiclePlateCOL;

    @FXML
    private TableColumn<Vehicle, String> vehicleSlotCOL;

    @FXML
    private TableColumn<Vehicle, String> vehicleEnabledCOL;

    @FXML
    private Button vehicleEditBTN;

    @FXML
    private Button vehicleDeleteBTN;

    /*Map view (Throwing this one under the rug for a little while)*/

    /*Users DB*/

    @FXML
    private TableView<?> usersTBV;

    @FXML
    private TableColumn<?, String> userNameCOL;

    @FXML
    private TableColumn<?, String> userUsernameCOL;

    @FXML
    private TableColumn<?, String> userIDCOL;

    @FXML
    private Button userDeleteBTN;

    @FXML
    private Button userEditBTN;

    /*Receipt Generation*/

    @FXML
    private CheckBox receiptToggle;

    @FXML
    private VBox monthlyVBox;

    @FXML
    private Label receiptCodeMonthlyLBL;

    @FXML
    private TextField receiptCityMonthlyTF;

    @FXML
    private Label receiptPaidValueMonthlyLBL;

    @FXML
    private DatePicker receiptDateMonthlyDTP;

    @FXML
    private TextField receiptClientNameMonthlyTF;

    @FXML
    private Label receiptVehicleMonthlyLBL;

    @FXML
    private Label receiptDaysMonthlyLBL;

    @FXML
    private VBox dailyVBox;

    @FXML
    private ChoiceBox<String> receiptVehicleTypeCHB;

    @FXML
    private DatePicker receiptDateDailyDTP;

    @FXML
    private Label receiptPaidValueDailyLBL;

    @FXML
    private TextField receiptPlateDailyTF;

    @FXML
    private TextField receiptHoursDailyTF;

    /*Report Generation*/

    @FXML
    private BorderPane reportPane;

    @FXML
    private DatePicker reportFromDTP;

    @FXML
    private DatePicker reportToDTP;

    @FXML
    private TextField reportFromHourTF;

    @FXML
    private TextField reportToHourTF;

    @FXML
    private ChoiceBox<?> reportTypeCHB;

    /*------------------------ METHODS ------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer and Constructors*/

    /*General Purpose*/

    /**
     * Launches an fxml file. <br>
     * @param fxml The name of the fxml file. @NotNull.<br>
     * @param title The title of the window to be launched. @NotEmpty.<br>
     * @param modality The modality which the window will be loaded with. Defined within the enum Modality. <br>
     * */
    private void launchFXML(String fxml, String title, Modality modality) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/" + fxml));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(modality);
            stage.show();
        } catch (IOException ignored) {}
    }

    /*Login*/

    /**
     * Takes the user and password and validates their login credentials. <br>
     * */
    @FXML
    void loginClicked(ActionEvent event) {

    }

    /**
     * Prompts the user with a registration window. <br>
     * */
    @FXML
    void registerClicked(ActionEvent event) {

    }

    /*Clients DB*/

    /**
     * Prompts the user to add a client to the program. <br>
     * */
    @FXML
    void addClient(ActionEvent event) {

    }

    /**
     * Deletes the selected client. <br>
     * */
    @FXML
    void deleteClient(ActionEvent event) {

    }

    /*Vehicles DB*/

    /**
     * Prompts the user to add a vehicle to the program. <br>
     * */
    @FXML
    void addVehicle(ActionEvent event) {

    }

    /**
     * Deletes the selected vehicle. <br>
     * */
    @FXML
    void deleteVehicle(ActionEvent event) {

    }

    /**
     * Prompts the user to edit the selected vehicle. <br>
     * */
    @FXML
    void editVehicle(ActionEvent event) {

    }

    /*Map view (Throwing this one under the rug for a little while)*/

    /*Users DB*/

    /**
     * Prompts the user to add another user to the program. <br>
     * The current user MUST have administrative privileges for this action to be effective. <br>
     * */
    @FXML
    void addUser(ActionEvent event) {

    }

    /**
     * Deletes the selected user. <br>
     * The current user MUST have administrative privileges for this action to be effective. <br>
     * */
    @FXML
    void deleteUser(ActionEvent event) {

    }

    /**
     * Prompts the user to edit the selected user. <br>
     * The current user MUST have administrative privileges for this action to be effective. <br>
     * */
    @FXML
    void editUser(ActionEvent event) {

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

    }

    /*Report Generation*/

    /**
     * Generates a report according to the specifications of the user. <br>
     * */
    @FXML
    void generateReport(ActionEvent event) {

    }
}
