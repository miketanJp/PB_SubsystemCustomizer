package it.miketan.pb.serializer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.FileChooser;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
      actCountField.setText("0");
      actDurationField.setText("0");
      heatField.setText("0");
      massField.setText("0");
      scrapValueField.setText("0");
      wpnConcussionField.setText("0");
      wpnDamageField.setText("0");
      wpnDamageRadiusField.setText("0");
      wpnImpactField.setText("0");
      wpnImpactRadiusField.setText("0");
      wpnProjLifeTimeField.setText("0");
      wpnProjRicochetField.setText("0");
      wpnRangeMaxField.setText("0");
      wpnRangeMinField.setText("0");
      wpnScatterAngleField.setText("0");
      wpnScatterAngleMovingField.setText("0");
      wpnSpeedField.setText("0");
   }

   @FXML
   protected void onLoadBtnClick() throws IOException {
      FileChooser fc = new FileChooser();
      File file = fc.showOpenDialog(null);
      fc.setTitle("Open Subsystem");
      if (file != null) {
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr);
         String strLn;


         while((strLn = br.readLine()) != null) {
            if (strLn.contains("value:")) {

               String[] parts = strLn.split("value:");
               String value = "";
               if (parts.length > 1) {
                  value = parts[1].trim();
               }

               System.out.println(strLn + " - " + "String length: " + strLn.length());

               switch (strLn) {
                  case "  act_count:" -> actCountField.setText(value);
                  case "  act_duration:" -> actDurationField.setText(value);
                  case "  act_heat:" -> heatField.setText(value);
                  case "  mass:" -> massField.setText(value);
                  case "  scrap_value:" -> scrapValueField.setText(value);
                  case "  wpn_concussion:" -> wpnConcussionField.setText(value);
                  case "  wpn_damage:" -> wpnDamageField.setText(value);
                  case "  wpn_damage_radius:" -> wpnDamageRadiusField.setText(value);
                  case "  wpn_impact:" -> wpnImpactField.setText(value);
                  case "  wpn_impact_radius:" -> wpnImpactRadiusField.setText(value);
                  case "  wpn_proj_lifetime:" -> wpnProjLifeTimeField.setText(value);
                  case "  wpn_proj_ricochet:" -> wpnProjRicochetField.setText(value);
                  case "  wpn_range_max:" -> wpnRangeMaxField.setText(value);
                  case "  wpn_range_min:" -> wpnRangeMinField.setText(value);
                  case "  wpn_scatter_angle:" -> wpnScatterAngleField.setText(value);
                  case "  wpn_scatter_angle_moving:" -> wpnScatterAngleMovingField.setText(value);
                  case "  wpn_speed:" -> wpnSpeedField.setText(value);
                  default -> System.out.println("not found!");
               }
            }
         }
      }
   }

   @FXML
   protected void onSaveBtnClick() throws NumberFormatException {

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

      LocalDate date = LocalDate.now();
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
      String dateFinal = date.format(dtf);

      Instant instant = Instant.now();
      LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

      int hour = ldt.getHour();
      int minutes = ldt.getMinute();
      int seconds = ldt.getSecond();

      String directory = "YamlOutput";
      String currDir = System.getProperty("user.dir");
      String path = currDir + File.separator + directory;

      File dir = new File(path);
      boolean dirCreated = dir.mkdir();

      if (dirCreated) {

         try (FileWriter writer = new FileWriter("YamlOutput/" + "stats_" + "output" + dateFinal + "_" + hour + minutes + seconds +".yaml")) {
            yaml.dump(nestedTags, writer);
            yaml.dump(nestedStatDistribution, writer);
            yaml.dump(statsRoot, writer);
         } catch (IOException e) {
            e.printStackTrace();
         }
         System.out.println("created: " + path);

      } else {

         try (FileWriter writer = new FileWriter("YamlOutput/wpn_main" + "_stats_" + "output" + dateFinal + "_" + hour + minutes + seconds +".yaml")) {
            yaml.dump(nestedTags, writer);
            yaml.dump(nestedStatDistribution, writer);
            yaml.dump(statsRoot, writer);
         } catch (IOException e) {
            e.printStackTrace();
         }
         System.out.println("file saved at" + path);
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