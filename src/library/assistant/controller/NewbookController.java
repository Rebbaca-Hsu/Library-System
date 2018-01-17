/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import library.assistant.model.Books;
import library.assistant.model.BookDAO;
import library.assistant.util.MessageBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NewbookController implements Initializable {

    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField authorField;
    @FXML
    private JFXTextField publisherField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;

    private BookDAO bookDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bookDAO = new BookDAO();

    }

    @FXML
    private void saveBook(ActionEvent event) {

        //getting info from fields..........
        String id = idField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();

        //validating.....
        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || publisher.isEmpty()) {
            System.out.println("Please Fill in all fields.");
            return;
        }
        //saving book info to database.......
        Books book = new Books(id, title, author, publisher, true);
        try {
            bookDAO.saveBook(book);
            MessageBox.showAndWaitMessage("Success", "Book was successfully added.");

            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(NewbookController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Book adding failed.");
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) throws IOException {

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();

    }

}
