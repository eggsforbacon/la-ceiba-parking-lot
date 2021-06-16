package ui.skins;

import com.jfoenix.transitions.JFXFillTransition;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BlueButtonSkin extends ButtonSkin {

    public BlueButtonSkin(Button button) {
        super(button);
        Color buttonColor = new Color(0.32549,0.42745,0.996078,1);
        Color onHoverColor = new Color(1,1,1,1);
        button.setOnMouseEntered(se -> {
            JFXFillTransition colorEaseIn = new JFXFillTransition();
            colorEaseIn.setDuration(Duration.millis(200));
            colorEaseIn.setRegion(button);
            colorEaseIn.setFromValue(buttonColor);
            colorEaseIn.setToValue(onHoverColor);
            colorEaseIn.play();
        });

        button.setOnMouseExited(e -> {
            JFXFillTransition colorEaseOut = new JFXFillTransition();
            colorEaseOut.setDuration(Duration.millis(700));
            colorEaseOut.setRegion(button);
            colorEaseOut.setFromValue(onHoverColor);
            colorEaseOut.setToValue(buttonColor);
            colorEaseOut.play();
        });
    }
}
