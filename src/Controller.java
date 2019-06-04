import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable {
    @FXML
    private Button btnLogin;
    @FXML
    private Pane paneEducationPlans,paneEducationPlansCreateAndEdit,paneCoursesAndProviders,paneCoursesAndProvidersAddAndEdit,
    paneCustomerCompanies,paneCustomerCompaniesAddAndEdit,paneCustomerEmployee,paneCustomerEmployeeAddAndEdit,paneManageLogins
            ,paneManageLoginsCreateAndEdit,tblViewCoursesAndProviders,tblViewManageLogins, tblViewEducation,tblViewCustomerCompanies,tblViewCustomerEmployee;
    @FXML
    private TextField tfUsername,tfPassword,tfEducationCustomerEmployeeName,tfEducationCustomerCompanyName,tfEducationPriority,
            tfEducationProvider,tfEducationCprNr,tfEducationSmartAcademyEmployeeID,tfCourseAndProvidersCourseTitle,tfCourseAndProvidersProviderZipcode,tfCourseAndProvidersProviderAddress,tfCourseAndProvidersNumberOfDays,
    tfCustomerCompaniesCVRNR,tfCustomerCompaniesName,tfCustomerCompaniesEmail,tfCustomerCompaniesZipcode,tfCustomerCompaniesPhone,tfCustomerCompaniesAddress;
    private String admin = "Admin";
    private String smartAcademyEmp = "SA";
    private String customer = "Customer";
    private String userNameData;
    private String passwordData;
    private String personID;
    @FXML
    private TextArea taCoursesAndProvidersCourseDescription;
    @FXML
    private DatePicker dpEducationStartDate,dpEducationEndDate;
    @FXML
    private TableView tblViewEducationPlans;
    @FXML
    private TableColumn<ObservableList<Integer>, Integer> ID,AMU;
    @FXML
    private TableColumn<ObservableList<String>, String> fullName,CPRNR,company,provider,priority,startDate,endDate;

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
    private void handleLogin (ActionEvent event ) throws NullPointerException, IOException {

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
        root= FXMLLoader.load(getClass().getResource("scenes/homePageAdmin2.fxml"));
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
            root= FXMLLoader.load(getClass().getResource("scenes/homePageSA.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if((userName.equals(userNameData)&& password.equals(passwordData) && personID.equals(customer))) {
            Stage stage;
            Parent root;
            stage = (Stage) btnLogin.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("scenes/homePageCustomer.fxml"));
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
    // end of manage LOGINS

    private void createNewEducationPlan()
    {
        // CPRNR SA_ID Name Priority

        DB.insertSQL("");


    }
    @FXML
    private void createNewCourse(ActionEvent event)
    {
        String title =  tfCourseAndProvidersCourseTitle.getText();
        String numberOfDays = tfCourseAndProvidersNumberOfDays.getText();
        String description = taCoursesAndProvidersCourseDescription.getText();

        DB.insertSQL("insert into tbl_Courses values('"+title+"','"+numberOfDays+"','"+description+"')");

        tfCourseAndProvidersCourseTitle.setText("");
        tfCourseAndProvidersNumberOfDays.setText("");
        taCoursesAndProvidersCourseDescription.setText("");

    }
    @FXML
    private void createNewCompany(ActionEvent event)
    {
        String CVRNR = tfCustomerCompaniesCVRNR.getText();
        String name = tfCustomerCompaniesName.getText();
        String e_mail = tfCustomerCompaniesEmail.getText();
        int zipcode = Integer.parseInt(tfCustomerCompaniesZipcode.getText());
        String phoneNr = tfCustomerCompaniesPhone.getText();
        String address = tfCustomerCompaniesAddress.getText();

        DB.insertSQL("insert into tbl_Customer values('"+CVRNR+"','"+name+"','"+e_mail+"',"+zipcode+",'"+phoneNr+"','"+address+"')");

        tfCustomerCompaniesCVRNR.setText("");
        tfCustomerCompaniesName.setText("");
        tfCustomerCompaniesEmail.setText("");
        tfCustomerCompaniesZipcode.setText("");
        tfCustomerCompaniesPhone.setText("");
        tfCustomerCompaniesAddress.setText("");

    }
    @FXML
    private void refreshBtn(ActionEvent event) throws SQLException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        // 9 ting er der per row

        try {


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SmartAcademy","sa","Procity8");
            CallableStatement cs = con.prepareCall("{CALL SA_view_ep(?)}");

            cs.setInt(1, 1);
            cs.execute();
            ResultSet rs2 = con.createStatement().executeQuery("execute SA_view_ep "+1+"");
            ResultSet rs = cs.getResultSet();
            ObservableList<String> row = FXCollections.observableArrayList();
            ResultSet rs3 = con.createStatement().executeQuery("select fld_CVR_NR from tbl_Customer");

            while (rs2.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs2.getString(i));
                    System.out.println(row);
                }
                data.add(row);
                System.out.println(data);
            }



            /*fullName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
                    return new SimpleStringProperty(param.getValue().get(0));
                }
            });*/

            //fullName.setCellValueFactory(new PropertyValueFactory<>("fullName") );

            tblViewEducationPlans.setItems(data);
            //asdad




        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }



}