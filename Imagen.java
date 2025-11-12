public class Imagen extends Contenido {
    private String resolucion;
    private String formato;
    private String urlImagen;

    public Imagen(int id, String titulo, String autor, Categoria categoria, String resolucion, String formato, String urlImagen) {
        super(id, titulo, autor, categoria);
        this.resolucion = resolucion;
        this.formato = formato;
        this.urlImagen = urlImagen;
    }

    @Override
    public void publicar() {
        marcarPublicado();
        System.out.println("[Imagen] Publicada: " + getTitulo() + " (" + formato + ")");
    }

    @Override
    public String mostrarDetalles() {
        return super.mostrarDetalles() + String.format(" | %s | %s", resolucion, formato);
    }

    @Override
    public void editar(String... campos) {
        super.editar(campos);
        if (campos != null) {
            if (campos.length > 2 && campos[2] != null) this.resolucion = campos[2];
            if (campos.length > 3 && campos[3] != null) this.formato = campos[3];
            if (campos.length > 4 && campos[4] != null) this.urlImagen = campos[4];
        }
    }
}
