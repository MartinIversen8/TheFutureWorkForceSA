import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Provider {
    private StringProperty providerName;
    private StringProperty address;
    private StringProperty zipcode;



    public void setProviderName(String value) { providerNameProperty().set(value); }
    public String getProviderName() { return providerNameProperty().get(); }
    public StringProperty providerNameProperty() {
        if (providerName == null) providerName = new SimpleStringProperty(this, "providerName");
        return providerName;}

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

    public Provider(String providerName,String address,String zipcode) {

        this.setAddress(address);
        this.setZipcode(zipcode);
        this.setProviderName(providerName);

    }
}
