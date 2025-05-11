package it.miketan.pb.serializer.utils;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * Utility class tasked for tooltips-on-textfield logic when hovering over TextField.
 *
 * @author Miketan
 * @version 1.1.0
 */
public class TooltipsUtil {

    public static void initializeTooltip(TextField textfield, String s) {
        textfield.setText("0");
        Tooltip tooltip = new Tooltip(s);
        Tooltip.install(textfield, tooltip);
    }
}
