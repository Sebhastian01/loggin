package sena.edu.poo.Loggin_model;

public interface CRUD<T> {
    T crear(T objeto);

    T leer(String id);

    void actualizar(T objeto);

    void eliminar(String id);
}