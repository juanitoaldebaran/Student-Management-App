package code;

public enum StudentClass {
    SCIENCE,
    SOCIAL,
    ENGINEERING,
    ART;

    public static StudentClass fromString(String studentClass) {
        try {
            return StudentClass.valueOf(studentClass.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid class" + studentClass);
        }
    }

}
