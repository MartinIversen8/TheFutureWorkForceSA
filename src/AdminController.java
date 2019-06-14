import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * A controller to the program TheFutureWorkForce
 * @date 13-06-2019
 * @author Group Gorm, Nicklas and Martin Iversen
 * */

public class AdminController implements Initializable {
    /**
     * All the panes used
     * */
    @FXML
    private Pane paneEducationPlans,paneEducationPlansCreateAndEdit,paneCoursesAndProviders, paneCoursesAdd,
    paneCustomerCompanies,paneCustomerCompaniesAddAndEdit, paneCustomerEmployeeEdit,paneCustomerEmployeeAdd,paneManageLogins
            ,paneManageLoginsCreate, panetblViewCourses, panetblViewManageLogins, panetblViewEducation, panetblViewCustomerCompanies, panetblViewCustomerEmployee,
            paneEducationPlansEdit,paneManageLoginsEdit,paneCoursesEdit,paneProvidersAdd,paneProvidersEdit,panetblViewProviders,paneCustomerEmployee,paneCustomerCompaniesEdit,paneAcademyEmployee
            ,paneAcademyEmployeeAdd,paneAcademyEmployeeEdit,panetblViewAcademyEmployee;
    /**
     * All the textfields used
     * */
    @FXML
    private TextField tfEducationCustomerEmployeeName,tfEducationCustomerCompanyName,tfEducationPriority,tfEducationCprNr, tfCourseAddPaneCourseTitle,tfCourseAddPaneNumberOfDays,
    tfCustomerCompaniesCVRNR,tfCustomerCompaniesName,tfCustomerCompaniesEmail,tfCustomerCompaniesZipcode,tfCustomerCompaniesPhone,tfCustomerCompaniesAddress,tfManageLoginsUsername,tfManageLoginsPersonID,tfManageLoginsPassword,tfEducationAmuNR
    ,tfCustomerEmployeesName,tfCustomerEmployeesPhone,tfCustomerEmployeesCPR,tfCustomerEmployeesCVR,tfCustomerEmployeesEmail,tfEducationCustomerEmployeeName2,tfEducationCprNr2,tfEducationSmartAcademyEmployeeID2,tfEducationAmuNR2
    ,tfEducationCustomerCompanyName2,tfEducationPriority2,tfEducationProvider2,tfManageLoginsEditPassword,tfManageLoginsEditUsername,tfManageLoginsEditPersonID
    ,tfCourseEditPaneCourseTitle,tfCourseEditPaneNumberOfDays,tfProvidersAddPaneName,tfProvidersAddPaneAddress, tfProvidersAddPaneZipcode,
    tfProvidersEditPaneName, tfProvidersEditPaneAddress, tfProvidersEditPaneZipcode,tfProvidersAddPaneCity,tfCustomerEmployeesEmailEdit,tfCustomerEmployeesCVREdit,tfCustomerEmployeesCPREdit,tfCustomerEmployeesPhoneEdit,tfCustomerEmployeesNameEdit
    ,tfCustomerCompaniesCVRNREdit,tfCustomerCompaniesNameEdit,tfCustomerCompaniesPhoneEdit,tfCustomerCompaniesEmailEdit,tfCustomerCompaniesAddressEdit,tfCustomerCompaniesZipcodeEdit,tfAcademyEmployeesEmailAdd,tfAcademyEmployeesAddressAdd,tfAcademyEmployeesZipcodeAdd
    ,tfAcademyEmployeesPhoneAdd,tfAcademyEmployeesNameAdd,tfAcademyEmployeesNameEdit,tfAcademyEmployeesPhoneEdit,tfAcademyEmployeesZipcodeEdit,tfAcademyEmployeesAddressEdit,tfAcademyEmployeesEmailEdit;
    /**
     * All the textareas used
     * */
    @FXML
    private TextArea taCoursesAddPaneCourseDescription,taCoursesEditPaneCourseDescription;
    /**
     * The datepickers we used
     * */
    @FXML
    private DatePicker dpEducationStartDate,dpEducationEndDate,dpEducationStartDate2,dpEducationEndDate2;
    /**
     * tableviews that can contain a class
     * */
    @FXML
    private TableView<EducationPlans> tblViewEducationPlans;
    @FXML
    private TableView<CustomerEmployees> tblViewCustomerEmployee;
    @FXML
    private TableView<LogIns> tblViewLogins;
    @FXML
    private TableView<Courses> tblViewCourses;
    @FXML
    private TableView<CustomerCompanies> tblViewCustomerCompanies;
    @FXML
    private TableView<Provider> tblViewProvider;
    @FXML
    private TableView<SmartAcademyEmp> tblViewAcademyEmployee;
    /**
     * all table columns for the tableviews
     * */
    @FXML
    private TableColumn<ObservableList<String>, String> tblColumnEducationFullName, tblColumnEducationCPRNR, tblColumnEducationCompany, tblColumnEducationProvider, tblColumnEducationPriority, tblColumnEducationStartDate, tblColumnEducationEndDate, tblColumnEducationID, tblColumnEducationAMU, tblColumnEducationCourse, tblColumnEducationMail
     ,tblColumnAcademyEmployeeName,tblColumnAcademyEmployeePhoneNr,tblColumnAcademyEmployeeMail,tblColumnAcademyEmployeeAddress,tblColumnAcademyEmployeeZipcode,
     tblColumnCustomerEmployeeName, tblColumnCustomerEmployeePhoneNr, tblColumnCustomerEmployeeMail, tblColumnCustomerEmployeeCPRNR, tblColumnCustomerEmployeeCVRNR, tblColumnLoginsUsername,
     tblColumnLoginsPassword, tblColumnLoginsPersonID, tblColumnCourseTitle, tblColumnCompaniesCVRNR, tblColumnCompaniesName, tblColumnCompaniesPhone, tblColumnCompaniesEmail, tblColumnCompaniesAddress,
     tblColumnCompaniesZipcode,tblColumnCourseAMU,tblColumnCourseDescription,tblColumnCourseNRofDays, tblColumnProviderName, tblColumnProviderAddress, tblColumnProviderZipcode,tblColumnAcademyEmployeeID;
    /**
     * Observable lists we use to get to the tableviews
     * */
    ObservableList<EducationPlans> epList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<CustomerEmployees> cusEmpList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<LogIns> logInsList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Courses> courseList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Provider> providerList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<CustomerCompanies> customerCompaniesList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<SmartAcademyEmp> smartAcademyEmpsList = FXCollections.observableArrayList(new ArrayList<>());
private int index;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

