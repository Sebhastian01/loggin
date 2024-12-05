package sena.edu.poo.Loggin_controller;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sena.edu.poo.Loggin_app.App;
import sena.edu.poo.Loggin_model.Aplicacion;
import sena.edu.poo.Loggin_model.Usuario;

public class InicioSesionController {

    @FXML
    private TextField identificacionField;

    @FXML
    private TextField contraseñaField;

    @FXML
    private Label mensajeLabel;

    private Aplicacion aplicacion;

    public InicioSesionController() {
        this.aplicacion = Aplicacion.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        identificacionField.setPromptText("Identificación");
        contraseñaField.setPromptText("Contraseña");

        identificacionField.setOnMouseClicked(event -> limpiarCampoTexto(event, identificacionField));

        contraseñaField.setOnMouseClicked(event -> limpiarCampoTexto(event, contraseñaField));

        identificacionField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && identificacionField.getText().isEmpty()) {
                identificacionField.setPromptText("Identificación");
            }
        });

        contraseñaField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && contraseñaField.getText().isEmpty()) {
                contraseñaField.setPromptText("Contraseña");
            }
        });
    }

    private void limpiarCampoTexto(MouseEvent event, TextField campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void IniciarSesion() {
        mensajeLabel.setVisible(false);
        String identificacion = identificacionField.getText().trim();
        String contraseña = contraseñaField.getText().trim();

        if (identificacion.isEmpty() || contraseña.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, complete todos los campos.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Verificar si las credenciales corresponden al administrador
            if (identificacion.equals(aplicacion.getAdministrador().getId()) &&
                    contraseña.equals(aplicacion.getAdministrador().getContraseña())) {
                App.setRoot("Administrador", "Administrador");
                mensajeLabel.setText("Inicio de sesión exitoso.");
                mensajeLabel.setStyle("-fx-text-fill: green;");
                mensajeLabel.setVisible(true);
                return;
            }

            // Verificar si las credenciales corresponden a un usuario
            List<Usuario> usuarios = aplicacion.getUsuarios();
            for (Usuario usuario : usuarios) {
                if (identificacion.equals(usuario.getId()) && contraseña.equals(usuario.getContraseña())) {
                    App.setRoot("Usuario", "Usuario");
                    mensajeLabel.setText("Inicio de sesión exitoso.");
                    mensajeLabel.setStyle("-fx-text-fill: green;");
                    mensajeLabel.setVisible(true);
                    return;
                }
            }

            // Si no se encuentra ningún usuario con las credenciales
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Credenciales incorrectas. Intente nuevamente.");
            mensajeLabel.setStyle("-fx-text-fill: red;");

        } catch (IOException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al iniciar sesión.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    private void Registrarse() throws IOException {
        App.setRoot("Registro", "Registro");
    }
}
