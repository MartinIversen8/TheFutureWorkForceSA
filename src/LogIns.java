import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogIns {

    private StringProperty userName;
    private StringProperty password;
    private StringProperty personID;

    public void setUserName(String value) { userNameProperty().set(value); }
    public String getUserName() { return userNameProperty().get(); }
    public StringProperty userNameProperty() {
        if (userName == null) userName = new SimpleStringProperty(this, "userName");
        return userName;
    }

    public void setPassword(String value) { passwordProperty().set(value); }
    public String getPassword() { return passwordProperty().get(); }
    public StringProperty passwordProperty() {
        if (password == null) password = new SimpleStringProperty(this, "password");
        return password;
    }

    public void setPersonID(String value) { personIDProperty().set(value); }
    public String getPersonID() { return personIDProperty().get(); }
    public StringProperty personIDProperty() {
        if (personID == null) personID = new SimpleStringProperty(this, "personID");
        return personID;
    }

    public LogIns(String userName,String password,String personID) {
        this.setUserName(userName);
        this.setPassword(password);
        this.setPersonID(personID);
    }

}
