import javax.swing.*;
import java.awt.*;

public class ArticleDialog extends JDialog {
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtAutor = new JTextField();
    private final JTextField txtCategoria = new JTextField();
    private final JTextField txtDescripcion = new JTextField();
    private final JTextArea  txtTexto = new JTextArea(6, 30);

    private boolean ok = false;

    public ArticleDialog(Frame owner) {
        super(owner, "Nuevo Artículo", true);
        setLayout(new BorderLayout(10,10));
        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        form.add(new JLabel("Título:")); form.add(txtTitulo);
        form.add(new JLabel("Autor:")); form.add(txtAutor);
        form.add(new JLabel("Categoría:")); form.add(txtCategoria);
        form.add(new JLabel("Descripción categoría:")); form.add(txtDescripcion);
        add(form, BorderLayout.NORTH);

        txtTexto.setLineWrap(true);
        txtTexto.setWrapStyleWord(true);
        add(new JScrollPane(txtTexto), BorderLayout.CENTER);

        JPanel actions = new JPanel();
        JButton btnOk = new JButton("Crear");
        JButton btnCancel = new JButton("Cancelar");
        actions.add(btnOk); actions.add(btnCancel);
        add(actions, BorderLayout.SOUTH);

        btnOk.addActionListener(e -> { ok = true; dispose(); });
        btnCancel.addActionListener(e -> { ok = false; dispose(); });

        setSize(500, 420);
        setLocationRelativeTo(owner);
    }

    public boolean isOk() { return ok; }
    public String getTitulo() { return txtTitulo.getText().trim(); }
    public String getAutor() { return txtAutor.getText().trim(); }
    public String getCategoria() { return txtCategoria.getText().trim(); }
    public String getDescripcion() { return txtDescripcion.getText().trim(); }
    public String getTexto() { return txtTexto.getText(); }
}
