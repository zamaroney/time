module com.zamaroney.time {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.zamaroney.time to javafx.fxml;
    exports com.zamaroney.time;
}