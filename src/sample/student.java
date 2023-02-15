package sample;

import javafx.beans.property.SimpleStringProperty;

public class student {
    private final SimpleStringProperty username = new SimpleStringProperty("");
    private final SimpleStringProperty courseCode = new SimpleStringProperty("");
    private final SimpleStringProperty courseName = new SimpleStringProperty("");

    public student() {
        this("", "", "");
    }

    public student(String username, String courseCode, String courseName) {
        setUsername(username);
        setCourseCode(courseCode);
        setCourseName(courseName);
    }

    public String getUsername() {
        return username.get();
    }
    private void setUsername(String uName) {
        username.set(uName);
    }

    public String getCourseCode() {
        return courseCode.get();
    }
    private void setCourseCode(String cCode) {
        courseCode.set(cCode);
    }
    public String getCourseName() {
        return courseName.get();
    }
    private void setCourseName(String cName) {
        courseName.set(cName);
    }





}
