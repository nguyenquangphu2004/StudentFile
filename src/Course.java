import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {

    public static int indexSubject = 0;
    public static int indexCourse = 0;
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
        this.studentOfGrades = new ArrayList<>();
    }

    public Course(String id, String name, String room, String time, Subject subject, ArrayList<StudentOfGrade> studentOfGrades) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.time = time;
        this.subject = subject;
        this.studentOfGrades = new ArrayList<>();

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

    public void addStudentOfCourse(Student student,Grade grade) {
        studentOfGrades.add(new StudentOfGrade(student,grade));
    }



    private class StudentOfGrade {
        private Student student;
        private Grade grade;

        public StudentOfGrade(Student student, Grade grade) {
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

//    Chuc nang cua lop hoc
//    thêm
    public Course addCourse(Scanner input,ArrayList<Subject> subjects,ArrayList<Course> courses) {
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
            if(checkSubject(idS,courses) == true) {
                System.out.println("===>Thêm thành công <===");
                return new Course(id, name, room, time, subject);
            } else {
                System.out.println("===> Mã môn đã tồn tại trong lớp <===");
                return null;
            }
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
    public static void readFileCOURSE(ArrayList<Course> courses,ArrayList<Subject> subjects) {
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
                printWriter.printf("%s;%s;%s;%s;%s\n",item.getId(),item.getName(),item.getRoom(),item.getTime(),item.getSubject().getId());

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
        System.out.println("3.Sửa thời gian.                        4.Sửa mã môn học.");
        System.out.println("Khác.Thoát.");
    }
    public void showMenuCourse() {
        System.out.println("1.Thêm mới lớp học.                 2.Lưu thông tin lớp học vào file");
        System.out.println("3.Hiển thị danh sách Lớp học.       4.Thêm sinh viên vào lớp học.");
        System.out.println("5.Sửa thông tin về lớp học.         6.Hiển thị danh sách sinh viên có trong lớp.");
        System.out.println("7.Hiển thị danh sách môn học        8.Tìm kiếm sinh viên có trong lớp hay không.");
        System.out.println("Khác.Thoát.                         9.Hiển thị bảng điểm của sinh viên trong lớp theo môn");
    }

    public void allOfFuntionCourse(ArrayList<Course> courses,Scanner input,ArrayList<Subject> subjects,ArrayList<Student> students) {
//        System.out.println("Mã lớp: ");
//        String id = input.nextLine();
//        var course = findOfCourse(id,courses);
//        if(course != null) {
            boolean check = true;
            while(check ) {
                showMenuCourse();
                System.out.println("Mời bạn chọn: ");
                int index = input.nextInt();
                input.nextLine();
                switch (index) {
                    case 1:
                        if(subjects.size() > 0) {
                            courses.add(addCourse(input, subjects, courses));
                        } else {
                            System.out.println("===> Thêm lớp thất bại <===");
                        }
                        break;
                    case 2:
                        writerFile(courses);
                        System.out.println("===>Lưu thành công <===");
                        break;
                    case 3:
                        showCourse(courses);
                        break;
                    case 4:
                        addStudentOfCourse(students,input,courses);
                        break;
                    case 5:
                        setCourse(input,courses,subjects);
                        break;
                    case 6:
                        System.out.printf("%-15s%-20s%-38s%-25s%-15s%-15s%-18s%-15s\n","Mã Sinh Viên","Họ Và Tên","Địa Chỉ","Email","Giới Tính","Số Điện Thoại","Tên Lớp","Chuyên Ngành");
                        for(int i = 0; i < courses.size(); i++) {
                            var item = courses.get(i).getStudentOfGrades().get(i).getStudent();
                            System.out.printf("%-15s%-20s%-38s%-25s%-15s%-15s%-18s%-15s\n",item.getId(),item.getFullName(),
                                                                                            item.getAddress(),item.getEmail(),
                                        item.getGender(),item.getPhone(),item.getNameClass(),item.getMajor());
                        }
                        break;
                    case 7:
                        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n","Mã Môn Học","Tên Môn Học","Số Tín Chỉ","Số Tiết Học","Số Kiểm Tra");
                        for (var item : courses) {
                            var item1 = item.getSubject();
                            System.out.printf("%-15s%-15s%-15s%-15s%-15s\n",item1.getId(),item1.getName(),item1.getCredit(),item1.getLesson(),item1.getTest());
                        }
                        break;
                    case 8:
                        searcheStudent(courses,input);
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("===> Sai chức năng <===");
                        check = false;
                }
            }

//        } else {
//            System.out.println("===> không tìm thấy mã lớp <=== ");
//        }
    }

    public Course findOfCourse(String id, ArrayList<Course> courses){
        for(int i = 0; i < courses.size(); i++) {
            if(id.compareTo(courses.get(i).getId()) == 0) {
                indexCourse = i;
                return courses.get(i);
            }
        }
        return null;
    }

    public boolean checkSubject(String id,ArrayList<Course> courses) {
        for(int i = 0; i < courses.size(); i++) {
            if(id.compareTo(courses.get(i).getSubject().getId()) == 0) {
                return false;
            }
        }
        return true;
    }

    public void setCourse(Scanner input,ArrayList<Course> courses,ArrayList<Subject> subjects) {
        System.out.println("Mã lớp học: ");
        String id = input.nextLine();
        var subject = findOfCourse(id,courses);
        if(subject != null) {
            boolean check = true;
            while(check) {
                 showSetMenuCourse();
                System.out.println("Mời bạn chọn: ");
                int check1 = input.nextInt();
                 switch (check1) {
                     case 1:
                         System.out.println("Tên lớp: ");
                         String name = input.nextLine();
                         courses.get(indexCourse).setName(name);
                         break;
                     case 2:
                         System.out.println("Tên phòng: ");
                         String room = input.nextLine();
                         courses.get(indexCourse).setRoom(room);
                         break;
                     case 3:
                         System.out.println("Thời gian: ");
                         String time = input.nextLine();
                         courses.get(indexCourse).setTime(time);
                         break;
                     case 4:
                         System.out.println("Mã môn học: ");
                         String idS = input.nextLine();
                         var sub = RunCode.findSubjectId(idS,subjects);
                         if(sub != null) {
                             courses.get(indexCourse).setSubject(sub);
                         } else {
                             System.out.println("===> Mã môn không hợp lệ <===");
                         }
                     default:
                         System.out.println("===> Chức năng không hợp lê.");
                         check = false;
                 }
            }
        } else {
            System.out.println("===> không tìm thấy lớp <===");
        }

    }
//    Thêm sinh viên
    public void addStudentOfCourse(ArrayList<Student> students,Scanner input,ArrayList<Course> courses) {
        System.out.println("Nhập mã sinh viên: ");
        String idStudent = input.nextLine();

        var student = RunCode.findStudentOfId(idStudent,students);
        if(student != null) {
            if(checkStudentOfCourse(idStudent,courses) == true){
                addStudentOfCourse(student,null);
                System.out.println("===> Thêm sinh viên thành công <===");
            }

        } else {
            System.out.println("===> Mã sinh viên không hợp lệ <===");
        }
    }
    public boolean checkStudentOfCourse(String id,ArrayList<Course> courses) {
        for(int i = 0; i < courses.size(); i++) {
            if(id.compareTo(courses.get(i).getStudentOfGrades().get(i).getStudent().getId()) == 0) {
                return false;
            }
        }
        return true;
    }

    public void searcheStudent(ArrayList<Course> courses,Scanner input) {
        System.out.println("Nhập mã sinh viên: ");
        String id = input.nextLine();
        for(int i = 0; i< courses.size();i++) {
            if(id.compareTo(courses.get(i).getStudentOfGrades().get(i).getStudent().getId()) == 0) {
                System.out.printf("%-15s%-20s%-38s%-25s%-15s%-15s%-18s%-15s\n","Mã Sinh Viên","Họ Và Tên","Địa Chỉ","Email","Giới Tính","Số Điện Thoại","Tên Lớp","Chuyên Ngành");
                    var item = courses.get(i).getStudentOfGrades().get(i).getStudent();
                    System.out.printf("%-15s%-20s%-38s%-25s%-15s%-15s%-18s%-15s\n",item.getId(),item.getFullName(),
                            item.getAddress(),item.getEmail(),
                            item.getGender(),item.getPhone(),item.getNameClass(),item.getMajor());
                break;
            }
        }
        System.out.println("===> Không tìm thấy <===");


    }




}
