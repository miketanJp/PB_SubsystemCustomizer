package it.miketan.pb.serializer.utils;

import javafx.scene.control.TextField;

/**
 * Utility class for TextField filtering logic.
 *
 * @author Miketan
 * @version 1.1.0
 */
public class FiltersUtil {
    public static void addDigitFilter(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                field.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }
}
