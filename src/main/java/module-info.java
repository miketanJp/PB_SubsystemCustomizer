module it.miketan.pb.serializer {
    requires javafx.graphics;
    requires javafx.fxml;
    requires org.yaml.snakeyaml;
    requires javafx.controls;
    opens it.miketan.pb.serializer to javafx.fxml;
    opens it.miketan.pb.serializer.controllers to javafx.fxml;
    exports it.miketan.pb.serializer;
    exports it.miketan.pb.serializer.helpers;
    exports it.miketan.pb.serializer.controllers;
}
