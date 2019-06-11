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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author Martin Iversen
 * */

public class AdminController implements Initializable {
    @FXML
    private Pane paneEducationPlans,paneEducationPlansCreateAndEdit,paneCoursesAndProviders,paneCoursesAndProvidersAddAndEdit,
    paneCustomerCompanies,paneCustomerCompaniesAddAndEdit,paneCustomerEmployee,paneCustomerEmployeeAdd,paneManageLogins
            ,paneManageLoginsCreate,panetblViewCoursesAndProviders, panetblViewManageLogins, panetblViewEducation, panetblViewCustomerCompanies, panetblViewCustomerEmployee,
            paneEducationPlansEdit,paneManageLoginsEdit;
    @FXML
    private TextField tfEducationCustomerEmployeeName,tfEducationCustomerCompanyName,tfEducationPriority,tfEducationCprNr,tfCourseAndProvidersCourseTitle,tfCourseAndProvidersProviderZipcode,tfCourseAndProvidersProviderAddress,tfCourseAndProvidersNumberOfDays,
    tfCustomerCompaniesCVRNR,tfCustomerCompaniesName,tfCustomerCompaniesEmail,tfCustomerCompaniesZipcode,tfCustomerCompaniesPhone,tfCustomerCompaniesAddress,tfManageLoginsUsername,tfManageLoginsPersonID,tfManageLoginsPassword,tfEducationAmuNR
            ,tfCustomerEmployeesName,tfCustomerEmployeesPhone,tfCustomerEmployeesCPR,tfCustomerEmployeesCVR,tfCustomerEmployeesEmail,tfEducationCustomerEmployeeName2,tfEducationCprNr2,tfEducationSmartAcademyEmployeeID2,tfEducationAmuNR2
            ,tfEducationCustomerCompanyName2,tfEducationPriority2,tfEducationProvider2,tfManageLoginsEditPassword,tfManageLoginsEditUsername,tfManageLoginsEditPersonID;

    @FXML
    private TextArea taCoursesAndProvidersCourseDescription;
    @FXML
    private DatePicker dpEducationStartDate,dpEducationEndDate,dpEducationStartDate2,dpEducationEndDate2;
    @FXML
    private TableView<EducationPlans> tblViewEducationPlans;
    @FXML
    private TableView<CustomerEmployees> tblViewCustomerEmployee;
    @FXML
    private TableView<LogIns> tblViewLoogins;
    @FXML
    private TableView<Courses> tblViewCoursesAndProviders;
    @FXML
    private TableView<CustomerCompanies> tblViewCustomerCompanies;
    @FXML
    private TableColumn<ObservableList<String>, String> fullName,cprNr,company,provider,priority,startDate,endDate,epID,AMU,course,mail;
    @FXML
    private TableColumn<ObservableList<String>,String> columnCustomerEmployeeName,columnCustomerEmployeePhoneNr,columnCustomerEmployeeMail,columnCustomerEmployeeCPRNR,columnCustomerEmployeeCVRNR,columnLoginsUsername,
            columnLoginsPassword,columnLoginsPersonID,columnCourseTitle,columnCourseProvider,columnCompaniesCVRNR,columnCompaniesName,columnCompaniesPhone,columnCompaniesEmail,columnCompaniesAddress,
            columnCompaniesZipcode;

