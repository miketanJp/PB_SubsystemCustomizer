package it.miketan.pb.serializer.controllers;

import it.miketan.pb.serializer.helpers.YamlHelper;
import it.miketan.pb.serializer.utils.FiltersUtil;
import it.miketan.pb.serializer.utils.TooltipsUtil;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

non-sealed public class MainController implements IController {

    //Group containers for stat types
    public Group basicStats;
    public Group railgunStats;

    //stats-unrelated fields
    @FXML
    private Text hiddenText;
    @FXML
    public CheckBox railgunStatschk;

    //Basic stats fields
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

    //Railgun-related stats fields
    @FXML
    private TextField penetrationChargesField;
    @FXML
    private TextField penetrationDamageKField;
    @FXML
    private TextField penetrationGeomCostField;
    @FXML
    private TextField penetrationUnitCostField;

    @FXML
    public void initialize() {

        Map<TextField, String> tooltips = new HashMap<>();

        //Put K,V on a Hashmap and then iterate it for each field tooltip.
        if (basicStats.isVisible()) {
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
        }

        //Railgun-related stats
        if (railgunStats.isVisible()) {
            tooltips.put(penetrationChargesField, "Penetration charges. Higher value means more charges are needed to penetrate the armor.");
            tooltips.put(penetrationDamageKField, "Penetration damage K. Higher value means more damage is needed to penetrate the armor.");
            tooltips.put(penetrationGeomCostField, "Penetration geometry cost. Higher value means more geometry is needed to penetrate the armor.");
            tooltips.put(penetrationUnitCostField, "Penetration unit cost. Higher value means more units are needed to penetrate the armor.");
        }

        //Tooltips for checkboxes
        if (railgunStatschk.isPressed() || railgunStats.isVisible()) {
            Map<CheckBox, String> chkTooltips = new HashMap<>();
            chkTooltips.put(railgunStatschk, "If checked, the railgun stats will be visible and exposed for export.");
            chkTooltips.forEach(TooltipsUtil::initializeChkTooltip);
        }

        tooltips.forEach(TooltipsUtil::initializeTooltip);
    }

    @FXML
    protected void onLoadBtnClick() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Subsystem");

        File file = fc.showOpenDialog(null);

        if (file != null) {
            Map<String, TextField> fieldsMap = Map.ofEntries(
                    Map.entry("act_count", actCountField),
                    Map.entry("act_duration", actDurationField),
                    Map.entry("act_heat", heatField),
                    Map.entry("mass", massField),
                    Map.entry("scrap_value", scrapValueField),
                    Map.entry("wpn_concussion", wpnConcussionField),
                    Map.entry("wpn_damage", wpnDamageField),
                    Map.entry("wpn_damage_radius", wpnDamageRadiusField),
                    Map.entry("wpn_impact", wpnImpactField),
                    Map.entry("wpn_impact_radius", wpnImpactRadiusField),
                    Map.entry("wpn_proj_lifetime", wpnProjLifeTimeField),
                    Map.entry("wpn_proj_ricochet", wpnProjRicochetField),
                    Map.entry("wpn_range_max", wpnRangeMaxField),
                    Map.entry("wpn_range_min", wpnRangeMinField),
                    Map.entry("wpn_scatter_angle", wpnScatterAngleField),
                    Map.entry("wpn_scatter_angle_moving", wpnScatterAngleMovingField),
                    Map.entry("wpn_speed", wpnSpeedField),
                    Map.entry("wpn_penetration_charges", penetrationChargesField),
                    Map.entry("wpn_penetration_damagek", penetrationDamageKField),
                    Map.entry("wpn_penetration_geomcost", penetrationGeomCostField),
                    Map.entry("wpn_penetration_unitcost", penetrationUnitCostField));

            YamlHelper.load(file, fieldsMap, railgunStats, hiddenText);
        }
    }

    @FXML
    protected void onSaveBtnClick() throws NumberFormatException {

        //Initialize the file chooser with filter to only save in .YAML
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("YAML files (*.yaml)", "*.yaml");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fc.setTitle("Save Subsystem");
        fc.getExtensionFilters().add(extFilter);

        File file = fc.showSaveDialog(null);

        if (file != null) {

            if(!file.getName().toLowerCase().endsWith(".yaml")) {
                file = new File(file.getAbsolutePath() + ".yaml");
            }

            YamlHelper.export(file, actCountField, actDurationField, heatField, massField, scrapValueField,
                    wpnConcussionField, wpnDamageField, wpnDamageRadiusField, wpnImpactField,
                    wpnImpactRadiusField, wpnProjLifeTimeField, wpnProjRicochetField,
                    wpnRangeMaxField, wpnRangeMinField, wpnScatterAngleField,
                    wpnScatterAngleMovingField, wpnSpeedField, penetrationChargesField,
                    penetrationDamageKField, penetrationGeomCostField, penetrationUnitCostField, railgunStats, hiddenText);
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
                    wpnScatterAngleMovingField, wpnSpeedField, penetrationChargesField,
                    penetrationDamageKField, penetrationGeomCostField, penetrationUnitCostField, railgunStats, hiddenText);
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

    public void onClickChk() {

        //If the checkbox is selected, show the railgun stats
        if (railgunStatschk.isSelected()) {
            railgunStats.setVisible(true);
            railgunStatschk.setSelected(true);
        } else {
            railgunStats.setVisible(false);
            railgunStatschk.setSelected(false);
        }

    }

    public void onResetBtnClick() {
        //Reset all fields to default values
        List<TextField> textFields = List.of(
                actCountField, actDurationField, heatField, massField, scrapValueField,
                wpnConcussionField, wpnDamageField, wpnDamageRadiusField, wpnImpactField,
                wpnImpactRadiusField, wpnProjLifeTimeField, wpnProjRicochetField,
                wpnRangeMaxField, wpnRangeMinField, wpnScatterAngleField,
                wpnScatterAngleMovingField, wpnSpeedField
        );

        if (railgunStats.isVisible()) {
            List<TextField> railgunTextFields = List.of(
                    penetrationChargesField, penetrationDamageKField, penetrationGeomCostField, penetrationUnitCostField
            );
            railgunTextFields.forEach(field -> field.setText("0"));
        }

        textFields.forEach(field -> field.setText("0"));

        hiddenText.setVisible(true);
        hiddenText.setText("All stats reset to default values.");
    }
}