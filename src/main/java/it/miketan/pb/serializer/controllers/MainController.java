package it.miketan.pb.serializer.controllers;

import it.miketan.pb.serializer.helpers.YamlHelper;
import it.miketan.pb.serializer.utils.FiltersUtil;
import it.miketan.pb.serializer.utils.TooltipsUtil;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
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
    private ImageView imageView;

    @FXML
    public void initialize() {

        //Load the version from the properties file
        try (var inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);

                //Strings for properties values
                String versionValue = properties.getProperty("versionValue");

                //Set the text to FXML
                versionText.setText("v" + versionValue);

            } else {
                versionText.setText("Version: N/A");
            }
        } catch (IOException e) {
            versionText.setText("Errore nel caricamento della versione");
        }


        //Initialize the tooltips and fill the fields with 0 as default value for each one of them.
        TooltipsUtil.initializeTooltip(actCountField, "how many projectiles can fire each use.");
        TooltipsUtil.initializeTooltip(actDurationField, "Duration of subsystem use in the timeline.");
        TooltipsUtil.initializeTooltip(heatField, "Heat generated each use. More heat generated means it gets faster to overheat.");
        TooltipsUtil.initializeTooltip(massField, "The weight of the subsystem. The higher, the heavier.");
        TooltipsUtil.initializeTooltip(scrapValueField, "the value of the subsystem if scrapped either in battle or base inventory.");
        TooltipsUtil.initializeTooltip(wpnConcussionField, "Concussion damage each projectile. Higher value means each projectile cause lots of concussion damage before neutralizing the enemy unit.");
        TooltipsUtil.initializeTooltip(wpnDamageField, "Base damage each projectile. Higher value translates into high base damage.");
        TooltipsUtil.initializeTooltip(wpnDamageRadiusField, "Damage radius. Higher radius may help in creating weapons based on AoE-style damage.");
        TooltipsUtil.initializeTooltip(wpnImpactField, "Damage applied to environment (e.g. buildings, trees, etc.");
        TooltipsUtil.initializeTooltip(wpnImpactRadiusField, "Impact radius. The higher, the wider impact value will be applied.");
        TooltipsUtil.initializeTooltip(wpnProjLifeTimeField, "Projectile's lifespan before expiration, either by exploding or fading up.");
        TooltipsUtil.initializeTooltip(wpnProjRicochetField, "Projectile ricochet. Higher value will increase the chance to ricochet when hitting the surface.");
        TooltipsUtil.initializeTooltip(wpnRangeMaxField, "Subsystem maximum range. The higher, the far you can hit. Useful for ML and sniper rifles.");
        TooltipsUtil.initializeTooltip(wpnRangeMinField, "Subsystem minimum range from which you can fire. Higher value means you can't hit at close range. Lowering the value is ideal for CQC firearms.");
        TooltipsUtil.initializeTooltip(wpnScatterAngleField, "Projectile dispersion angle when firing in Idle (expressed in Angle degree). Higher value makes projectiles dispersed in a wider angle, good especially for shotguns.");
        TooltipsUtil.initializeTooltip(wpnScatterAngleMovingField, "Projectile dispersion angle when moving (expressed in Angle degree).");
        TooltipsUtil.initializeTooltip(wpnSpeedField, "Projectile speed. Higher value makes them travel very fast. Useful for railgun-style weapons for instance.");
    }

    protected void displayImage() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("pb_logo.png")));
        imageView.setImage(image);
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
    protected void copyToClipboard() {
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
        FiltersUtil.addDigitFilter(actCountField);
        FiltersUtil.addDigitFilter(actDurationField);
        FiltersUtil.addDigitFilter(heatField);
        FiltersUtil.addDigitFilter(massField);
        FiltersUtil.addDigitFilter(scrapValueField);
        FiltersUtil.addDigitFilter(wpnConcussionField);
        FiltersUtil.addDigitFilter(wpnDamageField);
        FiltersUtil.addDigitFilter(wpnDamageRadiusField);
        FiltersUtil.addDigitFilter(wpnImpactField);
        FiltersUtil.addDigitFilter(wpnImpactRadiusField);
        FiltersUtil.addDigitFilter(wpnProjLifeTimeField);
        FiltersUtil.addDigitFilter(wpnProjRicochetField);
        FiltersUtil.addDigitFilter(wpnRangeMaxField);
        FiltersUtil.addDigitFilter(wpnRangeMinField);
        FiltersUtil.addDigitFilter(wpnScatterAngleField);
        FiltersUtil.addDigitFilter(wpnScatterAngleMovingField);
        FiltersUtil.addDigitFilter(wpnSpeedField);
    }

}