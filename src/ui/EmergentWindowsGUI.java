package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ParkingLot;

public class EmergentWindowsGUI {
    /*------------------------ JAVAFX FIELDS ------------------------*/

    /*User Registration*/

    @FXML
    private TextField newUserFullNameTF;

    @FXML
    private TextField newUsernameTF;

    @FXML
    private PasswordField newPasswordPWF;

    @FXML
    private TextField newUserIDTF;

    /*User Editing*/

    @FXML
    private TextField editUserFullNameTF;

    @FXML
    private TextField oldUsernameTF;

    @FXML
    private PasswordField oldPasswordPWF;

    @FXML
    private TextField editUsernameTF;

    @FXML
    private PasswordField editPasswordPWF;

    @FXML
    private TextField editUserIDTF;

    /*Client Registration*/

    @FXML
    private TextField newClientFullnameTF;

    @FXML
    private TextField newClientPhoneTF;

    @FXML
    private TextField newClientIDTF;

    @FXML
    private ChoiceBox<String> newClientDocumentTypeCHB;

    /*Client Editing*/

    @FXML
    private TextField editClientFullnameTF;

    @FXML
    private TextField editClientPhoneTF;

    @FXML
    private TextField editClientIDTF;

    @FXML
    private ChoiceBox<String> editClientDocumentTypeCHB;

    /*Vehicle Registration*/

    @FXML
    private ChoiceBox<String> newVehicleTypeCHB;

    @FXML
    private TextField newVehicleNumberDaysTF;

    @FXML
    private ChoiceBox<String> newVehicleStayTypeCHB;

    @FXML
    private TextField newVehiclePlatesTF;

    @FXML
    private TextField newVehicleModelTF;

    @FXML
    private ChoiceBox<String> newVehicleColorCHB;

    @FXML
    private ChoiceBox<Integer> newVehicleSeatCHB;

    /*Vehicle Editing*/

    @FXML
    private ChoiceBox<?> editVehicleTypeCHB;

    @FXML
    private TextField editVehicleNumberDaysTF;

    @FXML
    private ChoiceBox<?> editVehicleStayTypeCHB;

    @FXML
    private TextField editVehiclePlatesTF;

    @FXML
    private TextField editVehicleModelTF;

    @FXML
    private ChoiceBox<String> editVehicleColorCHB;

    @FXML
    private ChoiceBox<String> editVehicleSeatCHB;

    /*------------------------ CLASS ATTRIBUTES ------------------------*/

    ParkingLot laCeiba;

    /*------------------------ METHODS ------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer, Constructors and General*/

    /**
     * Principal constructor of the class. <br>
     * @param laCeiba The model object that stores all the information. @NotNull. <br>
     * */
    public EmergentWindowsGUI(ParkingLot laCeiba) {
        this.laCeiba = laCeiba;
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
