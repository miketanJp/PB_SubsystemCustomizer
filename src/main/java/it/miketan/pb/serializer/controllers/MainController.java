package it.miketan.pb.serializer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;


non-sealed public class MainController implements IController {

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

   //Fill text fields to 0
   @FXML
   public void initialize() {

      Tooltip tooltipActCount = new Tooltip("how many projectiles can fire each use.");
      actCountField.setText("0");
      Tooltip.install(actCountField, tooltipActCount);

      Tooltip tooltipactDuration = new Tooltip("Duration of subsystem use in the timeline.");
      actDurationField.setText("0");
      Tooltip.install(actDurationField, tooltipactDuration);

      Tooltip heatTooltip = new Tooltip("Heat generated each use. More heat generated means it gets faster to overheat.");
      heatField.setText("0");
      Tooltip.install(heatField, heatTooltip);

      Tooltip massTooltip = new Tooltip("The weight of the subsystem. The higher, the heavier.");
      massField.setText("0");
      Tooltip.install(massField, massTooltip);

      Tooltip scrapValueTooltip = new Tooltip("the value of the subsystem if scrapped either in battle or base inventory.");
      scrapValueField.setText("0");
      Tooltip.install(scrapValueField, scrapValueTooltip);

      Tooltip wpnConcussionTooltip = new Tooltip("Concussion damage each projectile. Higher value means each projectile cause lots of concussion damage before neutralizing the enemy unit.");
      wpnConcussionField.setText("0");
      Tooltip.install(wpnConcussionField, wpnConcussionTooltip);

      Tooltip wpnDamageTooltip = new Tooltip("Base damage each projectile. Higher value translates into high base damage.");
      wpnDamageField.setText("0");
      Tooltip.install(wpnDamageField, wpnDamageTooltip);

      Tooltip wpnDamageRadiusTooltip = new Tooltip("Damage radius. Higher radius may help in creating weapons based on AoE-style damage.");
      wpnDamageRadiusField.setText("0");
      Tooltip.install(wpnDamageRadiusField, wpnDamageRadiusTooltip);

      Tooltip wpnImpactTooltip = new Tooltip("Damage applied to environment (e.g. buildings, trees, etc.");
      wpnImpactField.setText("0");
      Tooltip.install(wpnImpactRadiusField, wpnImpactTooltip);

      Tooltip wpnImpactRadiusTooltip = new Tooltip("Impact radius. The higher, the wider impact value will be applied.");
      wpnImpactRadiusField.setText("0");
      Tooltip.install(wpnImpactRadiusField, wpnImpactRadiusTooltip);

      Tooltip wpnProjLifetimeTooltip = new Tooltip("Projectile's lifespan before expiration, either by exploding or fading up.");
      wpnProjLifeTimeField.setText("0");
      Tooltip.install(wpnProjLifeTimeField, wpnProjLifetimeTooltip);

      Tooltip wpnProjRicochetTooltip = new Tooltip("Projectile ricochet. Higher value will increase the chance to ricochet when hitting the surface.");
      wpnProjRicochetField.setText("0");
      Tooltip.install(wpnProjRicochetField, wpnProjRicochetTooltip);

      Tooltip wpnRangeMaxTooltip = new Tooltip("Subsystem maximum range. The higher, the far you can hit. Useful for ML and sniper rifles.");
      wpnRangeMaxField.setText("0");
      Tooltip.install(wpnRangeMaxField, wpnRangeMaxTooltip);

      Tooltip wpnRangeMinTooltip = new Tooltip("Subsystem minimum range from which you can fire. Higher value means you can't hit at close range. Lowering the value is ideal for CQC firearms.");
      wpnRangeMinField.setText("0");
      Tooltip.install(wpnRangeMinField, wpnRangeMinTooltip);

      Tooltip wpnScatterAngleTooltip = new Tooltip("Projectile dispersion angle when firing in Idle (expressed in Angle degree). Higher value makes projectiles dispersed in a wider angle, good especially for shotguns.");
      wpnScatterAngleField.setText("0");
      Tooltip.install(wpnScatterAngleField, wpnScatterAngleTooltip);

      Tooltip wpnScatterAngleMovingTooltip = new Tooltip("Projectile dispersion angle when moving (expressed in Angle degree).");
      wpnScatterAngleMovingField.setText("0");
      Tooltip.install(wpnScatterAngleMovingField, wpnScatterAngleMovingTooltip);

      Tooltip wpnSpeedTooltip = new Tooltip("Projectile speed. Higher value makes them travel very fast. Useful for railgun-style weapons for instance.");
      wpnSpeedField.setText("0");
      Tooltip.install(wpnSpeedField, wpnSpeedTooltip);
   }



   @FXML
   protected void onLoadBtnClick() throws IOException {
      FileChooser fc = new FileChooser();
      fc.setTitle("Open Subsystem");
      File file = fc.showOpenDialog(null);

      if (file != null) {
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

                     if (nestedFieldName != null) {
                        System.out.println("Value detected");
                        System.out.println("Nested Field Name: " + nestedFieldName);
                        System.out.println("Nested" + nestedFieldName + " value: " + ((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                     } else {
                        System.out.println("value not detected!");
                     }

                     if (nestedValue instanceof LinkedHashMap) {

                        switch (Objects.requireNonNull(nestedFieldName)) {
                           case "act_count" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 actCountField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }
                           case "act_duration" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 actDurationField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }
                           case "act_heat" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 heatField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "mass" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 massField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "scrap_value" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 scrapValueField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_concussion" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnConcussionField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_damage" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnDamageField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_damage_radius" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnDamageRadiusField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_impact" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnImpactField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_impact_radius" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnImpactRadiusField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_proj_lifetime" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnProjLifeTimeField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_proj_ricochet" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnProjRicochetField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_range_max" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnRangeMaxField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_range_min" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnRangeMinField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_scatter_angle" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnScatterAngleField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_scatter_angle_moving" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnScatterAngleMovingField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }

                           case "wpn_speed" -> {
                              if (((LinkedHashMap<?, ?>) nestedValue).containsKey("value")) {
                                 wpnSpeedField.setText(((LinkedHashMap<?, ?>) nestedValue).get("value").toString());
                              }
                              continue;
                           }
                        }
                     }
                     System.out.println("Nested Value: " + nestedValue);
                  }
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   @FXML
   protected void onSaveBtnClick() throws NumberFormatException {

      FileChooser fc = new FileChooser();
      fc.setTitle("Save Subsystem");
      File file = fc.showSaveDialog(null);

      if (file != null) {

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
            nestedStats.put("act_count", createNesting(Integer.parseInt(actCountField.getText())));
            nestedStats.put("act_duration", createNesting(Integer.parseInt(actDurationField.getText())));
            nestedStats.put("act_heat", createNesting(Integer.parseInt(heatField.getText())));
            nestedStats.put("mass", createNesting(Integer.parseInt(massField.getText())));
            nestedStats.put("scrap_value", createNesting(Integer.parseInt(scrapValueField.getText())));
            nestedStats.put("wpn_concussion", createNesting(Integer.parseInt(wpnConcussionField.getText())));
            nestedStats.put("wpn_damage", createNesting(Integer.parseInt(wpnDamageField.getText())));
            nestedStats.put("wpn_damage_radius", createNesting(Integer.parseInt(wpnDamageRadiusField.getText())));
            nestedStats.put("wpn_impact", createNesting(Integer.parseInt(wpnImpactField.getText())));
            nestedStats.put("wpn_impact_radius", createNesting(Integer.parseInt(wpnImpactRadiusField.getText())));
            nestedStats.put("wpn_proj_lifetime", createNesting(Integer.parseInt(wpnProjLifeTimeField.getText())));
            nestedStats.put("wpn_proj_ricochet", createNesting(Integer.parseInt(wpnProjRicochetField.getText())));
            nestedStats.put("wpn_range_max", createNesting(Integer.parseInt(wpnRangeMaxField.getText())));
            nestedStats.put("wpn_range_min", createNesting(Integer.parseInt(wpnRangeMinField.getText())));
            nestedStats.put("wpn_scatter_angle", createNesting(Integer.parseInt(wpnScatterAngleField.getText())));
            nestedStats.put("wpn_scatter_angle_moving", createNesting(Integer.parseInt(wpnScatterAngleMovingField.getText())));
            nestedStats.put("wpn_speed", createNesting(Integer.parseInt(wpnSpeedField.getText())));

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

            System.out.println("created: " + file+".yaml");
      }
   }

   private static Map<String, Object> createNesting(Integer value) {

      Map<String,Object> nestedValue = new LinkedHashMap<>();
         nestedValue.put("value", value);
         nestedValue.put("targetMode", 0);
         nestedValue.put("targetSocket", "");
         nestedValue.put("targetHardpoint", "");
         return nestedValue;
   }

   private void addDigitFilter(TextField field) {
      field.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.matches("\\d*")) {
            field.setText(newValue.replaceAll("[^\\d]", ""));
         }
      });
   }

   @FXML
   protected void copyToClipboard() {
      Clipboard clipboard = Clipboard.getSystemClipboard();
      ClipboardContent clipboardContent = new ClipboardContent();

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
      nestedStats.put("act_count", createNesting(Integer.parseInt(actCountField.getText())));
      nestedStats.put("act_duration", createNesting(Integer.parseInt(actDurationField.getText())));
      nestedStats.put("act_heat", createNesting(Integer.parseInt(heatField.getText())));
      nestedStats.put("mass", createNesting(Integer.parseInt(massField.getText())));
      nestedStats.put("scrap_value", createNesting(Integer.parseInt(scrapValueField.getText())));
      nestedStats.put("wpn_concussion", createNesting(Integer.parseInt(wpnConcussionField.getText())));
      nestedStats.put("wpn_damage", createNesting(Integer.parseInt(wpnDamageField.getText())));
      nestedStats.put("wpn_damage_radius", createNesting(Integer.parseInt(wpnDamageRadiusField.getText())));
      nestedStats.put("wpn_impact", createNesting(Integer.parseInt(wpnImpactField.getText())));
      nestedStats.put("wpn_impact_radius", createNesting(Integer.parseInt(wpnImpactRadiusField.getText())));
      nestedStats.put("wpn_proj_lifetime", createNesting(Integer.parseInt(wpnProjLifeTimeField.getText())));
      nestedStats.put("wpn_proj_ricochet", createNesting(Integer.parseInt(wpnProjRicochetField.getText())));
      nestedStats.put("wpn_range_max", createNesting(Integer.parseInt(wpnRangeMaxField.getText())));
      nestedStats.put("wpn_range_min", createNesting(Integer.parseInt(wpnRangeMinField.getText())));
      nestedStats.put("wpn_scatter_angle", createNesting(Integer.parseInt(wpnScatterAngleField.getText())));
      nestedStats.put("wpn_scatter_angle_moving", createNesting(Integer.parseInt(wpnScatterAngleMovingField.getText())));
      nestedStats.put("wpn_speed", createNesting(Integer.parseInt(wpnSpeedField.getText())));

      nestedTags.put("tags", "");
      nestedStatDistribution.put("statDistribution", "");
      statsRoot.put("stats", nestedStats);

      clipboardContent.putString(yaml.dump(statsRoot));

      clipboard.setContent(clipboardContent);

      System.out.println("content copied to clipboard: " + clipboardContent);
   }

   @FXML
   protected void onInput() {
      addDigitFilter(actCountField);
      addDigitFilter(actDurationField);
      addDigitFilter(heatField);
      addDigitFilter(massField);
      addDigitFilter(scrapValueField);
      addDigitFilter(wpnConcussionField);
      addDigitFilter(wpnDamageField);
      addDigitFilter(wpnDamageRadiusField);
      addDigitFilter(wpnImpactField);
      addDigitFilter(wpnImpactRadiusField);
      addDigitFilter(wpnProjLifeTimeField);
      addDigitFilter(wpnProjRicochetField);
      addDigitFilter(wpnRangeMaxField);
      addDigitFilter(wpnRangeMinField);
      addDigitFilter(wpnScatterAngleField);
      addDigitFilter(wpnScatterAngleMovingField);
      addDigitFilter(wpnSpeedField);
   }

}