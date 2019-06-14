import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginController {
    private String admin = "Admin";
    private String smartAcademyEmp = "SA";
    private String customer = "Customer";
    private String userNameData;
    private String passwordData;
    private String personID;

    @FXML
    private TextField tfUsername,tfPassword;

    @FXML
    private Button btnLogin;
    @FXML
    private Label labelIncorrectLogin;
    @FXML
    /**
     * this method handles the login, if your password or username is wrong you will not be able to access the program
     *
     * */
    private void handleLogin (ActionEvent event ) throws NullPointerException, IOException {

        String userName = tfUsername.getText();
        String password = tfPassword.getText();
        DB.selectSQL("select fld_Username from tbl_Log_In where fld_Username = '"+userName+"' and fld_Password = '"+password+"' ");
        userNameData = DB.getData();
        DB.selectSQL("select fld_Password from tbl_Log_In where fld_Username = '"+userName+"' and fld_Password = '"+password+"' ");
        passwordData = DB.getData();
        DB.selectSQL("select fld_Person_ID from tbl_Log_In where fld_Username = '"+userName+"' and fld_Password = '"+password+"'");
        personID = DB.getData();
        System.out.println(userNameData+passwordData);

        if(userName.equals(userNameData)&& password.equals(passwordData) && personID.equals(admin)){
            Stage stage;
            Parent root;
            stage = (Stage) btnLogin.getScene().getWindow() ;
            root= FXMLLoader.load(getClass().getResource("UI/scenes/homePageAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if((userName.equals(userNameData)&& password.equals(passwordData) && personID.equals(smartAcademyEmp))){
            Stage stage;
            Parent root;
            stage = (Stage) btnLogin.getScene().getWindow() ;
            root= FXMLLoader.load(getClass().getResource("UI/scenes/homePageSA.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if((userName.equals(userNameData)&& password.equals(passwordData) && personID.equals(customer))) {
            Stage stage;
            Parent root;
            stage = (Stage) btnLogin.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("UI/scenes/homePageCustomer.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            labelIncorrectLogin.setVisible(true);
            labelIncorrectLogin.setText("Incorrect");
        }
        // to avoid pending data error
        getPendingData();
    }
    /**
     * to avoid pending data error
     * */
    private void getPendingData()
    {
        if(DB.pendingData == true)
        {
            do {
                String data = DB.getData();
                if (data.equals(DB.NOMOREDATA)) {
                    break;
                } else {

                }
            } while (true);
        }
    }
}
