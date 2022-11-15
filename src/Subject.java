public class Subject {
    public static String idS = "SUB";
    public static int idN = 1001;
    private String id;
    private String name;
    private int credit;
    private int lesson;
    private int test;

    public Subject() {
        id = "";
        name ="";
        credit = 0;
        lesson = 0;
        test = 0;
    }

    public Subject(String id, String name, int credit, int lesson, int test) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.lesson = lesson;
        this.test = test;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = idS + idN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
