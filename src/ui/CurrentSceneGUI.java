package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;
import model.Employee;
import model.ParkingLot;
import model.Vehicle;
import threads.ChoiceBoxThread;

import java.net.URL;
import java.util.ArrayList;
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


    @FXML
    private Label dialMessageLBL;

    @FXML
    private Button dialDismissBTN;

    /*------------------------ CLASS ATTRIBUTES ------------------------*/


    ParkingLot laCeiba;
    EmergentWindowsGUI emergentWindowsController;
    boolean loginSuccessful;
    String currentScene;
    ArrayList<Thread> threads = new ArrayList<>();
    int iLoveThisnumber=1;

    /*---------------------------- METHODS -----------------------------*/
    //Methods will be written in order according to the intended flow of the program

    /*Initializer, Constructors and General*/

    /**
     * Principal constructor of the class. <br>
     * @param laCeiba The object in which the apps info will be stored. <br>
     * */
    public CurrentSceneGUI(ParkingLot laCeiba) {
        this.laCeiba = laCeiba;
        emergentWindowsController = new EmergentWindowsGUI(laCeiba,this);
        currentScene = "none";
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dummy = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        receiptVehicleTypeCHB.setItems(dummy);
        reportTypeCHB.setItems(dummy);
        currentInit();
    }

    /**
     * Optimizes initialization according to active pane. <br>
     * */
    void currentInit() {
        switch (currentScene) {
            case "Clientes":
                initClientsDB();
                break;
            case "Mapa":
                initMap();
                break;
            default:
                break;
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
        stage.setOnHidden(event -> {
            //do all your processing here
            emergentWindowsController.setSuccesssful(true);
        });
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
            System.out.println(e.getMessage());
            System.out.println("Can't load requested document right now.\nRequested document: \"" + fxml + "\"");
            throw new NullPointerException("Document is null");
        }
    }

    /**
     * Launches the dialogue pane. <br>
     * @param message The message to be displayed within the dialogue pane. @NotNull @NotEmpty.<br>
     * @param title The title of the dialogue windows. @NotNull @NotEmpty.<br>
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
     * @param currentScene The string name of the new scene to be set to. <br>
     * */
    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
    }

    /*Login*/

    /**
     * Takes the user and password and validates their login credentials. <br>
     * */
    @FXML
    void loginClicked(ActionEvent event) {
        int user = laCeiba.login(loginUserTF.getText(),loginPassPWF.getText());
        switch (user){
            case -1:
                //emergentWindowsController.setDialMessageLBL("Datos erroneos o incompletos. Intente de nuevo.");
                //launchFXML("dialogue.fxml","Mensaje de inicio de sesion");
                launchError("Datos erroneos o incompletos. Intente de nuevo.","Mensaje de inicio de sesion");
                break;
            case -2:
                //emergentWindowsController.setDialMessageLBL("El uso del usuario root no es recomendado. Proceder con precaución.");
                //launchFXML("dialogue.fxml","Mensaje de inicio de sesion");
                launchError("El uso del usuario root no es recomendado. Proceder con precaucion.","Mensaje de inicio de sesion");
                loginSuccessful = true;
                break;
            default:
                laCeiba.setActualEmployee(laCeiba.getEmployeesPL().get(user));
                launchError("Bienvenido "+laCeiba.getActualEmployee().getName(),"Mensaje de inicio de sesion");
                loginSuccessful = true;
                break;
        }
    }

    /**
     * Dismisses the dialogue window, aka closes it. <br>
     * */
    @FXML
    void dismissDialogue(ActionEvent event) {
        ((Stage) dialDismissBTN.getScene().getWindow()).close();
        dialMessageLBL.setText("Message");
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
     * Initializes the client database. <br>
     * */
    void initClientsDB() {
        clientsNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        clientsIDCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientsPhoneCOL.setCellValueFactory(new PropertyValueFactory<>("cellNumber"));
        clientEnabledCOL.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<Client>clientList = FXCollections.observableArrayList(laCeiba.getClientsPL());
        clientsTBV.setItems(clientList);
        //SideBar
        clientDeleteBTN.setDisable(true);
        clientEditBTN.setDisable(true);
        clientsTBV.setOnMouseClicked(event -> {
            if (!clientsTBV.getSelectionModel().getSelectedItems().isEmpty()) {
                clientDeleteBTN.setDisable(false);
                clientEditBTN.setDisable(false);
            }
        });
        clientsTBV.setOnKeyTyped(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                clientDeleteBTN.setDisable(true);
                clientEditBTN.setDisable(true);
            }
        });

        clientsNameCOL.setCellFactory(TextFieldTableCell.forTableColumn());
        clientsIDCOL.setCellFactory(TextFieldTableCell.forTableColumn());
        clientsPhoneCOL.setCellFactory(TextFieldTableCell.forTableColumn());
        clientEnabledCOL.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /**
     * Prompts the user to add a client with its vehicle to the program. <br>
     * */
    @FXML
    public void addClient(ActionEvent event) {
        launchFXML("create-client-vehicle.fxml", "Nuevo Cliente");
        ChoiceBoxThread odioLosHilos = new ChoiceBoxThread(emergentWindowsController);
        odioLosHilos.setDaemon(true);
        odioLosHilos.start();
    }

    /**
     * Deletes the selected client. <br>
     * */
    @FXML
    void deleteClient(ActionEvent event) {
        boolean canDelete;
        int selectedItems = clientsTBV.getSelectionModel().getSelectedItems().size();
        for (int i = 0; i < selectedItems; i++) {
            Client removed = clientsTBV.getSelectionModel().getSelectedItems().get(i);
            canDelete = laCeiba.disableClientByName(removed.getName());
            if (!canDelete) launchError("No se pudo deshabilitar","Error");
        }
        initClientsDB();
    }

    /**
     * Prompts the user to edit the selected client. <br>
     * */
    @FXML
    void editClient(ActionEvent event) {
        launchFXML("edit-client.fxml", "Editar Cliente");
    }

    /*Vehicles DB*/

    @FXML
    void toggleMonthlyVehicles(ActionEvent event) {

    }

    /**
     * Initializes the vehicles database. <br>
     * */
    void initVehiclesDB() {
        vehicleSlotCOL.setCellValueFactory(new PropertyValueFactory<>("spot"));
        vehicleStayCOL.setCellValueFactory(new PropertyValueFactory<>("spot"));
        vehicleTypeCOL.setCellValueFactory(new PropertyValueFactory<>("spot"));
        vehiclePlateCOL.setCellValueFactory(new PropertyValueFactory<>("spot"));
        vehicleEnabledCOL.setCellValueFactory(new PropertyValueFactory<>("spot"));
    }

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
        laCeiba.getPlMap().setLeftColumn();
        laCeiba.getPlMap().setBottomRow();
        laCeiba.getPlMap().setRightColumn();
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

    /**
     * @return The current status of the login. <br>
     * */
    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    /**
     * @param loginSuccessful The new login value. <br>
     * */
    public void setLoginSuccessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public TextField getLoginUserTF() {
        return loginUserTF;
    }

    public void setLoginUserTF(TextField loginUserTF) {
        this.loginUserTF = loginUserTF;
    }

    public PasswordField getLoginPassPWF() {
        return loginPassPWF;
    }

    public void setLoginPassPWF(PasswordField loginPassPWF) {
        this.loginPassPWF = loginPassPWF;
    }

    public TableView<Client> getClientsTBV() {
        return clientsTBV;
    }

    public void setClientsTBV(TableView<Client> clientsTBV) {
        this.clientsTBV = clientsTBV;
    }

    public TableColumn<Client, String> getClientsNameCOL() {
        return clientsNameCOL;
    }

    public void setClientsNameCOL(TableColumn<Client, String> clientsNameCOL) {
        this.clientsNameCOL = clientsNameCOL;
    }

    public TableColumn<Client, String> getClientsIDCOL() {
        return clientsIDCOL;
    }

    public void setClientsIDCOL(TableColumn<Client, String> clientsIDCOL) {
        this.clientsIDCOL = clientsIDCOL;
    }

    public TableColumn<Client, String> getClientsPhoneCOL() {
        return clientsPhoneCOL;
    }

    public void setClientsPhoneCOL(TableColumn<Client, String> clientsPhoneCOL) {
        this.clientsPhoneCOL = clientsPhoneCOL;
    }

    public TableColumn<Client, String> getClientEnabledCOL() {
        return clientEnabledCOL;
    }

    public void setClientEnabledCOL(TableColumn<Client, String> clientEnabledCOL) {
        this.clientEnabledCOL = clientEnabledCOL;
    }

    public Button getClientEditBTN() {
        return clientEditBTN;
    }

    public void setClientEditBTN(Button clientEditBTN) {
        this.clientEditBTN = clientEditBTN;
    }

    public Button getClientDeleteBTN() {
        return clientDeleteBTN;
    }

    public void setClientDeleteBTN(Button clientDeleteBTN) {
        this.clientDeleteBTN = clientDeleteBTN;
    }

    public TableView<Vehicle> getVehiclesTBV() {
        return vehiclesTBV;
    }

    public void setVehiclesTBV(TableView<Vehicle> vehiclesTBV) {
        this.vehiclesTBV = vehiclesTBV;
    }

    public TableColumn<Vehicle, String> getVehicleTypeCOL() {
        return vehicleTypeCOL;
    }

    public void setVehicleTypeCOL(TableColumn<Vehicle, String> vehicleTypeCOL) {
        this.vehicleTypeCOL = vehicleTypeCOL;
    }

    public TableColumn<Vehicle, String> getVehicleStayCOL() {
        return vehicleStayCOL;
    }

    public void setVehicleStayCOL(TableColumn<Vehicle, String> vehicleStayCOL) {
        this.vehicleStayCOL = vehicleStayCOL;
    }

    public TableColumn<Vehicle, String> getVehiclePlateCOL() {
        return vehiclePlateCOL;
    }

    public void setVehiclePlateCOL(TableColumn<Vehicle, String> vehiclePlateCOL) {
        this.vehiclePlateCOL = vehiclePlateCOL;
    }

    public TableColumn<Vehicle, String> getVehicleSlotCOL() {
        return vehicleSlotCOL;
    }

    public void setVehicleSlotCOL(TableColumn<Vehicle, String> vehicleSlotCOL) {
        this.vehicleSlotCOL = vehicleSlotCOL;
    }

    public TableColumn<Vehicle, String> getVehicleEnabledCOL() {
        return vehicleEnabledCOL;
    }

    public void setVehicleEnabledCOL(TableColumn<Vehicle, String> vehicleEnabledCOL) {
        this.vehicleEnabledCOL = vehicleEnabledCOL;
    }

    public Button getVehicleEditBTN() {
        return vehicleEditBTN;
    }

    public void setVehicleEditBTN(Button vehicleEditBTN) {
        this.vehicleEditBTN = vehicleEditBTN;
    }

    public Button getVehicleDeleteBTN() {
        return vehicleDeleteBTN;
    }

    public void setVehicleDeleteBTN(Button vehicleDeleteBTN) {
        this.vehicleDeleteBTN = vehicleDeleteBTN;
    }

    public HBox getTopHBOX() {
        return topHBOX;
    }

    public void setTopHBOX(HBox topHBOX) {
        this.topHBOX = topHBOX;
    }

    public HBox getBottomHBOX() {
        return bottomHBOX;
    }

    public void setBottomHBOX(HBox bottomHBOX) {
        this.bottomHBOX = bottomHBOX;
    }

    public VBox getSideVBOX() {
        return sideVBOX;
    }

    public void setSideVBOX(VBox sideVBOX) {
        this.sideVBOX = sideVBOX;
    }

    public TableView<Employee> getUsersTBV() {
        return usersTBV;
    }

    public void setUsersTBV(TableView<Employee> usersTBV) {
        this.usersTBV = usersTBV;
    }

    public TableColumn<Employee, String> getUserNameCOL() {
        return userNameCOL;
    }

    public void setUserNameCOL(TableColumn<Employee, String> userNameCOL) {
        this.userNameCOL = userNameCOL;
    }

    public TableColumn<Employee, String> getUserUsernameCOL() {
        return userUsernameCOL;
    }

    public void setUserUsernameCOL(TableColumn<Employee, String> userUsernameCOL) {
        this.userUsernameCOL = userUsernameCOL;
    }

    public TableColumn<Employee, String> getUserIDCOL() {
        return userIDCOL;
    }

    public void setUserIDCOL(TableColumn<Employee, String> userIDCOL) {
        this.userIDCOL = userIDCOL;
    }

    public Button getUserDeleteBTN() {
        return userDeleteBTN;
    }

    public void setUserDeleteBTN(Button userDeleteBTN) {
        this.userDeleteBTN = userDeleteBTN;
    }

    public Button getUserEditBTN() {
        return userEditBTN;
    }

    public void setUserEditBTN(Button userEditBTN) {
        this.userEditBTN = userEditBTN;
    }

    public CheckBox getReceiptToggle() {
        return receiptToggle;
    }

    public void setReceiptToggle(CheckBox receiptToggle) {
        this.receiptToggle = receiptToggle;
    }

    public VBox getMonthlyVBox() {
        return monthlyVBox;
    }

    public void setMonthlyVBox(VBox monthlyVBox) {
        this.monthlyVBox = monthlyVBox;
    }

    public Label getReceiptCodeMonthlyLBL() {
        return receiptCodeMonthlyLBL;
    }

    public void setReceiptCodeMonthlyLBL(Label receiptCodeMonthlyLBL) {
        this.receiptCodeMonthlyLBL = receiptCodeMonthlyLBL;
    }

    public TextField getReceiptCityMonthlyTF() {
        return receiptCityMonthlyTF;
    }

    public void setReceiptCityMonthlyTF(TextField receiptCityMonthlyTF) {
        this.receiptCityMonthlyTF = receiptCityMonthlyTF;
    }

    public Label getReceiptPaidValueMonthlyLBL() {
        return receiptPaidValueMonthlyLBL;
    }

    public void setReceiptPaidValueMonthlyLBL(Label receiptPaidValueMonthlyLBL) {
        this.receiptPaidValueMonthlyLBL = receiptPaidValueMonthlyLBL;
    }

    public DatePicker getReceiptDateMonthlyDTP() {
        return receiptDateMonthlyDTP;
    }

    public void setReceiptDateMonthlyDTP(DatePicker receiptDateMonthlyDTP) {
        this.receiptDateMonthlyDTP = receiptDateMonthlyDTP;
    }

    public TextField getReceiptClientNameMonthlyTF() {
        return receiptClientNameMonthlyTF;
    }

    public void setReceiptClientNameMonthlyTF(TextField receiptClientNameMonthlyTF) {
        this.receiptClientNameMonthlyTF = receiptClientNameMonthlyTF;
    }

    public Label getReceiptVehicleMonthlyLBL() {
        return receiptVehicleMonthlyLBL;
    }

    public void setReceiptVehicleMonthlyLBL(Label receiptVehicleMonthlyLBL) {
        this.receiptVehicleMonthlyLBL = receiptVehicleMonthlyLBL;
    }

    public Label getReceiptDaysMonthlyLBL() {
        return receiptDaysMonthlyLBL;
    }

    public void setReceiptDaysMonthlyLBL(Label receiptDaysMonthlyLBL) {
        this.receiptDaysMonthlyLBL = receiptDaysMonthlyLBL;
    }

    public VBox getDailyVBox() {
        return dailyVBox;
    }

    public void setDailyVBox(VBox dailyVBox) {
        this.dailyVBox = dailyVBox;
    }

    public ChoiceBox<String> getReceiptVehicleTypeCHB() {
        return receiptVehicleTypeCHB;
    }

    public void setReceiptVehicleTypeCHB(ChoiceBox<String> receiptVehicleTypeCHB) {
        this.receiptVehicleTypeCHB = receiptVehicleTypeCHB;
    }

    public DatePicker getReceiptDateDailyDTP() {
        return receiptDateDailyDTP;
    }

    public void setReceiptDateDailyDTP(DatePicker receiptDateDailyDTP) {
        this.receiptDateDailyDTP = receiptDateDailyDTP;
    }

    public Label getReceiptPaidValueDailyLBL() {
        return receiptPaidValueDailyLBL;
    }

    public void setReceiptPaidValueDailyLBL(Label receiptPaidValueDailyLBL) {
        this.receiptPaidValueDailyLBL = receiptPaidValueDailyLBL;
    }

    public TextField getReceiptPlateDailyTF() {
        return receiptPlateDailyTF;
    }

    public void setReceiptPlateDailyTF(TextField receiptPlateDailyTF) {
        this.receiptPlateDailyTF = receiptPlateDailyTF;
    }

    public TextField getReceiptHoursDailyTF() {
        return receiptHoursDailyTF;
    }

    public void setReceiptHoursDailyTF(TextField receiptHoursDailyTF) {
        this.receiptHoursDailyTF = receiptHoursDailyTF;
    }

    public BorderPane getReportPane() {
        return reportPane;
    }

    public void setReportPane(BorderPane reportPane) {
        this.reportPane = reportPane;
    }

    public DatePicker getReportFromDTP() {
        return reportFromDTP;
    }

    public void setReportFromDTP(DatePicker reportFromDTP) {
        this.reportFromDTP = reportFromDTP;
    }

    public DatePicker getReportToDTP() {
        return reportToDTP;
    }

    public void setReportToDTP(DatePicker reportToDTP) {
        this.reportToDTP = reportToDTP;
    }

    public TextField getReportFromHourTF() {
        return reportFromHourTF;
    }

    public void setReportFromHourTF(TextField reportFromHourTF) {
        this.reportFromHourTF = reportFromHourTF;
    }

    public TextField getReportToHourTF() {
        return reportToHourTF;
    }

    public void setReportToHourTF(TextField reportToHourTF) {
        this.reportToHourTF = reportToHourTF;
    }

    public ChoiceBox<String> getReportTypeCHB() {
        return reportTypeCHB;
    }

    public void setReportTypeCHB(ChoiceBox<String> reportTypeCHB) {
        this.reportTypeCHB = reportTypeCHB;
    }

    public Label getDialMessageLBL() {
        return dialMessageLBL;
    }

    public void setDialMessageLBL(Label dialMessageLBL) {
        this.dialMessageLBL = dialMessageLBL;
    }

    public Button getDialDismissBTN() {
        return dialDismissBTN;
    }

    public void setDialDismissBTN(Button dialDismissBTN) {
        this.dialDismissBTN = dialDismissBTN;
    }

    public ParkingLot getLaCeiba() {
        return laCeiba;
    }

    public void setLaCeiba(ParkingLot laCeiba) {
        this.laCeiba = laCeiba;
    }

    public EmergentWindowsGUI getEmergentWindowsController() {
        return emergentWindowsController;
    }

    public void setEmergentWindowsController(EmergentWindowsGUI emergentWindowsController) {
        this.emergentWindowsController = emergentWindowsController;
    }

    public String getCurrentScene() {
        return currentScene;
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<Thread> threads) {
        this.threads = threads;
    }

    public int getiLoveThisnumber() {
        return iLoveThisnumber;
    }

    public void setiLoveThisnumber(int iLoveThisnumber) {
        this.iLoveThisnumber = iLoveThisnumber;
    }
}
