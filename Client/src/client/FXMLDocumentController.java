/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import Remote.UserFacadeRemote;
import entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author WINDOWS 10
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;

//    DATABASE TOOls
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

//    NOW LETS CREATE OUR DATABASE : ) 
    private double x = 0;
    private double y = 0;

    public void loginAdmin() {

        try { // IT WORKS GOOD : ) NOW LETS DESIGN THE DASHBOARD FORM : ) 
            Alert alert;
            // Configure JNDI properties
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
            // Replace "hostname" and "port" with the actual values

            // Create the initial context
            Context context = new InitialContext(properties);

            // Lookup the remote EJB
            UserFacadeRemote userFacade = (UserFacadeRemote) context.lookup("ejb:/SR/User!Remote.UserFacadeRemote");

//            CHECK IF FIELDS ARE EMPTTY
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                User user = userFacade.signIn(username.getText(), password.getText());
                if (user != null) {
//                    THEN PROCEED TO DASHBOARD FORM

                    //getData.username = username.getText();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

//                    TO HIDE THE LOGIN FORM
                    loginBtn.getScene().getWindow().hide();
                    Parent root = null;
                    if (user.getRole().equals("1")) {//c-a-d un cordonateur:
                        //LINK YOUR DASHBOARD 
                        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    }
                    if (user.getRole().equals("2")) {//c-a-d un enseignant:
                        //LINK YOUR DASHBOARD 
                        root = FXMLLoader.load(getClass().getResource("dashboardEns.fxml"));
                        dashboardEnsController.setUsernam(username.getText());

                    }
                    if (user.getRole().equals("3")) {//c-a-d un etudiant:
                        //LINK YOUR DASHBOARD 
                        root = FXMLLoader.load(getClass().getResource("dashboardEtud.fxml"));
                    }
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();

                } else {
                    // THEN ERROR MESSAGE WILL APPEAR
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    //LETS NAME THE COMPONENTS ON LOGIN FORM : )
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
