import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.*;
import java.net.CookiePolicy;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class RunCode {
    public static ArrayList<Integer> addIndex = new ArrayList<>();
    public static Course course = new Course();
    public static int indexSubject = 0;
    public static Student student = new Student();
    public static int indexStudent = 0;
    public static Grade grade = new Grade();
    public static ArrayList<Grade> grades = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        var input = new Scanner(System.in);
        ArrayList<Subject> subjects = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        Subject subject = new Subject();
        boolean check = true;
//        đọc dữ liêụ từ file cua subject
        File fileSubject = new File("SUB.DAT");
        Scanner readFileSubject = new Scanner(fileSubject);
        while (readFileSubject.hasNextLine()) {
            String words = readFileSubject.nextLine();
            var str = words.split(";");
            subject.setId();
            String id = subject.getId();
            Subject.idN++;
            String name = str[1];
            int credit = Integer.parseInt(str[2]);
            int les = Integer.parseInt(str[3]);
            int tes = Integer.parseInt(str[4]);
            subjects.add(new Subject(id, name, credit, les, tes));
        }
        readFileSubject.close();
//        đọc file từ file cửa student;
        File fileStudent = new File("STU.DAT");
        Scanner readFileStudent = new Scanner(fileStudent);
        while (readFileStudent.hasNextLine()) {
            var words1 = readFileStudent.nextLine();
            var str1 = words1.split(";");
            String id = str1[0];
            String fullName = str1[1];
            String address = str1[2];
            String email = str1[3];
            String gender = str1[4];
            String phone = str1[5];
            String nameclass = str1[6];
            String major = str1[7];
            students.add(new Student(id, fullName, address, email, gender, phone, nameclass, major));

        }
        readFileSubject.close();

//        đỌC FILE TỪ SUBJECT
        readFileCOURSE(courses, subjects);


        readFileCourseStudent(courses);









        while (check) {
            showMenu();
            System.out.println("Mời bạn lựa chọn: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    boolean check1 = true;
                    while (check1) {
                        showSubject();
                        System.out.println("Mời bạn chọn: ");
                        int k = input.nextInt();
                        input.nextLine();
                        switch (k) {
                            case 1:
                                subjects.add(addSubject(input));
                                break;
                            case 2:
                                FileWriter fileWriter = new FileWriter("SUB.DAT");
                                PrintWriter printWriter = new PrintWriter(fileWriter, true);
                                for (int i = 0; i < subjects.size(); i++) {
                                    var item = subjects.get(i);
                                    printWriter.println(item.getId() + ";" + item.getName() + ";"
                                            + item.getCredit() + ";" + item.getLesson() +
                                            ";" + item.getTest());
                                }
                                printWriter.close();
                                fileWriter.close();
                                System.out.println("Lưu thành công.");

                                break;
                            case 3:
                                setSubject(input, subjects);
                                break;
                            case 5:
                                showListSubject(subjects);
                                break;
                            default:
                                System.out.println("Không hợp lệ.");
                                check1 = false;
                        }
                    }
                    break;
                case 2:
                    boolean check2 = true;
                    while (check2) {
                        showMenuStudent();
                        System.out.println("Mời bạn chọn: ");
                        int k = input.nextInt();
                        input.nextLine();
                        switch (k) {
                            case 1:
                                students.add(addStudent(input));
                                break;
                            case 2:
                                FileWriter fileWriter = new FileWriter("STU.DAT");
                                PrintWriter printWriter = new PrintWriter(fileWriter, true);
                                for (int i = 0; i < students.size(); i++) {
                                    var item = students.get(i);
                                    printWriter.printf("%s;%s;%s;%s;%s;%s;%s;%s\n", item.getId(), item.getFullName(), item.getAddress(), item.getEmail(), item.getGender(), item.getPhone(), item.getNameClass(), item.getMajor());
                                }
                                printWriter.close();
                                fileWriter.close();
                                System.out.println("===>Lưu thành công <===");
                                break;
                            case 3:
                                showStudent(students);
                                break;
                            case 4:
                                setStudent(input, students);
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("===> KHÔNG HỢP LỆ <===");
                                check2 = false;
                        }
                    }
                    break;
                case 3:
                    allOfFuntionCourse(courses, input, subjects, students);
                    break;
                case 4:
                    funtionGrade(input,courses,students);
                    break;
                default:
                    System.out.println("Bạn đã thoát");
            }
        }
    }

    public static void showMenu() {
        System.out.println("1.Thông tin về môn học.");
        System.out.println("2.Thông tin về sinh viên.");
        System.out.println("3.Thông tin về lớp học.");
        System.out.println("4.Thông tin về điểm.");
        System.out.println("Khác.Thoát.");
    }

    public static void showSubject() {
        System.out.println("1.Thêm mới một môn học.");
        System.out.println("2.Lưu môn học vào file");
        System.out.println("3.Sửa thông tin môn học.");
        System.out.println("4.Xóa môn học.");
        System.out.println("5.Hiển thị danh sách môn học.");
        System.out.println("Khác.Thoát.");
    }

    private static void showListSubject(ArrayList<Subject> subjects) {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Mã Môn Học", "Tên Môn Học", "Số Tín Chỉ", "Số Tiết Học", "Số Kiểm Tra");
        for (var item :
                subjects)
            showlist(item);
    }

    private static void showlist(Subject item) {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", item.getId(), item.getName(), item.getCredit(), item.getLesson(), item.getTest());
    }

    public static Subject addSubject(Scanner input) {
        Subject subject = new Subject();
        System.out.println("Mã môn học: ");
        subject.setId();
        String id = subject.getId();
        System.out.println(id);
        Subject.idN++;
        System.out.println("Tên môn học: ");
        String name = input.nextLine();
        System.out.println("Số tín chỉ: ");
        int cre = input.nextInt();
        System.out.println("Số tiết học: ");
        int les = input.nextInt();
        System.out.println("Số bài kiểm tra: ");
        int test = input.nextInt();
        input.nextLine();
        return new Subject(id, name, cre, les, test);
    }

    public static void setSubject(Scanner input, ArrayList<Subject> subjects) {
        System.out.println("Nhập mã môn học cần sửa: ");
        String id = input.nextLine();
        var subject = findSubjectId(id, subjects);
        if (subject != null) {
            boolean check = true;
            while (check) {
                showSetSubject();
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Nhập tên: ");
                        String name = input.nextLine();
                        subjects.get(indexSubject).setName(name);
                        break;
                    case 2:
                        System.out.println("Số tín chỉ: ");
                        int cre = input.nextInt();
                        subjects.get(indexStudent).setCredit(cre);
                        break;
                    case 3:
                        System.out.println("Số tiết học: ");
                        int le = input.nextInt();
                        subjects.get(indexSubject).setLesson(le);
                        break;
                    case 4:
                        System.out.println("Số bài kiểm tra: ");
                        int te = input.nextInt();
                        subjects.get(indexSubject).setTest(te);
                        break;
                    default:
                        System.out.println("Sai chức năng");
                        check = false;
                }
            }
        } else {
            System.out.println("Mã không hợp lệ.");
        }

    }

    private static void showSetSubject() {
        System.out.println("1.Sửa tên môn.");
        System.out.println("2.Sửa số tín chỉ.");
        System.out.println("3.Sửa số tiết học.");
        System.out.println("4.Sửa số bài kiểm tra.");
        System.out.println("Khác.Thoát.");
    }

    public static Subject findSubjectId(String id, ArrayList<Subject> subjects) {
        for (int i = 0; i < subjects.size(); i++) {
            if (id.compareTo(subjects.get(i).getId()) == 0) {
                indexSubject = i;
                return subjects.get(i);
            }
        }
        return null;
    }


    //    CHỨC NĂNG CỦA SINH VIÊN
    public static Student addStudent(Scanner input) {
        System.out.println("Mã sinh viên: ");
        student.setId();
        String id = student.getId();
        Student.idN++;
        System.out.println(id);
        System.out.println("Họ và tên: ");
        String fullName = input.nextLine();

        System.out.println("Địa chỉ: ");
        String address = input.nextLine();

        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Giới tính: ");
        var gender = input.nextLine();
        System.out.println("Số điện thoại: ");
        var phone = input.nextLine();
        System.out.println("Tên lớp: ");
        var nameClass = input.nextLine();
        System.out.println("Chuyên ngành: ");
        var major = input.nextLine();

        return new Student(id, fullName, address, email, gender, phone, nameClass, major);

    }

    public static void showStudent(ArrayList<Student> students) {
        System.out.printf("%-15s%-20s%-38s%-25s%-15s%-15s%-18s%-15s\n", "Mã Sinh Viên", "Họ Và Tên", "Địa Chỉ", "Email", "Giới Tính", "Số Điện Thoại", "Tên Lớp", "Chuyên Ngành");
        for (var item :
                students) {
            showListStudent(item);
        }
    }

    private static void showListStudent(Student item) {
        System.out.printf("%-15s%-20s%-38s%-25s%-15s%-15s%-18s%-15s\n", item.getId(), item.getFullName(), item.getAddress(), item.getEmail(), item.getGender(), item.getPhone(), item.getNameClass(), item.getMajor());
    }

    public static void showMenuSetStudent() {
        System.out.println("1.Sửa Họ và tên.                            2.Sửa địa chỉ.");
        System.out.println("3.Sửa email.                                4.Sửa giới tính.        ");
        System.out.println("5.Sửa số điện thoại.                        6.Sửa tên lớp.");
        System.out.println("7.Sửa chuyên ngành.                         Khác.Thoát.");

    }

    public static void setStudent(Scanner input, ArrayList<Student> students) {
        System.out.println("Mã sinh viên: ");
        String id = input.nextLine();

        var student = findStudentOfId(id, students);// STOPSHIP: 11/16/2022
        if (student != null) {
            boolean check = true;
            while (check) {
                showMenuSetStudent();
                System.out.println("===> Mời bạn chọn: <===");
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Họ và tên: ");
                        String name = input.nextLine();
                        students.get(indexStudent).setFullName(name);
                        break;
                    case 2:
                        System.out.println("Địa chỉ: ");
                        String address = input.nextLine();
                        students.get(indexStudent).setAddress(address);
                        break;
                    case 3:
                        System.out.println("Email: ");
                        String email = input.nextLine();
                        students.get(indexStudent).setEmail(email);
                        break;
                    case 4:
                        System.out.println("Giới tính: ");
                        String gender = input.nextLine();
                        students.get(indexStudent).setGender(gender);
                        break;
                    case 5:
                        System.out.println("Số điện thoại: ");
                        String phone = input.nextLine();
                        students.get(indexStudent).setPhone(phone);
                        break;
                    case 6:
                        System.out.println("Lớp: ");
                        String classN = input.nextLine();
                        students.get(indexStudent).setNameClass(classN);
                        break;
                    case 7:
                        System.out.println("Chuyên ngành: ");
                        String major = input.nextLine();
                        students.get(indexStudent).setMajor(major);
                        break;
                    default:
                        System.out.println("===> SAI CHỨC NĂNG <===");
                        check = false;
                }
            }
        } else {
            System.out.println("===>Mã không hợp lệ<===");
        }
    }

    public static void showMenuStudent() {
        System.out.println("1.Thêm mới sinh viên.");
        System.out.println("2.Lưu sinh viên vào file.");
        System.out.println("3.Hiển thị danh sách sinh viên.");
        System.out.println("4.Sửa thông tin sinh viên.");
        System.out.println("5.Xóa sinh viên.");
    }

    public static Student findStudentOfId(String id, ArrayList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            if (id.compareTo(students.get(i).getId()) == 0) {
                indexStudent = i;
                return students.get(i);
            }
        }
        return null;
    }

    //        Về lớp học.
    public static Course addCourse(Scanner input, ArrayList<Subject> subjects, ArrayList<Course> courses) {
        System.out.println("Mã lớp: ");
        course.setId();
        String id = course.getId();
        System.out.println(id);
        System.out.println("Tên lớp: ");
        String name = input.nextLine();

        System.out.println("Phòng học: ");
        String room = input.nextLine();

        System.out.println("Thời gian: ");
        String time = input.nextLine();

        System.out.println("Mã môn: ");
        String idS = input.nextLine();
        var subject = RunCode.findSubjectId(idS, subjects);
        if (subject != null) {
            if (checkSubject(idS, courses) == true) {
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

    public static void showCourse(ArrayList<Course> courses) {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Mã Lớp", "Tên Lớp", "Phòng Học", "Thời Gian", "Mã Môn");
        for (var item :
                courses) {
            showList(item);
        }
    }

    public static void showList(Course item) {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", item.getId(), item.getName(), item.getRoom(), item.getTime(), item.getSubject().getId());
    }

    public static void readFileCOURSE(ArrayList<Course> courses, ArrayList<Subject> subjects) {
        File file = new File("COURSE.DAT");
        try {
            var readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                var words = readFile.nextLine().split(";");
                String id = words[0];
                String name = words[1];
                String room = words[2];
                String time = words[3];
                String ids = words[4];
                var check = RunCode.findSubjectId(ids, subjects);
                courses.add(new Course(id, name, room, time, check));
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writerFile(ArrayList<Course> courses) {
        try {
            FileWriter fileWriter = new FileWriter("COURSE.DAT");
            PrintWriter printWriter = new PrintWriter(fileWriter, true);
            for (int i = 0; i < courses.size(); i++) {
                var item = courses.get(i);
                printWriter.printf("%s;%s;%s;%s;%s\n", item.getId(), item.getName(), item.getRoom(), item.getTime(), item.getSubject().getId());

            }
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void showSetMenuCourse() {
        System.out.println("1.Sửa tên lớp.                          2.Sửa tên phòng.");
        System.out.println("3.Sửa thời gian.                        4.Sửa mã môn học.");
        System.out.println("Khác.Thoát.");
    }

    public static void showMenuCourse() {
        System.out.println("1.Thêm mới lớp học.                 2.Lưu thông tin lớp học vào file");
        System.out.println("3.Hiển thị danh sách Lớp học.       4.Thêm sinh viên vào lớp học.");
        System.out.println("5.Sửa thông tin về lớp học.         6.Hiển thị danh sách sinh viên có trong lớp.");
        System.out.println("7.Hiển thị danh sách môn học        8.Tìm kiếm sinh viên có trong lớp hay không.");
        System.out.println("10.Lưu sinh viên vào file.          9.Hiển thị bảng điểm của sinh viên trong lớp theo môn");
        System.out.println("Khác.Thoát.                         11.Xóa học sinh khỏi lớp.");
    }

    public static void allOfFuntionCourse(ArrayList<Course> courses, Scanner input, ArrayList<Subject> subjects, ArrayList<Student> students) {
//        System.out.println("Mã lớp: ");
//        String id = input.nextLine();
//        var course = findOfCourse(id,courses);
//        if(course != null) {
        boolean check = true;
        while (check) {
            showMenuCourse();
            System.out.println("Mời bạn chọn: ");
            int index = input.nextInt();
            input.nextLine();
            switch (index) {
                case 1:
                    if (subjects.size() > 0) {
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
                    setCourse(input, courses, subjects);
                    break;
                case 6:
                    System.out.println("Nhập mã lớp: ");
                    String idCc = input.nextLine();
                    var cour = findOfCourse(idCc, courses);
                    if (cour != null) {
                        showStudentToCourse(Course.indexCourse,idCc,courses);
                    } else {
                        System.out.println("===> Mã lớp không hợp lệ <===");
                    }
                    break;
                case 7:
                    System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Mã Môn Học", "Tên Môn Học", "Số Tín Chỉ", "Số Tiết Học", "Số Kiểm Tra");
                    for (var item : courses) {
                        var item1 = item.getSubject();
                        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", item1.getId(), item1.getName(), item1.getCredit(), item1.getLesson(), item1.getTest());
                    }
                    break;
                case 8:
//                    var ck =searchStudent(courses, input);
//                    if(ck = false) {
//                        System.out.println("===>Không tìm thấy sinh viên <===");
//                    }
//                    break;
                case 9:
                    break;
                case 10:
                    writerFileCourseStudent(courses,input);
                    break;
                case 11:
                    deleteStudentOfCourse(input,courses,students);
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

    public static Course findOfCourse(String id, ArrayList<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            if (id.compareTo(courses.get(i).getId()) == 0) {
                Course.indexCourse = i;
                return courses.get(i);
            }
        }
        return null;
    }

    public static boolean checkSubject(String id, ArrayList<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            if (id.compareTo(courses.get(i).getSubject().getId()) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void setCourse(Scanner input, ArrayList<Course> courses, ArrayList<Subject> subjects) {
        System.out.println("Mã lớp học: ");
        String id = input.nextLine();
        var subject = findOfCourse(id, courses);
        if (subject != null) {
            boolean check = true;
            while (check) {
                showSetMenuCourse();
                System.out.println("Mời bạn chọn: ");
                int check1 = input.nextInt();
                switch (check1) {
                    case 1:
                        System.out.println("Tên lớp: ");
                        String name = input.nextLine();
                        courses.get(Course.indexCourse).setName(name);
                        break;
                    case 2:
                        System.out.println("Tên phòng: ");
                        String room = input.nextLine();
                        courses.get(Course.indexCourse).setRoom(room);
                        break;
                    case 3:
                        System.out.println("Thời gian: ");
                        String time = input.nextLine();
                        courses.get(Course.indexCourse).setTime(time);
                        break;
                    case 4:
                        System.out.println("Mã môn học: ");
                        String idS = input.nextLine();
                        var sub = findSubjectId(idS, subjects);
                        if (sub != null) {
                            courses.get(Course.indexCourse).setSubject(sub);
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
    public static void addStudentOfCourse(ArrayList<Student> students,Scanner input,ArrayList<Course> courses) {
        System.out.println("Nhập mã lớp: ");
        String idC = input.nextLine();
        var cours = findOfCourse(idC,courses);
        if(cours != null) {
            System.out.println("Nhập mã sinh viên: ");
            String idStudent = input.nextLine();
            var student = findStudentOfId(idStudent, students);
            if (student != null) {
//                if(checkStudentOfCourse(idStudent,courses) == true) {
                    courses.get(Course.indexCourse).addStudentofCourse(student,null);
                    System.out.println("===> Thêm sinh viên thành công <===");
//                } else {
//                    System.out.println("===>Sinh viên đã tồn tại trong lớp or trong lớp khác <===");
//                }
            } else {
                System.out.println("===> Mã sinh viên không hợp lệ <===");
            }
        } else {
            System.out.println("===> Không tìm thấy lớp <===");
        }
    }

    public static void showStudentToCourse(int index,String id,ArrayList<Course> courses) {
        System.out.printf("%-15s%-15s%-20s%-25s%-15s%-18s%-15s\n", "Mã lớp", "Mã Sinh Viên", "Họ Và Tên", "Email", "Giới Tính", "Tên Lớp", "Chuyên Ngành");

        for (int i = 0; i < courses.get(index).getStudentOfGrades().size(); i++) {
                var item0 = courses.get(i).getId();
                var item = courses.get(i).getStudentOfGrades().get(i).getStudent();
                System.out.printf("%-15s%-15s%-20s%-25s%-15s%-18s%-15s\n", item0, item.getId(), item.getFullName(),
                        item.getEmail(),
                        item.getGender(), item.getNameClass(), item.getMajor());
        }
    }

//    public static boolean checkStudentOfCourse(String id, ArrayList<Course> courses) {
//        for (int i = 0; i < courses.get(Course.indexCourse).getStudentOfGrades().size(); i++) {
//            if (id.compareTo(courses.get(Course.indexCourse).getStudentOfGrades().get(i).getStudent().getId()) == 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static Student searchStudent(ArrayList<Course> courses, String id) {
//        System.out.println("Nhập mã sinh viên: ");
//        String id = input.nextLine();
        for (int i = 0; i < courses.get(Course.indexCourse).getStudentOfGrades().size(); i++) {
            if (id.compareTo(courses.get(Course.indexCourse).getStudentOfGrades().get(i).getStudent().getId()) == 0) {
//                System.out.printf("%-15s%-15s%-20s%-25s%-15s%-18s%-15s\n", "Mã lớp","Mã Sinh Viên", "Họ Và Tên", "Email", "Giới Tính", "Tên Lớp", "Chuyên Ngành");
//                var item = courses.get(Course.indexCourse).getStudentOfGrades().get(i).getStudent();
//                System.out.printf("-15s%-15s%-20s%-25s%-15s%-18s%-15s\n",courses.get(Course.indexCourse).getId(), item.getId(), item.getFullName(),
//                         item.getEmail(),
//                        item.getGender(), item.getNameClass(), item.getMajor());
                return courses.get(Course.indexCourse).getStudentOfGrades().get(i).getStudent();
            }
        }
        return null;
    }


//    Ghi Lưu sinh viên trong lớp vào file
    public static void readFileCourseStudent(ArrayList<Course> courses) {
        File file = new File("COURSESTUDENT.DAT");
        try {
            var readFile = new Scanner(file);
            while(readFile.hasNextLine()) {
                var words1 = readFile.nextLine();
                var str1 = words1.split(";");
                String id = str1[0];
                String fullName = str1[1];
                String email = str1[2];
                String gender = str1[3];
                String nameclass = str1[4];
                String major = str1[5];
                courses.get(Course.indexCourse).addStudentofCourse(new Student(id,fullName,email,gender,nameclass,major),null);
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writerFileCourseStudent(ArrayList<Course> courses,Scanner input) {
        System.out.println("Nhập mã lớp: ");
        String id  = input.nextLine();
        var cour = findOfCourse(id,courses);
        if(cour != null) {
            try {
                FileWriter fileWriter = new FileWriter("COURSESTUDENT.DAT");
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (int i = 0; i < courses.get(Course.indexCourse).getStudentOfGrades().size(); i++) {
                    var item0 = courses.get(Course.indexCourse);
                    var item = courses.get(Course.indexCourse).getStudentOfGrades().get(i).getStudent();
                    printWriter.printf("%s;%s;%s;%s;%s;%s\n",item.getId(), item.getFullName(),item.getEmail(), item.getGender(), item.getNameClass(), item.getMajor());
                }
                printWriter.close();
                fileWriter.close();
                System.out.println("===>Lưu thành công <===");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("===>Mã lớp không hợp lệ <===");
        }
    }

    public static void deleteStudentOfCourse(Scanner input,ArrayList<Course> courses,ArrayList<Student> students) {
        System.out.println("Nhập mã lớp học: ");
        String id = input.nextLine();
        var course = findOfCourse(id,courses);
        if(course != null) {
            System.out.println("Mã sinh viên: ");
            String idS = input.nextLine();
            var student = findStudentOfId(idS,students);
            if(student != null) {
                for (int i = 0; i < courses.get(Course.indexCourse).getStudentOfGrades().size(); i++) {
                    var item = courses.get(Course.indexCourse).getStudentOfGrades().get(i).getStudent().getId();
                    if (idS.compareTo(item) == 0) {
                        courses.get(Course.indexCourse).deleteStudent(student);
                        System.out.println("===>Xóa thành công<===");
                        break;

                    }
                }
            } else {
                System.out.println("===> Mã không hợp lệ <====");
            }
        } else {
            System.out.println("===> Mã lớp không hợp lệ <===");
        }

    }



    public static void addGradeofStudent(Scanner input,ArrayList<Course> courses,ArrayList<Student> students) {
        System.out.println("Nhập điểm cho lớp: ");
        String idCourse = input.nextLine();
        var cour = findOfCourse(idCourse,courses);
        if(cour != null) {
            System.out.println("Mã sinh viên: ");
            String idStudent = input.nextLine();
            var student = searchStudent(courses,idStudent);
            if(student != null) {
                System.out.println("Mã bảng điểm: ");
                grade.setId();
                Grade.idN ++;
                String id = grade.getId();
                System.out.println(id);
                System.out.println("Điểm hệ 1: ");
                double grade1  = input.nextDouble();
                System.out.println("Điểm hệ 2: ");
                double grade2 = input.nextDouble();
                System.out.println("Điểm hệ 3: ");
                double grade3 = input.nextDouble();
                input.nextLine();
                grade.setGrade1(grade1);
                grade.setGrade2(grade2);
                grade.setGrade3(grade3);
                grade.setAvrGrade();
                double aveGrade = grade.getAvrGrade();
                System.out.printf("Điểm trung bình: %.2f\n",aveGrade);
                var study = grade.studyStudent(aveGrade);
                Grade a = new Grade(id,grade1,grade2,grade3,aveGrade,study);
                courses.get(Course.indexCourse).addStudentofCourse(student,a);
                Grade.indexGrade = 1;
                System.out.println("===> Nhập điểm thành công <===");

            } else {
                System.out.println("===> Mã sinh viên không hợp lệ <===");
            }

        } else {
            System.out.println("===> Mã lớp không hợp lệ <===");
        }
    }

    public static void funtionGrade(Scanner input,ArrayList<Course> courses,ArrayList<Student> students) {
        boolean check = true;
        while(check) {
            showMenuGrade();
            System.out.println("Mời bạn chọn: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    addGradeofStudent(input,courses,students);
                    break;
                case 2:
                    setGradeOfStudent(courses,input);
                    break;
                case 3:
                    showAllGradeOfCourse(courses);
                    break;
                case 4:
                    showGradeofCourse(courses,input);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("===>Sai chức năng <===");
                    check = false;
            }

        }
    }

    public static void showMenuGrade() {
        System.out.println("1.Nhập điểm cho từng sinh viên có trong lớp                 2.Sửa điểm của sinh viên."    );
        System.out.println("3.Hiển thị tất cả bảng điểm của các lớp học.                4.Hiển thị bảng điểm lớp học theo mã lớp"  );
        System.out.println("4.Tìm kiếm điểm của sinh viên theo mã.                      5.Sắp xếp bảng điểm theo điểm trung bình giảm dần.");
        System.out.println("Khác.Thoát.");
    }
    public static void showGradeofCourse(ArrayList<Course> courses,Scanner input) {
        if(Grade.indexGrade != 0) {
            System.out.println("Nhập mã lớp: ");
            String id = input.nextLine();
            var course = findOfCourse(id, courses);
            if (course != null) {
                System.out.printf("%-10s%-15s%-20s%-20s%-15s%-15s%-15s%-19s%-10s\n", "Mã lớp", "Mã bảng điểm", "Mã sinh viên", "Họ và tên", "Điểm hệ 1", "Điểm hệ 2", "Điểm hệ 3", "Điểm trung bình", "Học lực");
                for (int i = 0; i < courses.get(Course.indexCourse).getStudentOfGrades().size(); i++) {
                    var item1 = courses.get(Course.indexCourse).getStudentOfGrades().get(i).getStudent();
                    var item2  = courses.get(Course.indexCourse).getStudentOfGrades().get(i).getGrade();
                    System.out.printf("%-10s%-15s%-20s%-20s%-15.2f%-15.2f%-15.2f%-19.2f%-10s\n", courses.get(Course.indexCourse).getId(), item2.getId(), item1.getId(), item1.getFullName(), item2.getGrade1(), item2.getGrade2(), item2.getGrade3(), item2.getAvrGrade(), item2.studyStudent(item2.getAvrGrade()));
                }

            } else {
                System.out.println("===> Mã lớp không hợp lệ <===");
            }
        } else {
            System.out.println("===>Chưa có lớp nào có điểm <===");
        }


    }

//    Sửa điểm cho sinh viên;

    public static void setGradeOfStudent(ArrayList<Course> courses,Scanner input) {
        System.out.println("Nhập mã lớp: ");
        String idCourse = input.nextLine();
        var course = findOfCourse(idCourse,courses);

        if(course != null) {
            System.out.println("Mã sinh viên: ");
            String id = input.nextLine();
            for(int i = 0; i < courses.get(Course.indexCourse).getStudentOfGrades().size(); i++) {
                if(id.compareTo(courses.get(Course.indexCourse).getStudentOfGrades().get(i).getStudent().getId()) == 0) {
                    boolean check = true;
                    while (check) {
                        showSetMenuGrade();
                        System.out.println("Mời bạn chọn: ");
                        int choice = input.nextInt();
                        input.nextLine();
                        switch (choice) {
                            case 1:
                                System.out.println("Điểm hệ 1: ");
                                double grade1 = input.nextDouble();
                                courses.get(Course.indexCourse).getStudentOfGrades().get(i).getGrade().setGrade1(grade1);
                                System.out.println("Điểm trung bình: ");
                                System.out.println(courses.get(Course.indexCourse).getStudentOfGrades().get(i).getGrade().getAvrGrade());
                                break;
                            case 2:
                                System.out.println("Điểm hệ 2: ");
                                double grade2 = input.nextDouble();
                                courses.get(Course.indexCourse).getStudentOfGrades().get(i).getGrade().setGrade1(grade2);
                                System.out.println(courses.get(Course.indexCourse).getStudentOfGrades().get(i).getGrade().getAvrGrade());

                                break;
                            case 3:
                                System.out.println("Điểm hệ 3: ");
                                double grade3 = input.nextDouble();
                                courses.get(Course.indexCourse).getStudentOfGrades().get(i).getGrade().setGrade1(grade3);
                                System.out.println(courses.get(Course.indexCourse).getStudentOfGrades().get(i).getGrade().getAvrGrade());

                                break;
                            default:
                                System.out.println("===> Chức năng không hợp lệ <===");
                                check = false;
                        }
                    }
                }
            }

        } else {
            System.out.println("===> Mã lớp không hợp lệ <===");
        }
    }
    public static void showSetMenuGrade() {
        System.out.println("1.Sửa điểm hệ 1                     2.Sửa điểm hệ 2");
        System.out.println("3.Sửa điểm hệ 3                     Khác.Thoát");
    }

//    Hiển thi tất cả các bảng điểm
    public static void showAllGradeOfCourse(ArrayList<Course> courses) {
        System.out.printf("%-15s%-15s%-20s%-19s%-15s","Mã lớp","Mã sinh viên","Họ và tên","Điểm trung bình","Học lực");
        for(int i = 0; i < courses.size(); i++) {
            var check = courses.get(i).getStudentOfGrades().get(i);
            if(check != null) {
                System.out.printf("%-15s%-15s%-20s%-19s%-15s",courses.get(i).getId(),check.getStudent().getId(),
                                            check.getStudent().getFullName(),check.getGrade().getAvrGrade(),
                                check.getGrade().studyStudent(check.getGrade().getAvrGrade()));
            }
        }

    }


}





