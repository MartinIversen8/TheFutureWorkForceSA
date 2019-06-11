import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerCompanies {

    private StringProperty cvrNr;
    private StringProperty customerCompanyName;
    private StringProperty phoneNr;
    private StringProperty customerMail;
    private StringProperty address;
    private StringProperty zipcode;

    public void setCvrNr(String value) { cvrNrProperty().set(value); }
    public String getCvrNr() { return cvrNrProperty().get(); }
    public StringProperty cvrNrProperty() {
        if (cvrNr == null) cvrNr = new SimpleStringProperty(this, "cvrNr");
        return cvrNr;}

    public void setCustomerCompanyName(String value) { customerCompanyNameProperty().set(value); }
    public String getcustomerCompanyName() { return customerCompanyNameProperty().get(); }
    public StringProperty customerCompanyNameProperty() {
        if (customerCompanyName == null) customerCompanyName = new SimpleStringProperty(this, "customerCompanyName");
        return customerCompanyName;}

    public void setPhoneNr(String value) { phoneNrProperty().set(value); }
    public String getPhoneNr() { return phoneNrProperty().get(); }
    public StringProperty phoneNrProperty() {
        if (phoneNr == null) phoneNr = new SimpleStringProperty(this, "phoneNr");
        return phoneNr;}

    public void setCustomerMail(String value) { customerMailProperty().set(value); }
    public String getCustomerMail() { return customerMailProperty().get(); }
    public StringProperty customerMailProperty() {
        if (customerMail == null) customerMail = new SimpleStringProperty(this, "customerMail");
        return customerMail;}

    public void setAddress(String value) { addressProperty().set(value); }
    public String getAddress() { return addressProperty().get(); }
    public StringProperty addressProperty() {
        if (address == null) address = new SimpleStringProperty(this, "address");
        return address;}

    public void setZipcode(String value) { zipcodeProperty().set(value); }
    public String getZipcode() { return zipcodeProperty().get(); }
    public StringProperty zipcodeProperty() {
        if (zipcode == null) zipcode = new SimpleStringProperty(this, "zipcode");
        return zipcode;}

    public CustomerCompanies(String cvrNr,String customerCompanyName,String phoneNr, String customerMail, String address,String zipcode) {

        this.setCvrNr(cvrNr);
        this.setCustomerCompanyName(customerCompanyName);
        this.setPhoneNr(phoneNr);
        this.setCustomerMail(customerMail);
        this.setAddress(address);
        this.setZipcode(zipcode);

    }

}
