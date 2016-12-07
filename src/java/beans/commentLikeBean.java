/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author kATHRYN
 */
public class commentLikeBean {
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
    private int commentID;
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof commentLikeBean) {
            commentLikeBean clb = (commentLikeBean)other;
            if (this.getCommentID() == clb.getCommentID() && this.getUserID() == clb.getUserID())
            return true;
        }
        return false;
    }
    
}
