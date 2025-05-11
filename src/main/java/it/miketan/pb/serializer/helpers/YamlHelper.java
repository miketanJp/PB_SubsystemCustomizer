package it.miketan.pb.serializer.helpers;

import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class YamlHelper {

    private static Map<String, Object> createYamlNesting(Integer value) {

        Map<String, Object> nestedValue = new LinkedHashMap<>();
        nestedValue.put("value", value);
        nestedValue.put("targetMode", 0);
        nestedValue.put("targetSocket", "");
        nestedValue.put("targetHardpoint", "");
        return nestedValue;
    }

    public static void load (File file, TextField actCountField, TextField actDurationField, TextField heatField, TextField massField, TextField scrapValueField,
                            TextField wpnConcussionField, TextField wpnDamageField, TextField wpnDamageRadiusField, TextField wpnImpactField,
                            TextField wpnImpactRadiusField, TextField wpnProjLifeTimeField, TextField wpnProjRicochetField,
                            TextField wpnRangeMaxField, TextField wpnRangeMinField, TextField wpnScatterAngleField,
                            TextField wpnScatterAngleMovingField, TextField wpnSpeedField, Text hiddenText) throws NumberFormatException {

        try (InputStream input = new FileInputStream(file)) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(input);

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                Object value = entry.getValue();

                if (value instanceof Map) {
                    Map<String, Object> nestedMap = (Map<String, Object>) value;
                    for (Map.Entry<String, Object> nestedEntry : nestedMap.entrySet()) {
                        String nestedFieldName = nestedEntry.getKey();
                        Object nestedValue = nestedEntry.getValue();

                            /* UNCOMMENT TO DEBUG
                            if (nestedFieldName != null) {
                                System.out.println("Value detected");
                                System.out.println("Nested Field Name: " + nestedFieldName);
                                System.out.println("Nested" + nestedFieldName + " value: " + ((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                            } else {
                                System.out.println("value not detected!");
                            }*/

                        if (nestedValue instanceof LinkedHashMap) {

                            switch (Objects.requireNonNull(nestedFieldName)) {
                                case "act_count" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        actCountField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }
                                case "act_duration" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        actDurationField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }
                                case "act_heat" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        heatField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "mass" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        massField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "scrap_value" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        scrapValueField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_concussion" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnConcussionField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_damage" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnDamageField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_damage_radius" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnDamageRadiusField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_impact" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnImpactField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_impact_radius" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnImpactRadiusField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_proj_lifetime" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnProjLifeTimeField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_proj_ricochet" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnProjRicochetField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_range_max" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnRangeMaxField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_range_min" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnRangeMinField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_scatter_angle" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnScatterAngleField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_scatter_angle_moving" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnScatterAngleMovingField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }

                                case "wpn_speed" -> {
                                    if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                        wpnSpeedField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                                    }
                                }
                            }
                        }
                        // UNCOMMENT TO DEBUG.
                        // System.out.println("Nested Value: " + nestedValue);
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
                              TextField wpnScatterAngleMovingField, TextField wpnSpeedField, Text hiddenText) throws NumberFormatException {

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

        nestedTags.put("tags", "");
        nestedStatDistribution.put("statDistribution", "");
        statsRoot.put("stats", nestedStats);

        try (FileWriter writer = new FileWriter(file + ".yaml")) {
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
                                         TextField wpnScatterAngleMovingField, TextField wpnSpeedField, Text hiddenText) throws NumberFormatException {

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
