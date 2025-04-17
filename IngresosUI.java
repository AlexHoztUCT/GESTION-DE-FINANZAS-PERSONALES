import javax.swing.*;
import java.awt.*;

public class IngresosUI extends JFrame {

    public IngresosUI() {
        setTitle("Gestión de Finanzas - Ingresos");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------- MENÚ SUPERIOR ------------
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
        topBar.setBackground(new Color(200, 0, 0)); // rojo

        String[] menuItems = {"Inicio", "Cuentas", "Ingresos", "Gastos", "Presupuesto"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            estilizarBotonMenu(btn);
            topBar.add(btn);
        }

        topBar.add(Box.createHorizontalGlue());

        JButton cerrarSesion = crearBotonNegro("Cerrar sesión");
        cerrarSesion.setFont(new Font("Arial", Font.BOLD, 14));
        cerrarSesion.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        topBar.add(cerrarSesion);

        // ----------- TÍTULO ------------
        JLabel titulo = new JLabel("Ingresos", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(new Color(36, 36, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // ----------- PANEL DE FORMULARIO ------------
        JPanel panelContenedor = new JPanel();
        panelContenedor.setBackground(new Color(239, 239, 239));
        panelContenedor.setLayout(new GridBagLayout());

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(new Color(223, 222, 239));
        formulario.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Componentes del formulario
        JLabel lblFecha = new JLabel("Fecha:");
        JTextField txtFecha = crearCampoTexto("Seleccionar fecha");
        txtFecha.setPreferredSize(new Dimension(160, 30));
        txtFecha.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel lblCuenta = new JLabel("Cuenta:");
        JTextField txtCuenta = crearCampoTexto("Seleccionar cuenta");

        JLabel lblFuente = new JLabel("Fuente de ingreso:");
        JTextField txtFuente = crearCampoTexto("Seleccionar fuente");

        JLabel lblNuevaFuente = new JLabel("Nueva fuente:");
        JTextField txtNuevaFuente = crearCampoTexto("");
        txtNuevaFuente.setPreferredSize(new Dimension(150, 30));

        JButton btnAgregar = crearBotonNegro("Agregar");

        JLabel lblMonto = new JLabel("Monto:");
        JTextField txtMonto = crearCampoTexto("");

        JButton btnGuardar = crearBotonNegro("Guardar Ingreso");
        JButton btnReset = crearBotonNegro("Restablecer");

        // Añadir componentes al formulario
        gbc.gridx = 0; gbc.gridy = 0;
        formulario.add(lblFecha, gbc);
        gbc.gridx = 1;
        formulario.add(txtFecha, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formulario.add(lblCuenta, gbc);
        gbc.gridx = 1;
        formulario.add(txtCuenta, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formulario.add(lblFuente, gbc);
        gbc.gridx = 1;
        formulario.add(txtFuente, gbc);
        gbc.gridx = 2;
        formulario.add(lblNuevaFuente, gbc);
        gbc.gridx = 3;
        formulario.add(btnAgregar, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formulario.add(lblMonto, gbc);
        gbc.gridx = 1;
        formulario.add(txtMonto, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        formulario.add(btnGuardar, gbc);
        gbc.gridx = 2;
        formulario.add(btnReset, gbc);

        panelContenedor.add(formulario);

        // Agregar todo al Frame
        add(topBar, BorderLayout.NORTH);
        add(titulo, BorderLayout.CENTER);
        add(panelContenedor, BorderLayout.SOUTH);
    }

    private JTextField crearCampoTexto(String texto) {
        JTextField campo = new JTextField(texto);
        campo.setPreferredSize(new Dimension(200, 30));
        campo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        campo.setBackground(Color.WHITE);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(Color.GRAY);
        return campo;
    }

    private JButton crearBotonNegro(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    private void estilizarBotonMenu(JButton btn) {
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(200, 0, 0));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IngresosUI().setVisible(true);
        });
    }
}
