import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerEmployees {

    private StringProperty fullName;
    private StringProperty mail;
    private StringProperty companyCVRNR;
    private StringProperty cprNr;
    private StringProperty phnoeNr;

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
        return fullName;}

    public void setMail(String value) { mailProperty().set(value); }
    public String getMail() { return mailProperty().get(); }
    public StringProperty mailProperty() {
        if (mail == null) mail = new SimpleStringProperty(this, "mail");
        return mail;}

    public void setCompanyCVRNR(String value) { companyCVRNRProperty().set(value); }
    public String getCompanyCVRNR() { return companyCVRNRProperty().get(); }
    public StringProperty companyCVRNRProperty() {
        if (companyCVRNR == null) companyCVRNR = new SimpleStringProperty(this, "companyCVRNR");
        return companyCVRNR;
    }

    public void setPhoneNr(String value) { phnoeNrProperty().set(value); }
    public String getPhoneNr() { return phnoeNrProperty().get(); }
    public StringProperty  phnoeNrProperty() {
        if (phnoeNr == null) phnoeNr = new SimpleStringProperty(this, "phoneNr");
        return phnoeNr;
    }

    public CustomerEmployees(String fullName,String mail,String cprNr,String companyCVRNR, String phnoeNr) {

        this.setName(fullName);
        this.setMail(mail);
        this.setCprNr(cprNr);
        this.setPhoneNr(phnoeNr);
        this.setCompanyCVRNR(companyCVRNR);
    }



}
