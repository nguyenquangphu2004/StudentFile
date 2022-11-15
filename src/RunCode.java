import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RunCode {
    public static int index = 0;
    public static void main(String[] args) throws IOException {
        var input = new Scanner(System.in);
        ArrayList<Subject> subjects = new ArrayList<>();
        boolean check = true;
        Subject subject = new Subject();
//        đọc dữ liêụ từ file
        File file = new File("SUB.DAT");
        Scanner readFile = new Scanner(file);
        while(readFile.hasNextLine()) {
            String words = readFile.nextLine();
            var str = words.split(";");
            subject.setId();
            String id = subject.getId();
            Subject.idN ++;
            String name = str[1];
            int credit = Integer.parseInt(str[2]);
            int les = Integer.parseInt(str[3]);
            int tes = Integer.parseInt(str[4]);
            subjects.add(new Subject(id,name,credit,les,tes));
        }
        readFile.close();
        while(check) {
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
                                setSubject(input,subjects);
                                break;
                            case 5:
                                showListSubject(subjects);
                                break;
                            default:
                                System.out.println("Không hợp lệ.");
                                check1 = false;
                        }
                    }



                case 2:

                case 3:

                default:
                    System.out.println("Bạn đã thoát");
            }
        }
    }
    public static void showMenu() {
        System.out.println("1.Thông tin về môn học.");
        System.out.println("2.Thông tin về sinh viên.");
        System.out.println("3.Thông tin về lớp học.");
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
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n","Mã Môn Học","Tên Môn Học","Số Tín Chỉ","Số Tiết Học","Số Kiểm Tra");
        for (var item :
                subjects)
            showlist(item);
    }
    private static void showlist(Subject item) {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n",item.getId(),item.getName(),item.getCredit(),item.getLesson(),item.getTest());
    }
    public static Subject addSubject(Scanner input) {
        Subject subject = new Subject();
        System.out.println("Mã môn học: ");
        subject.setId();
        String id = subject.getId();
        System.out.println(id);
        Subject.idN ++;
        System.out.println("Tên môn học: ");
        String name = input.nextLine();
        System.out.println("Số tín chỉ: ");
        int cre = input.nextInt();
        System.out.println("Số tiết học: ");
        int les = input.nextInt();
        System.out.println("Số bài kiểm tra: ");
        int test = input.nextInt();
        input.nextLine();
        return  new Subject(id,name,cre,les,test);
    }
    public static void setSubject(Scanner input,ArrayList<Subject> subjects) {
        System.out.println("Nhập mã môn học cần sửa: ");
        String id = input.nextLine();
        var subject = findSubjectId(id,subjects);
        if(subject != null) {
            boolean check = true;
            while (check) {
                showSetSubject();
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Nhập tên: ");
                        String name = input.nextLine();
                        subjects.get(index).setName(name);
                        break;
                    case 2:
                        System.out.println("Số tín chỉ: ");
                        int cre = input.nextInt();
                        subjects.get(index).setCredit(cre);
                        break;
                    case 3:
                        System.out.println("Số tiết học: ");
                        int le = input.nextInt();
                        subjects.get(index).setLesson(le);
                        break;
                    case 4:
                        System.out.println("Số bài kiểm tra: ");
                        int te = input.nextInt();
                        subjects.get(index).setTest(te);
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

    private static Subject findSubjectId(String id, ArrayList<Subject> subjects) {
        for(int i = 0; i < subjects.size(); i++) {
            if(id.compareTo(subjects.get(i).getId()) == 0) {
                index = i;
                return subjects.get(i);
            }
        }
        return null;
    }
}