 // here we start with the actions for educations plans

  @FXML
  /**
   * method that hides all other panes that the one we want to see
   * */
    private void showBtnEducationPlans(ActionEvent event) throws NullPointerException
    {
        paneEducationPlans.setVisible(true);
        paneCoursesAndProviders.setVisible(false);
        paneCustomerCompanies.setVisible(false);
        paneCustomerEmployee.setVisible(false);
        paneManageLogins.setVisible(false);
        paneEducationPlansEdit.setVisible(false);
        paneAcademyEmployee.setVisible(false);

    }
    /**
     * method for showing the pane with the tableview and calling the method viewEducationPlans,
     * that puts data in the tableview
     **/
    @FXML
    private void showEducationPlans (ActionEvent event) throws SQLException {
        panetblViewEducation.setVisible(true);
        paneEducationPlansCreateAndEdit.setVisible(false);
        paneEducationPlansEdit.setVisible(false);
        viewEducationPlans();

    }

    @FXML
    /**
     * method for showing the pane where you can create a educationPlan
     * and hiding the other panes
     * */
    private void showCreatePlans (ActionEvent event)
    {
        panetblViewEducation.setVisible(false);
        paneEducationPlansCreateAndEdit.setVisible(true);
        paneEducationPlansEdit.setVisible(false);
    }

    @FXML
    /**
     * method for showing the pane where you can edit an EducationPlan
     * and hiding the other pane. It also gets info from the tableview
     * and displays it in the text fields that are in the pane
     * */
    private void editEducationPlan() {

        panetblViewEducation.setVisible(false);
        paneEducationPlansCreateAndEdit.setVisible(false);
        paneEducationPlansEdit.setVisible(true);
        // index is which row of the tblViewEducationPlans were chosen
        index = tblViewEducationPlans.getSelectionModel().getFocusedIndex(); // maybe change the name to column index and not just call it index
        // here it gets all info from the select row, and adds it to the text fields and datepickers
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
    /**
     * method for updating the education plans if something needs to be change
     * the update statements get the info from the different textfields and datepickers
     * */
    private void updateEducationPlan(ActionEvent event)
    {   // defining and getting info from textfields and datepickers
        //we had to format the datepickers before they could be used in update statements
        String startDate = dpEducationStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dpEducationEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String amuNo = tfEducationAmuNR.getText();
        String empName = tfEducationCustomerEmployeeName.getText();
        String priority = tfEducationPriority.getText();
        // here we get the id of which EducationPlan to change
        String epId = tblViewEducationPlans.getItems().get(index).getEpID();
        // first update statement, cause we have information from two tables in the tableview
        DB.updateSQL("update tbl_Education_Plan set fld_Name_of_Attendee = '"+empName+"', fld_Prority = '"+priority+"' where fld_Education_Plan_ID = '"+epId+"' ");
        DB.updateSQL("update tbl_Calendar set fld_Start_Date = '"+startDate+"',fld_End_Date = '"+endDate+"',fld_AMUNo ="+amuNo+" where fld_EducationPlan_ID = '"+epId+"' ");
        // resting the textfields and datepickers
        tfEducationAmuNR.setText("");
        tfEducationCustomerEmployeeName.setText("");
        tfEducationPriority.setText("");
        dpEducationStartDate.setValue(null);
        dpEducationEndDate.setValue(null);

    }

    @FXML
    /**
     * method for deleting a educationPlan
     * */
    private void deleteEducationPlan(ActionEvent event) throws SQLException {
        // index is which row of the tblViewEducationPlans were chosen
        index = tblViewEducationPlans.getSelectionModel().getFocusedIndex();
        // getting the educationPlan id
        String epID = tblViewEducationPlans.getItems().get(index).getEpID();
        // we are deleting the records where educationPlan ID is equal to the selected tableview row's
        DB.deleteSQL("delete from tbl_Calendar where fld_EducationPlan_ID = '"+epID+"'");
        DB.deleteSQL("delete from tbl_Education_Plan where fld_Education_Plan_ID = '"+epID+"'");
        // updating the tableview
        viewEducationPlans();

    }

    @FXML
    /**
     * method for creating a new educationPlan
     * */
    private void createEducationPlan(ActionEvent event)
    {   // defining and getting input
        String startDate = dpEducationStartDate2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dpEducationEndDate2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String amuNo = tfEducationAmuNR2.getText();
        String cprNr = tfEducationCprNr2.getText();
        String empName = tfEducationCustomerEmployeeName2.getText();
        String priority = tfEducationPriority2.getText();
        String smartAcademyID = tfEducationSmartAcademyEmployeeID2.getText();
        String provider = tfEducationProvider2.getText();
        // inserts into database/tbl_Education_Plan
        DB.insertSQL("insert into tbl_Education_Plan values('"+priority+"','"+empName+"','"+cprNr+"','"+smartAcademyID+"')");
        // for getting the newest education plan created so we can insert into the tbl_Calendar
        DB.selectSQL("SELECT TOP 1 fld_Education_Plan_ID FROM tbl_Education_Plan ORDER BY fld_Education_Plan_ID DESC");
        String educationPlanID = DB.getData();
        // this for avoiding the error message pending data
        getPendingData();
        // inserts the rest of the data
        DB.insertSQL("insert into tbl_Calendar values('"+amuNo+"','"+provider+"','"+startDate+"','"+endDate+"','"+educationPlanID+"')");
        // clearing textfields
        tfEducationProvider2.setText("");
        tfEducationSmartAcademyEmployeeID2.setText("");
        tfEducationPriority2.setText("");
        tfEducationCustomerEmployeeName2.setText("");
        tfEducationCustomerCompanyName2.setText("");
        tfEducationCprNr2.setText("");
        tfEducationAmuNR2.setText("");
    }
    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewEducationPlans() throws SQLException {
        // counter is a helper to when creating a educationPlan
        int counter = 0;
        // row is for holding the information we get from our stored procedure
        ObservableList<String> row = FXCollections.observableArrayList();
        // clearing the table for avoiding recurring data
        tblViewEducationPlans.getItems().clear();
        try {
            // making resultset from the stored procedure
            ResultSet rs = DB.createProcResultset("execute view_ep_Admin ");
            //populating each individual column with data
            tblColumnEducationID.setCellValueFactory(new PropertyValueFactory<>("epID"));
            tblColumnEducationAMU.setCellValueFactory(new PropertyValueFactory<>("AMU"));
            tblColumnEducationCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
            tblColumnEducationFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            tblColumnEducationPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
            tblColumnEducationMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            tblColumnEducationStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            tblColumnEducationEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            tblColumnEducationCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
            tblColumnEducationCPRNR.setCellValueFactory(new PropertyValueFactory<>("cprNr"));
            tblColumnEducationProvider.setCellValueFactory(new PropertyValueFactory<>("provider"));
            // getting data from resultset and adding it to the list row
            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }
            // creating educationPlans from the list row.
            // row is divide by the number of parameters in the constructor
            for (int i = 0; i <row.size()/11 ; i++) {
                EducationPlans ep2 = new EducationPlans(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2), row.get(i+counter+3), row.get(i+counter+4), row.get(i+counter+5),
                        row.get(i+counter+6), row.get(i+counter+7), row.get(i+counter+8),row.get(i+counter+9),row.get(i+counter+10));
                // adding the created educationPlan to the eplist
                epList.add(ep2);
                counter+=10;
            }
            // setting the "items" in the tableview to be the data in the epList that contains EducationPlans
            tblViewEducationPlans.setItems(epList);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }
    // the end of education plans

