/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.model;

import java.sql.Date;

/**
 *
 * @author User
 */
public class IssueInfo {
    
    private String bookId;
    private String memberId;
    private Date issueDate;
    private int renewCount;

    public IssueInfo(String bookId, String memberId, Date issueDate, int renewCount) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.renewCount = renewCount;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getRenewCount() {
        return renewCount;
    }

    public void setRenewCount(int renewCount) {
        this.renewCount = renewCount;
    }
    
}
