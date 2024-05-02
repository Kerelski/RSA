module View {
    requires javafx.controls;
    requires javafx.fxml;
    requires Model;
    opens org.view to javafx.fxml;
    exports org.view;
}