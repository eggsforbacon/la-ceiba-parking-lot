package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ParkingLot;

import java.net.URL;
import java.util.ResourceBundle;

public class EmergentWindowsGUI implements Initializable {
    /*------------------------ JAVAFX FIELDS ------------------------*/

    /*Dialogue Window*/

    @FXML
    private Button dialDismissBTN = new Button();

    @FXML
    private Label dialMessageLBL = new Label();

    /*User Registration*/

    @FXML
    private TextField newUserFullNameTF = new TextField();

    @FXML
    private TextField newUsernameTF = new TextField();

    @FXML
    private PasswordField newPasswordPWF = new PasswordField();

    @FXML
    private TextField newUserIDTF = new TextField();

    /*User Editing*/

    @FXML
    private TextField editUserFullNameTF = new TextField();

    @FXML
    private TextField oldUsernameTF = new TextField();

    @FXML
    private PasswordField oldPasswordPWF = new PasswordField();

    @FXML
    private TextField editUsernameTF = new TextField();

    @FXML
    private PasswordField editPasswordPWF = new PasswordField();

    @FXML
    private TextField editUserIDTF = new TextField();

    /*Client Registration*/

    @FXML
    private TextField newClientFullnameTF = new TextField();

    @FXML
    private TextField newClientPhoneTF = new TextField();

    @FXML
    private TextField newClientIDTF = new TextField();

    @FXML
    private ChoiceBox<String> newClientDocumentTypeCHB = new ChoiceBox<>();

    /*Client Editing*/

    @FXML
    private TextField editClientFullnameTF = new TextField();

    @FXML
    private TextField editClientPhoneTF = new TextField();

    @FXML
    private TextField editClientIDTF = new TextField();

    @FXML
    private ChoiceBox<String> editClientDocumentTypeCHB = new ChoiceBox<>();

    /*Vehicle Registration*/

    @FXML
    private ChoiceBox<String> newVehicleTypeCHB = new ChoiceBox<>();

    @FXML
    private TextField newVehicleNumberDaysTF = new TextField();

    @FXML
    private ChoiceBox<String> newVehicleStayTypeCHB = new ChoiceBox<>();

    @FXML
    private TextField newVehiclePlatesTF = new TextField();

    @FXML
    private TextField newVehicleModelTF = new TextField();

    @FXML
    private ChoiceBox<String> newVehicleColorCHB = new ChoiceBox<>();

    @FXML
    private ChoiceBox<Integer> newVehicleSeatCHB = new ChoiceBox<>();

    /*Vehicle Editing*/

    @FXML
    private ChoiceBox<String> editVehicleTypeCHB = new ChoiceBox<>();

    @FXML
    private TextField editVehicleNumberDaysTF = new TextField();

    @FXML
    private ChoiceBox<String> editVehicleStayTypeCHB = new ChoiceBox<>();

    @FXML
    private TextField editVehiclePlatesTF = new TextField();

    @FXML
    private TextField editVehicleModelTF = new TextField();

    @FXML
    private ChoiceBox<String> editVehicleColorCHB = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> editVehicleSeatCHB = new ChoiceBox<>();

    /*------------------------ CLASS ATTRIBUTES ------------------------*/

    ParkingLot laCeiba;

    /*------------------------ METHODS ------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer, Constructors and General*/

    /**
     * Principal constructor of the class. <br>
     *
     * @param laCeiba The object in which the apps info will be stored. <br>*/
    public EmergentWindowsGUI(ParkingLot laCeiba) {
        this.laCeiba = laCeiba;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dummy = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        ObservableList<Integer> dummyInt = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        newClientDocumentTypeCHB.setItems(dummy);
        editClientDocumentTypeCHB.setItems(dummy);
        newVehicleTypeCHB.setItems(dummy);
        newVehicleStayTypeCHB.setItems(dummy);
        newVehicleColorCHB.setItems(dummy);
        editVehicleTypeCHB.setItems(dummy);
        editVehicleStayTypeCHB.setItems(dummy);
        editVehicleColorCHB.setItems(dummy);
        editVehicleSeatCHB.setItems(dummy);
        newVehicleSeatCHB.setItems(dummyInt);
    }

    /*Dialogue window*/

    /**
     * @param message The message the dialogue window will contain. <br>
     * */
    public void setDialMessageLBL(String message) {
        dialMessageLBL.setText(message);
    }

    /**
     * Dismisses the dialogue window, aka closes it. <br>
     * */
    @FXML
    void dismissDialogue(ActionEvent event) {
        ((Stage) dialDismissBTN.getScene().getWindow()).close();
        dialMessageLBL.setText("Message");
    }

    /*Users*/

    /**
     * Confirms the creation of a new user and adds it to the program. <br>
     * */
    @FXML
    void createNewUser(ActionEvent event) {
        ((Stage) newUserFullNameTF.getScene().getWindow()).close();
    }

    /**
     * Cancels the creation of a new user. <br>
     * */
    @FXML
    void cancelCreateNewUser(ActionEvent event) {
        ((Stage) newUserFullNameTF.getScene().getWindow()).close();
    }

    /**
     * Applies the changes specified in the editing form to the selected user. <br>
     * */
    @FXML
    void confirmEditUser(ActionEvent event) {
        ((Stage) editUserFullNameTF.getScene().getWindow()).close();
    }

    /**
     * Cancels the editing of the selected user. <br>
     * */
    @FXML
    void cancelEditUser(ActionEvent event) {
        ((Stage) editUserFullNameTF.getScene().getWindow()).close();
    }

    /*Clients and Vehicles*/

    /**
     * Confirms the creation of a new client with its associated vehicle and adds it to the program. <br>
     * */
    @FXML
    void createNewClient(ActionEvent event) {
        ((Stage) newClientFullnameTF.getScene().getWindow()).close();
    }

    /**
     * Cancels the creation of a new client with its associated vehicle in the program. <br>
     * */
    @FXML
    void cancelCreateNewClient(ActionEvent event) {
        ((Stage) newClientFullnameTF.getScene().getWindow()).close();
    }

    /**
     * Applies the changes specified in the editing form to the selected client. <br>
     * */
    @FXML
    void confirmEditClient(ActionEvent event) {
        ((Stage) editClientFullnameTF.getScene().getWindow()).close();
    }

    /**
     * Cancels the editing of the selected client. <br>
     * */
    @FXML
    void cancelCancelClient(ActionEvent event) {
        ((Stage) editClientFullnameTF.getScene().getWindow()).close();
    }

    /**
     * Applies the changes specified in the editing form to the selected vehicle. <br>
     * */
    @FXML
    void confirmEditVehicle(ActionEvent event) {
        ((Stage) editVehicleTypeCHB.getScene().getWindow()).close();
    }

    /**
     * Cancels the editing of the selected vehicle. <br>
     * */
    @FXML
    void cancelCancelVehicle(ActionEvent event) {
        ((Stage) editVehicleTypeCHB.getScene().getWindow()).close();
    }
}
