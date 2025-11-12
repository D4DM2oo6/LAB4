public class Articulo extends Contenido {
    private String texto;
    private int numPalabras;

    public Articulo(int id, String titulo, String autor, Categoria categoria, String texto) {
        super(id, titulo, autor, categoria);
        this.texto = texto == null ? "" : texto;
        this.numPalabras = this.texto.isBlank() ? 0 : this.texto.trim().split("\\s+").length;
    }

    @Override
    public void publicar() {
        marcarPublicado();
        System.out.println("[Artículo] Publicado: " + getTitulo());
    }

    @Override
    public String mostrarDetalles() {
        return super.mostrarDetalles() + String.format(" | Palabras: %d", numPalabras);
    }

    @Override
    public void editar(String... campos) {
        super.editar(campos);
        if (campos != null && campos.length > 2 && campos[2] != null) {
            this.texto = campos[2];
            this.numPalabras = this.texto.isBlank() ? 0 : this.texto.trim().split("\\s+").length;
        }
    }
}
