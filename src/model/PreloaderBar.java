package model;

public class PreloaderBar {
    public final double LOADED_WIDTH = 805.0;
    private double barWidth;
    private boolean active;

    /**
     * Initializes a Preloader Bar object. <br>
     * */
    public PreloaderBar() {
        barWidth = 0.0;
        active = true;
    }

    /**
     * Advances the progress bar by a percentage value relative to the width of the bar. <br>
     * */
    public void doProgress() {
        if (barWidth >= LOADED_WIDTH) active = false;
        else barWidth++;
    }

    /*Getters*/

    /**
     * @return The current width value of the Preloader Bar. <br>
     * */
    public double getBarWidth() {
        return barWidth;
    }

    /**
     * @return The current status of the Preloader Bar. <br>
     * */
    public boolean isActive() {
        return active;
    }

    /*Setters*/

    /**
     * @param barWidth The value of the progress to be set to. <br>
     * */
    public void setBarWidth(double barWidth) {
        this.barWidth = barWidth;
    }

    /**
     * @param active The status to be toggled to. <br>
     * */
    public void setActive(boolean active) {
        this.active = active;
    }
}
