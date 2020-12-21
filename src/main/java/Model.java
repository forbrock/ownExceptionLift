import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private final Dao dao = new Dao();
    private final Connection connection = dao.connection;

    public void writeData(String name, String login, String password) {
        String query = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkLogin(String login) throws NotUniqueException {
        String query = "SELECT * FROM users WHERE login = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                throw new NotUniqueException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
