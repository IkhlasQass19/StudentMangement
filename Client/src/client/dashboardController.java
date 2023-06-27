package client;

import entities.Enseignant;
import entities.Etudiant;
import entities.Module;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rmiserveur.CoordinateurInt;

/**
 *
 * SUBSCRIBE OUR YOUTUBE CHANNEL ->
 * https://www.youtube.com/channel/UCPgcmw0LXToDn49akUEJBkQ THANKS FOR YOUR
 * SUPPORT : )
 */
public class dashboardController implements Initializable {

 
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button addStudents_btn;
    @FXML
    private Button addprof_btn;
    @FXML
    private TextField pdfFile;

    @FXML
    private Button availableCourse_btn;

    @FXML
    private Button studentGrade_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private Label home_totalFemale;

    @FXML
    private Label home_totalMale;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private TextField addStudents_search;
    @FXML
    private TextField addProfs_search;

    @FXML
    private TableView<studentData> addStudents_tableView;

    @FXML
    private TableView<studentData> addprof_tableView;

    @FXML
    private TableColumn<studentData, String> addStudents_col_studentNum;
    @FXML
    private TableColumn<studentData, String> addProfs_col_studentNum;

    @FXML
    private TableColumn<studentData, String> addStudents_col_year;

    @FXML
    private TableColumn<studentData, String> addStudents_col_course;
    @FXML
    private TableColumn<studentData, String> addProfs_col_course;

    @FXML
    private TableColumn<studentData, String> addStudents_col_firstName;
    @FXML
    private TableColumn<studentData, String> addProfs_col_firstName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_lastName;
    @FXML
    private TableColumn<studentData, String> addProfs_col_lastName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_gender;
    @FXML
    private TableColumn<studentData, String> addProfs_col_gender;

    @FXML
    private TableColumn<studentData, String> addStudents_col_birth;
    @FXML
    private TableColumn<studentData, String> addProfs_col_birth;

    @FXML
    private TableColumn<studentData, String> addStudents_col_status;
    @FXML
    private TableColumn<studentData, String> addProfs_col_status;

    @FXML
    private TextField addStudents_studentNum;
    @FXML
    private TextField addProfs_studentNum;

    @FXML
    private ComboBox<?> addStudents_year;
    @FXML
    private ComboBox<?> addStudents_year2;
    @FXML
    private ComboBox<Professor> profs;
    @FXML
    private ComboBox<?> addStudents_course;

    @FXML
    private TextField addStudents_firstName;
    @FXML
    private TextField addProfs_firstName;

    @FXML
    private TextField addStudents_lastName;
    @FXML
    private TextField addProfs_lastName;
    @FXML
    private TextField addStudents_cin;
    @FXML
    private TextField email;
    @FXML
    private DatePicker addStudents_birth;
    @FXML
    private TextField telphone;
    @FXML
    private DatePicker addProfs_birth;

    @FXML
    private TextField addStudents_status;

    @FXML
    private TextField addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private AnchorPane addProf_form;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private TextField availableCourse_degree;

    @FXML
    private Button availableCourse_addBtn;

    @FXML
    private Button availableCourse_updateBtn;

    @FXML
    private Button availableCourse_clearBtn;

    @FXML
    private Button availableCourse_deleteBtn;

    @FXML
    private TableView<courseData> availableCourse_tableView;
    @FXML
    private TableColumn<courseData, String> availableCourse_col_id;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_course;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_description;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_degree;

    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private TextField studentGrade_studentNum;

    @FXML
    private Label studentGrade_year;

    @FXML
    private Label studentGrade_course;

    @FXML
    private TextField studentGrade_firstSem;

    @FXML
    private TextField studentGrade_secondSem;

    @FXML
    private Button studentGrade_updateBtn;

    @FXML
    private Button studentGrade_clearBtn;

    @FXML
    private TableView<studentData> studentGrade_tableView;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_studentNum;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_year;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_course;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_firstSem;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_secondSem;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_final;

    @FXML
    private TextField studentGrade_search;
    @FXML
    private TextField pdf1TextField;

    @FXML
    private TextField pdf2TextField;

    @FXML
    private TextField pdf3TextField;

