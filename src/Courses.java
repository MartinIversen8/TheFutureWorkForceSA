import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Courses {
    private StringProperty courseTitle;
    private StringProperty courseAMU;
    private StringProperty courseDescription;
    private StringProperty courseNumberOfDays;

    public void setCourseTitle(String value) { courseTitleProperty().set(value); }
    public String getCourseTitle() { return courseTitleProperty().get(); }
    public StringProperty courseTitleProperty() {
        if (courseTitle == null) courseTitle = new SimpleStringProperty(this, "courseTitle");
        return courseTitle;
    }

    public void setCourseNumberOfDays(String value) { courseNumberOfDaysProperty().set(value); }
    public String getCourseNumberOfDays() { return courseNumberOfDaysProperty().get(); }
    public StringProperty courseNumberOfDaysProperty() {
        if (courseNumberOfDays == null) courseNumberOfDays = new SimpleStringProperty(this, "courseNumberOfDays");
        return courseNumberOfDays;
    }


    public void setCourseAMU(String value) { courseAMUProperty().set(value); }
    public String getCourseAMU() { return courseAMUProperty().get(); }
    public StringProperty courseAMUProperty() {
        if (courseAMU == null) courseAMU = new SimpleStringProperty(this, "courseAMU");
        return courseAMU;
    }

    public void setCourseDesscription(String value) { courseDescriptionProperty().set(value); }
    public String getCourseDescription() { return courseDescriptionProperty().get(); }
    public StringProperty courseDescriptionProperty() {
        if (courseDescription == null) courseDescription = new SimpleStringProperty(this, "courseDescription");
        return courseDescription;
    }

    public Courses(String courseAMU,String courseTitle, String courseNumberOfDays,String courseDescription ) {
        this.setCourseTitle(courseTitle);
        this.setCourseAMU(courseAMU);
        this.setCourseDesscription(courseDescription);
        this.setCourseNumberOfDays(courseNumberOfDays);
    }
}
