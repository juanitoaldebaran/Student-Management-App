package code;

public class BaseStudentData implements StudentData{

    private int id;
    private String name;
    private String email;
    private int age;
    private float gpa;
    private String studentClass;

    public BaseStudentData(int id, String name, String email, int age, float gpa, String studentClass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gpa = gpa;
        this.studentClass = studentClass;
    }

    //Getter
    @Override
    public int getID(){
        return id;
    }

    //Setter
    public void setId(int id) {
        this.id = id;
    }

    //Getter
    @Override
    public String getName() {
        return name;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

    //Getter
    @Override
    public String getEmail() {
        return email;
    }

    //Setter
    public void setEmail(String email) {
        this.email = email;
    }

    //Getter
    @Override
    public int getAge() {
        return age;
    }

    //Setter
    public void setAge(int age) {
        this.age = age;
    }

    //Getter
    @Override
    public float getGPA() {
        return gpa;
    }

    //Setter
    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    //Getter
    @Override
    public String getStudentClass() {
        return studentClass;
    }

    //Setter
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public String toString() {
        return String.format("[ ID : %d | Name : %s | Email : %s | Age : %d | GPA : %.4f | Class : %s ]",
                id, name, email, age, gpa, studentClass);
    }

}
