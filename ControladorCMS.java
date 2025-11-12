import java.util.*;
import java.util.stream.Collectors;

public class ControladorCMS {
    private final RepositorioContenido repositorio;
    private Usuario usuarioActual;
    private int secuenciaId = 1;

    public ControladorCMS(RepositorioContenido repositorio, Usuario usuario) {
        this.repositorio = repositorio;
        this.usuarioActual = usuario;
    }

    public void setUsuarioActual(Usuario u) { this.usuarioActual = u; }
    public Usuario getUsuarioActual() { return usuarioActual; }

    public Contenido crearContenido(String tipo, Map<String, String> datos, Categoria categoria) {
        if (!(usuarioActual instanceof Editor) && !(usuarioActual instanceof Administrador)) {
            throw new IllegalStateException("Permisos insuficientes para crear contenido.");
        }
        Editor e = (usuarioActual instanceof Editor) ? (Editor) usuarioActual : new Editor(0, usuarioActual.getNombre(), "n/a");
        Contenido nuevo = e.crearContenido(tipo, datos, secuenciaId++, categoria);
        if (nuevo == null) throw new IllegalArgumentException("Tipo de contenido no soportado.");
        repositorio.agregarContenido(nuevo);
        return nuevo;
    }

    public void editarContenido(Contenido c, Map<String, String> datos) {
        if (!(usuarioActual instanceof Editor) && !(usuarioActual instanceof Administrador)) {
            throw new IllegalStateException("Permisos insuficientes para editar.");
        }
        Editor e = (usuarioActual instanceof Editor) ? (Editor) usuarioActual : new Editor(0, usuarioActual.getNombre(), "n/a");
        e.editarContenido(c, datos);
        repositorio.editarContenido(c);
    }

    public void eliminarContenido(Contenido c) {
        if (!(usuarioActual instanceof Administrador)) {
            throw new IllegalStateException("Solo un Administrador puede eliminar.");
        }
        ((Administrador) usuarioActual).eliminarContenido(c);
        repositorio.eliminarContenido(c);
    }

    public void publicarContenido(Contenido c) {
        if (!(usuarioActual instanceof Administrador)) {
            throw new IllegalStateException("Solo un Administrador puede publicar.");
        }
        ((Administrador) usuarioActual).publicarContenido(c);
    }

    public List<Contenido> filtrarPorCategoria(Categoria cat) {
        return repositorio.buscarPorCategoria(cat);
    }

    public List<Contenido> filtrarPorTipo(String tipo) {
        return repositorio.buscarPorTipo(tipo);
    }

    public String reporte() {
        return repositorio.generarReporte();
    }

    public List<Contenido> getTodos() {
        return repositorio.getTodos();
    }

    public String resumenSimple() {
        return repositorio.getTodos().stream()
                .map(Contenido::mostrarDetalles)
                .collect(Collectors.joining("\n"));
    }
}
