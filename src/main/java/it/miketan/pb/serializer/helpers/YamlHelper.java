package it.miketan.pb.serializer.helpers;

import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * YamlHelper is a utility class for YAML-related operations.
 * It provides methods to load, export, copy YAML data to the clipboard and so on.
 *
 * @author Michele Paolucci (Miketan)
 * @version 1.2.0
 */
public class YamlHelper {

    private static Map<String, Object> createYamlNesting(Integer value) {

        Map<String, Object> nestedValue = new LinkedHashMap<>();
        nestedValue.put("value", value);
        nestedValue.put("targetMode", 0);
        nestedValue.put("targetSocket", "");
        nestedValue.put("targetHardpoint", "");
        return nestedValue;
    }

    public static void load(File file, Map<String, TextField> fieldsMap, Group railgunStats, Text hiddenText) throws NumberFormatException {
        try (InputStream input = new FileInputStream(file)) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(input);

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                Object value = entry.getValue();

                if (value instanceof Map) {

                    Map<String, Object> nestedMap = (Map<String, Object>) value;

                    for (Map.Entry<String, Object> nestedEntry : nestedMap.entrySet()) {
                        String nestedFieldName = nestedEntry.getKey();
                        LinkedHashMap nestedValue = (LinkedHashMap) nestedEntry.getValue();

                        //Needs to manipulate the value to prevent importing alphabetical values.
                        if (nestedValue != null && fieldsMap.containsKey(nestedFieldName)) {
                            Object rawValue = nestedValue.get("value");
                            Integer parsedValue = rawValue instanceof String ? parseField(new TextField(rawValue.toString())) : (Integer) rawValue;
                            fieldsMap.get(nestedFieldName).setText(String.valueOf(parsedValue));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        hiddenText.setVisible(true);
        hiddenText.setText("File imported successfully.");

    }

    public static void export(File file, TextField actCountField, TextField actDurationField, TextField heatField, TextField massField, TextField scrapValueField,
                              TextField wpnConcussionField, TextField wpnDamageField, TextField wpnDamageRadiusField, TextField wpnImpactField,
                              TextField wpnImpactRadiusField, TextField wpnProjLifeTimeField, TextField wpnProjRicochetField,
                              TextField wpnRangeMaxField, TextField wpnRangeMinField, TextField wpnScatterAngleField,
                              TextField wpnScatterAngleMovingField, TextField wpnSpeedField, TextField penetrationChargesField,
                              TextField penetrationDamageKField, TextField penetrationGeomCostField, TextField penetrationUnitCostField, Group railgunStats, Text hiddenText) throws NumberFormatException {

        //DumperOption controls YAML indentation Style
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setPrettyFlow(true);
        Yaml yaml = new Yaml(dumperOptions);

        Map<String, Object> tagsRoot = new LinkedHashMap<>();
        Map<String, Object> nestedTags = new LinkedHashMap<>();

        Map<String, Object> statDistributionRoot = new LinkedHashMap<>();
        Map<String, Object> nestedStatDistribution = new LinkedHashMap<>();

        Map<String, Object> statsRoot = new LinkedHashMap<>();
        Map<String, Object> nestedStats = new LinkedHashMap<>();
        nestedStats.put("act_count", createYamlNesting(parseField(actCountField)));
        nestedStats.put("act_duration", createYamlNesting(parseField(actDurationField)));
        nestedStats.put("act_heat", createYamlNesting(parseField(heatField)));
        nestedStats.put("mass", createYamlNesting(parseField(massField)));
        nestedStats.put("scrap_value", createYamlNesting(parseField(scrapValueField)));
        nestedStats.put("wpn_concussion", createYamlNesting(parseField(wpnConcussionField)));
        nestedStats.put("wpn_damage", createYamlNesting(parseField(wpnDamageField)));
        nestedStats.put("wpn_damage_radius", createYamlNesting(parseField(wpnDamageRadiusField)));
        nestedStats.put("wpn_impact", createYamlNesting(parseField(wpnImpactField)));
        nestedStats.put("wpn_impact_radius", createYamlNesting(parseField(wpnImpactRadiusField)));
        nestedStats.put("wpn_proj_lifetime", createYamlNesting(parseField(wpnProjLifeTimeField)));
        nestedStats.put("wpn_proj_ricochet", createYamlNesting(parseField(wpnProjRicochetField)));
        nestedStats.put("wpn_range_max", createYamlNesting(parseField(wpnRangeMaxField)));
        nestedStats.put("wpn_range_min", createYamlNesting(parseField(wpnRangeMinField)));
        nestedStats.put("wpn_scatter_angle", createYamlNesting(parseField(wpnScatterAngleField)));
        nestedStats.put("wpn_scatter_angle_moving", createYamlNesting(parseField(wpnScatterAngleMovingField)));
        nestedStats.put("wpn_speed", createYamlNesting(parseField(wpnSpeedField)));

        // Railgun stats exposed to serialization if the group container is visible.
        if (railgunStats.isVisible()) {
            nestedStats.put("wpn_penetration_charges", createYamlNesting(parseField(penetrationChargesField)));
            nestedStats.put("wpn_penetration_damagek", createYamlNesting(parseField(penetrationDamageKField)));
            nestedStats.put("wpn_penetration_geomcost", createYamlNesting(parseField(penetrationGeomCostField)));
            nestedStats.put("wpn_penetration_unitcost", createYamlNesting(parseField(penetrationUnitCostField)));
        }

        nestedTags.put("tags", "");
        nestedStatDistribution.put("statDistribution", "");
        statsRoot.put("stats", nestedStats);

        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(nestedTags, writer);
            yaml.dump(nestedStatDistribution, writer);
            yaml.dump(statsRoot, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // UNCOMMENT TO DEBUG
        // System.out.println(STR."created: \{file}.yaml");

        hiddenText.setVisible(true);
        hiddenText.setText("File saved successfully.");
    }

    public static void fieldsToClipboard(Clipboard clipboard, ClipboardContent clipboardContent, TextField actCountField, TextField actDurationField, TextField heatField, TextField massField, TextField scrapValueField,
                                         TextField wpnConcussionField, TextField wpnDamageField, TextField wpnDamageRadiusField, TextField wpnImpactField,
                                         TextField wpnImpactRadiusField, TextField wpnProjLifeTimeField, TextField wpnProjRicochetField,
                                         TextField wpnRangeMaxField, TextField wpnRangeMinField, TextField wpnScatterAngleField,
                                         TextField wpnScatterAngleMovingField, TextField wpnSpeedField, TextField penetrationChargesField,
                                         TextField penetrationDamageKField, TextField penetrationGeomCostField, TextField penetrationUnitCostField, Group railgunStats, Text hiddenText) throws NumberFormatException {

        // DumperOption controls YAML indentation Style
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setPrettyFlow(true);
        Yaml yaml = new Yaml(dumperOptions);

        // Yaml structure creation.
        Map<String, Object> statsRoot = new LinkedHashMap<>();
        Map<String, Object> nestedStats = new LinkedHashMap<>();

        nestedStats.put("act_count", createYamlNesting(parseField(actCountField)));
        nestedStats.put("act_duration", createYamlNesting(parseField(actDurationField)));
        nestedStats.put("act_heat", createYamlNesting(parseField(heatField)));
        nestedStats.put("mass", createYamlNesting(parseField(massField)));
        nestedStats.put("scrap_value", createYamlNesting(parseField(scrapValueField)));
        nestedStats.put("wpn_concussion", createYamlNesting(parseField(wpnConcussionField)));
        nestedStats.put("wpn_damage", createYamlNesting(parseField(wpnDamageField)));
        nestedStats.put("wpn_damage_radius", createYamlNesting(parseField(wpnDamageRadiusField)));
        nestedStats.put("wpn_impact", createYamlNesting(parseField(wpnImpactField)));
        nestedStats.put("wpn_impact_radius", createYamlNesting(parseField(wpnImpactRadiusField)));
        nestedStats.put("wpn_proj_lifetime", createYamlNesting(parseField(wpnProjLifeTimeField)));
        nestedStats.put("wpn_proj_ricochet", createYamlNesting(parseField(wpnProjRicochetField)));
        nestedStats.put("wpn_range_max", createYamlNesting(parseField(wpnRangeMaxField)));
        nestedStats.put("wpn_range_min", createYamlNesting(parseField(wpnRangeMinField)));
        nestedStats.put("wpn_scatter_angle", createYamlNesting(parseField(wpnScatterAngleField)));
        nestedStats.put("wpn_scatter_angle_moving", createYamlNesting(parseField(wpnScatterAngleMovingField)));
        nestedStats.put("wpn_speed", createYamlNesting(parseField(wpnSpeedField)));

        // Railgun stats exposed to clipboard if the group container is visible.
        if (railgunStats.isVisible()) {
            nestedStats.put("wpn_penetration_charges", createYamlNesting(parseField(penetrationChargesField)));
            nestedStats.put("wpn_penetration_damagek", createYamlNesting(parseField(penetrationDamageKField)));
            nestedStats.put("wpn_penetration_geomcost", createYamlNesting(parseField(penetrationGeomCostField)));
            nestedStats.put("wpn_penetration_unitcost", createYamlNesting(parseField(penetrationUnitCostField)));
        }

        statsRoot.put("stats", nestedStats);

        clipboardContent.putString(yaml.dump(statsRoot));
        clipboard.setContent(clipboardContent);

        hiddenText.setVisible(true);
        hiddenText.setText("Copied successfully to clipboard.");
    }

    // Utility method to parse the field value. It prevents NumberFormatException from happening if one or more fields are empty.
    private static Integer parseField(TextField field) {

        if (field.getText().isBlank() || field.getText().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(field.getText());
        }

    }
}
