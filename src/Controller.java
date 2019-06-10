import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    tfCustomerCompaniesCVRNR,tfCustomerCompaniesName,tfCustomerCompaniesEmail,tfCustomerCompaniesZipcode,tfCustomerCompaniesPhone,tfCustomerCompaniesAddress,tfManageLoginsUsername,tfManageLoginsPersonID,tfManageLoginsPassword,tfEducationAmuNR
            ,tfCustomerEmployeesName,tfCustomerEmployeesPhone,tfCustomerEmployeesCPR,tfCustomerEmployeesCVR,tfCustomerEmployeesEmail;
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
    private TableView<EducationPlans> tblViewEducationPlans;
    @FXML
    private TableView<CustomerEmployees> tblViewCustomerEmployeeTableView;
    @FXML
    private TableView<LogIns> tblViewLoogins;
    @FXML
    private TableColumn<ObservableList<String>, String> fullName,cprNr,company,provider,priority,startDate,endDate,epID,AMU,cours,mail;
    @FXML
    private TableColumn<ObservableList<String>,String> columnCustomerEmployeeName,columnCustomerEmployeePhoneNr,columnCustomerEmployeeMail,columnCustomerEmployeeCPRNR,columnCustomerEmployeeCVRNR,columnLoginsUsername,
            columnLoginsPassword,columnLoginsPersonID;
    @FXML
    private ListView<String> listView;

    ObservableList<EducationPlans> epList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<CustomerEmployees> cusEmpList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<LogIns> logInsList = FXCollections.observableArrayList(new ArrayList<>());
