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
import library.assistant.model.BookDAO;
import library.assistant.model.Books;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BooksListController implements Initializable {

    @FXML
    private TableView<Books> booksListTable;
    @FXML
    private TableColumn<Books, String> idColumn;
    @FXML
    private TableColumn<Books, String> titleColumn;
    @FXML
    private TableColumn<Books, String> authorColumn;
    @FXML
    private TableColumn<Books, String> publisherColumn;

    private BookDAO bookDAO;
    @FXML
    private MenuItem deleteItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initColumn();
        bookDAO = new BookDAO();
        loadData();

    }

    private void initColumn() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        

    }

    private void loadData() {

        try {
            List<Books> books = bookDAO.getBooks();
            booksListTable.getItems().setAll(books);
        } catch (SQLException ex) {
            Logger.getLogger(BooksListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void deleteBook(ActionEvent event) {

        Books book = booksListTable.getSelectionModel().getSelectedItem();

        if (book == null) {
            System.out.println("Plz select the book that you want to delete.");
            return;
        }

        try {
            bookDAO.deleteBook(book.getId());
            booksListTable.getItems().remove(book);
        } catch (SQLException ex) {
            System.out.println("Book was not successfully deleted.");
            Logger.getLogger(BooksListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
