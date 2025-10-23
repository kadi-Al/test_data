Java\npublic class Student {\n    int studentId;\n    String studentName;\n    double grades[]; \n\n    public Student(int id, String name, double[] grades){\n        this.studentId = id;\n        this.studentName = name;\n        this.grades = grades;\n    }\n    public double averageGrade(){\n        double sum = 0;\n        for(int i=0;i<grades.length;i++){\n            sum+= grades[i];\n            return sum/grades.length;\n        }\n    }\n}\n
Java
import java.util.Arrays;

public class Student {
    private int studentId;
    private String studentName;
    private double[] grades;

    public Student(int id, String name, double[] grades) {
        this.studentId = id;
        this.studentName = name;
        if (grades == null || grades.length != 5) {
            throw new IllegalArgumentException("A student must have exactly five grades.");
        }
        this.grades = Arrays.copyOf(grades, grades.length); // Ensure the array is not mutable outside the class
    }

    public double averageGrade() {
        if (grades == null || grades.length == 0) {
            throw new IllegalArgumentException("No grades provided for the student.");
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }

    public static void main(String[] args) {
        // Example usage:
        try {
            Student student1 = new Student(1, "Alice", new double[]{85, 90, 78, 92, 88});
            System.out.println("Student ID: " + student1.studentId);
            System.out.println("Student Name: " + student1.studentName);
            System.out.println("Average Grade: " + student1.averageGrade());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            Student student2 = new Student(2, "Bob", new double[]{80, 85}); // Incomplete grades
            System.out.println("Student ID: " + student2.studentId);
            System.out.println("Student Name: " + student2.studentName);
            System.out.println("Average Grade: " + student2.averageGrade());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}