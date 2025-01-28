package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentDatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/student";;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aldebaran1711";

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = getConnected();
            System.out.println("Successfully connected to database!");
        } catch (SQLException e) {
            System.out.println("Error getting the connection to database");
            e.printStackTrace();

        } catch (ClassNotFoundException e){
           System.out.println("Error getting connected the class database");
           e.printStackTrace();
        } finally {
            closedConnection(connection);
        }
    }
    /*
    @return
    @throws
    @throws
    */

    private static Connection getConnected() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }


    /*
    @params
     */

    private static Connection closedConnection(Connection connection)  {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection has been closed!");
            } catch (SQLException e) {
                System.out.println("Failed to connect to closed connection to database");
                e.printStackTrace();
            }

        }
        return null;
    }
}