    // here starts the actions we have for Courses and providers
    @FXML
    /**
     * showing the course and providers pane
     * */
    private void showBtnCoursesAndProviders(ActionEvent event)
    {
        paneCoursesAndProviders.setVisible(true);
        paneEducationPlans.setVisible(false);
        paneCustomerCompanies.setVisible(false);
        paneCustomerEmployee.setVisible(false);
        paneManageLogins.setVisible(false);
        paneAcademyEmployee.setVisible(false);
    }
    @FXML
    /**
     * shows the pane where you can see the tableview
     * and loads its content
     * */
    private void btnViewCourses (ActionEvent event)
    {
        panetblViewCourses.setVisible(true);
        paneCoursesAdd.setVisible(false);
        paneCoursesEdit.setVisible(false);
        paneProvidersAdd.setVisible(false);
        paneProvidersEdit.setVisible(false);
        panetblViewProviders.setVisible(false);
        viewCourses();

    }
    @FXML
    /**
     * shows the pane where you can add/create a new Course
     * */
    private void showAddCourses(ActionEvent event)
    {
        paneCoursesAdd.setVisible(true);
        panetblViewCourses.setVisible(false);
        paneCoursesEdit.setVisible(false);
        paneProvidersAdd.setVisible(false);
        paneProvidersEdit.setVisible(false);
        panetblViewProviders.setVisible(false);

    }

    @FXML
    /**
     * shows the pane where you can Edit a course
     * and inserts data from the selected course in to textfields
     * */
    private void showEditCourses(ActionEvent event)
    {
        paneCoursesAdd.setVisible(false);
        panetblViewCourses.setVisible(false);
        paneCoursesEdit.setVisible(true);
        paneProvidersAdd.setVisible(false);
        paneProvidersEdit.setVisible(false);
        panetblViewProviders.setVisible(false);
        index = tblViewCourses.getSelectionModel().getFocusedIndex();
        // the if statement is for if you forget to select a course
        if(index>=0) {
            tfCourseEditPaneCourseTitle.setText(tblViewCourses.getItems().get(index).getCourseTitle());
            tfCourseEditPaneNumberOfDays.setText(tblViewCourses.getItems().get(index).getCourseNumberOfDays());
            taCoursesEditPaneCourseDescription.setText(tblViewCourses.getItems().get(index).getCourseDescription());
        }

    }
    @FXML
    /**
     * shows the pane where you can see all the providers
     * and loads the data into the tableview
     * */
    private void showViewProviders(ActionEvent event)
    {
        paneCoursesAdd.setVisible(false);
        panetblViewCourses.setVisible(false);
        paneCoursesEdit.setVisible(false);
        paneProvidersAdd.setVisible(false);
        paneProvidersEdit.setVisible(false);
        panetblViewProviders.setVisible(false);
        panetblViewProviders.setVisible(true);
        paneProvidersEdit.setVisible(false);
        paneProvidersAdd.setVisible(false);
        viewProviders();
    }

    @FXML
    /**
     * shows the pane where you can edit the provider
     * and adds information to the textfields
     * */
    private void showProvidersEditPane()
    {
        paneCoursesAdd.setVisible(false);
        panetblViewCourses.setVisible(false);
        paneCoursesEdit.setVisible(false);
        paneProvidersAdd.setVisible(false);
        paneProvidersEdit.setVisible(false);
        panetblViewProviders.setVisible(false);
        panetblViewProviders.setVisible(false);
        paneProvidersEdit.setVisible(true);
        paneProvidersAdd.setVisible(false);
        // the if statement is for if you forget to select a provider
        index = tblViewProvider.getSelectionModel().getFocusedIndex();
        if(index>=0){
        tfProvidersEditPaneName.setText(tblViewProvider.getItems().get(index).getProviderName());
        tfProvidersEditPaneAddress.setText(tblViewProvider.getItems().get(index).getAddress());
        tfProvidersEditPaneZipcode.setText(tblViewProvider.getItems().get(index).getZipcode());}
    }

