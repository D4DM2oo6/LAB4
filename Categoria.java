public class Categoria {
    private final String nombre;
    private final String descripcion;

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return nombre + (descripcion == null || descripcion.isBlank() ? "" : " - " + descripcion);
    }
}
