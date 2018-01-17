/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.assistant.model.Member;
import library.assistant.model.MemberDAO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MembersListController implements Initializable {

    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, String> idColumn;
    @FXML
    private TableColumn<Member, String> nameColumn;
    @FXML
    private TableColumn<Member, String> mobileColumn;
    @FXML
    private TableColumn<Member, String> addressColumn;

    private MemberDAO memberDAO;
    @FXML
    private MenuItem deleteItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initColumn();
        memberDAO = new MemberDAO();
        loadData();
    }

    private void initColumn() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadData() {

        try {
            List<Member> members = memberDAO.getMembers();
            memberTable.getItems().setAll(members);
        } catch (SQLException ex) {
            Logger.getLogger(MembersListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteMember(ActionEvent event) {

        Member member = memberTable.getSelectionModel().getSelectedItem();

        if (member == null) {
            System.out.println("Plz select the member that you want to delete.");
            return;
        }

        try {
            memberDAO.deleteMember(member.getId());
            memberTable.getItems().remove(member);
        } catch (SQLException ex) {
            System.out.println("Member was not successfully deleted.");
            Logger.getLogger(MembersListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
