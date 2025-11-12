public interface Publicable {
    void publicar();
    void editar(String... campos);
    void eliminar();
    String mostrarDetalles();
}
