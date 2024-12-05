package sena.edu.poo.Loggin_model;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import sena.edu.poo.Loggin_persistencia.Persistencia_usuario;

@Getter
@Setter
public class Aplicacion implements Serializable {

    private static Aplicacion instancia;
    private List<Usuario> usuarios;
    private Administrador administrador;
    private UsuarioCRUD usuarioCRUD;
    private Thread hiloCopia;

    private Aplicacion() {
        this.usuarios = new LinkedList<>();
        this.usuarioCRUD = new UsuarioCRUD(this);
        this.administrador = Administrador.getInstance();

    }

    public static Aplicacion getInstancia() {
        if (instancia == null) {
            synchronized (Aplicacion.class) {
                if (instancia == null) {
                    instancia = new Aplicacion();
                    instancia.cargarDatosUsuarios();
                }
            }
        }
        return instancia;
    }

    private void cargarDatosUsuarios() {
        Persistencia_usuario persistencia = Persistencia_usuario.getInstancia();
        try {
            List<Usuario> usuariosCargados = persistencia.cargarUsuarios();
            if (usuariosCargados != null) {
                this.usuarios.addAll(usuariosCargados);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
        }
    }
}
