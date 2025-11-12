import java.util.Date;

public abstract class Contenido implements Publicable {
    private final int id;
    private String titulo;
    private String autor;
    private Date fechaPublicacion;
    private Categoria categoria;
    private boolean publicado;

    protected Contenido(int id, String titulo, String autor, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.publicado = false;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public Date getFechaPublicacion() { return fechaPublicacion; }
    public boolean isPublicado() { return publicado; }

    protected void marcarPublicado() {
        this.publicado = true;
        this.fechaPublicacion = new Date();
    }

    @Override
    public void eliminar() {
        this.publicado = false;
    }

    @Override
    public String mostrarDetalles() {
        String base = String.format("#%d | %s | %s | Cat: %s | Publicado: %s",
                id, titulo, autor, (categoria == null ? "-" : categoria.getNombre()),
                publicado ? "Sí" : "No");
        return base;
    }

    @Override
    public void editar(String... campos) {
        if (campos != null) {
            if (campos.length > 0 && campos[0] != null) setTitulo(campos[0]);
            if (campos.length > 1 && campos[1] != null) setAutor(campos[1]);
        }
    }

    @Override
    public String toString() {
        return mostrarDetalles();
    }
}
