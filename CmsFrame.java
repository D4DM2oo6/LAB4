import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class CmsFrame extends JFrame {
    private final ControladorCMS controlador;

    private final DefaultListModel<Contenido> listModel = new DefaultListModel<>();
    private final JList<Contenido> lista = new JList<>(listModel);

    private final JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Todos", "Articulo", "Video", "Imagen"});
    private final JTextField txtCategoria = new JTextField();
    private final JButton btnFiltrar = new JButton("Aplicar filtro");
    private final JButton btnLimpiar = new JButton("Limpiar filtro");

    private final JButton btnCrearArticulo = new JButton("Nuevo Artículo");
    private final JButton btnCrearVideo = new JButton("Nuevo Video");
    private final JButton btnCrearImagen = new JButton("Nuevo Imagen");

    private final JButton btnPublicar = new JButton("Publicar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnReporte = new JButton("Reporte");

    private final JComboBox<String> cbRol = new JComboBox<>(new String[]{"Editor", "Admin"});

    private final Administrador admin = new Administrador(2, "Luis Admin", "admin@cms.com");
    private final Editor editor = new Editor(1, "Luis Editor", "editor@cms.com");

    public CmsFrame(ControladorCMS controlador) {
        super("CMS EGA – GUI");
        this.controlador = controlador;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel izquierdo (acciones y filtros)
        JPanel left = new JPanel(new GridBagLayout());
        left.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 0;

        left.add(new JLabel("Rol actual:"), c);
        c.gridy++;
        left.add(cbRol, c);

        c.gridy++;
        left.add(new JSeparator(), c);

        c.gridy++;
        left.add(btnCrearArticulo, c);
        c.gridy++;
        left.add(btnCrearVideo, c);
        c.gridy++;
        left.add(btnCrearImagen, c);

        c.gridy++;
        left.add(new JSeparator(), c);

        c.gridy++;
        left.add(new JLabel("Tipo:"), c);
        c.gridy++;
        left.add(cbTipo, c);

        c.gridy++;
        left.add(new JLabel("Categoría (nombre):"), c);
        c.gridy++;
        left.add(txtCategoria, c);

        c.gridy++;
        left.add(btnFiltrar, c);
        c.gridy++;
        left.add(btnLimpiar, c);

        c.gridy++;
        left.add(new JSeparator(), c);

        c.gridy++;
        left.add(btnPublicar, c);
        c.gridy++;
        left.add(btnEliminar, c);
        c.gridy++;
        left.add(btnReporte, c);

        add(left, BorderLayout.WEST);

        // Panel centro (lista)
        lista.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        add(new JScrollPane(lista), BorderLayout.CENTER);

        // Eventos
        cbRol.addActionListener(this::onCambioRol);
        btnCrearArticulo.addActionListener(this::onCrearArticulo);
        btnCrearVideo.addActionListener(this::onCrearVideo);
        btnCrearImagen.addActionListener(this::onCrearImagen);
        btnFiltrar.addActionListener(this::onFiltrar);
        btnLimpiar.addActionListener(this::onLimpiar);
        btnPublicar.addActionListener(this::onPublicar);
        btnEliminar.addActionListener(this::onEliminar);
        btnReporte.addActionListener(this::onReporte);

        // Estado inicial
        cbRol.setSelectedIndex(controlador.getUsuarioActual() instanceof Administrador ? 1 : 0);
        refrescarLista(controlador.getTodos());
    }

    private void onCambioRol(ActionEvent e) {
        if (cbRol.getSelectedIndex() == 0) controlador.setUsuarioActual(editor);
        else controlador.setUsuarioActual(admin);
        JOptionPane.showMessageDialog(this, "Rol activo: " + controlador.getUsuarioActual().getRol());
    }

    private void onCrearArticulo(ActionEvent e) {
        ArticleDialog dlg = new ArticleDialog(this);
        dlg.setVisible(true);
        if (!dlg.isOk()) return;

        Map<String, String> datos = new HashMap<>();
        datos.put("titulo", dlg.getTitulo());
        datos.put("autor", dlg.getAutor());
        datos.put("texto", dlg.getTexto());
        Categoria cat = new Categoria(dlg.getCategoria(), dlg.getDescripcion());

        try {
            Contenido ctt = controlador.crearContenido("articulo", datos, cat);
            refrescarLista(controlador.getTodos());
            JOptionPane.showMessageDialog(this, "Artículo creado:\n" + ctt.mostrarDetalles());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCrearVideo(ActionEvent e) {
        VideoDialog dlg = new VideoDialog(this);
        dlg.setVisible(true);
        if (!dlg.isOk()) return;

        Map<String, String> datos = new HashMap<>();
        datos.put("titulo", dlg.getTitulo());
        datos.put("autor", dlg.getAutor());
        datos.put("duracion", String.valueOf(dlg.getDuracionSeg()));
        datos.put("resolucion", dlg.getResolucion());
        datos.put("url", dlg.getUrl());
        Categoria cat = new Categoria(dlg.getCategoria(), dlg.getDescripcion());

        try {
            Contenido ctt = controlador.crearContenido("video", datos, cat);
            refrescarLista(controlador.getTodos());
            JOptionPane.showMessageDialog(this, "Video creado:\n" + ctt.mostrarDetalles());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCrearImagen(ActionEvent e) {
        ImageDialog dlg = new ImageDialog(this);
        dlg.setVisible(true);
        if (!dlg.isOk()) return;

        Map<String, String> datos = new HashMap<>();
        datos.put("titulo", dlg.getTitulo());
        datos.put("autor", dlg.getAutor());
        datos.put("resolucion", dlg.getResolucion());
        datos.put("formato", dlg.getFormato());
        datos.put("url", dlg.getUrl());
        Categoria cat = new Categoria(dlg.getCategoria(), dlg.getDescripcion());

        try {
            Contenido ctt = controlador.crearContenido("imagen", datos, cat);
            refrescarLista(controlador.getTodos());
            JOptionPane.showMessageDialog(this, "Imagen creada:\n" + ctt.mostrarDetalles());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onFiltrar(ActionEvent e) {
        String tipo = Objects.toString(cbTipo.getSelectedItem(), "Todos");
        String cat = txtCategoria.getText().trim();

        List<Contenido> base = controlador.getTodos();
        if (!"Todos".equalsIgnoreCase(tipo)) {
            base = controlador.filtrarPorTipo(tipo);
        }
        if (!cat.isBlank()) {
            base = controlador.filtrarPorCategoria(new Categoria(cat, ""));
        }
        refrescarLista(base);
    }

    private void onLimpiar(ActionEvent e) {
        cbTipo.setSelectedIndex(0);
        txtCategoria.setText("");
        refrescarLista(controlador.getTodos());
    }

    private void onPublicar(ActionEvent e) {
        Contenido sel = lista.getSelectedValue();
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un contenido.");
            return;
        }
        try {
            controlador.publicarContenido(sel);
            refrescarLista(controlador.getTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onEliminar(ActionEvent e) {
        Contenido sel = lista.getSelectedValue();
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un contenido.");
            return;
        }
        int r = JOptionPane.showConfirmDialog(this, "¿Eliminar seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) return;

        try {
            controlador.eliminarContenido(sel);
            refrescarLista(controlador.getTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onReporte(ActionEvent e) {
        JOptionPane.showMessageDialog(this, controlador.reporte(), "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    private void refrescarLista(List<Contenido> contenidos) {
        listModel.clear();
        for (Contenido c : contenidos) listModel.addElement(c);
    }
}
