import java.util.ArrayList;

public class Student {
    public static String idS = "STU";
    public static int idN = 100001;
    private String id;
    private FullName fullName;
    private String address;
    private String email;
    private String gender;
    private String phone;
    private String nameClass;
    private String major;



    public Student() {
        id = "";
        fullName = new  FullName();
        address = "";
        email ="";
        gender = "";
        phone = "";
        nameClass = "";
        major = "";



    }

    public Student(String id, String fullName) {
        this.id = id;
        this.fullName = new FullName();
        setFullName(fullName);
    }

    public Student(String id, String fullName, String email, String gender, String nameClass, String major) {
        this.id = id;
        this.fullName = new FullName();
        setFullName(fullName);
        this.email = email;
        this.gender = gender;
        this.nameClass = nameClass;
        this.major = major;
    }

    public Student(String id, String fullName, String address,
                   String email, String gender, String phone,
                   String nameClass, String major) {
        this.id = id;
        this.fullName = new FullName();
        this.address = address;
        setFullName(fullName);
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.nameClass = nameClass;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = idS + idN;
    }

    public String getFullName() {
        return fullName.last + " " + fullName.mid + fullName.first;
    }

    public void setFullName(String fullName) {
        var words = fullName.split(" ");
        this.fullName.first = words[words.length - 1];
        this.fullName.last = words[0];
        this.fullName.mid = "";
        for (int i = 1; i < words.length - 1; i++) {
            this.fullName.mid += words[i] + " ";
        }


    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    public class FullName {
        private String mid;
        private String first;
        private String last;
    }


}
