import java.util.*;
import java.util.stream.Collectors;

public class RepositorioContenido {
    private final List<Contenido> listaContenidos = new ArrayList<>();

    public void agregarContenido(Contenido c) {
        if (c != null) listaContenidos.add(c);
    }

    public void editarContenido(Contenido c) { /* no-op en memoria */ }

    public void eliminarContenido(Contenido c) {
        if (c != null) listaContenidos.remove(c);
    }

    public List<Contenido> buscarPorCategoria(Categoria cat) {
        if (cat == null) return Collections.emptyList();
        return listaContenidos.stream()
                .filter(x -> x.getCategoria() != null && x.getCategoria().getNombre().equalsIgnoreCase(cat.getNombre()))
                .collect(Collectors.toList());
    }

    public List<Contenido> buscarPorTipo(String tipo) {
        if (tipo == null) return Collections.emptyList();
        String t = tipo.toLowerCase();
        return listaContenidos.stream()
                .filter(x ->
                        ("articulo".equals(t) && x instanceof Articulo) ||
                        ("video".equals(t) && x instanceof Video) ||
                        ("imagen".equals(t) && x instanceof Imagen))
                .collect(Collectors.toList());
    }

    public String generarReporte() {
        long a = listaContenidos.stream().filter(c -> c instanceof Articulo).count();
        long v = listaContenidos.stream().filter(c -> c instanceof Video).count();
        long i = listaContenidos.stream().filter(c -> c instanceof Imagen).count();
        long total = listaContenidos.size();
        return String.format("REPORTE CMS%nArtículos: %d%nVideos: %d%nImágenes: %d%nTotal: %d", a, v, i, total);
    }

    public List<Contenido> getTodos() {
        return Collections.unmodifiableList(listaContenidos);
    }
}
