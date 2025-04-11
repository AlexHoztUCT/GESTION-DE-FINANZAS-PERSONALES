package conectbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;

    public static void connect() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/finanzas_personales"; // nombre de la BD
            String user = "root";
            String password = ""; // por defecto en XAMPP es vacío

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    // Método main para probar la conexión
    public static void main(String[] args) {
        try {
            DatabaseManager.connect();
            Connection conn = DatabaseManager.getConnection();

            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Conexión exitosa a la base de datos.");
                conn.close(); // Cerramos conexión después de probar
            } else {
                System.out.println("❌ No se pudo establecer conexión.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}
