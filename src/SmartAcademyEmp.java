import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SmartAcademyEmp {

    private StringProperty fullName;
    private StringProperty mail;
    private StringProperty zipcode;
    private StringProperty address;
    private StringProperty phoneNr;
    private StringProperty ID;


    public void setName(String value) { fullNameProperty().set(value); }
    public String getName() { return fullNameProperty().get(); }
    public StringProperty fullNameProperty() {
        if (fullName == null) fullName = new SimpleStringProperty(this, "fullName");
        return fullName;}

    public void setPhoneNr(String value) { phnoeNrProperty().set(value); }
    public String getPhoneNr() { return phnoeNrProperty().get(); }
    public StringProperty  phnoeNrProperty() {
        if (phoneNr == null) phoneNr = new SimpleStringProperty(this, "phoneNr");
        return phoneNr;
    }

    public void setMail(String value) { mailProperty().set(value); }
    public String getMail() { return mailProperty().get(); }
    public StringProperty mailProperty() {
        if (mail == null) mail = new SimpleStringProperty(this, "mail");
        return mail;}


    public void setZipcode(String value) { zipcodeProperty().set(value); }
    public String getZipcode() { return zipcodeProperty().get(); }
    public StringProperty zipcodeProperty() {
        if (zipcode == null) zipcode = new SimpleStringProperty(this, "zipcode");
        return zipcode;}


    public void setAddress(String value) { addressProperty().set(value); }
    public String getAddress() { return addressProperty().get(); }
    public StringProperty addressProperty() {
        if (address == null) address = new SimpleStringProperty(this, "address");
        return address;}

    public void setID(String value) { IDProperty().set(value); }
    public String getID() { return IDProperty().get(); }
    public StringProperty IDProperty() {
        if (ID == null) ID = new SimpleStringProperty(this, "ID");
        return ID;
    }



    public SmartAcademyEmp(String fullName,String mail,String phoneNr,String zipcode,String address,String ID) {

        this.setName(fullName);
        this.setPhoneNr(phoneNr);
        this.setMail(mail);
        this.setAddress(zipcode);
        this.setZipcode(address);
        this.setID(ID);
    }
}
