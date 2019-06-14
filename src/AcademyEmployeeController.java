import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AcademyEmployeeController {

    @FXML
    private Pane paneEducationPlans,paneEducationPlansCreateAndEdit,paneCoursesAndProviders, paneCoursesAdd,
            paneCustomerCompanies,paneCustomerCompaniesAddAndEdit, paneCustomerEmployeeEdit,paneCustomerEmployeeAdd
            , panetblViewCourses, panetblViewEducation, panetblViewCustomerCompanies, panetblViewCustomerEmployee,
            paneEducationPlansEdit,paneCoursesEdit,paneProvidersAdd,paneProvidersEdit,panetblViewProviders,paneCustomerEmployee,paneCustomerCompaniesEdit;
    @FXML
    private TextField tfEducationCustomerEmployeeName,tfEducationCustomerCompanyName,tfEducationPriority,tfEducationCprNr, tfCourseAddPaneCourseTitle,tfCourseAddPaneNumberOfDays,
            tfCustomerCompaniesCVRNR,tfCustomerCompaniesName,tfCustomerCompaniesEmail,tfCustomerCompaniesZipcode,tfCustomerCompaniesPhone,tfCustomerCompaniesAddress,tfEducationAmuNR
            ,tfCustomerEmployeesName,tfCustomerEmployeesPhone,tfCustomerEmployeesCPR,tfCustomerEmployeesCVR,tfCustomerEmployeesEmail,tfEducationCustomerEmployeeName2,tfEducationCprNr2,tfEducationSmartAcademyEmployeeID2,tfEducationAmuNR2
            ,tfEducationCustomerCompanyName2,tfEducationPriority2,tfEducationProvider2
            ,tfCourseEditPaneCourseTitle,tfCourseEditPaneNumberOfDays,tfProvidersAddPaneName,tfProvidersAddPaneAddress, tfProvidersAddPaneZipcode,
            tfProvidersEditPaneName, tfProvidersEditPaneAddress, tfProvidersEditPaneZipcode,tfCustomerEmployeesEmailEdit,tfCustomerEmployeesCVREdit,tfCustomerEmployeesCPREdit,tfCustomerEmployeesPhoneEdit,tfCustomerEmployeesNameEdit
            ,tfCustomerCompaniesCVRNREdit,tfCustomerCompaniesNameEdit,tfCustomerCompaniesPhoneEdit,tfCustomerCompaniesEmailEdit,tfCustomerCompaniesAddressEdit,tfCustomerCompaniesZipcodeEdit;
    @FXML
    private TextArea taCoursesAddPaneCourseDescription,taCoursesEditPaneCourseDescription;
    @FXML
    private DatePicker dpEducationStartDate,dpEducationEndDate,dpEducationStartDate2,dpEducationEndDate2;
    @FXML
    private TableView<EducationPlans> tblViewEducationPlans;
    @FXML
    private TableView<CustomerEmployees> tblViewCustomerEmployee;
    @FXML
    private TableView<Courses> tblViewCourses;
    @FXML
    private TableView<CustomerCompanies> tblViewCustomerCompanies;
    @FXML
    private TableView<Provider> tblViewProvider;
    @FXML
    private TableColumn<ObservableList<String>, String> tblColumnEducationFullName, tblColumnEducationCPRNR, tblColumnEducationCompany, tblColumnEducationProvider, tblColumnEducationPriority, tblColumnEducationStartDate, tblColumnEducationEndDate, tblColumnEducationID, tblColumnEducationAMU, tblColumnEducationCourse, tblColumnEducationMail;
    @FXML
    private TableColumn<ObservableList<String>,String> tblColumnCustomerEmployeeName, tblColumnCustomerEmployeePhoneNr, tblColumnCustomerEmployeeMail, tblColumnCustomerEmployeeCPRNR, tblColumnCustomerEmployeeCVRNR
            , tblColumnCourseTitle, tblColumnCompaniesCVRNR, tblColumnCompaniesName, tblColumnCompaniesPhone, tblColumnCompaniesEmail, tblColumnCompaniesAddress,
            tblColumnCompaniesZipcode,tblColumnCourseAMU,tblColumnCourseDescription,tblColumnCourseNRofDays, tblColumnProviderName, tblColumnProviderAddress, tblColumnProviderZipcode;

    ObservableList<EducationPlans> epList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<CustomerEmployees> cusEmpList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Courses> courseList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Provider> providerList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<CustomerCompanies> customerCompaniesList = FXCollections.observableArrayList(new ArrayList<>());

    private int index;

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
        paneEducationPlansEdit.setVisible(false);


    }

    @FXML
    /**
     * method for showing the pane with the tableview and calling the method viewEducationPlans,
     * that puts data in the tableview
     **/
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
    /**
     * method for updating the education plans if something needs to be change
     * the update statements get the info from the different textfields and datepickers
     * */
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
        index = tblViewEducationPlans.getSelectionModel().getFocusedIndex();
        String epID = tblViewEducationPlans.getItems().get(index).getEpID();

        DB.selectSQL("select fld_ID from tbl_Calendar where fld_EducationPlan_ID = '"+epID+"'");
        String calendarID = DB.getData();
        getPendingData();
        DB.deleteSQL("delete from tbl_Customer_specific_courses where fld_calendar_ID = '"+calendarID+"' ");
        DB.deleteSQL("delete from tbl_Calendar where fld_EducationPlan_ID = '"+epID+"'");
        DB.deleteSQL("delete from tbl_Education_Plan where fld_Education_Plan_ID = '"+epID+"'");
        viewEducationPlans();

    }

    @FXML
    /**
     * method for creating a new educationPlan
     * */
    private void createEducationPlan(ActionEvent event)
    {
        String startDate = dpEducationStartDate2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dpEducationEndDate2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String amuNo = tfEducationAmuNR2.getText();
        String cprNr = tfEducationCprNr2.getText();
        String empName = tfEducationCustomerEmployeeName2.getText();
        String priority = tfEducationPriority2.getText();
        String provider = tfEducationProvider2.getText();
        DB.insertSQL("insert into tbl_Education_Plan values('"+priority+"','"+empName+"','"+cprNr+"','"+1+"')"); // TODO make it so it is not hard coded but depends on log in might be a major work
        DB.selectSQL("SELECT TOP 1 fld_Education_Plan_ID FROM tbl_Education_Plan ORDER BY fld_Education_Plan_ID DESC");
        String educationPlanID = DB.getData();
        // this is for avoiding the error message pending data
        getPendingData();
        DB.insertSQL("insert into tbl_Calendar values('"+amuNo+"','"+provider+"','"+startDate+"','"+endDate+"','"+educationPlanID+"')");

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
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewEducationPlans.getItems().clear();
        try {
            ResultSet rs = DB.createProcResultset("execute SA_view_ep '"+1+"' ");
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

            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }

            for (int i = 0; i <row.size()/11 ; i++) {
                EducationPlans ep2 = new EducationPlans(row.get(i+counter), row.get(i+counter+1), row.get(i+counter+2), row.get(i+counter+3), row.get(i+counter+4), row.get(i+counter+5),
                        row.get(i+counter+6), row.get(i+counter+7), row.get(i+counter+8),row.get(i+counter+9),row.get(i+counter+10));
                epList.add(ep2);
                counter+=10;
            }
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
        index = tblViewProvider.getSelectionModel().getFocusedIndex(); // maybe change the name to column index and not just call it index
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

    }
    @FXML
    /**
     * for when you create a new Course
     * */
    private void createNewCourse(ActionEvent event)
    {
        String title =  tfCourseAddPaneCourseTitle.getText();
        String numberOfDays = tfCourseAddPaneNumberOfDays.getText();
        String description = taCoursesAddPaneCourseDescription.getText();
        DB.insertSQL("insert into tbl_Courses values('"+title+"','"+numberOfDays+"','"+description+"')");
        tfCourseAddPaneCourseTitle.setText("");
        tfCourseAddPaneNumberOfDays.setText("");
        taCoursesAddPaneCourseDescription.setText("");

    }
    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewCourses()
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewCourses.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute view_courses");
            tblColumnCourseTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
            tblColumnCourseAMU.setCellValueFactory(new PropertyValueFactory<>("courseAMU"));
            tblColumnCourseNRofDays.setCellValueFactory(new PropertyValueFactory<>("courseNumberOfDays"));
            tblColumnCourseDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));

            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }

            for (int i = 0; i <row.size()/4; i++) {
                Courses courses = new Courses(row.get(i+counter), row.get(i+counter+1),row.get(i+counter+2),row.get(i+counter+3));
                courseList.add(courses);
                counter+=3;
            }
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
    {
        String courseTitle = tfCourseEditPaneCourseTitle.getText();
        String courseNumberOfDays = tfCourseEditPaneNumberOfDays.getText();
        String courseDescription = taCoursesEditPaneCourseDescription.getText();
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
    {
        index = tblViewCourses.getSelectionModel().getFocusedIndex();
        String amu = tblViewCourses.getItems().get(index).getCourseAMU();
        DB.deleteSQL("delete from tbl_Courses where fld_AMU_No = '"+amu+"'");
        viewCourses();

    }
    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewProviders()
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewProvider.getItems().clear();
        try {

            ResultSet rs = DB.createProcResultset("execute view_providers");
            tblColumnProviderName.setCellValueFactory(new PropertyValueFactory<>("providerName"));
            tblColumnProviderAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            tblColumnProviderZipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
            }

            for (int i = 0; i <row.size()/3; i++) {
                Provider provider = new Provider(row.get(i+counter), row.get(i+counter+1),row.get(i+counter+2));
                providerList.add(provider);
                counter+=2;
            }
            tblViewProvider.setItems(providerList);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    /**
     * for updating the provider in database
     * */
    @FXML private void updateProvider (ActionEvent event)
    {
        String providerName = tfProvidersEditPaneName.getText();
        String address = tfProvidersEditPaneAddress.getText();
        String zipcode = tfProvidersEditPaneZipcode.getText();
        index = tblViewProvider.getSelectionModel().getFocusedIndex();
        String providersName = tblViewProvider.getItems().get(index).getProviderName();
        DB.updateSQL("update tbl_Provider set fld_Prov_Name = '"+providerName+"',fld_Prov_Address = '"+address+"',fld_Zipcode = '"+zipcode+"' where fld_Prov_Name = '"+providersName+"'" );
        tfProvidersEditPaneName.setText("");
        tfProvidersEditPaneAddress.setText("");
        tfProvidersEditPaneZipcode.setText("");
    }

    @FXML
    /**
     * for when you create a new provider
     * */
    private void createNewProvider ()
    {
        String providerName = tfProvidersAddPaneName.getText();
        String address = tfProvidersAddPaneAddress.getText();
        String zipcodde = tfProvidersAddPaneZipcode.getText();
        DB.insertSQL("insert into tbl_Provider values('"+providerName+"','"+address+"','"+zipcodde+"')");
        tfProvidersAddPaneName.setText("");
        tfProvidersAddPaneAddress.setText("");
        tfProvidersAddPaneZipcode.setText("");

    }

    @FXML
    /**
     * for when you click delete button
     * */
    private void deleteProvider()
    {
        index = tblViewProvider.getSelectionModel().getFocusedIndex();
        String providerName = tblViewProvider.getItems().get(index).getProviderName();
        DB.deleteSQL("delete from tbl_Provider where fld_Prov_Name = '"+providerName+"'");
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
        String address = tfCustomerCompaniesAddressEdit.getText();
        String cvrNR = tfCustomerCompaniesCVRNREdit.getText();
        String mail = tfCustomerCompaniesEmailEdit.getText();
        String name = tfCustomerCompaniesNameEdit.getText();
        String phone = tfCustomerCompaniesPhoneEdit.getText();
        String zipcode = tfCustomerCompaniesZipcodeEdit.getText();
        index = tblViewCustomerCompanies.getSelectionModel().getFocusedIndex();
        String companyCVR = tblViewCustomerCompanies.getItems().get(index).getCvrNr();
        DB.updateSQL("update tbl_Customer set fld_CVR_NR = '"+cvrNR+"',fld_Name_Cos = '"+name+"',fld_Phone_Nr = '"+phone+"',fld_Cos_Email = '"+mail+"',fld_Cos_Address = '"+address+"',fld_Zipcode = '"+zipcode+"' where fld_CVR_NR='"+companyCVR+"' ");

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


    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewCustomerCompanies()
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewCustomerCompanies.getItems().clear();
        try {
            // cant get it to work with only seeing companies that the employee has done some work for
            ResultSet rs = DB.createProcResultset("execute view_customers_admin");
            tblColumnCompaniesCVRNR.setCellValueFactory(new PropertyValueFactory<>("cvrNr"));
            tblColumnCompaniesName.setCellValueFactory(new PropertyValueFactory<>("customerCompanyName"));
            tblColumnCompaniesPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));
            tblColumnCompaniesEmail.setCellValueFactory(new PropertyValueFactory<>("customerMail"));
            tblColumnCompaniesAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            tblColumnCompaniesZipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));

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
    {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewCustomerEmployee.getItems().clear();
        try {
            // we had to hard code this because of time limit
            ResultSet rs = DB.createProcResultset("execute view_Cus_emps_SA '"+1+"' ");
            tblColumnCustomerEmployeeName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            tblColumnCustomerEmployeePhoneNr.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));
            tblColumnCustomerEmployeeMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            tblColumnCustomerEmployeeCPRNR.setCellValueFactory(new PropertyValueFactory<>("cprNr"));
            tblColumnCustomerEmployeeCVRNR.setCellValueFactory(new PropertyValueFactory<>("companyCVRNR"));

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

    @FXML
    /**
     * for creating a new customerEmployee
     * inserting input to database
     * */
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
    @FXML
    /**
     * for updating a customerEmployee
     *
     * */
    private void updateCustomerEmployees()
    {
        String phoneNr = tfCustomerEmployeesPhoneEdit.getText();
        String name = tfCustomerEmployeesNameEdit.getText();
        String email = tfCustomerEmployeesEmailEdit.getText();
        String cvrNR = tfCustomerEmployeesCVREdit.getText();
        String cprNr = tfCustomerEmployeesCPREdit.getText();
        index = tblViewCustomerEmployee.getSelectionModel().getFocusedIndex();
        String cpr = tblViewCustomerEmployee.getItems().get(index).getCprNr();
        DB.updateSQL("update tbl_Customer_Employee set fld_CPR_NR = '"+cprNr+"', fld_Cos_Emp_Name = '"+name+"',fld_Cos_Emp_Email = '"+email+"',fld_Cos_Emp_Phone_Nr = '"+phoneNr+"', fld_Customer_CVR_NR = '"+cvrNR+"' where fld_CPR_NR = '"+cpr+"' ");

        tfCustomerEmployeesPhoneEdit.setText("");
        tfCustomerEmployeesNameEdit.setText("");
        tfCustomerEmployeesEmailEdit.setText("");
        tfCustomerEmployeesCVREdit.setText("");
        tfCustomerEmployeesCPREdit.setText("");
    }

    // End of the Customer Employee

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
 * for avoiding an error with pending data from a previous select statements
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
