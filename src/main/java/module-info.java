module org.example.apispringfron {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires okhttp3;


    opens org.example.apispringfron to javafx.fxml;
    exports org.example.apispringfron;
}