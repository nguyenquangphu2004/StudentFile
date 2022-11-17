public enum Study {
    F("Trượt môn"),
    D("Yếu"),
    C("Trung bình"),
    B("Khá"),
    A("Giỏi"),
    A_PLUS("Xuất sắc");

    Study(String value) {
        this.value = value;



    }
    private String value;

    public String getValue() {
        return value;
    }
}
