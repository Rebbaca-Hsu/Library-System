/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 *
 * @author User
 */
public class Database {

    private Connection conn;//declare

    private static Database db;//declare

    private static String host;
    private static int port;
    private static String username;
    private static String password;

    private Database() throws SQLException {
        connect();
        createDatabase();
        createTables();
    }

    public static Database getInstance() throws SQLException {
        if (db == null) {
            db = new Database();
        }// Singleton Pattern
        return db;
    }

    public void connect() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDatabaseConfig();
        conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/", username, password);
    }

    public void createDatabase() throws SQLException {
        String createSql = "create database if not exists lbdb";

        Statement stmt = conn.createStatement();
        stmt.execute(createSql);
    }

    public void createTables() throws SQLException {
        String createBookTableSql = "create table if not exists lbdb.books(id varchar (255) primary key,title varchar (255), author varchar (255),publisher varchar (255),is_available boolean default true)";
        String createMemberTableSql = "create table if not exists lbdb.members(id varchar (255) primary key,name varchar (255),mobile varchar (255),address varchar (255))";
        String createIssueTableSql = "create table if not exists lbdb.issue(member_id varchar (255),book_id varchar (255),issue_date Date,renew_count int,foreign key (member_id) references members(id),foreign key (book_id) references books(id))";

        Statement stmt1 = conn.createStatement();
        stmt1.execute(createBookTableSql);

        Statement stmt2 = conn.createStatement();
        stmt2.execute(createMemberTableSql);

        Statement stmt3 = conn.createStatement();
        stmt3.execute(createIssueTableSql);
    }

    public Connection getConnection() {
        return conn;
    }

    private void loadDatabaseConfig() {
        Preferences prefs = Preferences.userRoot().node("lbdb");
        
        host = prefs.get("host", "localhost");
        username = prefs.get("user", "root");
        password = prefs.get("pass", "");
        port = prefs.getInt("port", 3306);

    }

}
