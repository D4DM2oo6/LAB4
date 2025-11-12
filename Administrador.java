public class Administrador extends Usuario {
    public Administrador(int id, String nombre, String correo) {
        super(id, nombre, correo, "ADMIN");
    }

    public void publicarContenido(Contenido c) {
        if (c != null) c.publicar();
    }

    public void eliminarContenido(Contenido c) {
        if (c != null) c.eliminar();
    }
}
