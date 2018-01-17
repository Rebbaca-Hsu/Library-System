/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import library.assistant.database.Database;

/**
 *
 * @author User
 */
public class BookDAO {

    public void saveBook(Books book) throws SQLException {

        String insertSql = "insert into lbdb.books(id,title,author,publisher,is_available) values(?,?,?,?,?)";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(insertSql);
        stmt.setString(1, book.getId());
        stmt.setString(2, book.getTitle());
        stmt.setString(3, book.getAuthor());
        stmt.setString(4, book.getPublisher());
        stmt.setBoolean(5, book.isAvailable());
        stmt.execute();
    }

    public Books getBook(String id) throws SQLException {

        String selectSql = "select * from lbdb.books where id=?";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(selectSql);
        stmt.setString(1, id);
        ResultSet result = stmt.executeQuery();

        Books book = null;
        if (result.next()) {
            String title = result.getString("title");
            String author = result.getString("author");
            String publisher = result.getString("publisher");
            Boolean available = result.getBoolean("is_available");
            book = new Books(id, title, author, publisher, available);
        }

        return book;
    }

    public List<Books> getBooks() throws SQLException {

        Connection conn = Database.getInstance().getConnection();
        List<Books> books = new ArrayList<>();

        String sql = "select * from lbdb.books";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while (result.next()) {

            String id = result.getString("id");
            String title = result.getString("title");
            String author = result.getString("author");
            String publisher = result.getString("publisher");
            Boolean available = result.getBoolean("is_available");
            
            books.add(new Books(id, title, author, publisher,available));
        }

        return books;

    }

    public void updateBook(String id, boolean available) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        String updateSql = "update lbdb.books set is_available =? where id=?";
        PreparedStatement stmt = conn.prepareStatement(updateSql);
        stmt.setBoolean(1, available);
        stmt.setString(2, id);
        stmt.execute();

    }

    public void deleteBook(String id) throws SQLException {

        Connection conn = Database.getInstance().getConnection();
        String deleteSql = "delete from lbdb.books where id=?";
        PreparedStatement stmt = conn.prepareStatement(deleteSql);
        stmt.setString(1, id);
        stmt.execute();

    }

}
