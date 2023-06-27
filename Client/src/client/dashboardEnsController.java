package client;

import Remote.EnseignantFacadeRemote;
import Facade.ModuleCours;
import Remote.EtudiantFacadeRemote;
import Remote.NoteFacadeRemote;
import Remote.UserFacadeRemote;
import entities.Enseignant;
import entities.Etudiant;
import entities.Module;
import entities.Note;
import entities.Response;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import rmiserveur.CoordinateurInt;

/**
 *
 * SUBSCRIBE OUR YOUTUBE CHANNEL ->
 * https://www.youtube.com/channel/UCPgcmw0LXToDn49akUEJBkQ THANKS FOR YOUR
 * SUPPORT : )
 */
public class dashboardEnsController implements Initializable {

    static String usernam;

    static void setUsernam(String text) {

        usernam = text;
        System.out.println(text);
    }

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
    private Button importButton;

    @FXML
    private TableView<ModuleCours> fileTableView;

    @FXML
    private TableColumn<ModuleCours, String> fileColumn1;

    @FXML
    private TableColumn<ModuleCours, String> fileColumn2;

    @FXML
    private TableColumn<ModuleCours, String> fileColumn3;

    @FXML
    private TableColumn<ModuleCours, String> fileColumn4;

    @FXML
    private TableColumn<ModuleCours, String> fileColumn5;

    @FXML
    private void handleImportButtonClick(ActionEvent event) throws NamingException {

        // Code pour gérer l'événement de clic sur le bouton d'importation du fichier
        // Afficher le dialogue de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier");
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            // Appeler la méthode saveFile avec le chemin d'accès du fichier
            try {
                byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
                String moduleName = module;
                System.out.println(moduleName);
                String section = addStudents_year.getSelectionModel().getSelectedItem().toString();
                String fileName = selectedFile.getName();
                Properties properties = new Properties();
                properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
                properties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
                // Replace "hostname" and "port" with the actual values
                // Create the initial context
                Context context = new InitialContext(properties);

                // Lookup the remote EJB
                EnseignantFacadeRemote enseignantFacade = (EnseignantFacadeRemote) context.lookup("ejb:/SR/Enseignant!Remote.EnseignantFacadeRemote");

                // Create the initial context
                enseignantFacade.saveFile(fileContent, moduleName, section, fileName);
                // Lookup the remote EJB

            } catch (IOException e) {
                System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            }
        }
    }

    public String module, type, nom;

    private List<FileItem> fileList;

    public void file() throws NamingException, NotBoundException, MalformedURLException, RemoteException {

        CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
        System.out.println(usernam);
        List<Module> moduleList = stub.Modules(usernam);
        String[] module = new String[moduleList.size()];
        System.out.println(moduleList.size());
        int i = 0;
        for (Module modul : moduleList) {
            module[i] = modul.getLibelleDuModule();
            i++;
            System.out.println("HHHH" + modul.getLibelleDuModule());
        }
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        // Replace "hostname" and "port" with the actual values
        // Create the initial context
        Context context = new InitialContext(properties);

        // Lookup the remote EJB
        EtudiantFacadeRemote etudFacade = (EtudiantFacadeRemote) context.lookup("ejb:/SR/Etudiant!Remote.EtudiantFacadeRemote");

        // Create the initial context
        List<ModuleCours> modulesCours = etudFacade.getAllCoursPDF(module);
        fileColumn1.setCellValueFactory(data -> data.getValue().getCoursFiles().isEmpty() ? null
                : new SimpleStringProperty(data.getValue().getCoursFiles().get(0).getName()));
        fileColumn2.setCellValueFactory(data -> data.getValue().getTDFiles().isEmpty() ? null
                : new SimpleStringProperty(data.getValue().getTDFiles().get(0).getName()));
        fileColumn3.setCellValueFactory(data -> data.getValue().getTPFiles().isEmpty() ? null
                : new SimpleStringProperty(data.getValue().getTPFiles().get(0).getName()));
        fileColumn4.setCellValueFactory(data -> data.getValue().getExamFiles().isEmpty() ? null
                : new SimpleStringProperty(data.getValue().getExamFiles().get(0).getName()));
        fileColumn5.setCellValueFactory(data
                -> new SimpleStringProperty(data.getValue().getModuleName()));
        // Set the items in the TableView

        // Set the items in the TableView
        fileTableView.getItems().addAll(modulesCours);
        // Set the items in the TableView

    }

    @FXML
    private void handleTextFieldAction(ActionEvent event) {
        TextField textField = (TextField) event.getSource();
        String enteredText = textField.getText();
        System.out.println("Action effectuée avec le texte : " + enteredText);
        nom = enteredText;
        System.out.println(nom);
        // Appeler la méthode ou exécuter le code souhaité ici
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

    public void addStudentsCourseList() {

        try {

            ObservableList listC = FXCollections.observableArrayList();

            CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
            System.out.println(usernam);
            List<Module> moduleList = stub.Modules(usernam);
            System.out.println(moduleList.size());
            for (Module module : moduleList) {
                listC.add(module.getLibelleDuModule());
                System.out.println("HHHH" + module.getLibelleDuModule());
            }
            addStudents_course.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }
        addStudents_course.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("ID sélectionné : " + newValue);
                module = (String) newValue;
                System.out.println(module);
                //System.out.println("hh:"+profs.getSelectionModel().selectedItemProperty());
            }
        });
    }

