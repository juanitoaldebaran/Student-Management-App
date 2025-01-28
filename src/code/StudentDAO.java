package code;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    void addStudent(BaseStudentData studentData) throws SQLException;
    BaseStudentData getStudentById(int id) throws SQLException;
    List<BaseStudentData> getAllStudent() throws SQLException;

    void updateStudent(int newID, String newName, String newEmail, int newAge, float newGPA, String newClass) throws SQLException;

    void deleteStudentByID(int id) throws SQLException;
}
