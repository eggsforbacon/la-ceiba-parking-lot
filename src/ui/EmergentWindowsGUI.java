package ui;

import exceptions.IDAlreadyInUseException;
import exceptions.NotAllowedException;
import exceptions.UsernameAlreadyInUseException;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ParkingLot;
import model.Vehicle;

import java.net.URL;
import java.util.ResourceBundle;

public class EmergentWindowsGUI implements Initializable, ColorsOrWhatever {
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
    private TableView<Vehicle> perHODVehiclesTBV = new TableView<>();

    @FXML
    private TableColumn<Vehicle, String> perHODVehicleTypeCOL= new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> perHODVehiclePlateCOL= new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> perHODVehicleEntryCOL= new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> perHODVehicleExitCOL= new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> perHODVehiclePayCOL= new TableColumn<>();
    
    @FXML
    private TableView<Vehicle> monthlyVehiclesTBV=new TableView<>();

    @FXML
    private TableColumn<Vehicle, String> monthlyVehicleTypeCOL= new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> monthlyVehiclePlateCOL= new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> MonthlyVehicleEntryCOL= new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> MonthlyVehicleExitCOL= new TableColumn<>();

    @FXML
    private TableColumn<Vehicle, String> MonthlyVehicleAmountToPayCOL= new TableColumn<>();

    @FXML
    private Button monthlyVehicleStartBTN= new Button();
    
	@FXML
	private Button perHODVehicleStartBTN= new Button();

    /*Map*/

    @FXML
    private VBox slotBDP = new VBox();

    @FXML
    private Label slotNumberLBL = new Label();

    @FXML
    private Rectangle vehicleRCT = new Rectangle();

    @FXML
    private Circle bikeCRC = new Circle();

    @FXML
    private Label contextLBL = new Label();

    @FXML
    private Label contextSlotLBL = new Label();

    @FXML
    private Label contextTypeLBL = new Label();

    /*------------------------ CLASS ATTRIBUTES ------------------------*/

    ParkingLot laCeiba;
    boolean successful = false;
    CurrentSceneGUI currentController;
    MainGUI mainController;

    /*------------------------ METHODS ------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer, Constructors and General*/

    /**
     * Principal constructor of the class. <br>
     *
     * @param laCeiba The object in which the apps info will be stored. <br>*/
    public EmergentWindowsGUI(ParkingLot laCeiba, CurrentSceneGUI currentController, MainGUI mainController) {
        this.laCeiba = laCeiba;
        this.currentController = currentController;
        this.mainController = mainController;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dummy = FXCollections.observableArrayList("AUTOMOVIL","MOTO","CAMION","BUS","FURGON","CAMIONETA");
        ObservableList<String> dummy2 = FXCollections.observableArrayList("HORA","DIA","MES","INDEFINIDO");
        ObservableList<String> dummy3 = FXCollections.observableArrayList("NEGRO","BLANCO","GRIS","ROJO","AZUL","CELESTE","AMARILLO","NARANJA","VERDE","ROSA","MORADO","CAFE","BEIGE","OTRO");
        newVehicleTypeCHB.setItems(dummy);
        newVehicleStayTypeCHB.setItems(dummy2);
        newVehicleColorCHB.setItems(dummy3);
        editVehicleColorCHB.setItems(dummy3);
    }

    /**
     * Initializes the filtered view of the vehicles database showing only the data that has their stay set to "HOUR" or "DAY". <br>
     * */
    public void iniTableViewPerHOD() {
    	laCeiba.fillPerHODVehiclesPL();
    	ObservableList<Vehicle> perHODVehicles = FXCollections.observableArrayList(laCeiba.getPerHODVehiclesPL());
    	perHODVehiclesTBV.setItems(perHODVehicles);
    	perHODVehicleTypeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
    	perHODVehiclePlateCOL.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
    	perHODVehicleEntryCOL.setCellValueFactory(new PropertyValueFactory<>("entryDateString"));
    	perHODVehicleExitCOL.setCellValueFactory(new PropertyValueFactory<>("supposedExitDateString"));
    	perHODVehiclePayCOL.setCellValueFactory(new PropertyValueFactory<>("valueToPay"));
    }