//    NOW WE NEED THE COURSE, SO LETS WORK NOW THE AVAILABLE COURSE FORM : ) 
//    LETS WORK FIRST THE ADD STUDENTS FORM : ) 
    public void availableCourseAdd() throws NamingException, NotBoundException, MalformedURLException, RemoteException {
        Alert alert;

        if (addStudents_course.getSelectionModel().getSelectedItem() == null
                || addStudents_year.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {

            nom = availableCourse_course.getText();
            module = (String) addStudents_course.getSelectionModel().getSelectedItem();
            type = (String) addStudents_year.getSelectionModel().getSelectedItem();
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();

            // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
            availableCourseShowListData();
            // TO CLEAR THE TEXT FIELDS
            availableCourseClear();
            file();
        }
    }

    public void availableCourseClear() {
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_year.getSelectionModel().clearSelection();
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
        //  availableCourseList = availableCourseListData();
        // availableCourse_col_id.setCellValueFactory(new PropertyValueFactory<>("fileColumn1"));
        // availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("fileColumn2"));
        // availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("fileColumn3"));
        // availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("fileColumn4"));

//        availableCourse_tableView.setItems(availableCourseList);
    }
    private String[] yearList = {"Cours", "TD", "TP", "Examen"};

    public void addStudentsYearList() {

        List<String> yearL = new ArrayList<>();

        for (String data : yearList) {
            yearL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(ObList);
        addStudents_year.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("ID sélectionné : " + newValue);
                type = (String) newValue;
                System.out.println(type);
                //System.out.println("hh:"+profs.getSelectionModel().selectedItemProperty());
            }
        });
    }

    String idcourse;

    public void availableCourseSelect() {
        courseData courseD = availableCourse_tableView.getSelectionModel().getSelectedItem();
        int num = availableCourse_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableCourse_course.setText(courseD.getCourse());

        idcourse = courseD.getId();
        System.out.println("hhh" + idcourse);
    }

    public void studentGradesUpdate() {

        try {

            Alert alert;

            if (studentGrade_studentNum.getText().isEmpty()
                    || studentGrade_year.getText().isEmpty()) {
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
                    String studentGradeText = studentGrade_firstSem.getText();
                    double studentGrade = Double.parseDouble(studentGradeText);
                    Note a = new Note(studentGrade, Integer.parseInt(usernam), Integer.parseInt(studentGrade_studentNum.getText()), Integer.parseInt(studentGrade_year.getText()));
                    CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
                    stub.addNote(a);

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

        //  String sql = "SELECT * FROM student_grade";
        //  connect = database.connectDb();
        try {
            studentData studentD;

            CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
            System.out.println(usernam);
            List<Response> moduleList = stub.allResponse(usernam);
            int i = 0;
            for (Response etud : moduleList) {
                
                studentD = new studentData(Integer.parseInt(etud.getIdetud()), String.valueOf(etud.getSem()), etud.getNom(), etud.getPrenom(), etud.getIdmod(), stub.getNote(Integer.parseInt(etud.getIdetud())));
                listData.add(studentD);
                System.out.print(Integer.parseInt(etud.getIdetud()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        studentGrade_year.setText(studentD.getSecondSem());
        studentGrade_firstSem.setText(String.valueOf(studentD.getFinals()));

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

    public void switchForm(ActionEvent event) throws NotBoundException, MalformedURLException, RemoteException, NamingException {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");

            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            homeDisplayTotalEnrolledStudents();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();

        } else if (event.getSource() == availableCourse_btn) {
            home_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);
            addStudentsCourseList();
            availableCourse_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");

            home_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");
            file();
            //  availableCourseShowListData();

        } else if (event.getSource() == studentGrade_btn) {
            home_form.setVisible(false);

            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);

            studentGrade_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");

            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            studentGradesShowListData();
            studentGradesSearch();

        }
    }

    public static class FileItem {

        private String file1;
        private String file2;
        private String file3;
        private String Module;

        public FileItem(String file1, String file2, String file3, String Module) {
            this.file1 = file1;
            this.file2 = file2;
            this.file3 = file3;
            this.Module = Module;
        }

        private FileItem() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public void setFile1(String file1) {
            this.file1 = file1;
        }

        public void setFile2(String file2) {
            this.file2 = file2;
        }

        public void setFile3(String file3) {
            this.file3 = file3;
        }

        public String getModule() {
            return Module;
        }

        public void setModule(String Module) {
            this.Module = Module;
        }

        public String getFile1() {
            return file1;
        }

        public String getFile2() {
            return file2;
        }

        public String getFile3() {
            return file3;
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
        //addStudentsGenderList();
        //addStudentsStatusList();
        addStudentsYearList();
        availableCourseShowListData();
        try {
            file();
        } catch (NamingException ex) {
            Logger.getLogger(dashboardEnsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(dashboardEnsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(dashboardEnsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(dashboardEnsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // studentGradesShowListData();
        addStudentsCourseList();
    }

}
