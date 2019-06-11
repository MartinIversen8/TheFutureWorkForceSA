import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Courses {
    private StringProperty courseTitle;
    private StringProperty courseProvider;

    public void setCourseTitle(String value) { courseTitleProperty().set(value); }
    public String getCourseTitle() { return courseTitleProperty().get(); }
    public StringProperty courseTitleProperty() {
        if (courseTitle == null) courseTitle = new SimpleStringProperty(this, "courseTitle");
        return courseTitle;
    }

    public void setCourseProvider(String value) { courseProviderProperty().set(value); }
    public String getCourseProvider() { return courseProviderProperty().get(); }
    public StringProperty courseProviderProperty() {
        if (courseProvider == null) courseProvider = new SimpleStringProperty(this, "courseProvider");
        return courseProvider;
    }

    public Courses(String courseTitle, String courseProvider ) {
        this.setCourseTitle(courseTitle);
        this.setCourseProvider(courseProvider);
    }
}
