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
public class MemberDAO {

    public void saveMember(Member member) throws SQLException {

        String insertSql = "insert into lbdb.members(id,name,mobile,address)values(?,?,?,?)";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(insertSql);
        stmt.setString(1, member.getId());
        stmt.setString(2, member.getName());
        stmt.setString(3, member.getMobile());
        stmt.setString(4, member.getAddress());
        stmt.execute();
    }

    public List<Member> getMembers() throws SQLException {

        Connection conn = Database.getInstance().getConnection();
        List<Member> members = new ArrayList<>();

        String sql = "select * from lbdb.members";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while (result.next()) {
            String id = result.getString("id");
            String name = result.getString("name");
            String mobile = result.getString("mobile");
            String address = result.getString("address");
            members.add(new Member(id, name, mobile, address));
        }

        return members;
    }

    public void deleteMember(String id) throws SQLException {

        Connection conn = Database.getInstance().getConnection();
        String deleteSql = "delete from lbdb.members where id=?";
        PreparedStatement stmt = conn.prepareStatement(deleteSql);
        stmt.setString(1, id);
        stmt.execute();
    }

    public Member getMember(String id) throws SQLException {

        Connection conn = Database.getInstance().getConnection();
        String selectSql = "select * from lbdb.members where id=?";
        PreparedStatement stmt = conn.prepareStatement(selectSql);
        stmt.setString(1, id);
        ResultSet result = stmt.executeQuery();

        Member member = null;
        if (result.next()) {
            String name = result.getString("name");
            String mobile = result.getString("mobile");
            String address = result.getString("address");
            member = new Member(id, name, mobile, address);
        }
        return member;
    }

}
