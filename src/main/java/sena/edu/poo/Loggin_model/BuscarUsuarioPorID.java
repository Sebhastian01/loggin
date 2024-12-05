package sena.edu.poo.Loggin_model;

import java.util.List;

public class BuscarUsuarioPorID {

    private static Aplicacion aplicacion;

    static {
        aplicacion = Aplicacion.getInstancia();
    }

    public static Usuario buscarUsuarioPorIdentificacion(String identificacion) {
        return buscarUsuarioRecursivo(aplicacion.getUsuarios(), identificacion, 0);
    }

    private static Usuario buscarUsuarioRecursivo(List<Usuario> usuarios, String identificacion, int indice) {
        if (indice >= usuarios.size()) {
            return null;
        }

        Usuario usuario = usuarios.get(indice);
        if (usuario.getId().equals(identificacion)) {
            return usuario;
        }

        return buscarUsuarioRecursivo(usuarios, identificacion, indice + 1);
    }
}