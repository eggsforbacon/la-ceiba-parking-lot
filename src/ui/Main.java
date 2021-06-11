package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Employee;
import model.ParkingLot;

import java.io.*;
import java.util.Objects;

public class Main extends Application {
    PreloaderGUI gui;
    ParkingLot laCeiba;

    public Main(){
        laCeiba = new ParkingLot();
        gui = new PreloaderGUI(laCeiba);
    }
	/**
     * @param args The arguments for the compiler and the JavaVM. <br>
     * */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/splash-screen.fxml"));
        fxmlLoader.setController(gui);
        Parent root = fxmlLoader.load();
        Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void stop() {
        try {
            PrintWriter pw = new PrintWriter("data/Serializable/plain_text/Employees.txt");
            for(int i=0;i<laCeiba.getEmployeesPL().size();i++){
                Employee employee = laCeiba.getEmployeesPL().get(i);
                pw.println(employee.getName()+";"+employee.getId()+";"+employee.getUsername()+";"+employee.getPassword());
            }
            pw.close();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Serializable/plain_text/data.1jz"));
            oos.writeObject(laCeiba);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }
}
