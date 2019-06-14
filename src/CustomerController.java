import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerController {

    @FXML
    private Pane paneEducationPlans,paneCoursesAndProviders, paneCustomerEmployeeEdit,paneCustomerEmployeeAdd
            , panetblViewCourses, panetblViewEducation, panetblViewCustomerEmployee,
            paneEducationPlansEdit,panetblViewProviders,paneCustomerEmployee;
    @FXML
    private TextField tfEducationPriority
            ,tfCustomerEmployeesName,tfCustomerEmployeesPhone,tfCustomerEmployeesCPR,tfCustomerEmployeesCVR,tfCustomerEmployeesEmail,tfCustomerEmployeesEmailEdit,tfCustomerEmployeesCVREdit,tfCustomerEmployeesCPREdit,tfCustomerEmployeesPhoneEdit,tfCustomerEmployeesNameEdit;

    @FXML
    private TableView<EducationPlans> tblViewEducationPlans;
    @FXML
    private TableView<CustomerEmployees> tblViewCustomerEmployee;
    @FXML
    private TableView<Courses> tblViewCourses;
    @FXML
    private TableView<Provider> tblViewProvider;
    @FXML
    private TableColumn<ObservableList<String>, String> tblColumnEducationFullName, tblColumnEducationCPRNR, tblColumnEducationCompany, tblColumnEducationProvider, tblColumnEducationPriority, tblColumnEducationStartDate, tblColumnEducationEndDate, tblColumnEducationID, tblColumnEducationAMU, tblColumnEducationCourse, tblColumnEducationMail;
    @FXML
    private TableColumn<ObservableList<String>,String> tblColumnCustomerEmployeeName, tblColumnCustomerEmployeePhoneNr, tblColumnCustomerEmployeeMail, tblColumnCustomerEmployeeCPRNR, tblColumnCustomerEmployeeCVRNR
            , tblColumnCourseTitle,tblColumnCourseAMU,tblColumnCourseDescription,tblColumnCourseNRofDays, tblColumnProviderName, tblColumnProviderAddress, tblColumnProviderZipcode;

    ObservableList<EducationPlans> epList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<CustomerEmployees> cusEmpList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Courses> courseList = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Provider> providerList = FXCollections.observableArrayList(new ArrayList<>());

    private int index;

    @FXML
    /**
     * method that hides all other panes that the one we want to see
     * */
    private void showBtnEducationPlans(ActionEvent event) throws NullPointerException
    {
        paneEducationPlans.setVisible(true);
        paneCoursesAndProviders.setVisible(false);
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
        paneEducationPlansEdit.setVisible(false);
        viewEducationPlans();
    }

    @FXML
    /**
     * method for showing the pane where you can edit an EducationPlan
     * and hiding the other pane. It also gets info from the tableview
     * and displays it in the text field that are in the pane
     * */
    private void editEducationPlan() {
        panetblViewEducation.setVisible(false);
        paneEducationPlansEdit.setVisible(true);
        index = tblViewEducationPlans.getSelectionModel().getFocusedIndex();
        tfEducationPriority.setText(tblViewEducationPlans.getItems().get(index).getPriority());
    }

    @FXML
    /**
     * method for updating the education plans if something needs to be change
     * the update statements get the info from the different textfields and datepickers
     * */
    private void updateEducationPlan(ActionEvent event)
    {
        String priority = tfEducationPriority.getText();
        String epId = tblViewEducationPlans.getItems().get(index).getEpID();
        DB.updateSQL("update tbl_Education_Plan set fld_Prority = '"+priority+"' where fld_Education_Plan_ID = '"+epId+"' ");
        tfEducationPriority.setText("");
    }


    /**
     * method for creating and inserting data to the tableview
     * */
    private void viewEducationPlans() throws SQLException {
        int counter = 0;
        ObservableList<String> row = FXCollections.observableArrayList();
        tblViewEducationPlans.getItems().clear();
        try {
            ResultSet rs = DB.createProcResultset("execute cus_view_eps_inner '"+12345+"' ");
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
        panetblViewProviders.setVisible(false);
        viewCourses();

    }

    @FXML
    /**
     * shows the pane where you can see all the providers
     * and loads the data into the tableview
     * */
    private void showViewProviders(ActionEvent event)
    {
        panetblViewCourses.setVisible(false);
        panetblViewProviders.setVisible(true);
        viewProviders();
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

    @FXML
    /**
     * shows the pane where you can see CustomerEmployee pane
     * */
    private void showCustomerEmployeePane(ActionEvent event)
    {
        paneCustomerEmployee.setVisible(true);
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
        index = tblViewCustomerEmployee.getSelectionModel().getFocusedIndex();
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
            ResultSet rs = DB.createProcResultset("execute view_Cus_emps_Customer2 '"+12345+"' ");
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
        System.out.println(employeeName);
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


    // End of Courses and providers

}
