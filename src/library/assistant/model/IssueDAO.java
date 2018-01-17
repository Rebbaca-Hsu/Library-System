/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.assistant.database.Database;

/**
 *
 * @author User
 */
public class IssueDAO {

    public void saveIssueInfo(String memberId, String bookId) throws SQLException {

        String insertSql = "insert into lbdb.issue(member_id,book_id,issue_date,renew_count) values(?,?,curdate(),0)";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(insertSql);
        stmt.setString(1, memberId);
        stmt.setString(2, bookId);
        stmt.execute();
    }

    public boolean checkBookAvailable(String bookId) throws SQLException {

        String selectSql = "select count(*) as count from lbdb.issue where book_id=?";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(selectSql);
        stmt.setString(1, bookId);
        ResultSet result = stmt.executeQuery();
        boolean available = true;
        if (result.next()) {
            int count = result.getInt("count");
            if (count == 0) {
                available = false;
            }
        }
        return available;
    }

    public IssueInfo searchIssueInfo(String bookId) throws SQLException {

        String selectSql = "select * from lbdb.issue where book_id=?";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(selectSql);
        stmt.setString(1, bookId);
        ResultSet result = stmt.executeQuery();

        IssueInfo issueInfo = null;
        if (result.next()) {
            String memberId = result.getString("member_id");
            Date issueDate = result.getDate("issue_date");
            int renewCount = result.getInt("renew_count");
            issueInfo = new IssueInfo(bookId, memberId, issueDate, renewCount);
        }
        return issueInfo;
    }

    public void deleteIssueInfo(String bookId) throws SQLException {
        String deleteSql = "delete from lbdb.issue where book_id=?";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(deleteSql);
        stmt.setString(1, bookId);
        stmt.execute();
    }

    public void updateIssueInfo(String bookId) throws SQLException {

        String updateSql = "update lbdb.issue set renew_count=renew_count+1 where book_id=?";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(updateSql);
        stmt.setString(1, bookId);
        stmt.execute();
    }

}
