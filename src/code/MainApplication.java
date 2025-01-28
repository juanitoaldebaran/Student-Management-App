package code;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/student";;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aldebaran1711";

    private static BaseStudentDAO baseStudentDAO;
    private static  Connection connection;

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {

            baseStudentDAO = new BaseStudentDAO(connection);

            System.out.println("Welcome to the Student Management App");
            System.out.println("Please enter your name: ");
            String username = scanner.nextLine();

            System.out.println("Hi " + username + " Welcome to the Student Management App!");
            boolean condition = true;
            while(condition) {
                displayOption();
                System.out.print("Select an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        listStudentByID();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        viewStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        condition = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid input, please select a valid option: ");
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to run the system");
            e.printStackTrace();
        } finally {
            try {
               if (connection != null) {
                   connection.close();
                   System.out.println("Connection closed successfully!");
               }
            } catch (SQLException e) {
                System.out.println("Failed to closed connection");
                e.printStackTrace();
            }
        }

    }

    public static void displayOption() {
        try {
            System.out.println("--------------------------------------");
            System.out.println("1) Add student details");
            System.out.println("2) List student details by ID");
            System.out.println("3) Update student details");
            System.out.println("4) View student details");
            System.out.println("5) Delete student details");
            System.out.println("6) Exiting...");
            System.out.println("--------------------------------------");
        } catch (Exception e) {
            System.out.println("Failed to display option");
            e.printStackTrace();
        }
    }

    public static void addStudent() throws SQLException {
        System.out.println("Enter student name: ");
        String studentName = scanner.nextLine();

        System.out.println("Enter student email: ");
        String studentEmail = scanner.nextLine();

        System.out.println("Enter student age: ");
        int studentAge = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter student GPA: ");
        float studentGPA = scanner.nextFloat();
        scanner.nextLine();

        StudentClass studentClass = null;
        while (studentClass == null) {
            System.out.println("Enter student class (SCIENCE, SOCIAL, ENGINEERING, ART): ");
            String studentClassInput = scanner.nextLine();
            try {
                studentClass = StudentClass.valueOf(studentClassInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid class entered, please try again later");
            }
        }

        BaseStudentData baseStudentData = new BaseStudentData(0, studentName, studentEmail, studentAge, studentGPA, studentClass);
        if (baseStudentData != null) {
            baseStudentDAO.addStudent(baseStudentData);
        }

    }

    public static void listStudentByID() throws SQLException {
        System.out.println("Enter student ID you want to view: ");
        int studentID = scanner.nextInt();
        scanner.nextLine();

        BaseStudentData baseStudentData = baseStudentDAO.getStudentById(studentID);
        if (baseStudentData != null) {
            System.out.println(baseStudentData);
        } else {
            System.out.println("Failed to get a list of student");
        }
    }

    public static void updateStudent() throws SQLException {
        System.out.println("Enter student ID you want to update: ");
        int updateStudentID = scanner.nextInt();
        scanner.nextLine();

        BaseStudentData baseStudentData = baseStudentDAO.getStudentById(updateStudentID);
        if (baseStudentData != null) {
            System.out.println("Current student name: " + baseStudentData.getName());
            System.out.println("Enter new student name (or press Enter to keep current): ");
            String newName = scanner.nextLine();
            if (newName.isEmpty()) newName = baseStudentData.getName();

            System.out.println("Current student email: " + baseStudentData.getEmail());
            System.out.println("Enter new student email (or press Enter to keep current): ");
            String newEmail = scanner.nextLine();
            if (newEmail.isEmpty()) newEmail = baseStudentData.getEmail();

            System.out.println("Current student age: " + baseStudentData.getAge());
            System.out.println("Enter new student age (or press Enter to keep current): ");
            String ageInput = scanner.nextLine();
            int newAge = ageInput.isEmpty() ? baseStudentData.getAge() : Integer.parseInt(ageInput);

            System.out.println("Current student GPA: " + baseStudentData.getGPA());
            System.out.println("Enter new student GPA (or press Enter to keep current): ");
            String gpaInput = scanner.nextLine();
            float newGPA = gpaInput.isEmpty() ? baseStudentData.getGPA() : Float.parseFloat(gpaInput);

            System.out.println("Current student class: " + baseStudentData.getStudentClass());
            System.out.println("Enter new student class (or press Enter to keep current): ");
            String classInput = scanner.nextLine();
            StudentClass newClass = classInput.isEmpty() ? baseStudentData.getStudentClass() : StudentClass.valueOf(classInput.toUpperCase());

            baseStudentDAO.updateStudent(updateStudentID, newName, newEmail, newAge, newGPA, newClass.name());
            System.out.println("Student details updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static List<BaseStudentData> viewStudent() throws SQLException {
        List<BaseStudentData> baseStudentData = baseStudentDAO.getAllStudent();

        if (baseStudentData.isEmpty()) {
            System.out.println("The list was empty");
        } else {
            for (BaseStudentData baseStudentDataValue : baseStudentData) {
                System.out.println(baseStudentDataValue);
            }
        }

        return null;
    }

    public static void deleteStudent() throws SQLException{
        System.out.println("Enter student ID to be deleted: ");
        int deleteID = scanner.nextInt();
        scanner.nextLine();

        if (deleteID > 0) {
            baseStudentDAO.deleteStudentByID(deleteID);
            System.out.println("Successfully delete student details");
        }

    }


}