    @FXML
    private void choosePDF1() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            pdf1TextField.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void choosePDF2() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            pdf2TextField.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void choosePDF3() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            pdf3TextField.setText(selectedFile.getAbsolutePath());
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    public void homeDisplayTotalEnrolledStudents() {
//nbr prof

        home_totalEnrolled.setText(String.valueOf("14"));

    }

    public void homeDisplayFemaleEnrolled() {

//nbr of student
        home_totalFemale.setText(String.valueOf("30"));

    }

    public void homeDisplayMaleEnrolled() {

        //nbr of modele
        home_totalMale.setText(String.valueOf("6"));

    }

//   public static Coordinateur coordinateur;
    public void addStudentsAdd() {

        // CoordinateurInt rmiProxy = RmiProxy.getRmiProxy();
        try {
            Alert alert;

            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getText().isEmpty()
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                //     prepare = connect.prepareStatement(insertData);
                LocalDate currentDate = LocalDate.now();
                Period age = Period.between(addStudents_birth.getValue(), currentDate);

                int years = age.getYears();
                LocalDate selectedDate = addStudents_birth.getValue();

// Convert the selected date to a java.util.Date object
                Date date = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                Etudiant etudiant = new Etudiant();
                etudiant.setAge(years);
                etudiant.setCin(addStudents_studentNum.getText());
                etudiant.setDateNaissance(sqlDate);
                etudiant.setEmail(addStudents_status.getText());
                etudiant.setId(null);
                etudiant.setNom(addStudents_lastName.getText());
                etudiant.setPrenom(addStudents_firstName.getText());
                etudiant.setRole("3");
                String selectedYear = addStudents_year.getSelectionModel().getSelectedItem().toString();
                int year = Integer.parseInt(selectedYear);
                etudiant.setSem(year);
                etudiant.setTelephone(addStudents_gender.getText());
                CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                stub.addEtudiant(etudiant);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                // TO UPDATE THE TABLEVIEW
                addStudentsShowListData();
                // TO CLEAR THE FIELDS
                addStudentsClear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getText().isEmpty()
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + addStudents_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    LocalDate currentDate = LocalDate.now();
                    Period age = Period.between(addStudents_birth.getValue(), currentDate);

                    int years = age.getYears();
                    LocalDate selectedDate = addStudents_birth.getValue();

// Convert the selected date to a java.util.Date object
                    Date date = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    Etudiant etudiant = new Etudiant();
                    etudiant.setAge(years);
                    etudiant.setCin(addStudents_studentNum.getText());
                    etudiant.setDateNaissance(sqlDate);
                    etudiant.setEmail(addStudents_status.getText());
                    etudiant.setId(null);
                    etudiant.setNom(addStudents_lastName.getText());
                    etudiant.setPrenom(addStudents_firstName.getText());
                    etudiant.setRole("3");
                    String selectedYear = addStudents_year.getSelectionModel().getSelectedItem().toString();
                    int year = Integer.parseInt(selectedYear);
                    etudiant.setSem(year);
                    etudiant.setTelephone(addStudents_gender.getText());
                    CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                    stub.UpdateEtudiant(etudiant);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsDelete() {

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getText().isEmpty()
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #" + addStudents_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                    stub.deleteEtudiantByCin(addStudents_studentNum.getText());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();

                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addProfsAdd() {

        // CoordinateurInt rmiProxy = RmiProxy.getRmiProxy();
        try {
            Alert alert;

            if (addProfs_studentNum.getText().isEmpty()
                    || addProfs_firstName.getText().isEmpty()
                    || addProfs_lastName.getText().isEmpty()
                    || telphone.getText().isEmpty()
                    || addProfs_birth.getValue() == null
                    || email.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                //     prepare = connect.prepareStatement(insertData);
                LocalDate currentDate = LocalDate.now();
                Period age = Period.between(addProfs_birth.getValue(), currentDate);
                int years = age.getYears();
                LocalDate selectedDate = addProfs_birth.getValue();

// Convert the selected date to a java.util.Date object
                Date date = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                Enseignant etudiant = new Enseignant();
                etudiant.setAge(years);
                etudiant.setCin(addProfs_studentNum.getText());
                etudiant.setDateNaissance(sqlDate);
                etudiant.setEmail(email.getText());
                etudiant.setId(null);
                etudiant.setNom(addProfs_lastName.getText());
                etudiant.setPrenom(addProfs_firstName.getText());
                etudiant.setRole("2");
                etudiant.setTelephone(telphone.getText());
                CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                stub.addEnseignant(etudiant);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                // TO UPDATE THE TABLEVIEW
                addProfsShowListData();
                // TO CLEAR THE FIELDS
                addProfsClear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProfsUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        try {
            Alert alert;
            if (addProfs_studentNum.getText().isEmpty()
                    || addProfs_firstName.getText().isEmpty()
                    || addProfs_lastName.getText().isEmpty()
                    || telphone.getText().isEmpty()
                    || addProfs_birth.getValue() == null
                    || email.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Proffesseur :" + addProfs_firstName.getText() + " " + addProfs_lastName.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    LocalDate currentDate = LocalDate.now();
                    Period age = Period.between(addProfs_birth.getValue(), currentDate);
                    int years = age.getYears();
                    LocalDate selectedDate = addProfs_birth.getValue();
// Convert the selected date to a java.util.Date object
                    Date date = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    Enseignant etudiant = new Enseignant();
                    etudiant.setAge(years);
                    etudiant.setCin(addProfs_studentNum.getText());
                    etudiant.setDateNaissance(sqlDate);
                    etudiant.setEmail(email.getText());
                    etudiant.setId(null);
                    etudiant.setNom(addProfs_lastName.getText());
                    etudiant.setPrenom(addProfs_firstName.getText());
                    etudiant.setRole("2");
                    etudiant.setTelephone(telphone.getText());
                    CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                    stub.UpdateEnseignant(etudiant);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addProfsShowListData();
                    // TO CLEAR THE FIELDS
                    addProfsClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProfsDelete() {

        try {
            Alert alert;
            if (addProfs_studentNum.getText().isEmpty()
                    || addProfs_firstName.getText().isEmpty()
                    || addProfs_lastName.getText().isEmpty()
                    || telphone.getText().isEmpty()
                    || addProfs_birth.getValue() == null
                    || email.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #" + addStudents_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                    stub.deleteEnseignantByCin(addProfs_studentNum.getText());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();

                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addStudentsClear() {
        addStudents_studentNum.setText("");
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.setText("");
        addStudents_birth.setValue(null);
        addStudents_status.setText("");
    }

    public void addProfsClear() {
        addProfs_studentNum.setText("");
        addProfs_firstName.setText("");
        addProfs_lastName.setText("");
        telphone.setText("");
        addProfs_birth.setValue(null);
        email.setText("");
    }

    public void addStudentsInsertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            image = new Image(file.toURI().toString(), 120, 149, false, true);
            addStudents_imageView.setImage(image);

            getData.path = file.getAbsolutePath();

        }
    } //WHILE WE INSERT THE DATA ON STUDENT, WE SHOULD INSERT ALSO THE DATA TO STUDENT_GRADE

    public void addStudentsSearch() {

        FilteredList<studentData> filter = new FilteredList<>(addStudentsListD, e -> true);

        addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);

    }

    public void addProfsSearch() {

        FilteredList<studentData> filter = new FilteredList<>(addProfsListD, e -> true);

        addProfs_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addprof_tableView.comparatorProperty());
        addprof_tableView.setItems(sortList);

    }

    private String[] yearList = {"1", "2", "3", "4"};

    public void addStudentsYearList() {

        List<String> yearL = new ArrayList<>();

        for (String data : yearList) {
            yearL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(ObList);

    }
    private String[] yearList2= {"1", "2", "3", "4"};
    public void addStudentsYearList2() {

        List<String> yearL2 = new ArrayList<>();

        for (String data : yearList2) {
            yearL2.add(data);
        }

        ObservableList ObList2 = FXCollections.observableArrayList( yearL2);
        addStudents_year2.setItems(ObList2);

    }

    private ObservableList<Professor> createProfessorList(String[][] profData) {
        ObservableList<Professor> professors = FXCollections.observableArrayList();

        for (String[] row : profData) {
            String id = row[0];
            String firstName = row[1];
            String lastName = row[2];
            Professor professor = new Professor(id, firstName, lastName);
            professors.add(professor);
        }
        return professors;
    }
    String cin2;

    public void addProfsYearList() throws NotBoundException, MalformedURLException, RemoteException {
        CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
        String[][] profData = new String[stub.getProfs().length][3];
        profData = stub.getProfs();
        ObservableList<Professor> professorList = createProfessorList(profData);
        profs.setItems(professorList);

        // Ajout d'un écouteur de sélection pour afficher l'ID du professeur sélectionné
        profs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("ID sélectionné : " + newValue.getId());
                cin2 = newValue.getId();
                //System.out.println("hh:"+profs.getSelectionModel().selectedItemProperty());
            }
        });

    }

    public void addStudentsCourseList() {
/*
        String listCourse = "SELECT * FROM course";

        //   connect = database.connectDb();
        try {

            ObservableList listC = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("course"));
            }
            addStudents_course.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

//    NOW WE NEED THE COURSE, SO LETS WORK NOW THE AVAILABLE COURSE FORM : ) 
//    LETS WORK FIRST THE ADD STUDENTS FORM : ) 
    public ObservableList<studentData> addStudentsListData() {

        ObservableList<studentData> listStudents = FXCollections.observableArrayList();

        //   String sql = "SELECT * FROM student";
        //     connect = database.connectDb();
        try {
            studentData studentD;
            CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
            List<Etudiant> etudiantList = stub.allEtudiants();
            int i = 1;
            for (Etudiant etudiant : etudiantList) {
                studentD = new studentData(i,
                        String.valueOf(etudiant.getSem()),
                        etudiant.getCin(),
                        etudiant.getPrenom(),
                        etudiant.getNom(),
                        etudiant.getEmail(),
                        (java.sql.Date) (Date) etudiant.getDateNaissance(),
                        etudiant.getTelephone(),
                        "null");
                i++;

                listStudents.add(studentD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    public ObservableList<studentData> addProfListData() {

        ObservableList<studentData> listStudents = FXCollections.observableArrayList();

        //   String sql = "SELECT * FROM student";
        //     connect = database.connectDb();
        try {
            studentData studentD;
            CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
            List<Enseignant> etudiantList = stub.allEnseignants();
            int i = 1;
            for (Enseignant etudiant : etudiantList) {
                studentD = new studentData(i,
                        null,
                        etudiant.getCin(),
                        etudiant.getPrenom(),
                        etudiant.getNom(),
                        etudiant.getEmail(),
                        (java.sql.Date) (Date) etudiant.getDateNaissance(),
                        etudiant.getTelephone(),
                        "null");
                i++;

                listStudents.add(studentD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    private ObservableList<studentData> addStudentsListD;
    private ObservableList<studentData> addProfsListD;

    public void addStudentsShowListData() {
        addStudentsListD = addStudentsListData();

        addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudents_tableView.setItems(addStudentsListD);

    }

    public void addProfsShowListData() {
        addProfsListD = addProfListData();

        addProfs_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        //addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addProfs_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addProfs_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addProfs_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addProfs_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addProfs_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addProfs_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addprof_tableView.setItems(addProfsListD);

    }

    public void addStudentsSelect() {

        studentData studentD = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addStudents_studentNum.setText(String.valueOf(studentD.getCourse()));

        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        addStudents_birth.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));
        addStudents_status.setText(studentD.getStatus());
        addStudents_gender.setText(studentD.getGender());
        String uri = "file:" + studentD.getImage();

        image = new Image(uri, 120, 149, false, true);
        addStudents_imageView.setImage(image);

        getData.path = studentD.getImage();

    }

    public void addProfsSelect() {

        studentData studentD = addprof_tableView.getSelectionModel().getSelectedItem();
        int num = addprof_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addProfs_studentNum.setText(String.valueOf(studentD.getCourse()));
        addProfs_firstName.setText(studentD.getFirstName());
        addProfs_lastName.setText(studentD.getLastName());
        addProfs_birth.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));
        telphone.setText(studentD.getStatus());
        email.setText(studentD.getGender());
        String uri = "file:" + studentD.getImage();

        image = new Image(uri, 120, 149, false, true);
        addStudents_imageView.setImage(image);

        getData.path = studentD.getImage();

    }

    public void availableCourseAdd() {

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || profs.getSelectionModel().getSelectedItem() == null
                    || addStudents_year2.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String selectedYear = addStudents_year2.getSelectionModel().getSelectedItem().toString();
                String cin = profs.getSelectionModel().getSelectedItem().toString();
                System.out.println(cin);
                CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                stub.insertModule(selectedYear, cin, cin2, availableCourse_course.getText());
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                availableCourseShowListData();
                // TO CLEAR THE TEXT FIELDS
                availableCourseClear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableCourseUpdate() {

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || profs.getSelectionModel().getSelectedItem() == null
                    || addStudents_year2.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String selectedYear = addStudents_year2.getSelectionModel().getSelectedItem().toString();
                String cin = profs.getSelectionModel().getSelectedItem().toString();
                System.out.println(cin);
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                    //stub.UpdateModule(selectedYear, cin, cin2, availableCourse_course.getText());
                    stub.UpdateModule(selectedYear, cin, cin2, availableCourse_course.getText(), Integer.parseInt(idcourse));
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                    availableCourseShowListData();
                    // TO CLEAR THE TEXT FIELDS
                    availableCourseClear();

                } else {
                    return;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableCourseDelete() {

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
//                ALL GOOD GUYS! NOW LETS PROCEED TO ADD STUDENTS FORM
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                    //stub.UpdateModule(selectedYear, cin, cin2, availableCourse_course.getText());
                    stub.deleteModule(Integer.parseInt(idcourse));
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                    availableCourseShowListData();
                    // TO CLEAR THE TEXT FIELDS
                    availableCourseClear();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableCourseClear() {
        availableCourse_course.setText("");
        addStudents_year2.getSelectionModel().clearSelection();
        profs.getSelectionModel().clearSelection();
    }

    public ObservableList<courseData> availableCourseListData() {

        ObservableList<courseData> listData = FXCollections.observableArrayList();

        try {
            courseData courseD;
            CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
            List<Module> moduleList = stub.allModules();
            for (Module module : moduleList) {
                courseD = new courseData(String.valueOf(module.getId()), module.getLibelleDuModule(), module.getIntervenant(), module.getEtablissement());

                listData.add(courseD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<courseData> availableCourseList;

    public void availableCourseShowListData() {
        availableCourseList = availableCourseListData();
        availableCourse_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));

        availableCourse_tableView.setItems(availableCourseList);

    }
    String idcourse;

    public void availableCourseSelect() {
        courseData courseD = availableCourse_tableView.getSelectionModel().getSelectedItem();
        int num = availableCourse_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableCourse_course.setText(courseD.getCourse());
        // availableCourse_description.setText(courseD.getDescription());
        // availableCourse_degree.setText(courseD.getDegree());
        idcourse = courseD.getId();
        System.out.println("hhh" + idcourse);
    }

    public void studentGradesUpdate() {
        double finalCheck1 = 0;
        double finalCheck2 = 0;

        String checkData = "SELECT * FROM student_grade WHERE studentNum = '"
                + studentGrade_studentNum.getText() + "'";

        //  connect = database.connectDb();
        double finalResult = 0;

        try {

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            if (result.next()) {
                finalCheck1 = result.getDouble("first_sem");
                finalCheck2 = result.getDouble("second_sem");
            }

            if (finalCheck1 == 0 || finalCheck2 == 0) {
                finalResult = 0;
            } else { //LIKE (X+Y)/2 AVE WE NEED TO FIND FOR FINALS
                finalResult = (Double.parseDouble(studentGrade_firstSem.getText())
                        + Double.parseDouble(studentGrade_secondSem.getText()) / 2);
            }

            String updateData = "UPDATE student_grade SET "
                    + " year = '" + studentGrade_year.getText()
                    + "', course = '" + studentGrade_course.getText()
                    + "', first_sem = '" + studentGrade_firstSem.getText()
                    + "', second_sem = '" + studentGrade_secondSem.getText()
                    + "', final = '" + finalResult + "' WHERE studentNum = '"
                    + studentGrade_studentNum.getText() + "'";

            Alert alert;

            if (studentGrade_studentNum.getText().isEmpty()
                    || studentGrade_year.getText().isEmpty()
                    || studentGrade_course.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + studentGrade_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    studentGradesShowListData();
                } else {
                    return;
                }

            }// NOT WE ARE CLOSER TO THE ENDING PART  :) LETS PROCEED TO DASHBOARD FORM 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void studentGradesClear() {
        studentGrade_studentNum.setText("");
        studentGrade_year.setText("");
        studentGrade_course.setText("");
        studentGrade_firstSem.setText("");
        studentGrade_secondSem.setText("");
    }

    public ObservableList<studentData> studentGradesListData() {

        ObservableList<studentData> listData = FXCollections.observableArrayList();
/*
        String sql = "SELECT * FROM student_grade";

        //  connect = database.connectDb();
        try {
            studentData studentD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(result.getInt("studentNum"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getDouble("first_sem"),
                       // result.getDouble("second_sem"),
                        result.getDouble("final"));

                listData.add(studentD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return listData;
    }

    private ObservableList<studentData> studentGradesList;

    public void studentGradesShowListData() {
        studentGradesList = studentGradesListData();

        studentGrade_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        studentGrade_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        studentGrade_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentGrade_col_firstSem.setCellValueFactory(new PropertyValueFactory<>("firstSem"));
        studentGrade_col_secondSem.setCellValueFactory(new PropertyValueFactory<>("secondSem"));
        studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));
//        WE NEED TO FIX THE DELETE ON ADD STUDENT FORM 
        studentGrade_tableView.setItems(studentGradesList);

    }

    public void studentGradesSelect() {

        studentData studentD = studentGrade_tableView.getSelectionModel().getSelectedItem();
        int num = studentGrade_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        studentGrade_studentNum.setText(String.valueOf(studentD.getStudentNum()));
        studentGrade_year.setText(studentD.getYear());
        studentGrade_course.setText(studentD.getCourse());
        studentGrade_firstSem.setText(String.valueOf(studentD.getFirstSem()));
        studentGrade_secondSem.setText(String.valueOf(studentD.getSecondSem()));
    }

    public void studentGradesSearch() {

        FilteredList<studentData> filter = new FilteredList<>(studentGradesList, e -> true);

        studentGrade_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstSem().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getSecondSem().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFinals().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
        studentGrade_tableView.setItems(sortList);

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM 
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayUsername() {
        username.setText(getData.username);
    }

    // THATS IT FOR THESE VIDEOS, THANKS FOR WATCHING!! SUBSCRIBE AND TURN ON NOTIFICATION 
//    TO NOTIF YOU FOR MORE UPCOMING VIDEOS THANKS FOR THE SUPPORT! : )
    public void defaultNav() {
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    }

    public void switchForm(ActionEvent event) throws NotBoundException, MalformedURLException, RemoteException {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            addProf_form.setVisible(false);
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            addprof_btn.setStyle("-fx-background-color:transparent");
            homeDisplayTotalEnrolledStudents();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();

        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            addProf_form.setVisible(false);
            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            addprof_btn.setStyle("-fx-background-color:transparent");
//            TO BECOME UPDATED ONCE YOU CLICK THE ADD STUDENTS BUTTON ON NAV
            addStudentsShowListData();
            addStudentsYearList();
            addStudentsCourseList();
            addStudentsSearch();

        } else if (event.getSource() == availableCourse_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);
            addProf_form.setVisible(false);

            availableCourse_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            addprof_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            addProfsYearList();
            availableCourseShowListData();
            addStudentsYearList2();

        } else if (event.getSource() == studentGrade_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);
            addprof_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
addStudentsYearList2();
            studentGradesShowListData();
            studentGradesSearch();

        } else if (event.getSource() == addprof_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            addProf_form.setVisible(true);
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            addprof_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            addProfsShowListData();
            addStudentsYearList();
            //addStudentsCourseList();
            addProfsSearch();

        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    // SORRY ABOUT THAT, I JUST NAMED THE DIFFERENT COMPONENTS WITH THE SAME NAME 
    // MAKE SURE THAT THE NAME YOU GAVE TO THEM ARE DIFFERENT TO THE OTHER OKAY?
    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        defaultNav();

        homeDisplayTotalEnrolledStudents();
        homeDisplayMaleEnrolled();
        homeDisplayFemaleEnrolled();

        // TO SHOW IMMIDIATELY WHEN WE PROCEED TO DASHBOARD APPLICATION FORM
        addStudentsShowListData();
        addStudentsYearList();
        try {
            addProfsYearList();
        } catch (NotBoundException ex) {
            Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //addStudentsGenderList();
        //addStudentsStatusList();
        addStudentsCourseList();

        availableCourseShowListData();

        studentGradesShowListData();

        addStudentsYearList2();
    }

    public static class Professor {

        private String id;
        private String firstName;
        private String lastName;

        public Professor(String id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

}
