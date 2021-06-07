package threads;

import ui.MainGUI;

public class LoginThread extends Thread{
    private MainGUI m;

    public LoginThread(MainGUI m){
        this.m=m;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void run() {
        while(m.getCurrentSceneController().isLoginSuccessful() != true){
            wait(2);
            if(m.getCurrentSceneController().isLoginSuccessful() == true){
                m.toggleButtons(false);
            }
        }
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