    ObservableList<EducationPlans> epList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<CustomerEmployees> cusEmpList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<LogIns> logInsList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Courses> courseList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<CustomerCompanies> customerCompaniesList = FXCollections.observableArrayList(new ArrayList<>());
private int index;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        panetblViewEducation.setVisible(true);
        paneEducationPlansCreateAndEdit.setVisible(false);
        viewEducationPlans();

    }

    @FXML
    private void showCreatePlans (ActionEvent event)
    {
        panetblViewEducation.setVisible(false);
        paneEducationPlansCreateAndEdit.setVisible(true);
    }

    @FXML
    private void editEducationPlan() {

        panetblViewEducation.setVisible(false);
        paneEducationPlansCreateAndEdit.setVisible(false);
        paneEducationPlansEdit.setVisible(true);
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
    private void updateEducationPlan(ActionEvent event)
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
    private void deleteEducationPlan(ActionEvent event) throws SQLException {
        index = tblViewEducationPlans.getSelectionModel().getFocusedIndex();
        String epID = tblViewEducationPlans.getItems().get(index).getEpID();

        DB.selectSQL("select fld_ID from tbl_Calendar where fld_EducationPlan_ID = '"+epID+"'");
        String calendarID = DB.getData();
        // this is for avoiding an error with pending data
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

        DB.deleteSQL("delete from tbl_Customer_specific_courses where fld_calendar_ID = '"+calendarID+"' ");
        DB.deleteSQL("delete from tbl_Calendar where fld_EducationPlan_ID = '"+epID+"'");
        DB.deleteSQL("delete from tbl_Education_Plan where fld_Education_Plan_ID = '"+epID+"'");
        viewEducationPlans();

    }

    @FXML
    private void createEducationPlan(ActionEvent event)
    {
        String startDate = dpEducationStartDate2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dpEducationEndDate2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String amuNo = tfEducationAmuNR2.getText();
        String cprNr = tfEducationCprNr2.getText();
        String empName = tfEducationCustomerEmployeeName2.getText();
        String priority = tfEducationPriority2.getText();
        String smartAcademyID = tfEducationSmartAcademyEmployeeID2.getText();
        String provider = tfEducationProvider2.getText();
        DB.insertSQL("insert into tbl_Education_Plan values('"+priority+"','"+empName+"','"+cprNr+"','"+smartAcademyID+"')");
        DB.selectSQL("SELECT TOP 1 fld_Education_Plan_ID FROM tbl_Education_Plan ORDER BY fld_Education_Plan_ID DESC");
        String educationPlanID = DB.getData();
        // this do while loop is for avoiding the error message pending data
        do {
            String data = DB.getData();
            if (data.equals(DB.NOMOREDATA)) {
                break;
            } else {

            }
        } while (true);
        DB.insertSQL("insert into tbl_Calendar values('"+amuNo+"','"+provider+"','"+startDate+"','"+endDate+"','"+educationPlanID+"')");

        tfEducationProvider2.setText("");
        tfEducationSmartAcademyEmployeeID2.setText("");
        tfEducationPriority2.setText("");
        tfEducationCustomerEmployeeName2.setText("");
        tfEducationCustomerCompanyName2.setText("");
        tfEducationCprNr2.setText("");
        tfEducationAmuNR2.setText("");
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
        panetblViewCoursesAndProviders.setVisible(true);
        paneCoursesAndProvidersAddAndEdit.setVisible(false);
    }
    @FXML
    private void showAddCourses(ActionEvent event)
    {
        paneCoursesAndProvidersAddAndEdit.setVisible(true);
        panetblViewCoursesAndProviders.setVisible(false);
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

    private void viewCourses() // TODO find ud af hvad der skal stå i courses fordi det giver ikke mening fordi providers har kun forbindelse til tbl_calendar
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewCoursesAndProviders.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute view_logins");
            do {
                String data = DB.getData();
                if (data.equals(DB.NOMOREDATA)) {
                    break;
                } else {

                }
            } while (true);
            columnCourseTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
            columnCourseProvider.setCellValueFactory(new PropertyValueFactory<>("columnCourseProvider"));

            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }

            for (int i = 0; i <row.size()/2 ; i++) {
                Courses courses = new Courses(row.get(i+counter), row.get(i+counter+1));
                courseList.add(courses);
                counter+=1;
            }
            tblViewCoursesAndProviders.setItems(courseList);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
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
        panetblViewCustomerCompanies.setVisible(true);
        paneCustomerCompaniesAddAndEdit.setVisible(false);
        viewCustomerCompanies();
    }

    @FXML
    private void showCustomerAddPane (ActionEvent event)
    {
        panetblViewCustomerCompanies.setVisible(false);
        paneCustomerCompaniesAddAndEdit.setVisible(true);
    }

    @FXML
    private void createNewCompany(ActionEvent event)
    {
        String CVRNR = tfCustomerCompaniesCVRNR.getText();
        String name = tfCustomerCompaniesName.getText();
        String e_mail = tfCustomerCompaniesEmail.getText();
        String zipcode = tfCustomerCompaniesZipcode.getText();
        String phoneNr = tfCustomerCompaniesPhone.getText();
        String address = tfCustomerCompaniesAddress.getText();
        // loop to avoid pending data from previous select statement
        DB.insertSQL("insert into tbl_Customer values('"+CVRNR+"','"+name+"','"+phoneNr+"','"+e_mail+"','"+address+"',"+zipcode+")");
        tfCustomerCompaniesCVRNR.setText("");
        tfCustomerCompaniesName.setText("");
        tfCustomerCompaniesEmail.setText("");
        tfCustomerCompaniesZipcode.setText("");
        tfCustomerCompaniesPhone.setText("");
        tfCustomerCompaniesAddress.setText("");

    }

    private void viewCustomers()
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewLoogins.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute view_logins");
            do {
                String data = DB.getData();
                if (data.equals(DB.NOMOREDATA)) {
                    break;
                } else {

                }
            } while (true);
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
        paneCustomerEmployeeAdd.setVisible(true);
        panetblViewCustomerEmployee.setVisible(false);
    }

    @FXML
    private void showTblViewEmpolyees (ActionEvent event)
    {
        panetblViewCustomerEmployee.setVisible(true);
        paneCustomerEmployeeAdd.setVisible(false);
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
        panetblViewManageLogins.setVisible(true);
        paneManageLoginsCreate.setVisible(false);
        paneManageLoginsEdit.setVisible(false);
        viewLogins();
    }
    @FXML
    private void showPaneManageLoginsCreateAndEdit(ActionEvent event)
    {
        paneManageLoginsCreate.setVisible(true);
        panetblViewManageLogins.setVisible(false);
        paneManageLoginsEdit.setVisible(false);
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
    private void deleteLogIn (ActionEvent event)
    {
        index = tblViewLoogins.getSelectionModel().getFocusedIndex();
        String password = tblViewLoogins.getItems().get(index).getPassword();
        DB.deleteSQL("delete from tbl_Log_In where fld_Password = '"+password+"'");
        viewLogins();
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
    private void editLogins() {

        paneManageLoginsCreate.setVisible(false);
        panetblViewManageLogins.setVisible(false);
        paneManageLoginsEdit.setVisible(true);
        index = tblViewLoogins.getSelectionModel().getFocusedIndex(); // maybe change the name to column index and not just call it index
        tfManageLoginsEditUsername.setText(tblViewLoogins.getItems().get(index).getUserName());
        tfManageLoginsEditPassword.setText(tblViewLoogins.getItems().get(index).getPassword());
        tfManageLoginsEditPersonID.setText(tblViewLoogins.getItems().get(index).getPersonID());

    }

    @FXML
    private void updateLogins(ActionEvent event)
    {
        String username = tfManageLoginsEditUsername.getText();
        String password = tfManageLoginsEditPassword.getText();
        String personID = tfManageLoginsEditPersonID.getText();
        index = tblViewLoogins.getSelectionModel().getFocusedIndex();
        String oldPassword = tblViewLoogins.getItems().get(index).getPassword();
        DB.updateSQL("update tbl_Log_In set fld_Username = '"+username+"', fld_Password = '"+password+"', fld_Person_ID = '"+personID+"' where fld_Password = '"+oldPassword+"'");

    }

    // end of manage LOGINS

    private void viewEducationPlans() throws SQLException {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewEducationPlans.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute view_ep_Admin ");
            epID.setCellValueFactory(new PropertyValueFactory<>("epID"));
            AMU.setCellValueFactory(new PropertyValueFactory<>("AMU"));
            course.setCellValueFactory(new PropertyValueFactory<>("course"));
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
    private void exportCsvFile(ActionEvent event) throws Exception {
        Writer writer = null;
        try {
            File file = new File("C:\\Users\\Marti\\Desktop\\csvFiler fra SA\\mojn.csv");
            writer = new BufferedWriter(new FileWriter(file));
            for (EducationPlans ep : epList) {

                String text = ep.getEpID() + "" + ep.getAMU() + "" + ep.getCourse() + "";
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
        tblViewCustomerEmployee.getItems().clear();
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
            tblViewCustomerEmployee.setItems(cusEmpList);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    private void viewCustomerCompanies()
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewCustomerCompanies.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute view_customers_admin");
            columnCompaniesCVRNR.setCellValueFactory(new PropertyValueFactory<>("cvrNr"));
            columnCompaniesName.setCellValueFactory(new PropertyValueFactory<>("customerCompanyName"));
            columnCompaniesPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));
            columnCompaniesEmail.setCellValueFactory(new PropertyValueFactory<>("customerMail"));
            columnCompaniesAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            columnCompaniesZipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }

            for (int i = 0; i <row.size()/6 ; i++) {
                CustomerCompanies customerCompanies = new CustomerCompanies(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2), row.get(i+counter+3), row.get(i+counter+4),row.get(i+counter+5));
                customerCompaniesList.add(customerCompanies);
                counter+=5;
            }
            tblViewCustomerCompanies.setItems(customerCompaniesList);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    private void deleteCustomerCompany(ActionEvent event)
    {
        index = tblViewCustomerCompanies.getSelectionModel().getFocusedIndex();
        String cvrNr = tblViewCustomerCompanies.getItems().get(index).getCvrNr();
        DB.deleteSQL("delete from tbl_Customer where fld_CVR_NR = '"+cvrNr+"'");
        viewCustomerCompanies();

    }
    @FXML
    private void createNewCustomerEmployee(ActionEvent event)
    {
       String employeeName = tfCustomerEmployeesName.getText();
       String employeeCPRNR = tfCustomerEmployeesCPR.getText();
       String employeeCVRNR = tfCustomerEmployeesCVR.getText();
       String employeePhone = tfCustomerEmployeesPhone.getText();
       String employeeMail = tfCustomerEmployeesEmail.getText();
       DB.insertSQL("insert into tbl_Customer_Employee values('"+employeeCPRNR+"','"+employeeName+"','"+employeeMail+"','"+employeePhone+"','"+employeeCVRNR+"')");
       tfCustomerEmployeesName.setText("");
       tfCustomerEmployeesCPR.setText("");
       tfCustomerEmployeesCVR.setText("");
       tfCustomerEmployeesEmail.setText("");
       tfCustomerEmployeesPhone.setText("");
    }

    /**
     * this method is for deleting a customers employee from the database
     * @param event actionevent to trigger when the button is pressed
     * @author Martin Iversen
     * @date 11-06-2019
     * */
    @FXML
    private void deleteCustomerEmployee(ActionEvent event)
    {
        index = tblViewCustomerEmployee.getSelectionModel().getFocusedIndex();
        String cprNr = tblViewCustomerEmployee.getItems().get(index).getCprNr();
        DB.deleteSQL("delete from tbl_Calendar where fld_EducationPlan_ID = (select fld_Education_Plan_ID from tbl_Education_Plan where fld_Customer_CPR_NR = '"+cprNr+"')");
        DB.deleteSQL("delete from tbl_Education_Plan where fld_Customer_CPR_NR = '"+cprNr+"'");
        DB.deleteSQL("delete from tbl_Customer_Employee where fld_CPR_NR = '"+cprNr+"'");
        viewCustomerEmployees();

    }







}