public class Video extends Contenido {
    private int duracionSeg;
    private String resolucion;
    private String urlVideo;

    public Video(int id, String titulo, String autor, Categoria categoria, int duracionSeg, String resolucion, String urlVideo) {
        super(id, titulo, autor, categoria);
        this.duracionSeg = Math.max(0, duracionSeg);
        this.resolucion = resolucion;
        this.urlVideo = urlVideo;
    }

    @Override
    public void publicar() {
        marcarPublicado();
        System.out.println("[Video] Publicado: " + getTitulo() + " (" + resolucion + ")");
    }

    @Override
    public String mostrarDetalles() {
        return super.mostrarDetalles() + String.format(" | Duración: %ds | %s", duracionSeg, urlVideo);
    }

    @Override
    public void editar(String... campos) {
        super.editar(campos);
        if (campos != null) {
            if (campos.length > 2 && campos[2] != null) this.resolucion = campos[2];
            if (campos.length > 3 && campos[3] != null) this.urlVideo = campos[3];
        }
    }
}
