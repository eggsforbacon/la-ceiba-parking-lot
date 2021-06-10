package ui;

import exceptions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ParkingLot;
import model.Vehicle;

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


    /*Client Editing*/

    @FXML
    private TextField editClientFullnameTF = new TextField();

    @FXML
    private TextField editClientPhoneTF = new TextField();

    @FXML
    private TextField editClientIDTF = new TextField();


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
    
    /*Vehicles per hour or daily*/
    //Tableview
    @FXML
    private TableView<Vehicle> perHODVehiclesTBV;

    @FXML
    private TableColumn<Vehicle, String> perHODVehicleTypeCOL;

    @FXML
    private TableColumn<Vehicle, String> perHODVehiclePlateCOL;

    @FXML
    private TableColumn<Vehicle, String> perHODVehicleEntryCOL;

    @FXML
    private TableColumn<Vehicle, String> perHODVehicleExitCOL;

    @FXML
    private TableColumn<Vehicle, Double> perHODVehicleAmountToPayCOL;

    
	    //Edit vehicle per hour 
	    @FXML
	    private Button perHODVehicleEditBTN;
	    
	    //Delete vehicle per hour 
	    @FXML
	    private Button perHODVehicleDeleteBTN;
	    
	    //vehicle per hour search txt
	    @FXML
	    private TextField perHODVehicleTXT;
	    
	    //search vehicle per hour 
	    @FXML
	    private Button perHODVehicleSearchBTN;
	    
	    //Per hour vehicle start button
	    @FXML
	    private Button perHODVehicleStartBTN;

	/*vehicles monthly*/
    @FXML
    private Button monthlyVehicleStartBTN;
    
    
    /*Map*/

    @FXML
    private VBox slotBDP = new VBox();

    @FXML
    private Label slotNumberLBL = new Label();

    @FXML
    private Rectangle vehicleRCT = new Rectangle();

    @FXML
    private Label contextColorLBL = new Label();

    @FXML
    private Label contextStayLBL = new Label();

    @FXML
    private Label contextPlateLBL = new Label();

    @FXML
    private Label contextPayYetLBL = new Label();

    @FXML
    private Label contextSlotLBL = new Label();

    @FXML
    private Label contextTypeLBL = new Label();

    /*------------------------ CLASS ATTRIBUTES ------------------------*/

    ParkingLot laCeiba;
    boolean successful = false;
    CurrentSceneGUI aVerSiEstoFunciona;

    /*------------------------ METHODS ------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer, Constructors and General*/

    /**
     * Principal constructor of the class. <br>
     *
     * @param laCeiba The object in which the apps info will be stored. <br>*/
    public EmergentWindowsGUI(ParkingLot laCeiba,CurrentSceneGUI aVerSiEstoFunciona) {
        this.laCeiba = laCeiba;
        this.aVerSiEstoFunciona = aVerSiEstoFunciona;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dummy = FXCollections.observableArrayList("AUTOMOVIL","MOTO","CAMION","BUS","FURGON","CAMIONETA");
        ObservableList<String> dummy2 = FXCollections.observableArrayList("HORA","DIA","MES","INDEFINIDO");
        ObservableList<String> dummy3 = FXCollections.observableArrayList("NEGRO","BLANCO","GRIS","ROJO","AZUL","AMARILLO","NARANJA","VERDE","ROSA","MORADO");
        laCeiba.initPerHODList();
    	ObservableList<Vehicle> perHODVehicles = FXCollections.observableArrayList(laCeiba.getPerHODVehiclesPL());
    	
    	perHODVehicleTypeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
    	perHODVehiclePlateCOL.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
    	perHODVehicleEntryCOL.setCellValueFactory(new PropertyValueFactory<>("entryDateString"));
    	perHODVehicleExitCOL.setCellValueFactory(new PropertyValueFactory<>("actualExitDateString"));
    	perHODVehicleAmountToPayCOL.setCellValueFactory(new PropertyValueFactory<>("valueToPay"));
        perHODVehiclesTBV.setItems(perHODVehicles);
        //SideBar
        perHODVehicleDeleteBTN.setDisable(true);
        perHODVehicleEditBTN.setDisable(true);
        perHODVehiclesTBV.setOnMouseClicked(event -> {
            if (! perHODVehiclesTBV.getSelectionModel().getSelectedItems().isEmpty()) {
            	perHODVehicleDeleteBTN.setDisable(false);
            	perHODVehicleEditBTN.setDisable(false);
            }
        });
        
        newVehicleTypeCHB.setItems(dummy);
        newVehicleStayTypeCHB.setItems(dummy2);
        newVehicleColorCHB.setItems(dummy3);
        editVehicleTypeCHB.setItems(dummy);
        editVehicleStayTypeCHB.setItems(dummy2);
        editVehicleColorCHB.setItems(dummy3);
    }

    public Button getDialDismissBTN() {
        return dialDismissBTN;
    }

    public void setDialDismissBTN(Button dialDismissBTN) {
        this.dialDismissBTN = dialDismissBTN;
    }

    public ChoiceBox<Integer> getNewVehicleSeatCHB() {
        return newVehicleSeatCHB;
    }

    public void setNewVehicleSeatCHB(ChoiceBox<Integer> newVehicleSeatCHB) {
        this.newVehicleSeatCHB = newVehicleSeatCHB;
    }

    public ChoiceBox<String> getEditVehicleSeatCHB() {
        return editVehicleSeatCHB;
    }

    public void setEditVehicleSeatCHB(ChoiceBox<String> editVehicleSeatCHB) {
        this.editVehicleSeatCHB = editVehicleSeatCHB;
    }

    /**
     * @return The type of the selected vehicle.<br>
     * */
    public String getVehicleType(){
        if(newVehicleTypeCHB.getSelectionModel().getSelectedItem() != null){
            return newVehicleTypeCHB.getValue();
        }
        else{
            return "";
        }
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
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (Exception e) {
            System.out.println("Can't load requested document right now.\nRequested document: \"" + fxml + "\"");
            throw new NullPointerException("Document is null");
        }
    }

    /*Dialogue window*/

    /**
     * Launches the error window. <br>
     * @param title The title of the window. <br>
     * @param message The message to be shown in the error/dialogue window. <br>
     * */
    public void launchError(String message, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/dialogue.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage errorPane = new Stage();
            errorPane.setScene(new Scene(root));
            errorPane.initModality(Modality.APPLICATION_MODAL);
            errorPane.setTitle(title);
            dialMessageLBL.setText(message);
            dialMessageLBL.setStyle("\n-fx-font-style: italic;");
            errorPane.setResizable(false);
            errorPane.show();
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }

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
        boolean check = false;
        try{
            check = laCeiba.addEmployee(newUserFullNameTF.getText(),newUserIDTF.getText(),newUsernameTF.getText(),newPasswordPWF.getText());
        }catch ( IDAlreadyInUseException | UsernameAlreadyInUseException e){
            launchError("Uno de los atributos escogidos ya esta en uso. Intente de nuevo","Creacion de empleado");
        }

        if(check){
            launchError("Creado correctamente","Creacion de empleado");
        }
        else{
            launchError("Error. El empleado no pudo ser agregado","Creacion de empleado");
        }
        aVerSiEstoFunciona.initUsersDB();
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
        int loginxd = laCeiba.login(oldUsernameTF.getText(),oldPasswordPWF.getText());
        if(loginxd != -1 && loginxd != -2){
            ((Stage) editUserFullNameTF.getScene().getWindow()).close();
            String user = aVerSiEstoFunciona.getUsersTBV().getSelectionModel().getSelectedItem().getId();
            if(!editUserFullNameTF.getText().equals("")){
                if(!laCeiba.updateEmployeeName(user,editUserFullNameTF.getText())){
                    launchError("No se pudo actualizar el nombre","Actualizacion de datos");
                }
            }
            if(!editUserIDTF.getText().equals("")){
                if(!laCeiba.updateEmployeeID(user,editUserIDTF.getText())){
                    launchError("No se pudo actualizar el ID","Actualizacion de datos");
                }
            }

            if(!editUsernameTF.getText().equals("")){
                if(!laCeiba.updateEmployeeUsername(user,newUsernameTF.getText(),oldUsernameTF.getText(),oldPasswordPWF.getText())){
                    launchError("No se pudo actualizar el Nombre de usuario","Actualizacion de datos");
                }
            }
            if(!editPasswordPWF.getText().equals("")){
                if(!laCeiba.updateEmployeePassword(user,editUsernameTF.getText(),oldPasswordPWF.getText(),editPasswordPWF.getText())){
                    launchError("No se pudo actualizar la contrasenia","Actualizacion de datos");
                }
            }
        }
        else{
            launchError("Nombre de usuario o contraseña incorrectos","Error");
        }

        aVerSiEstoFunciona.initUsersDB();
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
        boolean check;
        try{
            check = laCeiba.addClient(newClientFullnameTF.getText(),newClientIDTF.getText(),newClientPhoneTF.getText());
        }
        catch(IDAlreadyInUseException e){
            check = true;
        }

        if(check){
            successful =true;
            try{
                boolean check2 = laCeiba.addVehicle(translateVehicleType(getVehicleType()),newVehicleModelTF.getText(),newVehiclePlatesTF.
                                getText(),newVehicleColorCHB.getSelectionModel().getSelectedItem(),
                        laCeiba.searchByID(newClientIDTF.getText()),
                        newVehicleSeatCHB.getSelectionModel().getSelectedItem(),translateVehicleStay(newVehicleStayTypeCHB.getSelectionModel().getSelectedItem())
                        ,Integer.parseInt(newVehicleNumberDaysTF.getText()));
                if(check2){
                    launchError("Cliente y vehiculo creados correctamente","Creacion de vehiculo y cliente");
                    aVerSiEstoFunciona.initClientsDB();
                }
                else{
                    laCeiba.removeLastClient();
                    launchError("Error con los datos del vehiculo","Creacion de vehiculo y cliente");
                }
            }catch (NullPointerException | NotAllowedException e){
                laCeiba.removeLastClient();
                launchError("Asegurese de llenar todos los espacios","Creacion de vehiculo y cliente");
            }
        }
        else{
            launchError("Datos del cliente incorrectos o invalidos","Creacion de cliente");
        }
    }

    /**
     * Translates the provided string to a working integer index representing the vehicle's type.<br>
     * @param cb The string value to be translated. @NotNull. Must belong to the <b>VehicleType</b> enum. <br><br>
     * */
    public int translateVehicleType(String cb){
        switch (cb){
            case "AUTOMOVIL":
                return  0;
            case "MOTO":
                return 1;
            case "CAMION":
                return 2;
            case "BUS":
                return 3;
            case "FURGON":
                return 4;
            case "CAMIONETA":
                return 5;
            default:
                return -1;
        }
    }

    /**
     * Translates the provided string into a working integer index representing the vehicle's stay type. <br>
     * @param cb The string value to be translated. @NotNull. Must belong to the <b>StayTime</b> enum. <br><br>
     * */
    public int translateVehicleStay(String cb){
        switch (cb){
            case "HORA":
                return  0;
            case "DIA":
                return 1;
            case "MES":
                return 2;
            case "INDEFINIDO":
                return 3;
            default:
                return -1;
        }
    }

    public boolean getSuccessful(){
        return successful;
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
        String user = aVerSiEstoFunciona.getClientsTBV().getSelectionModel().getSelectedItem().getId();
        if(!editClientFullnameTF.getText().equals("")){
            if(!laCeiba.updateClientName(user,editClientFullnameTF.getText())){
                launchError("No se pudo actualizar el nombre","Actualizacion de datos");
            }
        }
        if(!editClientIDTF.getText().equals("")){
            if(!laCeiba.updateClientID(user,editClientIDTF.getText())){
                launchError("No se pudo actualizar el ID","Actualizacion de datos");
            }
        }
        if(!editClientPhoneTF.getText().equals("")){
            if(!laCeiba.updateClientCellNumber(user,editClientPhoneTF.getText())){
                launchError("No se pudo actualizar el numero de telefono","Actualizacion de datos");
            }
        }
        aVerSiEstoFunciona.initClientsDB();

    }

    /**
     * Cancels the editing of the selected client. <br>
     * */
    @FXML
    void cancelEditClient(ActionEvent event) {
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

    /*Map*/

    /**
     * Loads a parking slot. <br>
     * @param slotNumber The number of the slot. Negative numbers represent bike slots. <br>
     * @param slotId The css id this slot will have. Must be either <b>upper</b>, <b>mid</b> or <b>bottom</b>.
     * @param busy The state of the slot. Whether there is or isn't a vehicle in it. */
    public void loadSlot(int slotNumber, String slotId, boolean busy) {
        slotNumberLBL.setText(String.valueOf(slotNumber));
        slotBDP.setId(slotId);
        if (busy) {
            slotNumberLBL.setText("");
            slotNumberLBL.setPrefHeight(0);
            vehicleRCT.setHeight(50);
        }
    }

    /**
     * Shows the slot's information on context menu requested. <br>
     * */
    @FXML
    void showSlotInformation(ContextMenuEvent event) {
        int LABEL = 0;
        int slotNumber = Integer.parseInt(((Label) ((VBox) event.getSource()).getChildren().get(LABEL)).getText()); //And all it took were two casts and a parse :D -z
        launchFXML("context-map.fxml", "Puesto " + slotNumber);
        contextSlotLBL.setText("Puesto N°" + slotNumber);
        System.out.println(slotNumber);
        contextColorLBL.setText(laCeiba.getPlMap().spotAt(slotNumber).getInformation());
    }

    /**
     * Called when the button "Delete" is actioned on the map's context menu.
     * */
    @FXML
    void mapContextDelete(ActionEvent event) {

    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
