import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
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

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = idStr + idN;
        idN ++;
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

    public void setStudentOfGrades(ArrayList<StudentOfGrade> studentOfGrades) {
        this.studentOfGrades = studentOfGrades;
    }

    private class StudentOfGrade {
        private Student student;
        private Grade grade;
    }






//    Chuc nang cua lop hoc
//    thêm
    public Course addCourse(Scanner input,ArrayList<Subject> subjects) {
        System.out.println("Mã lớp: ");
        setId();
        String id = getId();
        System.out.println(id);
        System.out.println("Tên lớp: ");
        String name = input.nextLine();

        System.out.println("Phòng học: ");
        String room = input.nextLine();

        System.out.println("Thời gian: ");
        String time = input.nextLine();

        System.out.println("Mã môn: ");
        String idS = input.nextLine();
        var subject = RunCode.findSubjectId(idS,subjects);
        if(subject != null ){
            System.out.println("===>Thêm thành công <===");
            return new Course(id,name,room,time,subject);
        } else {
            System.out.println("===>Mã môn không hợp lệ <===");
            return null;
        }
    }
//    Hiển thị lớp
    public void showCourse(ArrayList<Course> courses) {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n","Mã Lớp","Tên Lớp","Phòng Học","Thời Gian","Mã Môn");
        for (var item :
                courses) {
            showList(item);
        }
    }
    public void showList(Course item) {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n",item.getId(),item.getName(),item.getRoom(),item.getTime(),item.getSubject().getId());
    }

//    đỌC file COURSE.DAT;
    public void readFileCOURSE(ArrayList<Course> courses,ArrayList<Subject> subjects) {
        File file = new File("COURSE.DAT");
        try {
            var readFile = new Scanner(file);
            while(readFile.hasNextLine()) {
                var words = readFile.nextLine().split(";");
                String id = words[0];
                String name = words[1];
                String room = words[2];
                String time = words[3];
                String ids = words[4];
                var check = RunCode.findSubjectId(ids,subjects);
                courses.add(new Course(id,name,room,time,check));
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

//Ghi dữ liệu vào file
    public void writerFile(ArrayList<Course>courses) {
        try {
            FileWriter fileWriter = new FileWriter("COURSE.DAT");
            PrintWriter printWriter = new PrintWriter(fileWriter,true);
            for(int i = 0; i < courses.size();i++) {
                var item = courses.get(i);
                printWriter.printf("%s%s%s%s%s\n",item.getId(),item.getName(),item.getRoom(),item.getTime(),item.getSubject().getId());

            }
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    Sửa thông tin về môn học.
    public void showSetMenuCourse() {
        System.out.println("1.Sửa tên lớp.                          2.Sửa tên phòng."         );
        System.out.println("3.Sửa thời gian.                        3.Sửa mã môn học.");
        System.out.println("Khác.Thoát.");
    }
    public void showMenuCourse() {
        System.out.println("1.Thêm mới lớp học.                 2.Lưu thông tin lớp học vào file");
        System.out.println("3.Hiển thị danh sách môn học        4.Thêm sinh viên vào lớp học.");
        System.out.println("5.Sửa thông tin về lớp học.         6.Hiển thị danh sách sinh viên có trong lớp.");
        System.out.println("7.Hiển thị danh sách môn học        8.Tìm kiếm sinh viên có trong lớp hay không.");
        System.out.println("Khác.Thoát.                         9.Hiển thị bảng điểm của sinh viên trong lớp theo môn");
    }







}
