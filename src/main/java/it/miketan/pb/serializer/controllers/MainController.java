package it.miketan.pb.serializer.controllers;

import it.miketan.pb.serializer.helpers.YamlHelper;
import it.miketan.pb.serializer.utils.FiltersUtil;
import it.miketan.pb.serializer.utils.TooltipsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


non-sealed public class MainController implements IController {

    @FXML
    private Text versionText;
    @FXML
    private Text hiddenText;
    @FXML
    private TextField actCountField;
    @FXML
    private TextField actDurationField;
    @FXML
    private TextField heatField;
    @FXML
    private TextField massField;
    @FXML
    private TextField scrapValueField;
    @FXML
    private TextField wpnConcussionField;
    @FXML
    private TextField wpnDamageField;
    @FXML
    private TextField wpnDamageRadiusField;
    @FXML
    private TextField wpnImpactField;
    @FXML
    private TextField wpnImpactRadiusField;
    @FXML
    private TextField wpnProjLifeTimeField;
    @FXML
    private TextField wpnProjRicochetField;
    @FXML
    private TextField wpnRangeMaxField;
    @FXML
    private TextField wpnRangeMinField;
    @FXML
    private TextField wpnScatterAngleField;
    @FXML
    private TextField wpnScatterAngleMovingField;
    @FXML
    private TextField wpnSpeedField;

    @FXML
    public void initialize() {

        //Load property values
        try (var inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);

                //Strings for property values
                String versionValue = properties.getProperty("versionValue");

                //Set the text to FXML
                versionText.setText("v" + versionValue);

            } else {
                versionText.setText("Version: N/A");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Put K,V on a Hashmap and then iterate it for each field tooltip.
        Map<TextField, String> tooltips = new HashMap<>();
        tooltips.put(actCountField, "How many projectiles can fire each use.");
        tooltips.put(actDurationField, "Duration of subsystem use in the timeline.");
        tooltips.put(heatField, "Heat generated each use. More heat generated means it gets faster to overheat.");
        tooltips.put(massField, "The weight of the subsystem. The higher, the heavier.");
        tooltips.put(scrapValueField, "The value of the subsystem if scrapped either in battle or base inventory.");
        tooltips.put(wpnConcussionField, "Concussion damage each projectile. Higher value means each projectile cause lots of concussion damage before neutralizing the enemy unit.");
        tooltips.put(wpnDamageField, "Base damage each projectile. Higher value translates into high base damage.");
        tooltips.put(wpnDamageRadiusField, "Damage radius. Higher radius may help in creating weapons based on AoE-style damage.");
        tooltips.put(wpnImpactField, "Damage applied to environment (e.g. buildings, trees, etc.");
        tooltips.put(wpnImpactRadiusField, "Impact radius. The higher, the wider impact value will be applied.");
        tooltips.put(wpnProjLifeTimeField, "Projectile's lifespan before expiration, either by exploding or fading up.");
        tooltips.put(wpnProjRicochetField, "Projectile ricochet. Higher value will increase the chance to ricochet when hitting the surface.");
        tooltips.put(wpnRangeMaxField, "Subsystem maximum range. The higher, the far you can hit. Useful for ML and sniper rifles.");
        tooltips.put(wpnRangeMinField, "Subsystem minimum range from which you can fire. Higher value means you can't hit at close range. Lowering the value is ideal for CQC firearms.");
        tooltips.put(wpnScatterAngleField, "Projectile dispersion angle when firing in Idle (expressed in Angle degree). Higher value makes projectiles dispersed in a wider angle, good especially for shotguns.");
        tooltips.put(wpnScatterAngleMovingField, "Projectile dispersion angle when moving (expressed in Angle degree).");
        tooltips.put(wpnSpeedField, "Projectile speed. Higher value makes them travel very fast. Useful for railgun-style weapons for instance.");

        tooltips.forEach(TooltipsUtil::initializeTooltip);
    }

    @FXML
    protected void onLoadBtnClick() throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Subsystem");

        File file = fc.showOpenDialog(null);

        if (file != null) {
            YamlHelper.loadYaml(file, actCountField, actDurationField, heatField, massField, scrapValueField,
                    wpnConcussionField, wpnDamageField, wpnDamageRadiusField, wpnImpactField,
                    wpnImpactRadiusField, wpnProjLifeTimeField, wpnProjRicochetField,
                    wpnRangeMaxField, wpnRangeMinField, wpnScatterAngleField,
                    wpnScatterAngleMovingField, wpnSpeedField, hiddenText);
        }
    }

    @FXML
    protected void onSaveBtnClick() throws NumberFormatException {

        FileChooser fc = new FileChooser();
        fc.setTitle("Save Subsystem");

        File file = fc.showSaveDialog(null);

        if (file != null) {
            YamlHelper.saveYaml(file, actCountField, actDurationField, heatField, massField, scrapValueField,
                    wpnConcussionField, wpnDamageField, wpnDamageRadiusField, wpnImpactField,
                    wpnImpactRadiusField, wpnProjLifeTimeField, wpnProjRicochetField,
                    wpnRangeMaxField, wpnRangeMinField, wpnScatterAngleField,
                    wpnScatterAngleMovingField, wpnSpeedField, hiddenText);
        }
    }

    @FXML
    protected void copyToClipboard() throws NumberFormatException {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //Alert error = new Alert(Alert.AlertType.ERROR);

        if (clipboard != null) {
            YamlHelper.fieldsToClipboard(clipboard, clipboardContent, actCountField, actDurationField, heatField, massField, scrapValueField,
                    wpnConcussionField, wpnDamageField, wpnDamageRadiusField, wpnImpactField,
                    wpnImpactRadiusField, wpnProjLifeTimeField, wpnProjRicochetField,
                    wpnRangeMaxField, wpnRangeMinField, wpnScatterAngleField,
                    wpnScatterAngleMovingField, wpnSpeedField, hiddenText);
        }
    }

    @FXML
    protected void onInput() {

        List<TextField> textFields = List.of(
                actCountField, actDurationField, heatField, massField, scrapValueField,
                wpnConcussionField, wpnDamageField, wpnDamageRadiusField, wpnImpactField,
                wpnImpactRadiusField, wpnProjLifeTimeField, wpnProjRicochetField,
                wpnRangeMaxField, wpnRangeMinField, wpnScatterAngleField,
                wpnScatterAngleMovingField, wpnSpeedField
        );

        textFields.forEach(FiltersUtil::addDigitFilter);
    }
}