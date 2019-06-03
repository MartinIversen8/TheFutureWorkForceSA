import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btnLogin,btnManageLogins;
    @FXML
    private Pane paneEducationPlans,paneEducationPlansCreateAndEdit,paneCoursesAndProviders,paneCoursesAndProvidersAddAndEdit,
    paneCustomerCompanies,paneCustomerCompaniesAddAndEdit,paneCustomerEmployee,paneCustomerEmployeeAddAndEdit,paneManageLogins
            ,paneManageLoginsCreateAndEdit,tblViewCoursesAndProviders,tblViewManageLogins, tblViewEducation,tblViewCustomerCompanies,tblViewCustomerEmployee;
    @FXML
    private TextField tfUsername,tfPassword;
    private String admin = "Admin";
    private String smartAcademyEmp = "SA";
    private String customer = "Customer";
    private String userNameData;
    private String passwordData;
    private String personID;
    private TableView tblViewEducationPlans;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> personIDList =  new ArrayList<>();
        DB.selectSQL(" select fld_Person_ID from tbl_Log_In");
        do {
            String data = DB.getData();
            if (data.equals(DB.NOMOREDATA)) {
                break;
            } else {
                personIDList.add(data);
            }
        } while (true);


    }

    @FXML
    private void btnNextPage (ActionEvent event ) throws NullPointerException, IOException {

        String userName = tfUsername.getText();
        String password = tfPassword.getText();
        DB.selectSQL("select fld_Username from tbl_Log_In where fld_Username = '"+userName+"' and fld_Password = '"+password+"' ");
        userNameData = DB.getData();
        DB.selectSQL("select fld_Password from tbl_Log_In where fld_Username = '"+userName+"' and fld_Password = '"+password+"' ");
        passwordData = DB.getData();
        DB.selectSQL("select fld_Person_ID from tbl_Log_In where fld_Username = '"+userName+"' and fld_Password = '"+password+"'");
        personID = DB.getData(); // BETTER NAMING PLS
        System.out.println(userNameData+passwordData);

        if(userName.equals(userNameData)&& password.equals(passwordData) && personID.equals(admin)){
        Stage stage;
        Parent root;
        stage = (Stage) btnLogin.getScene().getWindow() ;
        root= FXMLLoader.load(getClass().getResource("scenes/homePage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // fix it
            System.out.println(personID);


        }
        else if((userName.equals(userNameData)&& password.equals(passwordData) && personID.equals(smartAcademyEmp))){
            Stage stage;
            Parent root;
            stage = (Stage) btnLogin.getScene().getWindow() ;
            root= FXMLLoader.load(getClass().getResource("scenes/homePageSAEmp.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if((userName.equals(userNameData)&& password.equals(passwordData) && personID.equals(customer))) {
            Stage stage;
            Parent root;
            stage = (Stage) btnLogin.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("scenes/homePage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
            {
                System.out.println("you shall not pass");
            }

    }
    
 // here we start with the actions for educations plans
  @FXML
    private void showBtnEducationPlans(ActionEvent event) throws NullPointerException
    {
        paneEducationPlans.setVisible(true);
        paneCoursesAndProviders.setVisible(false);
        paneCustomerCompanies.setVisible(false);
        paneCustomerEmployee.setVisible(false);
        paneManageLogins.setVisible(false);

    }

    @FXML
    private void showEducationPlans (ActionEvent event) throws SQLException {
        tblViewEducation.setVisible(true);
        paneEducationPlansCreateAndEdit.setVisible(false);
        DB.storedProcSA_view_ep(1,"12345",tblViewEducationPlans);


    }

    @FXML
    private void showCreatePlans (ActionEvent event)
    {
        tblViewEducation.setVisible(false);
        paneEducationPlansCreateAndEdit.setVisible(true);
    }
    // the end of education plans

    // here starts the actions we have for Courses and providers
    @FXML
    private void showBtnCoursesAndProviders(ActionEvent event)
    {
        paneCoursesAndProviders.setVisible(true);
        paneEducationPlans.setVisible(false);
        paneCustomerCompanies.setVisible(false);
        paneCustomerEmployee.setVisible(false);
        paneManageLogins.setVisible(false);
    }
    @FXML
    private void btnViewCourses (ActionEvent event)
    {
        tblViewCoursesAndProviders.setVisible(true);
        paneCoursesAndProvidersAddAndEdit.setVisible(false);
    }
    @FXML
    private void showAddCourses(ActionEvent event)
    {
        paneCoursesAndProvidersAddAndEdit.setVisible(true);
        tblViewCoursesAndProviders.setVisible(false);
    }
    // End of Courses and providers

    // Start of Customer/Companies
    @FXML
    private void showCustomerPane (ActionEvent event)
    {
        paneCustomerCompanies.setVisible(true);
        paneEducationPlans.setVisible(false);
        paneCoursesAndProviders.setVisible(false);
        paneCustomerEmployee.setVisible(false);
        paneManageLogins.setVisible(false);
    }

    @FXML
    private void showCustomerTblView (ActionEvent event)
    {
        tblViewCustomerCompanies.setVisible(true);
        paneCustomerCompaniesAddAndEdit.setVisible(false);
    }

    @FXML
    private void showCustomerAddPane (ActionEvent event)
    {
        tblViewCustomerCompanies.setVisible(false);
        paneCustomerCompaniesAddAndEdit.setVisible(true);
    }
    // end of Customer/Companies

    // Start of Customer Employees
    @FXML
    private void showCustomerEmployeePane(ActionEvent event)
    {
        paneCustomerEmployee.setVisible(true);
        paneCustomerCompanies.setVisible(false);
        paneEducationPlans.setVisible(false);
        paneCoursesAndProviders.setVisible(false);

    }
    @FXML
    private void showAddEmployee(ActionEvent event)
    {
        paneCustomerEmployeeAddAndEdit.setVisible(true);
        tblViewCustomerEmployee.setVisible(false);
    }

    @FXML
    private void showTblViewEmpolyees (ActionEvent event)
    {
        tblViewCustomerEmployee.setVisible(true);
        paneCustomerEmployeeAddAndEdit.setVisible(false);
    }
    // End of the Customer Employee

    //Start of manage Logins

    @FXML
    private void showManageLoginsPane(ActionEvent event)
    {
        paneCustomerEmployee.setVisible(false);
        paneCustomerCompanies.setVisible(false);
        paneEducationPlans.setVisible(false);
        paneCoursesAndProviders.setVisible(false);
        paneManageLogins.setVisible(true);
    }
    @FXML
    private void showtblViewLogins(ActionEvent event)
    {
        tblViewManageLogins.setVisible(true);
        paneManageLoginsCreateAndEdit.setVisible(false);
    }
    @FXML
    private void showPaneManageLoginsCreateAndEdit(ActionEvent event)
    {
        paneManageLoginsCreateAndEdit.setVisible(true);
        tblViewManageLogins.setVisible(false);
    }




}