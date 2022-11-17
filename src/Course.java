import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    public static int indexSubject = 0;
    public static int indexCourse = 0;
    public static int indexOfStudenttoCourse = 0;
    public static String idStr = "COU";
    public static int idN = 1001;
    private String id;
    private String name;
    private String room;
    private String time;
    private Subject subject;
    private ArrayList<StudentOfGrade> studentOfGrades;

    public Course() {
        id = "";
        name = "";
        room = "";
        time = "";
        subject = null;
        studentOfGrades = new ArrayList<>();

    }

    public Course(String id, String name, String room, String time, Subject subject) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.time = time;
        this.subject = subject;
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = idStr + idN;
        idN++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ArrayList<StudentOfGrade> getStudentOfGrades() {
        return studentOfGrades;
    }

    public void addStudentofCourse(Student student) {
        studentOfGrades = new ArrayList<>();
        studentOfGrades.add(new StudentOfGrade(student,null));
    }
    public void addStudentofCourse(Student student,Grade grade) {
        studentOfGrades = new ArrayList<>();
        studentOfGrades.add(new StudentOfGrade(student,grade));
    }


    public void deleteStudent(Student student) {
        studentOfGrades.remove(new StudentOfGrade(student,null));
    }

    public class StudentOfGrade {
        private Student student;
        private Grade grade;

        public StudentOfGrade(Student student,Grade grade) {
            this.student = student;
            this.grade = grade;

        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public Grade getGrade() {
            return grade;
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }
    }
}
