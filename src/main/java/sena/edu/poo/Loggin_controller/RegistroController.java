package sena.edu.poo.Loggin_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sena.edu.poo.Loggin_app.App;
import sena.edu.poo.Loggin_model.Aplicacion;
import sena.edu.poo.Loggin_model.Usuario;

public class RegistroController {

    @FXML
    private TextField identificacionField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField correoField;

    @FXML
    private TextField telefonoField;

    @FXML
    private TextField direccionField;

    @FXML
    private TextField contraseñaField;

    @FXML
    private Label mensajeLabel;

    private Aplicacion aplicacion;

    public RegistroController() {
        this.aplicacion = Aplicacion.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        identificacionField.setPromptText("Identificación");
        nombreField.setPromptText("Nombre Completo");
        correoField.setPromptText("Correo");
        telefonoField.setPromptText("Telefono");
        direccionField.setPromptText("Dirección");
        contraseñaField.setPromptText("Contraseña");

        TextField[] fields = { identificacionField, nombreField, correoField, telefonoField,
                direccionField, contraseñaField };

        for (TextField field : fields) {
            field.setOnMouseClicked(event -> limpiarCampoTexto(field));
            field.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue && field.getText().isEmpty()) {
                    field.setPromptText(field.getPromptText());
                }
            });
        }
    }

    private void limpiarCampoTexto(TextField campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void Registrarse() throws IOException {
        String identificacion = identificacionField.getText();
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String telefono = telefonoField.getText();
        String direccion = direccionField.getText();
        String contraseña = contraseñaField.getText();

        if (identificacion.isEmpty() || nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()
                || direccion.isEmpty() || contraseña.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, complete todos los campos.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Crear el usuario directamente con los datos del formulario
            Usuario usuario = new Usuario(identificacion, contraseña, nombre, correo, telefono, direccion, 0.0);

            // Registrar el usuario en la aplicación
            aplicacion.getUsuarioCRUD().crear(usuario);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Usuario registrado exitosamente.");
            mensajeLabel.setStyle("-fx-text-fill: green;");
            limpiarCampos();

        } catch (NumberFormatException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Saldo inicial debe ser un número válido.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        } catch (IllegalArgumentException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El usuario ya está registrado.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al crear el usuario");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void limpiarCampos() {
        identificacionField.clear();
        nombreField.clear();
        correoField.clear();
        telefonoField.clear();
        direccionField.clear();
        contraseñaField.clear();

        identificacionField.setPromptText("Identificación");
        nombreField.setPromptText("Nombre Completo");
        correoField.setPromptText("Correo");
        telefonoField.setPromptText("Telefono");
        direccionField.setPromptText("Dirección");
        contraseñaField.setPromptText("Contraseña");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("InicioSesion", "Inicio Sesion");
    }
}
