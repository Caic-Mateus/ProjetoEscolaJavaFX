module br.com.fatec.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens br.com.fatec.mavenproject1 to javafx.fxml;
    exports br.com.fatec.mavenproject1;
}
