import javax.swing.*;
import java.awt.*;

public class VideoDialog extends JDialog {
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtAutor = new JTextField();
    private final JTextField txtCategoria = new JTextField();
    private final JTextField txtDescripcion = new JTextField();

    private final JTextField txtDuracion = new JTextField("120");
    private final JTextField txtResolucion = new JTextField("1080p");
    private final JTextField txtUrl = new JTextField("https://");

    private boolean ok = false;

    public VideoDialog(Frame owner) {
        super(owner, "Nuevo Video", true);
        setLayout(new BorderLayout(10,10));
        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        form.add(new JLabel("Título:")); form.add(txtTitulo);
        form.add(new JLabel("Autor:")); form.add(txtAutor);
        form.add(new JLabel("Categoría:")); form.add(txtCategoria);
        form.add(new JLabel("Descripción categoría:")); form.add(txtDescripcion);
        form.add(new JLabel("Duración (seg):")); form.add(txtDuracion);
        form.add(new JLabel("Resolución:")); form.add(txtResolucion);
        form.add(new JLabel("URL:")); form.add(txtUrl);

        add(form, BorderLayout.CENTER);

        JPanel actions = new JPanel();
        JButton btnOk = new JButton("Crear");
        JButton btnCancel = new JButton("Cancelar");
        actions.add(btnOk); actions.add(btnCancel);
        add(actions, BorderLayout.SOUTH);

        btnOk.addActionListener(e -> { ok = true; dispose(); });
        btnCancel.addActionListener(e -> { ok = false; dispose(); });

        setSize(480, 360);
        setLocationRelativeTo(owner);
    }

    public boolean isOk() { return ok; }
    public String getTitulo() { return txtTitulo.getText().trim(); }
    public String getAutor() { return txtAutor.getText().trim(); }
    public String getCategoria() { return txtCategoria.getText().trim(); }
    public String getDescripcion() { return txtDescripcion.getText().trim(); }
    public int getDuracionSeg() {
        try { return Integer.parseInt(txtDuracion.getText().trim()); } catch (Exception e) { return 0; }
    }
    public String getResolucion() { return txtResolucion.getText().trim(); }
    public String getUrl() { return txtUrl.getText().trim(); }
}
