package sena.edu.poo.Loggin_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario extends Persona {
    public double saldoTotal;
    private Aplicacion aplicacion;

    public Usuario(String id, String contraseña, String nombre, String correo, String telefono, String direccion,
            double saldoTotal) {
        super(id, contraseña, nombre, correo, telefono, direccion);
        this.saldoTotal = saldoTotal;
        this.aplicacion = Aplicacion.getInstancia();
    }

    @Override
    public String toString() {
        return "Usuario [saldoTotal=" + saldoTotal + ", billeteraVirtual=" + aplicacion + "]";
    }

}
