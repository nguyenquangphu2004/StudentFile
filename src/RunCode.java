import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RunCode {
    public static int indexSubject = 0;
    public static Student student = new Student();
    public static int indexStudent = 0;

    public static void main(String[] args) throws IOException {
        var input = new Scanner(System.in);
        ArrayList<Subject> subjects = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        Course course = new Course();
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
        Course.readFileCOURSE(courses, subjects);

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
                    course.allOfFuntionCourse(courses,input,subjects,students);
                    break;
                default:
                    System.out.println("Bạn đã thoát");
            }
        }
    }
        public static void showMenu () {
            System.out.println("1.Thông tin về môn học.");
            System.out.println("2.Thông tin về sinh viên.");
            System.out.println("3.Thông tin về lớp học.");
            System.out.println("Khác.Thoát.");
        }
        public static void showSubject () {
            System.out.println("1.Thêm mới một môn học.");
            System.out.println("2.Lưu môn học vào file");
            System.out.println("3.Sửa thông tin môn học.");
            System.out.println("4.Xóa môn học.");
            System.out.println("5.Hiển thị danh sách môn học.");
            System.out.println("Khác.Thoát.");
        }

        private static void showListSubject (ArrayList < Subject > subjects) {
            System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Mã Môn Học", "Tên Môn Học", "Số Tín Chỉ", "Số Tiết Học", "Số Kiểm Tra");
            for (var item :
                    subjects)
                showlist(item);
        }
        private static void showlist (Subject item){
            System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", item.getId(), item.getName(), item.getCredit(), item.getLesson(), item.getTest());
        }
        public static Subject addSubject (Scanner input){
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
        public static void setSubject (Scanner input, ArrayList < Subject > subjects){
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

        private static void showSetSubject () {
            System.out.println("1.Sửa tên môn.");
            System.out.println("2.Sửa số tín chỉ.");
            System.out.println("3.Sửa số tiết học.");
            System.out.println("4.Sửa số bài kiểm tra.");
            System.out.println("Khác.Thoát.");
        }

        public static Subject findSubjectId (String id, ArrayList < Subject > subjects){
            for (int i = 0; i < subjects.size(); i++) {
                if (id.compareTo(subjects.get(i).getId()) == 0) {
                    indexSubject = i;
                    return subjects.get(i);
                }
            }
            return null;
        }


//    CHỨC NĂNG CỦA SINH VIÊN
        public static Student addStudent (Scanner input){
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

        public static void showStudent (ArrayList < Student > students) {
            System.out.printf("%-15s%-20s%-38s%-25s%-15s%-15s%-18s%-15s\n", "Mã Sinh Viên", "Họ Và Tên", "Địa Chỉ", "Email", "Giới Tính", "Số Điện Thoại", "Tên Lớp", "Chuyên Ngành");
            for (var item :
                    students) {
                showListStudent(item);
            }
        }

        private static void showListStudent (Student item){
            System.out.printf("%-15s%-20s%-38s%-25s%-15s%-15s%-18s%-15s\n", item.getId(), item.getFullName(), item.getAddress(), item.getEmail(), item.getGender(), item.getPhone(), item.getNameClass(), item.getMajor());
        }

        public static void showMenuSetStudent () {
            System.out.println("1.Sửa Họ và tên.                            2.Sửa địa chỉ.");
            System.out.println("3.Sửa email.                                4.Sửa giới tính.        ");
            System.out.println("5.Sửa số điện thoại.                        6.Sửa tên lớp.");
            System.out.println("7.Sửa chuyên ngành.                         Khác.Thoát.");

        }

        public static void setStudent (Scanner input, ArrayList < Student > students){
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
        public static void showMenuStudent () {
            System.out.println("1.Thêm mới sinh viên.");
            System.out.println("2.Lưu sinh viên vào file.");
            System.out.println("3.Hiển thị danh sách sinh viên.");
            System.out.println("4.Sửa thông tin sinh viên.");
            System.out.println("5.Xóa sinh viên.");
        }

        public static Student findStudentOfId (String id, ArrayList < Student > students){
            for (int i = 0; i < students.size(); i++) {
                if (id.compareTo(students.get(i).getId()) == 0) {
                    indexStudent = i;
                    return students.get(i);
                }
            }
            return null;
        }
}



