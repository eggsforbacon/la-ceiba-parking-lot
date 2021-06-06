package threads;

import javafx.application.Platform;
import model.PreloaderBar;
import ui.PreloaderGUI2;

public class PreloaderThread extends Thread {
    PreloaderBar progressBar;
    PreloaderGUI2 preloader;

    public PreloaderThread(PreloaderBar bar, PreloaderGUI2 controller) {
        progressBar = bar;
        preloader = controller;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void run() {
        int LOADING_TIME_INTERVAL = 3;
        while (progressBar.isActive()) {
            progressBar.doProgress();
            Platform.runLater(new Thread(() -> preloader.loadBar()));
            wait(LOADING_TIME_INTERVAL);
        }
        Platform.runLater(new Thread(() -> preloader.postLoaded()));
    }

    /**
     * Sleeps the thread for a specified amount of milliseconds. <br>
     * @param millis The amount of milliseconds. @NotNeg. <br>
     * */
    private void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
