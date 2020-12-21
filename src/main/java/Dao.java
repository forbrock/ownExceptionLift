import java.sql.*;

public class Dao {
    public final Connection connection;

    {
        connection = createConnection();
        createTable();
    }

    public static Connection createConnection() {
        String URL = "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1";
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createTable(){
        String query = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "login VARCHAR(30) NOT NULL," +
                "password VARCHAR(10) NOT NULL);";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
