import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Config inicial
            RepositorioContenido repo = new RepositorioContenido();
            Editor editor = new Editor(1, "Luis Editor", "editor@cms.com");
            ControladorCMS controlador = new ControladorCMS(repo, editor);

            // GUI
            CmsFrame frame = new CmsFrame(controlador);
            frame.setVisible(true);
        });
    }
}
