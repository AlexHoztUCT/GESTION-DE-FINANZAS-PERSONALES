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
        getContentPane().setBackground(new Color(255, 230, 230)); // Fondo rojo claro

        //  MENU 
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
        topBar.setBackground(new Color(200, 0, 0));

        String[] menuItems = {"Inicio", "Cuentas", "Ingresos", "Gastos", "Presupuesto"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(200, 0, 0));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            topBar.add(btn);
        }

        // PRINCIPAL 
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(255, 230, 230));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 15, 15));
        formPanel.setBackground(new Color(255, 245, 245));
        formPanel.setBorder(BorderFactory.createTitledBorder("Detalles del Gasto"));

        addFormField(formPanel, "Fecha:", crearCampoFecha());
        addFormField(formPanel, "Cuenta:", crearComboBox());
        addFormField(formPanel, "Categoría:", crearComboBox());
        addFormField(formPanel, "Monto:", crearCampoTexto(""));

        JPanel newCategoryPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        newCategoryPanel.setBackground(new Color(255, 245, 245));
        newCategoryPanel.setBorder(BorderFactory.createTitledBorder("Nueva Categoría"));

        addFormField(newCategoryPanel, "Nombre:", crearCampoTexto(""));
        addFormField(newCategoryPanel, "Nota:", crearCampoTexto(""));

        JPanel actionPanel = new JPanel(new BorderLayout(20, 20));
        
        // botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.add(crearBotonRojo("Guardar categoría"));
        buttonPanel.add(crearBotonRojo("Guardar gasto"));
        
        // tabla
        String[] columnas = {"Cuenta", "Categoría", "Monto", "Fecha", "Nota"};
        JTable tabla = new JTable(new DefaultTableModel(columnas, 0));
        JScrollPane scrollPane = new JScrollPane(tabla);
        
        actionPanel.add(buttonPanel, BorderLayout.NORTH);
        actionPanel.add(scrollPane, BorderLayout.CENTER);

        // ensamblado final
        mainPanel.add(formPanel);
        mainPanel.add(newCategoryPanel);
        mainPanel.add(actionPanel);

        add(topBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, Component field) {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));
        container.setBackground(panel.getBackground());
        container.add(new JLabel(label));
        container.add(field);
        panel.add(container);
    }

    private JTextField crearCampoFecha() {
        JTextField campo = new JTextField("Seleccionar fecha");
        campo.setPreferredSize(new Dimension(200, 30));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 0, 0)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return campo;
    }

    private JComboBox<String> crearComboBox() {
        JComboBox<String> combo = new JComboBox<>(new String[]{"Seleccionar"});
        combo.setPreferredSize(new Dimension(200, 30));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        return combo;
    }

    private JTextField crearCampoTexto(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        campo.setPreferredSize(new Dimension(200, 30));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 0, 0)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return campo;
    }

    private JButton crearBotonRojo(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(200, 0, 0));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new gastosUI().setVisible(true));
    }
}