    /**
     * Initializes the filtered view of the vehicles database showing only the data that has their stay set to "MONTH". <br>
     * */
    public void iniTableViewMonthly() {
    	laCeiba.fillPerHODVehiclesPL();
    	 
    	ObservableList<Vehicle> perHODVehicles = FXCollections.observableArrayList(laCeiba.getMonthlyVehiclesPL());
    	monthlyVehiclesTBV.setItems(perHODVehicles);
    	monthlyVehicleTypeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
    	monthlyVehiclePlateCOL.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
    	MonthlyVehicleEntryCOL.setCellValueFactory(new PropertyValueFactory<>("entryDateString"));
    	MonthlyVehicleExitCOL.setCellValueFactory(new PropertyValueFactory<>("supposedExitDateString"));
    	MonthlyVehicleAmountToPayCOL.setCellValueFactory(new PropertyValueFactory<>("valueToPay"));

    }
//muerete
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
     * Launches the context fxml file. <br>
     * @param title The title of the window to be launched. @NotEmpty.<br>
     * */
    private void launchFXML(String title) {
        Parent root = loadFxml();
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
     * Loads an fxml file into a Parent object. <br>  */
    private Parent loadFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/context-map.fxml"));
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (Exception e) {
            System.out.println("Can't load requested document right now.\nRequested document: \"" + "context-map.fxml" + "\"");
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
            Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
            errorPane.getIcons().add(icon);
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
        } catch ( IDAlreadyInUseException | UsernameAlreadyInUseException e){
            launchError("Uno de los atributos escogidos ya esta en uso. Intente de nuevo","Creacion de empleado");
        }

        if (check) {
            launchError("Creado correctamente","Creacion de empleado");
        } else {
            launchError("Error. El empleado no pudo ser agregado","Creacion de empleado");
        }

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
        int login = laCeiba.login(oldUsernameTF.getText(),oldPasswordPWF.getText());
        if(login != -1 && login != -2){
            ((Stage) editUserFullNameTF.getScene().getWindow()).close();
            String user = currentController.getUsersTBV().getSelectionModel().getSelectedItem().getId();
            if(!editUsernameTF.getText().equals("")){
                if(!laCeiba.updateEmployeeUsername(user,editUsernameTF.getText())){
                    launchError("No se pudo actualizar el Nombre de usuario","Actualizacion de datos");
                }
            }
            if(!editPasswordPWF.getText().equals("")){
                if(!laCeiba.updateEmployeePassword(user,editPasswordPWF.getText())){
                    launchError("No se pudo actualizar la contrasenia","Actualizacion de datos");
                }
            }
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
        }
        else{
            launchError("Nombre de usuario o contraseña incorrectos","Error");
        }
        currentController.initClientsDB();
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
                    currentController.initClientsDB();
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
        switch (cb) {
            case "AUTOMOVIL":
                return 0;
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
        switch (cb) {
            case "HORA":
                return 0;
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

    /**
     * @return The boolean state of the login credentials. <br>*/
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
        String user = currentController.getClientsTBV().getSelectionModel().getSelectedItem().getId();
        if(!editClientFullnameTF.getText().equals("")){
            if(!laCeiba.updateClientName(user,editClientFullnameTF.getText())){
                launchError("No se pudo actualizar el nombre","Actualizacion de datos");
            }
        }
       
        if(!editClientPhoneTF.getText().equals("")){
            if(!laCeiba.updateClientCellNumber(user,editClientPhoneTF.getText())){
                launchError("No se pudo actualizar el numero de telefono","Actualizacion de datos");
            }
        }
        
        if(!editClientIDTF.getText().equals("")){
            if(!laCeiba.updateClientID(user,editClientIDTF.getText())){
                launchError("No se pudo actualizar el ID","Actualizacion de datos");
            }
        }
        currentController.initClientsDB();

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
    	
        ((Stage) editVehicleModelTF.getScene().getWindow()).close();
        String plate = currentController.getVehiclesTBV().getSelectionModel().getSelectedItem().getLicensePlate();

        
        
        if(!editVehicleModelTF.getText().equals("")){
            if(!laCeiba.updateVehicleModel(plate, editVehicleModelTF.getText())){
                launchError("No se pudo actualizar el modelo","Actualizacion de datos");
            }
        }
  
        if(editVehicleColorCHB.getSelectionModel().getSelectedItem() != null){
            if(!laCeiba.updateVehicleColor(plate, editVehicleColorCHB.getSelectionModel().getSelectedItem())){
                launchError("No se pudo actualizar el color","Actualizacion de datos");
            }
        }
        if(editVehicleSeatCHB.getSelectionModel().getSelectedItem() != null){
            if(!laCeiba.updateSpot(plate,Integer.parseInt(editVehicleSeatCHB.getSelectionModel().getSelectedItem()))){

                launchError("No se pudo actualizar la posicion","Actualizacion de datos");
            }
        }
        if(!editVehiclePlatesTF.getText().equals("")){
            if(!laCeiba.updateVehiclePlate(plate, editVehiclePlatesTF.getText())){
                launchError("No se pudo actualizar la placa","Actualizacion de datos");
            }
        }
        //aaaaaaaaaaa

        mainController.vehiclesClicked(event);
    }
   
    public void verifyEditSpotVehicle(Vehicle a) {
    	switch(a.getType()) {
    	case MOTO:
    		  ObservableList<String> temp = FXCollections.observableArrayList("-10","-9","-8","-7","-6","-5","-4","-3","-2","-1");
    		  editVehicleSeatCHB.setItems(temp);
    	break;
    	case AUTOMOVIL:
    		ObservableList<String> temp2 = FXCollections.observableArrayList("2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20");
  			 editVehicleSeatCHB.setItems(temp2);
    	break;	
    	case CAMION:
    	case FURGON:
    	case CAMIONETA:
    	case BUS:
    		ObservableList<String> temp3 = FXCollections.observableArrayList("21","22","23","24","25","26","27","28","29");
 			 editVehicleSeatCHB.setItems(temp3);
 		break;
 		default:
 			System.out.println("si llega a aparecer este mensaje beso al monitor");
    		
    	}
    }

    /**
     * Cancels the editing of the selected vehicle. <br>
     * */
    @FXML
    void cancelCancelVehicle(ActionEvent event) {
        ((Stage) editVehiclePlatesTF.getScene().getWindow()).close();
    }

    /*Map*/

    /**
     * Loads a parking slot. <br>
     * @param slotNumber The number of the slot. Negative numbers represent bike slots. <br>
     * @param slotId The css id this slot will have. Must be either <b>upper</b>, <b>mid</b> or <b>bottom</b>. */
    public void loadSlot(int slotNumber, String slotId) {
        slotBDP.setId(slotId);
        slotNumberLBL.setText(String.valueOf(slotNumber));
        if (laCeiba.verifySpot(slotNumber)) {
            slotNumberLBL.setPrefHeight(28);
            vehicleRCT.setHeight(0);
            bikeCRC.setRadius(0);
        } else {
            if (slotNumber >= 0) {
                slotNumberLBL.setPrefHeight(0);
                if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("AUTOMOVIL")) {
                    vehicleRCT.setHeight(30);
                    vehicleRCT.setWidth(15);
                } else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("CAMION")) {
                    vehicleRCT.setHeight(30);
                    vehicleRCT.setWidth(10);
                } else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("BUS")) {
                    vehicleRCT.setHeight(40);
                    vehicleRCT.setWidth(15);
                } else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("FURGON")) {
                    vehicleRCT.setHeight(35);
                    vehicleRCT.setWidth(15);
                } else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("CAMIONETA")) {
                    vehicleRCT.setHeight(45);
                    vehicleRCT.setWidth(20);
                }

