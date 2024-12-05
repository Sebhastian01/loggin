module sena.edu.poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.logging;
    requires java.desktop;
    requires org.mapstruct;
    requires java.base;

    // Abrir paquetes para la reflexión de JavaFX (por ejemplo, para FXML)
    opens sena.edu.poo.Loggin_app to javafx.fxml;
    opens sena.edu.poo.Loggin_controller to javafx.fxml;
    opens sena.edu.poo.Loggin_model to javafx.fxml;

    // Exportar paquetes para que puedan ser utilizados por otros módulos
    exports sena.edu.poo.Loggin_app;
    exports sena.edu.poo.Loggin_controller;
    exports sena.edu.poo.Loggin_model;
}
