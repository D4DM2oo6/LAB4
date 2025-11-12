import java.util.Map;

public class Editor extends Usuario {
    public Editor(int id, String nombre, String correo) {
        super(id, nombre, correo, "EDITOR");
    }

    public Contenido crearContenido(String tipo, Map<String, String> datos, int nuevoId, Categoria categoria) {
        if (tipo == null || datos == null) return null;
        String titulo = datos.getOrDefault("titulo", "Sin título");
        String autor = datos.getOrDefault("autor", getNombre());

        switch (tipo.toLowerCase()) {
            case "articulo":
                String texto = datos.getOrDefault("texto", "");
                return new Articulo(nuevoId, titulo, autor, categoria, texto);
            case "video":
                int dur = Integer.parseInt(datos.getOrDefault("duracion", "0"));
                String res = datos.getOrDefault("resolucion", "1080p");
                String urlV = datos.getOrDefault("url", "");
                return new Video(nuevoId, titulo, autor, categoria, dur, res, urlV);
            case "imagen":
                String resI = datos.getOrDefault("resolucion", "1920x1080");
                String formato = datos.getOrDefault("formato", "PNG");
                String urlI = datos.getOrDefault("url", "");
                return new Imagen(nuevoId, titulo, autor, categoria, resI, formato, urlI);
            default:
                return null;
        }
    }

    public void editarContenido(Contenido c, Map<String, String> datos) {
        if (c == null || datos == null) return;
        String t = datos.get("titulo");
        String a = datos.get("autor");

        if (c instanceof Articulo) {
            String texto = datos.get("texto");
            c.editar(t, a, texto);
        } else if (c instanceof Video) {
            String res = datos.get("resolucion");
            String url = datos.get("url");
            c.editar(t, a, res, url);
        } else if (c instanceof Imagen) {
            String resI = datos.get("resolucion");
            String formato = datos.get("formato");
            String urlI = datos.get("url");
            c.editar(t, a, resI, formato, urlI);
        } else {
            c.editar(t, a);
        }
    }
}
