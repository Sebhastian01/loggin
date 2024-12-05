package sena.edu.poo.Loggin_model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Administrador extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Administrador instancia;

    private Administrador(String id, String contraseña, String nombre, String correo, String telefono,
            String direccion) {
        super(id, contraseña, nombre, correo, telefono, direccion);

        this.contraseña = contraseña;
    }

    public static Administrador getInstance() {
        if (instancia == null) {
            instancia = new Administrador(
                    "1094966310",
                    "1111",
                    "Sebastian Rios",
                    "sriosa01@gmail.com",
                    "3218885655",
                    "Villa andrea");
        }
        return instancia;
    }
}