                if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("NEGRO"))
                    vehicleRCT.setFill(Paint.valueOf(BLACK));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("BLANCO"))
                    vehicleRCT.setFill(Paint.valueOf(WHITE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("ROJO"))
                    vehicleRCT.setFill(Paint.valueOf(RED));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("GRIS"))
                    vehicleRCT.setFill(Paint.valueOf(GRAY));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("AZUL"))
                    vehicleRCT.setFill(Paint.valueOf(BLUE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("CELESTE"))
                    vehicleRCT.setFill(Paint.valueOf(LIGHT_BLUE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("AMARILLO"))
                    vehicleRCT.setFill(Paint.valueOf(YELLOW));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("NARANJA"))
                    vehicleRCT.setFill(Paint.valueOf(ORANGE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("VERDE"))
                    vehicleRCT.setFill(Paint.valueOf(GREEN));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("ROSA"))
                    vehicleRCT.setFill(Paint.valueOf(PINK));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("MORADO"))
                    vehicleRCT.setFill(Paint.valueOf(PURPLE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("CAFE"))
                    vehicleRCT.setFill(Paint.valueOf(BROWN));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("BEIGE"))
                    vehicleRCT.setFill(Paint.valueOf(BEIGE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("OTRO"))
                    vehicleRCT.setFill(Paint.valueOf(OTHER));
            } else {
                bikeCRC.setRadius(10);
                if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("NEGRO"))
                    bikeCRC.setFill(Paint.valueOf(BLACK));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("BLANCO"))
                    bikeCRC.setFill(Paint.valueOf(WHITE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("ROJO"))
                    bikeCRC.setFill(Paint.valueOf(RED));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("GRIS"))
                    bikeCRC.setFill(Paint.valueOf(GRAY));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("AZUL"))
                    bikeCRC.setFill(Paint.valueOf(BLUE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("CELESTE"))
                    bikeCRC.setFill(Paint.valueOf(LIGHT_BLUE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("AMARILLO"))
                    bikeCRC.setFill(Paint.valueOf(YELLOW));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("NARANJA"))
                    bikeCRC.setFill(Paint.valueOf(ORANGE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("VERDE"))
                    bikeCRC.setFill(Paint.valueOf(GREEN));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("ROSA"))
                    bikeCRC.setFill(Paint.valueOf(PINK));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("MORADO"))
                    bikeCRC.setFill(Paint.valueOf(PURPLE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("CAFE"))
                    bikeCRC.setFill(Paint.valueOf(BROWN));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("BEIGE"))
                    bikeCRC.setFill(Paint.valueOf(BEIGE));
                else if (laCeiba.getPlMap().spotAt(slotNumber).getInformation().contains("OTRO"))
                    bikeCRC.setFill(Paint.valueOf(OTHER));
            }
        }
    }

    /**
     * Shows the slot's information on context menu requested. <br>
     * */
    @FXML
    void showSlotInformation(ContextMenuEvent event) {
        int LABEL = 0;
        int slotNumber = Integer.parseInt(((Label) ((VBox) event.getSource()).getChildren().get(LABEL)).getText()); //And all it took were two casts and a parse :D -z
        launchFXML("Puesto " + slotNumber);
        contextSlotLBL.setText("Puesto #" + slotNumber);
        System.out.println(slotNumber);
        contextLBL.setText(laCeiba.getPlMap().spotAt(slotNumber).getInformation());
    }

    /**
     * Closes the context menu. <br>
     * */
    @FXML
    void mapContextDismiss(ActionEvent event) {
        ((Stage) contextLBL.getScene().getWindow()).close();
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
