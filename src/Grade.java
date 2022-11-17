public class Grade {

    public static int indexGrade = 0;
    public static String idS = "TRA";
    public static int idN = 1001;
    private String id;
    private double grade1;
    private double grade2;
    private double grade3;
    private double avrGrade;
    private Study study;

    public Grade(String id,double grade1){
        this.id = id;
        this.grade1 = grade1;
    }
    public Grade() {
        id = "";
        grade1 = 0;
        grade2 = 0;
        grade3 = 0;
        study = null;
    }

    public Grade(double grade1, double grade2, double grade3) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    public Grade(String id, double grade1, double grade2, double grade3, double avrGrade, Study study) {
        this.id = id;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.avrGrade = avrGrade;
        this.study = study;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = idS + idN;
    }

    public double getGrade1() {
        return grade1;
    }

    public void setGrade1(double grade1) {
        this.grade1 = grade1;
    }

    public double getGrade2() {
        return grade2;
    }

    public void setGrade2(double grade2) {
        this.grade2 = grade2;
    }

    public double getGrade3() {
        return grade3;
    }

    public void setGrade3(double grade3) {
        this.grade3 = grade3;
    }

    public double getAvrGrade() {
        return avrGrade;
    }

        public void setAvrGrade() {
        avrGrade = (getGrade1() + getGrade2() * 2 + getGrade3() * 3)/6 ;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public Study studyStudent(double avgr) {
        Study result = null;
        if(avgr >= 9.0) {
            result  = Study.A_PLUS;

        } else if(avgr >= 8.0) {
            result = Study.A;

        } else if(avgr >= 6.5) {
            result = Study.B;
        } else if(avgr >= 5.0) {
            result = Study.C;
        } else if(avgr >= 4.0) {
            result = Study.D;
        } else if(avgr < 4.0) {
            result = Study.F;
        }
        return result;
    }


}
