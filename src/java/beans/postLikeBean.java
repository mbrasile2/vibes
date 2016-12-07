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
public class postLikeBean {
    private int userID;
    private int postID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    } 
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof postLikeBean) {
            postLikeBean pb = (postLikeBean)other;
            if (this.getPostID() == pb.getPostID() && this.getUserID() == pb.getUserID())
            return true;
        }
        return false;
    }
}
