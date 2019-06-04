import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EducationPlans {

    private StringProperty fullName;



    public void setName(String value) { NameProperty().set(value); }
    public String getName() { return NameProperty().get(); }
    public StringProperty NameProperty() {
        if (fullName == null) fullName = new SimpleStringProperty(this, "fullName");
        return fullName;
    }

    public EducationPlans(String fullName) {
        this.setName(fullName);
    }


}
