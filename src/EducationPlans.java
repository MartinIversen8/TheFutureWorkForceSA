import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EducationPlans {

    private StringProperty fullName;
    private StringProperty AMU;
    private StringProperty epID;
    private StringProperty course;
    private StringProperty priority;
    private StringProperty mail;
    private StringProperty startDate;
    private StringProperty endDate;
    private StringProperty company;
    private StringProperty cprNr;


    public void setCprNr(String value) { cprNrProperty().set(value); }
    public String getCprNr() { return cprNrProperty().get(); }
    public StringProperty cprNrProperty() {
        if (cprNr == null) cprNr = new SimpleStringProperty(this, "cprNr");
        return cprNr;
    }

    public void setName(String value) { fullNameProperty().set(value); }
    public String getName() { return fullNameProperty().get(); }
    public StringProperty fullNameProperty() {
        if (fullName == null) fullName = new SimpleStringProperty(this, "fullName");
        return fullName;
    }

    public void setAMU(String value) { AMUProperty().set(value); }
    public String getAMU() { return AMUProperty().get(); }
    public StringProperty AMUProperty() {
        if (AMU == null) AMU = new SimpleStringProperty(this, "AMU");
        return AMU;
    }

    public void setEpID(String value) { epIDProperty().set(value); }
    public String getEpID() { return epIDProperty().get(); }
    public StringProperty epIDProperty() {
        if (epID == null) epID = new SimpleStringProperty(this, "epID");
        return epID;
    }

    public void setCourse(String value) { courseProperty().set(value); }
    public String getCourse() { return courseProperty().get(); }
    public StringProperty courseProperty() {
        if (course == null) course = new SimpleStringProperty(this, "course");
        return course;
    }

    public void setPriority(String value) { priorityProperty().set(value); }
    public String getPriority() { return priorityProperty().get(); }
    public StringProperty priorityProperty() {
        if (priority == null) priority = new SimpleStringProperty(this, "priority");
        return priority;
    }

    public void setMail(String value) { mailProperty().set(value); }
    public String getMail() { return mailProperty().get(); }
    public StringProperty mailProperty() {
        if (mail == null) mail = new SimpleStringProperty(this, "mail");
        return mail;
    }

    public void setStartDate(String value) { startDateProperty().set(value); }
    public String getStartDate() { return startDateProperty().get(); }
    public StringProperty startDateProperty() {
        if (startDate == null) startDate = new SimpleStringProperty(this, "startDate");
        return startDate;
    }

    public void setEndDate(String value) { endDateProperty().set(value); }
    public String getEndDate() { return endDateProperty().get(); }
    public StringProperty endDateProperty() {
        if (endDate == null) endDate = new SimpleStringProperty(this, "endDate");
        return endDate;
    }

    public void setCompany(String value) { companyProperty().set(value); }
    public String getCompany() { return companyProperty().get(); }
    public StringProperty companyProperty() {
        if (company == null) company = new SimpleStringProperty(this, "company");
        return company;
    }


    public EducationPlans(String epID,String AMU,String course,String fullName,String priority,String mail,String startDate,String endDate,String company,String cprNr) {
        this.setEpID(epID);
        this.setAMU(AMU);
        this.setCourse(course);
        this.setName(fullName);
        this.setPriority(priority);
        this.setMail(mail);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setCompany(company);
        this.setCprNr(cprNr);
    }


}
