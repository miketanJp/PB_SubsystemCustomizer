module it.miketan.pb.serializer.serializer {

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.yaml.snakeyaml;
    requires jdk.compiler;
    requires javafx.fxml;

    opens it.miketan.pb.serializer to javafx.fxml;
    exports it.miketan.pb.serializer;
    exports it.miketan.pb.serializer.controllers;
    opens it.miketan.pb.serializer.controllers to javafx.fxml;
}