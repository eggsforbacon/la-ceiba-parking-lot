package threads;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.EmergentWindowsGUI;

/**
 * The thread in charge of the choice-boxes of the program
 */
public class ChoiceBoxThread extends Thread {

    private EmergentWindowsGUI e;
    private String choice = "";

    public ChoiceBoxThread(EmergentWindowsGUI e) {
        this.e = e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (true) {
            pause();
            ObservableList<Integer> dummy;
            switch (e.getVehicleType()) {
                case "AUTOMOVIL":
                    if (!choice.equals("AUTOMOVIL")) {
                        dummy = FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
                        e.getNewVehicleSeatCHB().setItems(dummy);
                        choice = "AUTOMOVIL";
                    }
                    break;
                case "MOTO":
                    if (!choice.equals("MOTO")) {
                        dummy = FXCollections.observableArrayList(-10, -9, -8, -7, -6, -5, -4, -3, -2, -1);
                        e.getNewVehicleSeatCHB().setItems(dummy);
                        choice = "MOTO";
                    }
                    break;
                case "CAMION":
                case "BUS":
                case "FURGON":
                case "CAMIONETA":
                    if (!choice.equals("GRANDE")) {
                        dummy = FXCollections.observableArrayList(21, 22, 23, 24, 25, 26, 27, 28, 29);
                        e.getNewVehicleSeatCHB().setItems(dummy);
                        e.getNewVehicleSeatCHB().hide();
                        choice = "GRANDE";
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Sleeps the thread for 1000 milliseconds. <br>
     */
    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
