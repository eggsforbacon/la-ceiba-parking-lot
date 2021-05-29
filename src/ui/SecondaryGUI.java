package ui;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.*;

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

    /*------------------------ METHODS ------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer and Constructors*/

    /*General Purpose*/

    /*Login*/

    /**
     * Takes the user and password and validates their login credentials. <br>
     */
    @FXML
    void loginClicked(ActionEvent event) {

    }

    /**
     * Prompts the user with a registration window. <br>
     */
    @FXML
    void registerClicked(ActionEvent event) {

    }

    /*Clients DB*/

    /**
     * Prompts the user to add a client to the program. <br>
     */
    @FXML
    void addClient(ActionEvent event) {

    }

    /**
     * Deletes the selected client. <br>
     */
    @FXML
    void deleteClient(ActionEvent event) {

    }

    /*Vehicles DB*/

    /**
     * Prompts the user to add a vehicle to the program. <br>
     */
    @FXML
    void addVehicle(ActionEvent event) {

    }

    /**
     * Deletes the selected vehicle. <br>
     */
    @FXML
    void deleteVehicle(ActionEvent event) {

    }

    /**
     * Prompts the user to edit the selected vehicle. <br>
     */
    @FXML
    void editVehicle(ActionEvent event) {

    }

    /*Map view (Throwing this one under the rug for a little while)*/

    /*Users DB*/

    @FXML
    void addUser(ActionEvent event) {

    }

    @FXML
    void deleteUser(ActionEvent event) {

    }

    @FXML
    void editUser(ActionEvent event) {

    }

    /*Report Generation*/

    /**
     * Generates a report according to the specifications of the user. <br>
     */
    @FXML
    void generateReport(ActionEvent event) {

    }

    /*Receipt Generation*/

    /**
     * Toggles monthly or daily receipt format accordingly. <br>
     */
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
     */
    @FXML
    void generateReceipt(ActionEvent event) {

    }
}
