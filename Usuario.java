public abstract class Usuario {
    private final int id;
    private final String nombre;
    private final String correo;
    private final String rol;

    protected Usuario(int id, String nombre, String correo, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
    public String getRol() { return rol; }

    public boolean iniciarSesion(String c, String pass) {
        return c != null && pass != null && c.equalsIgnoreCase(correo) && !pass.isBlank();
    }

    public void cerrarSesion() { /* noop */ }
}
