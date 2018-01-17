/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ServerController implements Initializable {

    @FXML
    private TextField hostField;
    @FXML
    private Spinner<Integer> portSpinner;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    private Preferences prefs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prefs = Preferences.userRoot().node("lbdb");
        String host = prefs.get("host", "localhost");
        String username = prefs.get("user", "root");
        String password = prefs.get("pass", "");
        int port = prefs.getInt("port", 3306);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3300, 3320, port);
        portSpinner.setValueFactory(valueFactory);
        hostField.setText(host);
        nameField.setText(username);
        passField.setText(password);
    }

    @FXML
    private void saveServerConfig(ActionEvent event) {

        String host = hostField.getText();
        String username = nameField.getText();
        String password = passField.getText();
        int port = portSpinner.getValue();
        prefs.put("host", host);
        prefs.put("user", username);
        prefs.put("pass", password);
        prefs.putInt("port", port);
        
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

}
