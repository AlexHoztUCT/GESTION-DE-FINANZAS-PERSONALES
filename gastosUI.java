import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class gastosUI extends JFrame {

    public gastosUI() {
        setTitle("Gestión de Finanzas - Gastos");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------- MENÚ SUPERIOR ------------
        JPanel topBar = crearTopBar();

        // ----------- TÍTULO ------------
        JLabel titulo = new JLabel("Gastos", SwingConstants.CENTER);
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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Componentes del formulario
        gbc.gridx = 0; gbc.gridy = 0;
        formulario.add(new JLabel("Fecha:"), gbc);
        gbc.gridx = 1;
        formulario.add(crearCampoTexto("Seleccionar fecha"), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formulario.add(new JLabel("Cuenta:"), gbc);
        gbc.gridx = 1;
        formulario.add(crearComboBox(), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formulario.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        formulario.add(crearComboBox(), gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        formulario.add(new JLabel("Nueva categoría:"), gbc);
        gbc.gridx = 3;
        formulario.add(crearCampoTexto(""), gbc);
        gbc.gridx = 4;
        formulario.add(crearBotonNegro("Agregar"), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formulario.add(new JLabel("Monto:"), gbc);
        gbc.gridx = 1;
        formulario.add(crearCampoTexto(""), gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formulario.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        formulario.add(crearCampoTexto(""), gbc);

        // Botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        botonesPanel.setBackground(new Color(223, 222, 239));
        botonesPanel.add(crearBotonNegro("Agregar Categoría"));
        botonesPanel.add(crearBotonNegro("Guardar Gasto"));
        botonesPanel.add(crearBotonNegro("Restablecer"));

        // Tabla de gastos
        String[] columnas = {"Fecha", "Cuenta", "Categoría", "Monto", "Descripción"};
        Object[][] datos = {};
        JTable tablaGastos = new JTable(new DefaultTableModel(datos, columnas));
        JScrollPane scrollPane = new JScrollPane(tablaGastos);
        scrollPane.setPreferredSize(new Dimension(900, 200));

        // Organización final
        panelContenedor.add(formulario);
        add(topBar, BorderLayout.NORTH);
        add(titulo, BorderLayout.CENTER);
        add(panelContenedor, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);
    }

    private JPanel crearTopBar() {
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
        topBar.setBackground(new Color(200, 0, 0));

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

        return topBar;
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

    private JComboBox<String> crearComboBox() {
        JComboBox<String> combo = new JComboBox<>(new String[]{"-- Seleccionar --"});
        combo.setPreferredSize(new Dimension(200, 30));
        combo.setBackground(Color.WHITE);
        return combo;
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
            new gastosUI().setVisible(true);
        });
    }
}