    @FXML
    /**
     * shows the pane were you can add/create a new provider
     * */
    private void showProvidersAdd ()
    {
        paneCoursesAdd.setVisible(false);
        panetblViewCourses.setVisible(false);
        paneCoursesEdit.setVisible(false);
        paneProvidersAdd.setVisible(false);
        paneProvidersEdit.setVisible(false);
        panetblViewProviders.setVisible(false);
        panetblViewProviders.setVisible(false);
        paneProvidersEdit.setVisible(false);
        paneProvidersAdd.setVisible(true);
        paneAcademyEmployee.setVisible(false);
    }
    @FXML
    /**
     * for when you create a new Course
     * */
    private void createNewCourse(ActionEvent event)
    {   // getting input
        String title =  tfCourseAddPaneCourseTitle.getText();
        String numberOfDays = tfCourseAddPaneNumberOfDays.getText();
        String description = taCoursesAddPaneCourseDescription.getText();
        // inserting input to the database
        DB.insertSQL("insert into tbl_Courses values('"+title+"','"+numberOfDays+"','"+description+"')");
        //clearing the textfields
        tfCourseAddPaneCourseTitle.setText("");
        tfCourseAddPaneNumberOfDays.setText("");
        taCoursesAddPaneCourseDescription.setText("");

    }
    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewCourses()
    {
        // counter is a helper to when creating a course
        int counter = 0;
        // row is for holding the information we get from our stored procedure
        ObservableList<String> row = FXCollections.observableArrayList();
        // clearing the table for avoiding recurring data
        tblViewCourses.getItems().clear();
        try {
            // making resultset from the stored procedure
            ResultSet rs = DB.createProcResultset("execute view_courses");
            //populating each individual column with data
            tblColumnCourseTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
            tblColumnCourseAMU.setCellValueFactory(new PropertyValueFactory<>("courseAMU"));
            tblColumnCourseNRofDays.setCellValueFactory(new PropertyValueFactory<>("courseNumberOfDays"));
            tblColumnCourseDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
            // getting data from resultset and adding it to the list row
            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }
            // creating educationPlans from the list row.
            // row is divide by the number of parameters in the constructor
            for (int i = 0; i <row.size()/4; i++) {
                Courses courses = new Courses(row.get(i+counter), row.get(i+counter+1),row.get(i+counter+2),row.get(i+counter+3));
                // adding the created course to the courselist
                courseList.add(courses);
                counter+=3;
            }

            // setting the "items" in the tableview to be the data in the epList that contains EducationPlans
            tblViewCourses.setItems(courseList);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    /**
     * when you update the course
     * */
    private void updateCourses()
    {   //getting input
        String courseTitle = tfCourseEditPaneCourseTitle.getText();
        String courseNumberOfDays = tfCourseEditPaneNumberOfDays.getText();
        String courseDescription = taCoursesEditPaneCourseDescription.getText();
        // index is which row of the tblView were chosen
        index = tblViewCourses.getSelectionModel().getFocusedIndex();
        String AMU = tblViewCourses.getItems().get(index).getCourseAMU();
        DB.updateSQL("update tbl_Courses set fld_Course_title = '"+courseTitle+"', fld_Number_of_days = '"+courseNumberOfDays+"',fld_Description = '"+courseDescription+"' where fld_AMU_No = '"+AMU+"'");
        tfCourseEditPaneCourseTitle.setText("");
        tfCourseEditPaneNumberOfDays.setText("");
        taCoursesEditPaneCourseDescription.setText("");
    }

    @FXML
    /**
     * for deleting a course
     * gets which course you want to delete
     * and then deletes it
     * */
    private void deleteCourse(ActionEvent event)
    {   // index is which row of the tblView were chosen
        index = tblViewCourses.getSelectionModel().getFocusedIndex();
        String amu = tblViewCourses.getItems().get(index).getCourseAMU();
        DB.deleteSQL("delete from tbl_Courses where fld_AMU_No = '"+amu+"'");
        // updating the tableview
        viewCourses();

    }
    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewProviders()
    {    // counter is a helper to when creating a providers
        int counter = 0;
        // row is for holding the information we get from our stored procedure
        ObservableList<String> row = FXCollections.observableArrayList();
        // clearing the table for avoiding recurring data
        tblViewProvider.getItems().clear();
        try {
            // making resultset from the stored procedure
            ResultSet rs = DB.createProcResultset("execute view_providers");
            tblColumnProviderName.setCellValueFactory(new PropertyValueFactory<>("providerName"));
            tblColumnProviderAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            tblColumnProviderZipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
            // getting data from resultset and adding it to the list row
            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }
            // creating Providers from the list row.
            // row is divide by the number of parameters in the constructor
            for (int i = 0; i <row.size()/3; i++) {
                Provider provider = new Provider(row.get(i+counter), row.get(i+counter+1),row.get(i+counter+2));
                // adding the created provider til providerList
                providerList.add(provider);
                counter+=2;
            }
            // setting the "items" in the tableview
            tblViewProvider.setItems(providerList);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    /**
     * for updating the provider in database
     * */
    private void updateProvider (ActionEvent event)
    {   // getting input
        String providerName = tfProvidersEditPaneName.getText();
        String address = tfProvidersEditPaneAddress.getText();
        String zipcode = tfProvidersEditPaneZipcode.getText();
        index = tblViewProvider.getSelectionModel().getFocusedIndex();
        String providersName = tblViewProvider.getItems().get(index).getProviderName();
        // updating the tbl_provider
        DB.updateSQL("update tbl_Provider set fld_Prov_Name = '"+providerName+"',fld_Prov_Address = '"+address+"',fld_Zipcode = '"+zipcode+"' where fld_Prov_Name = '"+providersName+"'" );
        //clearing the textfields
        tfProvidersEditPaneName.setText("");
        tfProvidersEditPaneAddress.setText("");
        tfProvidersEditPaneZipcode.setText("");
    }

    @FXML
    /**
     * for when you create a new provider
     *
     * */
    private void createNewProvider ()
    {
        // getting input
        String providerName = tfProvidersAddPaneName.getText();
        String address = tfProvidersAddPaneAddress.getText();
        String zipcodde = tfProvidersAddPaneZipcode.getText();
        String city = tfProvidersAddPaneCity.getText();
        DB.selectSQL("select fld_Zipcode from tbl_Zipcode");
        ObservableList zipcodeList = FXCollections.observableArrayList();
        // to avoid pending data
        getPendingData();
        // if you add a provider in a city that doesnt exist it creates a new city
        if (zipcodeList.contains(zipcodde)==false)
        {
            DB.insertSQL("insert into tbl_Zipcode values('"+zipcodde+"','"+city+"')");
        }
        // inserting data
        DB.insertSQL("insert into tbl_Provider values('"+providerName+"','"+address+"','"+zipcodde+"')");
        // clearing textfield
        tfProvidersAddPaneName.setText("");
        tfProvidersAddPaneAddress.setText("");
        tfProvidersAddPaneZipcode.setText("");
        tfProvidersAddPaneCity.setText("");
    }

    @FXML
    /**
     * for when you click delete button
     * */
    private void deleteProvider()
    {   // index is the selected row number of the tableview
        index = tblViewProvider.getSelectionModel().getFocusedIndex();
        String providerName = tblViewProvider.getItems().get(index).getProviderName();
        DB.deleteSQL("delete from tbl_Courses where fld_AMU_No = '"+providerName+"'");
        // updating table
        viewProviders();
    }

    // End of Courses and providers

    // Start of Customer/Companies
    @FXML
    /**
     * shows the pane with Customer
     * */
    private void showCustomerPane (ActionEvent event)
    {
        paneCustomerCompanies.setVisible(true);
        paneEducationPlans.setVisible(false);
        paneCoursesAndProviders.setVisible(false);
        paneCustomerEmployee.setVisible(false);
        paneManageLogins.setVisible(false);
        paneAcademyEmployee.setVisible(false);
    }

    @FXML
    /**
     * shows the pane with tableview of Customers
     * and fills it with data
     * */
    private void showCustomerTblView (ActionEvent event)
    {
        panetblViewCustomerCompanies.setVisible(true);
        paneCustomerCompaniesAddAndEdit.setVisible(false);
        paneCustomerCompaniesEdit.setVisible(false);
        viewCustomerCompanies();
    }

    @FXML
    /**
     * show the pane where you can add/create a new Customer
     * */
    private void showCustomerAddPane (ActionEvent event)
    {
        panetblViewCustomerCompanies.setVisible(false);
        paneCustomerCompaniesAddAndEdit.setVisible(true);
        paneCustomerCompaniesEdit.setVisible(false);
    }

    @FXML
    /**
     * shows the pane where you can edit the different things in customer
     * and fills the textfields with data from the selected tableview row
     * */
    private void showCustomerEditPane()
    {
        paneCustomerCompaniesEdit.setVisible(true);
        panetblViewCustomerCompanies.setVisible(false);
        paneCustomerCompaniesAddAndEdit.setVisible(false);

        index = tblViewCustomerCompanies.getSelectionModel().getFocusedIndex(); // maybe change the name to column index and not just call it index
        if(index>=0){
            tfCustomerCompaniesAddressEdit.setText(tblViewCustomerCompanies.getItems().get(index).getAddress());
            tfCustomerCompaniesCVRNREdit.setText(tblViewCustomerCompanies.getItems().get(index).getCvrNr());
            tfCustomerCompaniesEmailEdit.setText(tblViewCustomerCompanies.getItems().get(index).getCustomerMail());
            tfCustomerCompaniesNameEdit.setText(tblViewCustomerCompanies.getItems().get(index).getcustomerCompanyName());
            tfCustomerCompaniesPhoneEdit.setText(tblViewCustomerCompanies.getItems().get(index).getPhoneNr());
            tfCustomerCompaniesZipcodeEdit.setText(tblViewCustomerCompanies.getItems().get(index).getZipcode());

        }

    }
    @FXML
    /**
     * for updating information about the customer
     *
     * */
    private void updateCustomerCompanies()
    {
        // getting the input
        String address = tfCustomerCompaniesAddressEdit.getText();
        String cvrNR = tfCustomerCompaniesCVRNREdit.getText();
        String mail = tfCustomerCompaniesEmailEdit.getText();
        String name = tfCustomerCompaniesNameEdit.getText();
        String phone = tfCustomerCompaniesPhoneEdit.getText();
        String zipcode = tfCustomerCompaniesZipcodeEdit.getText();
        //index is the selected tableview row number
        index = tblViewCustomerCompanies.getSelectionModel().getFocusedIndex();
        String companyCVR = tblViewCustomerCompanies.getItems().get(index).getCvrNr();
        // updating the table in the database
        DB.updateSQL("update tbl_Customer set fld_CVR_NR = '"+cvrNR+"',fld_Name_Cos = '"+name+"',fld_Phone_Nr = '"+phone+"',fld_Cos_Email = '"+mail+"',fld_Cos_Address = '"+address+"',fld_Zipcode = '"+zipcode+"' where fld_CVR_NR='"+companyCVR+"' ");
        // clearing textfields
        tfCustomerCompaniesAddressEdit.setText("");
        tfCustomerCompaniesCVRNREdit.setText("");
        tfCustomerCompaniesEmailEdit.setText("");
        tfCustomerCompaniesNameEdit.setText("");
        tfCustomerCompaniesPhoneEdit.setText("");
        tfCustomerCompaniesZipcodeEdit.setText("");


    }

    @FXML
    /**
     * for when you create a new company/customer
     * */
    private void createNewCompany(ActionEvent event)
    {
        //getting the input
        String CVRNR = tfCustomerCompaniesCVRNR.getText();
        String name = tfCustomerCompaniesName.getText();
        String e_mail = tfCustomerCompaniesEmail.getText();
        String zipcode = tfCustomerCompaniesZipcode.getText();
        String phoneNr = tfCustomerCompaniesPhone.getText();
        String address = tfCustomerCompaniesAddress.getText();
        // inserting into the database
        DB.insertSQL("insert into tbl_Customer values('"+CVRNR+"','"+name+"','"+phoneNr+"','"+e_mail+"','"+address+"',"+zipcode+")");
        //clearing textfields
        tfCustomerCompaniesCVRNR.setText("");
        tfCustomerCompaniesName.setText("");
        tfCustomerCompaniesEmail.setText("");
        tfCustomerCompaniesZipcode.setText("");
        tfCustomerCompaniesPhone.setText("");
        tfCustomerCompaniesAddress.setText("");

    }
    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewCustomerCompanies()
    {    // counter is a helper to when creating a CustmerCompanies
        int counter = 0;
        // row is for holding the information we get from our stored procedure
        ObservableList<String> row = FXCollections.observableArrayList();
        // clearing the table for avoiding recurring data
        tblViewCustomerCompanies.getItems().clear();
        try {
            // making resultset from the stored procedure
            ResultSet rs = DB.createProcResultset("execute view_customers_admin");
            tblColumnCompaniesCVRNR.setCellValueFactory(new PropertyValueFactory<>("cvrNr"));
            tblColumnCompaniesName.setCellValueFactory(new PropertyValueFactory<>("customerCompanyName"));
            tblColumnCompaniesPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));
            tblColumnCompaniesEmail.setCellValueFactory(new PropertyValueFactory<>("customerMail"));
            tblColumnCompaniesAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            tblColumnCompaniesZipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
            // getting data from resultset and adding it to the list row
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }
            // creating CustomerCompanies from the list row.
            // row is divide by the number of parameters in the constructor
            for (int i = 0; i <row.size()/6 ; i++) {
                CustomerCompanies customerCompanies = new CustomerCompanies(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2), row.get(i+counter+3), row.get(i+counter+4),row.get(i+counter+5));
                customerCompaniesList.add(customerCompanies);
                counter+=5;
            } // setting the "items" in the tableview
            tblViewCustomerCompanies.setItems(customerCompaniesList);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    /**
     * for deleting a customerCompany
     * */
    private void deleteCustomerCompany(ActionEvent event)
    {
        index = tblViewCustomerCompanies.getSelectionModel().getFocusedIndex();
        String cvrNr = tblViewCustomerCompanies.getItems().get(index).getCvrNr();
        DB.deleteSQL("delete from tbl_Customer where fld_CVR_NR = '"+cvrNr+"'");
        viewCustomerCompanies();

    }
    // end of Customer/Companies

