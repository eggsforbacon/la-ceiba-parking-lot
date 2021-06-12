package threads;

import ui.MainGUI;

/**
 * The thread in charge of the login interface. <br>
 */
public class LoginThread extends Thread {
    private MainGUI m;

    /**
     * The main constructor of the class. <br>
     */
    public LoginThread(MainGUI m) {
        this.m = m;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (!m.getCurrentSceneController().isLoginSuccessful()) {
            pause();
            if (m.getCurrentSceneController().isLoginSuccessful()) {
                m.toggleButtons(false);
            }
        }
    }

    /**
     * Sleeps the thread for 2 milliseconds. <br>
     */
    private void pause() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