private int index;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    @FXML
    private void editEducationPlan() {

        tblViewEducation.setVisible(false);
        paneEducationPlansCreateAndEdit.setVisible(true);
        index = tblViewEducationPlans.getSelectionModel().getFocusedIndex(); // maybe change the name to column index and not just call it index
        LocalDate localDateEnd = LocalDate.parse(tblViewEducationPlans.getItems().get(index).getEndDate());
        LocalDate localDateStart = LocalDate.parse(tblViewEducationPlans.getItems().get(index).getStartDate());
        tfEducationAmuNR.setText(tblViewEducationPlans.getItems().get(index).getAMU());
        tfEducationCprNr.setText(tblViewEducationPlans.getItems().get(index).getCprNr());
        tfEducationCustomerCompanyName.setText(tblViewEducationPlans.getItems().get(index).getCompany());
        tfEducationCustomerEmployeeName.setText(tblViewEducationPlans.getItems().get(index).getName());
        tfEducationPriority.setText(tblViewEducationPlans.getItems().get(index).getPriority());
        dpEducationEndDate.setValue(localDateEnd);
        dpEducationStartDate.setValue(localDateStart);

    }

    @FXML
    private void upDateEp(ActionEvent event)
    {
        String startDate = dpEducationStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dpEducationEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String amuNo = tfEducationAmuNR.getText();
        String empName = tfEducationCustomerEmployeeName.getText();
        String priority = tfEducationPriority.getText();
        String epId = tblViewEducationPlans.getItems().get(index).getEpID();
        DB.updateSQL("update tbl_Education_Plan set fld_Name_of_Attendee = '"+empName+"', fld_Prority = '"+priority+"' where fld_Education_Plan_ID = '"+epId+"' ");
        DB.updateSQL("update tbl_Calendar set fld_Start_Date = '"+startDate+"',fld_End_Date = '"+endDate+"',fld_AMUNo ="+amuNo+" where fld_EducationPlan_ID = '"+epId+"' ");

    }

    @FXML
    private void delteEp(ActionEvent event)
    {
        index = tblViewEducationPlans.getSelectionModel().getFocusedIndex();
        String epID = tblViewEducationPlans.getItems().get(index).getEpID();

        DB.selectSQL("select fld_ID from tbl_Calendar where fld_EducationPlan_ID = '"+epID+"'");
        String calendarID = DB.getData();
        do {
            String data = DB.getData();
            if (data.equals(DB.NOMOREDATA)) {
                break;
            } else {

            }
        } while (true);
        DB.deleteSQL("delete from tbl_Customer_specific_courses where fld_calendar_ID = '"+calendarID+"' ");
        DB.deleteSQL("delete from tbl_Calendar where fld_EducationPlan_ID = '"+epID+"'");
        DB.deleteSQL("delete from tbl_Education_Plan where fld_Education_Plan_ID = '"+epID+"'");

    }

    @FXML
    private void createEP(ActionEvent event)
    {
        String startDate = dpEducationStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dpEducationEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String amuNo = tfEducationAmuNR.getText();
        String cprNr = tfEducationCprNr.getText();
        String empName = tfEducationCustomerEmployeeName.getText();
        String priority = tfEducationPriority.getText();
        String smartAcademyID = tfEducationSmartAcademyEmployeeID.getText();
        String provider = tfEducationProvider.getText();

        DB.insertSQL("insert into tbl_Education_Plan values('"+priority+"','"+empName+"','"+cprNr+"','"+smartAcademyID+"')");
        DB.selectSQL("SELECT TOP 1 fld_Education_Plan_ID FROM tbl_Education_Plan ORDER BY fld_Education_Plan_ID DESC");
        String educationPlanID = DB.getData();

        do {
            String data = DB.getData();
            if (data.equals(DB.NOMOREDATA)) {
                break;
            } else {

            }
        } while (true);
        System.out.println(educationPlanID);

        DB.insertSQL("insert into tbl_Calendar values('"+amuNo+"','"+provider+"','"+startDate+"','"+endDate+"','"+educationPlanID+"')");

        tfEducationProvider.setText("");
        tfEducationSmartAcademyEmployeeID.setText("");
        tfEducationPriority.setText("");
        tfEducationCustomerEmployeeName.setText("");
        tfEducationCustomerCompanyName.setText("");
        tfEducationCprNr.setText("");
        tfEducationAmuNR.setText("");
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
        paneManageLogins.setVisible(false);
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
        viewCustomerEmployees();
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
        viewLogins();
    }
    @FXML
    private void showPaneManageLoginsCreateAndEdit(ActionEvent event)
    {
        paneManageLoginsCreateAndEdit.setVisible(true);
        tblViewManageLogins.setVisible(false);
    }
    // end of manage LOGINS
    @FXML
    private void createNewCus_emp(ActionEvent event)
    {
        String cus_empName = tfCustomerEmployeesName.getText();
        String cus_empPhoneNr = tfCustomerEmployeesPhone.getText();
        String cus_empCPRNR = tfCustomerEmployeesCPR.getText();
        String cus_emp_Employer = tfCustomerEmployeesCVR.getText();
        String cus_empMail = tfCustomerEmployeesEmail.getText();

        DB.insertSQL("insert into tbl_Customer_Employee values ('"+cus_empCPRNR+"','"+cus_empName+"','"+cus_empMail+"','"+cus_empPhoneNr+"','"+cus_emp_Employer+"')");

        tfCustomerEmployeesName.setText("");
        tfCustomerEmployeesPhone.setText("");
        tfCustomerEmployeesCPR.setText("");
        tfCustomerEmployeesCVR.setText("");
        tfCustomerEmployeesEmail.setText("");

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
    private void createNewLogin(ActionEvent event)
    {
        String username = tfManageLoginsUsername.getText();
        String password = tfManageLoginsPassword.getText();
        String personID = tfManageLoginsPersonID.getText();

        DB.insertSQL("insert into tbl_Log_In values('"+password+"','"+username+"','"+personID+"')");

        tfManageLoginsPassword.setText("");
        tfManageLoginsUsername.setText("");
        tfManageLoginsPersonID.setText("");

    }
    @FXML
    private void refreshBtn(ActionEvent event) throws SQLException {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewEducationPlans.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute SA_view_ep "+1+""); // TODO make it so it is not only for SA_emp with id 1, make it so it depends on who is logged in
            //ObservableList<EducationPlans> epList = FXCollections.observableArrayList(new ArrayList<>());

            epID.setCellValueFactory(new PropertyValueFactory<>("epID"));
            AMU.setCellValueFactory(new PropertyValueFactory<>("AMU"));
            cours.setCellValueFactory(new PropertyValueFactory<>("cours"));
            fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
            mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            company.setCellValueFactory(new PropertyValueFactory<>("company"));
            cprNr.setCellValueFactory(new PropertyValueFactory<>("cprNr"));


            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                }

            for (int i = 0; i <row.size()/10 ; i++) {
                EducationPlans ep2 = new EducationPlans(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2), row.get(i+counter+3), row.get(i+counter+4), row.get(i+counter+5),
                        row.get(i+counter+6), row.get(i+counter+7), row.get(i+counter+8),row.get(i+counter+9));
                System.out.println("ep2 "+ep2.toString());
                System.out.println(tblViewEducationPlans.getColumns());
                epList.add(ep2);
                counter+=9;

            }
            tblViewEducationPlans.setItems(epList);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }
    @FXML
    private void writeExcel(ActionEvent event) throws Exception {
        Writer writer = null;
        try {
            File file = new File("C:\\Users\\Marti\\Desktop\\csvFiler fra SA\\mojn.csv");
            writer = new BufferedWriter(new FileWriter(file));
            for (EducationPlans ep : epList) {

                String text = ep.getEpID() + "" + ep.getAMU() + "" + ep.getCours() + "";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }


    private void viewCustomerEmployees()
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewCustomerEmployeeTableView.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute view_cusEmps");

            columnCustomerEmployeeName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            columnCustomerEmployeePhoneNr.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));
            columnCustomerEmployeeMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            columnCustomerEmployeeCPRNR.setCellValueFactory(new PropertyValueFactory<>("cprNr"));
            columnCustomerEmployeeCVRNR.setCellValueFactory(new PropertyValueFactory<>("companyCVRNR"));

            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }

            for (int i = 0; i <row.size()/5 ; i++) {
                CustomerEmployees cusEmp = new CustomerEmployees(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2), row.get(i+counter+3), row.get(i+counter+4));
                cusEmpList.add(cusEmp);
                counter+=4;

            }
            tblViewCustomerEmployeeTableView.setItems(cusEmpList);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }



    private void viewLogins ()
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewLoogins.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute view_logins");
            columnLoginsUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
            columnLoginsPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            columnLoginsPersonID.setCellValueFactory(new PropertyValueFactory<>("personID"));

            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }

            for (int i = 0; i <row.size()/3 ; i++) {
                LogIns logIns = new LogIns(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2));
                logInsList.add(logIns);
                counter+=2;

            }
            tblViewLoogins.setItems(logInsList);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    @FXML
    private void deleteLogIn (ActionEvent event)
    {
        index = tblViewLoogins.getSelectionModel().getFocusedIndex();
        String password = tblViewLoogins.getItems().get(index).getPassword();

        System.out.println(password);

        DB.deleteSQL("delete from tbl_Log_In where fld_Password = '"+password+"'");

        viewLogins();

    }

}