/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import library.assistant.model.Member;
import library.assistant.model.MemberDAO;
import library.assistant.util.MessageBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NewMemberController implements Initializable {

    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField mobileField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;

    private MemberDAO memberDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        memberDAO = new MemberDAO();
    }

    @FXML
    private void saveMember(ActionEvent event) {

        String id = idField.getText();
        String name = nameField.getText();
        String mobile = mobileField.getText();
        String address = addressField.getText();

        if (id.isEmpty() || name.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        Member member = new Member(id, name, mobile, address);
        try {
            memberDAO.saveMember(member);
            
            MessageBox.showAndWaitMessage("Success","Member was successfully added.");
          
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewMemberController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Member adding failed.");
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