    // Start of Customer Employees
    @FXML
    /**
     * shows the pane where you can see CustomerEmployee pane
     * */
    private void showCustomerEmployeePane(ActionEvent event)
    {
        paneCustomerEmployee.setVisible(true);
        paneCustomerCompanies.setVisible(false);
        paneEducationPlans.setVisible(false);
        paneCoursesAndProviders.setVisible(false);
        paneManageLogins.setVisible(false);
        paneAcademyEmployee.setVisible(false);
    }
    @FXML
    /**
     * shows the pane where you can add/create a Employee
     * */
    private void showAddEmployee(ActionEvent event)
    {
        paneCustomerEmployeeAdd.setVisible(true);
        panetblViewCustomerEmployee.setVisible(false);
        paneCustomerEmployeeEdit.setVisible(false);
    }

    @FXML
    /**
     * shows the pane with the tableview of employees
     * */
    private void showTblViewEmpolyees (ActionEvent event)
    {
        panetblViewCustomerEmployee.setVisible(true);
        paneCustomerEmployeeAdd.setVisible(false);
        paneCustomerEmployeeEdit.setVisible(false);
        viewCustomerEmployees();
    }

    @FXML
    /**
     * shows the pane where you can edit the employee if wanted too
     * it also loads information from the selected employee who is going to be edited
     * */
    private void showEditEmployee()
    {
        panetblViewCustomerEmployee.setVisible(false);
        paneCustomerEmployeeAdd.setVisible(false);
        paneCustomerEmployeeEdit.setVisible(true);
        index = tblViewCustomerEmployee.getSelectionModel().getFocusedIndex(); // maybe change the name to column index and not just call it index
        if(index>=0){
            tfCustomerEmployeesCPREdit.setText(tblViewCustomerEmployee.getItems().get(index).getCprNr());
            tfCustomerEmployeesCVREdit.setText(tblViewCustomerEmployee.getItems().get(index).getCompanyCVRNR());
            tfCustomerEmployeesEmailEdit.setText(tblViewCustomerEmployee.getItems().get(index).getMail());
            tfCustomerEmployeesNameEdit.setText(tblViewCustomerEmployee.getItems().get(index).getName());
            tfCustomerEmployeesPhoneEdit.setText(tblViewCustomerEmployee.getItems().get(index).getPhoneNr());

        }
    }
    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewCustomerEmployees()
    {    // counter is a helper to when creating a CustomerEmployee
        int counter = 0;
        // row is for holding the information we get from our stored procedure
        ObservableList<String> row = FXCollections.observableArrayList();
        // clearing the table for avoiding recurring data
        tblViewCustomerEmployee.getItems().clear();
        try {
            // making resultset from the stored procedure
            ResultSet rs = DB.createProcResultset("execute view_cusEmps");
            tblColumnCustomerEmployeeName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            tblColumnCustomerEmployeePhoneNr.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));
            tblColumnCustomerEmployeeMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            tblColumnCustomerEmployeeCPRNR.setCellValueFactory(new PropertyValueFactory<>("cprNr"));
            tblColumnCustomerEmployeeCVRNR.setCellValueFactory(new PropertyValueFactory<>("companyCVRNR"));
            // getting data from resultset and adding it to the list row
            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }
            // creating CustomerEmployee from the list row.
            // row is divide by the number of parameters
            for (int i = 0; i <row.size()/5 ; i++) {
                CustomerEmployees cusEmp = new CustomerEmployees(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2), row.get(i+counter+3), row.get(i+counter+4));
                cusEmpList.add(cusEmp);
                counter+=4;

            }
            // setting the "items" in the tableview
            tblViewCustomerEmployee.setItems(cusEmpList);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    /**
     * for creating a new customerEmployee
     * inserting input to database
     * */
    private void createNewCustomerEmployee(ActionEvent event)
    {   //get input
        String employeeName = tfCustomerEmployeesName.getText();
        String employeeCPRNR = tfCustomerEmployeesCPR.getText();
        String employeeCVRNR = tfCustomerEmployeesCVR.getText();
        String employeePhone = tfCustomerEmployeesPhone.getText();
        String employeeMail = tfCustomerEmployeesEmail.getText();
        //inserting the input to the table in the database
        DB.insertSQL("insert into tbl_Customer_Employee values('"+employeeCPRNR+"','"+employeeName+"','"+employeeMail+"','"+employeePhone+"','"+employeeCVRNR+"')");
        // clearing textfields
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
    @FXML
    /**
     * for updating a customerEmployee
     *
     * */
    private void updateCustomerEmployees()
    {   // get input
        String phoneNr = tfCustomerEmployeesPhoneEdit.getText();
        String name = tfCustomerEmployeesNameEdit.getText();
        String email = tfCustomerEmployeesEmailEdit.getText();
        String cvrNR = tfCustomerEmployeesCVREdit.getText();
        String cprNr = tfCustomerEmployeesCPREdit.getText();
        index = tblViewCustomerEmployee.getSelectionModel().getFocusedIndex();
        String cpr = tblViewCustomerEmployee.getItems().get(index).getCprNr();
        // updating the table in the database
        DB.updateSQL("update tbl_Customer_Employee set fld_CPR_NR = '"+cprNr+"', fld_Cos_Emp_Name = '"+name+"',fld_Cos_Emp_Email = '"+email+"',fld_Cos_Emp_Phone_Nr = '"+phoneNr+"', fld_Customer_CVR_NR = '"+cvrNR+"' where fld_CPR_NR = '"+cpr+"' ");
        // clearing the textfields
        tfCustomerEmployeesPhoneEdit.setText("");
        tfCustomerEmployeesNameEdit.setText("");
        tfCustomerEmployeesEmailEdit.setText("");
        tfCustomerEmployeesCVREdit.setText("");
        tfCustomerEmployeesCPREdit.setText("");
    }

    // End of the Customer Employee

    // Start of smartAcademy
    @FXML
    /**
     * shows the pane of the SmartAcademyEmployee
     * and hides the other panes
     * */
    private void showSmartAcademyPane (ActionEvent event)
    {
        paneCustomerCompanies.setVisible(false);
        paneEducationPlans.setVisible(false);
        paneCoursesAndProviders.setVisible(false);
        paneCustomerEmployee.setVisible(false);
        paneManageLogins.setVisible(false);
        paneAcademyEmployee.setVisible(true);
    }

    @FXML
    /**
     * shows the pane with the tableview in and adds information
     * to the tableview
     * */
    private void showAcademyTblView (ActionEvent event)
    {
        panetblViewAcademyEmployee.setVisible(true);
        paneAcademyEmployeeEdit.setVisible(false);
        paneAcademyEmployeeAdd.setVisible(false);
        viewAcademyEmployee();
    }

    @FXML
    /**
     * shows the pane where you can add/Create a new smartAcademy employee
     * */
    private void showAcademyAddPane (ActionEvent event)
    {
        panetblViewAcademyEmployee.setVisible(false);
        paneAcademyEmployeeEdit.setVisible(false);
        paneAcademyEmployeeAdd.setVisible(true);
    }

    @FXML
    /**
     * shows the pane where you are able to edit information about a
     * smartAcademyEmployee and fills textfields out with the information of the selected employee
     * */
    private void showEditAcademyEmployee()
    {
        panetblViewAcademyEmployee.setVisible(false);
        paneAcademyEmployeeEdit.setVisible(true);
        paneAcademyEmployeeAdd.setVisible(false);
        index = tblViewAcademyEmployee.getSelectionModel().getFocusedIndex(); // maybe change the name to column index and not just call it index
        if(index>=0){
            tfAcademyEmployeesZipcodeEdit.setText(tblViewAcademyEmployee.getItems().get(index).getZipcode());
            tfAcademyEmployeesAddressEdit.setText(tblViewAcademyEmployee.getItems().get(index).getAddress());
            tfAcademyEmployeesEmailEdit.setText(tblViewAcademyEmployee.getItems().get(index).getMail());
            tfAcademyEmployeesNameEdit.setText(tblViewAcademyEmployee.getItems().get(index).getName());
            tfAcademyEmployeesPhoneEdit.setText(tblViewAcademyEmployee.getItems().get(index).getPhoneNr());

        }
    }

    private void viewAcademyEmployee()
    {   // counter is a helper to when creating a smartAcademyEmployee
        int counter = 0;
        // row is for holding the information we get from our stored procedure
        ObservableList<String> row = FXCollections.observableArrayList();
        // clearing the table for avoiding recurring data
        tblViewAcademyEmployee.getItems().clear();
        try {
            // making resultset from the stored procedure
            ResultSet rs = DB.createProcResultset("execute view_SA_emps");
            tblColumnAcademyEmployeeName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            tblColumnAcademyEmployeePhoneNr.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));
            tblColumnAcademyEmployeeMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            tblColumnAcademyEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            tblColumnAcademyEmployeeZipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
            tblColumnAcademyEmployeeID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            // getting data from resultset and adding it to the list row
            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }
            // creating smartAcademyEmployee from the list row.
            // row is divide by the number of parameters in the constructor
            for (int i = 0; i <row.size()/6 ; i++) {
                SmartAcademyEmp smartAcademyEmp = new SmartAcademyEmp(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2), row.get(i+counter+3), row.get(i+counter+4),row.get(i+counter+5));
                smartAcademyEmpsList.add(smartAcademyEmp);
                counter+=5;

            }
            // setting the "items" in the tableview
            tblViewAcademyEmployee.setItems(smartAcademyEmpsList);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    /**
     * for creating a new Academy employee
     * */
    private void createAcademyEmployee(ActionEvent event)
    {   //getting input
        String employeeName = tfAcademyEmployeesNameAdd.getText();
        String employeeAddress = tfAcademyEmployeesAddressAdd.getText();
        String employeeZipcode = tfAcademyEmployeesZipcodeAdd.getText();
        String employeePhone = tfAcademyEmployeesPhoneAdd.getText();
        String employeeMail = tfAcademyEmployeesEmailAdd.getText();
        // inserts input to the table in the database
        DB.insertSQL("insert into tbl_Smart_Academy_employee values('"+employeePhone+"','"+employeeName+"','"+employeeMail+"','"+employeeAddress+"','"+employeeZipcode+"')");
        tfAcademyEmployeesNameAdd.setText("");
        tfAcademyEmployeesAddressAdd.setText("");
        tfAcademyEmployeesZipcodeAdd.setText("");
        tfAcademyEmployeesPhoneAdd.setText("");
        tfAcademyEmployeesEmailAdd.setText("");

    }
    @FXML
    /**
     * for updating the AcademyEmployee information
     * */
    private void updateAcademyEmployee(ActionEvent event)
    {   // getting the inputs
        String phoneNr = tfAcademyEmployeesPhoneEdit.getText();
        String name = tfAcademyEmployeesNameEdit.getText();
        String email = tfAcademyEmployeesEmailEdit.getText();
        String zipcode = tfAcademyEmployeesZipcodeEdit.getText();
        String address = tfAcademyEmployeesAddressEdit.getText();
        index = tblViewAcademyEmployee.getSelectionModel().getFocusedIndex();
        String ID = tblViewAcademyEmployee.getItems().get(index).getID();
        // inserting inputs in the table in the database
        DB.updateSQL("update tbl_Smart_Academy_employee set fld_SA_Emp_Phone_Nr = '"+phoneNr+"',fld_SA_Emp_Name = '"+name+"',fld_SA_Emp_Email = '"+email+"',fld_SA_Emp_Address = '"+address+"',fld_Zipcode = '"+zipcode+"'where fld_SA_Employee_ID = '"+ID+"'");
        //clearing data
        tfAcademyEmployeesPhoneEdit.setText("");
        tfAcademyEmployeesNameEdit.setText("");
        tfAcademyEmployeesEmailEdit.setText("");
        tfAcademyEmployeesZipcodeEdit.setText("");
        tfAcademyEmployeesAddressEdit.setText("");
    }
    @FXML
    /**
     * for deleting a smartAcademyEmployee
     * and updating the tableview afterwards
     * */
    private void deleteAcademyEmployee(ActionEvent event)
    {
        index = tblViewAcademyEmployee.getSelectionModel().getFocusedIndex();
        String ID = tblViewAcademyEmployee.getItems().get(index).getID();
        DB.deleteSQL("delete from tbl_Smart_Academy_employee where fld_SA_Employee_ID = '"+ID+"' ");
        viewAcademyEmployee();

    }
    // end of smartAcademy

    //Start of manage Logins

    @FXML
    /**
     * shows the pane manageLogins
     * and hides the other panes
     * */
    private void showManageLoginsPane(ActionEvent event)
    {
        paneCustomerEmployee.setVisible(false);
        paneCustomerCompanies.setVisible(false);
        paneEducationPlans.setVisible(false);
        paneCoursesAndProviders.setVisible(false);
        paneManageLogins.setVisible(true);
        paneAcademyEmployee.setVisible(false);
    }
    @FXML
    /**
     * shows the pane with tableview that shows all the logins to the system
     * */
    private void showtblViewLogins(ActionEvent event)
    {
        panetblViewManageLogins.setVisible(true);
        paneManageLoginsCreate.setVisible(false);
        paneManageLoginsEdit.setVisible(false);
        viewLogins();
    }
    @FXML
    /**
     * shows the pane where you can create new logins to the system
     * */
    private void showPaneManageLoginsCreateAndEdit(ActionEvent event)
    {
        paneManageLoginsCreate.setVisible(true);
        panetblViewManageLogins.setVisible(false);
        paneManageLoginsEdit.setVisible(false);
    }
    @FXML
    /**
     * for when you want to create new logins
     * */
    private void createNewLogin(ActionEvent event)
    {   // getting input
        String username = tfManageLoginsUsername.getText();
        String password = tfManageLoginsPassword.getText();
        String personID = tfManageLoginsPersonID.getText();
        // inserting input to a table in the database
        DB.insertSQL("insert into tbl_Log_In values('"+password+"','"+username+"','"+personID+"')");
        //clearing textfields
        tfManageLoginsPassword.setText("");
        tfManageLoginsUsername.setText("");
        tfManageLoginsPersonID.setText("");

    }

    @FXML
    /**
     * for deleting a login
     * and updating the tableview again
     * */
    private void deleteLogIn (ActionEvent event)
    {
        index = tblViewLogins.getSelectionModel().getFocusedIndex();
        String password = tblViewLogins.getItems().get(index).getPassword();
        DB.deleteSQL("delete from tbl_Log_In where fld_Password = '"+password+"'");
        viewLogins();
    }

    private void viewLogins ()
    {    // counter is a helper to when creating a Logins
        int counter = 0;
        // row is for holding the information we get from our stored procedure
        ObservableList<String> row = FXCollections.observableArrayList();
        // clearing the table for avoiding recurring data
        tblViewLogins.getItems().clear();
        try {
            // making resultset from the stored procedure
            ResultSet rs = DB.createProcResultset("execute view_logins");
            tblColumnLoginsUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
            tblColumnLoginsPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            tblColumnLoginsPersonID.setCellValueFactory(new PropertyValueFactory<>("personID"));
            // getting data from resultset and adding it to the list row
            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }
            // creating Logins from the list row.
            // row is divide by the number of parameters
            for (int i = 0; i <row.size()/3 ; i++) {
                LogIns logIns = new LogIns(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2));
                logInsList.add(logIns);
                counter+=2;
            }
            // setting the "items" in the tableview
            tblViewLogins.setItems(logInsList);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    /**
     * show the pane where editing logins is possible
     * */
    private void editLogins() {

        paneManageLoginsCreate.setVisible(false);
        panetblViewManageLogins.setVisible(false);
        paneManageLoginsEdit.setVisible(true);
        index = tblViewLogins.getSelectionModel().getFocusedIndex();
        if(index>=0) {
            tfManageLoginsEditUsername.setText(tblViewLogins.getItems().get(index).getUserName());
            tfManageLoginsEditPassword.setText(tblViewLogins.getItems().get(index).getPassword());
            tfManageLoginsEditPersonID.setText(tblViewLogins.getItems().get(index).getPersonID());
        }

    }

    @FXML
    /**
     * for updating/editing the logins
     * */
    private void updateLogins(ActionEvent event)
    {
        String username = tfManageLoginsEditUsername.getText();
        String password = tfManageLoginsEditPassword.getText();
        String personID = tfManageLoginsEditPersonID.getText();
        index = tblViewLogins.getSelectionModel().getFocusedIndex();
        String oldPassword = tblViewLogins.getItems().get(index).getPassword();
        DB.updateSQL("update tbl_Log_In set fld_Username = '"+username+"', fld_Password = '"+password+"', fld_Person_ID = '"+personID+"' where fld_Password = '"+oldPassword+"'");
        tfManageLoginsEditUsername.setText("");
        tfManageLoginsEditPassword.setText("");
        tfManageLoginsEditPersonID.setText("");
    }

    // end of manage LOGINS


    @FXML
    /**
     * makes it possible to extract data from EducationPlansTableview from the program
     * */
    private void exportCsvFile(ActionEvent event) throws Exception {
        Writer writer = null;
        try {
            File file = new File("C:\\Users\\Marti\\Desktop\\csvFiler fra SA\\csvData.csv");
            writer = new BufferedWriter(new FileWriter(file));
            for (EducationPlans ep : epList) {

                String text = ep.getEpID() + "" + ep.getAMU() + "" + ep.getCourse() + ""+ep.getName()+""+ep.getCprNr()+
                ""+ep.getProvider()+""+ep.getPriority()+""+ep.getStartDate()+""+ep.getEndDate()+""+ep.getMail()+""+ep.getCompany();
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

    /**
     * for removing pending data fro a select statement
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