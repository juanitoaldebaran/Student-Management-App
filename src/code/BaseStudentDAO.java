package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseStudentDAO implements StudentDAO{

    private Connection connection;

    public BaseStudentDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addStudent(BaseStudentData studentData) throws SQLException {
        String insertQuery = "INSERT INTO student_table (name, email, age, gpa, class) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, studentData.getName());
            statement.setString(2, studentData.getEmail());
            statement.setInt(3, studentData.getAge());
            statement.setFloat(4, studentData.getGPA());
            statement.setString(5, studentData.getStudentClass().name());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student Data successfully added!");
            } else {
                System.out.println("Failed to add student data!");
            }
        }
    }

    @Override
    public BaseStudentData getStudentById(int id) throws SQLException {
        String listQueryById = "SELECT * FROM student_table WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(listQueryById)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BaseStudentData(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("age"),
                            resultSet.getFloat("gpa"),
                            StudentClass.fromString(resultSet.getString("class"))
                    );
                }
            }
        }

        return null;
    }
    @Override
    public List<BaseStudentData> getAllStudent() throws SQLException {
        String listQuery = "SELECT * FROM student_table";
        List<BaseStudentData> baseStudentData = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(listQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                   baseStudentData.add(new BaseStudentData(
                           resultSet.getInt("id"),
                           resultSet.getString("name"),
                           resultSet.getString("email"),
                           resultSet.getInt("age"),
                           resultSet.getFloat("gpa"),
                           StudentClass.fromString(resultSet.getString("class"))
                    ));
                }
            }
        }

        return baseStudentData;
    }

    @Override
    public void updateStudent(int newID, String newName, String newEmail, int newAge, float newGPA, String newClass) throws SQLException {
        String updateQuery = "UPDATE student_table SET id = ?, name = ?, email = ?, age = ?, gpa = ?, class = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, newID);
            statement.setString(2, newName);
            statement.setString(3, newEmail);
            statement.setInt(4, newAge);
            statement.setFloat(5, newGPA);
            statement.setString(6, newClass);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student Data successfully updated!");
            } else {
                System.out.println("Failed to update student data!");
            }
        }
    }

    @Override
    public void deleteStudentByID(int id) throws SQLException {
        String deleteQuery = "DELETE FROM student_table WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student Data successfully deleted!");
            } else {
                System.out.println("Failed to delete student data!");
            }
        }
    }


}
