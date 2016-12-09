/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.commentBean;
import beans.commentLikeBean;
import beans.groupBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import beans.advertisementBean;
import beans.postBean;
import beans.postLikeBean;
import java.util.ArrayList;
import beans.userBean;

/**
 *
 * @author kATHRYN
 */
public class PageLoadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = request.getRequestURL().toString();
        String[] urlParts = url.split("/");
        int ID = -1;
        boolean buy = false;
        if (urlParts[urlParts.length-1].equals("buySimulation")) 
            buy = true;
        else 
            ID= (int)Long.parseLong(urlParts[urlParts.length-1]);
        
        
        groupBean g = new groupBean();
        
        /* get session object */
        HttpSession session = request.getSession();

        String query;
            
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:33069/cse305", "root", "suckit");
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stmt = conn.createStatement();
            
            // load advertisement info
            if (buy) {
                query = "SELECT * FROM advertisement;";
                ArrayList<advertisementBean> adverts = new ArrayList<>();
                try {
                ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        advertisementBean a = new advertisementBean();
                        a.setContent(rs.getString("content"));
                        a.setAdID(rs.getInt("advertisementID"));
                        a.setDate(rs.getDate("dateCreated"));
                        a.setEmpID(rs.getInt("employeeID"));
                        a.setCompany(rs.getString("company"));
                       
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
            query = "SELECT * FROM Posts WHERE (Page = " +ID+ ") ORDER BY postdate DESC;";
            ArrayList<postBean> currentPosts = new ArrayList<>();
            ArrayList<commentBean> commentList = new ArrayList<>();
            ArrayList<commentLikeBean> commentLikes = new ArrayList<>();
            
            try {
                ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        postBean p = new postBean();
                        p.setContent(rs.getString("content"));
                        p.setPostID(rs.getInt("postID"));
                        p.setDate(rs.getDate("postdate"));
                        p.setAuthor((rs.getObject("author", Integer.class)).toString());
                        p.setAuthorID(Integer.valueOf(p.getAuthor()));
                        currentPosts.add(p);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<postLikeBean> postLikes = new ArrayList<>();
            for (postBean p : currentPosts) {
                try {
                    query = "SELECT firstname, lastname FROM user WHERE AccountNumber = " +p.getAuthor()+ ";";
                    ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            String fname = rs.getString("firstName");
                            String lname = rs.getString("lastname");
                            p.setAuthor(fname + " " + lname);   
                            }
                    } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                // add postlikes to request
                try {
                    query = "SELECT userID FROM postLikes WHERE postID = " +p.getPostID()+ ";";
                    ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            postLikeBean plb = new postLikeBean();
                            plb.setPostID(p.getPostID());
                            plb.setUserID(rs.getInt("UserID"));
                            postLikes.add(plb);
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                // add post comments to request
                try {
                    query = "SELECT post, content, author, commentID, commentDate, firstname, lastname FROM comments"
                        + " JOIN User ON comments.author = User.accountNumber WHERE post = " +p.getPostID()+
                            " ORDER BY commentdate DESC;";
                        
                    ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            commentBean c = new commentBean();
                            c.setPostID(p.getPostID());
                            c.setAuthorID(rs.getInt("author"));
                            c.setContent(rs.getString("content"));
                            c.setDate(rs.getDate("commentDate"));
                            c.setAuthor(rs.getString("firstName") + " " + rs.getString("lastName"));
                            c.setCommentID(rs.getInt("commentID"));
                            commentList.add(c);
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }    
            }
            // add likes to request
            for (commentBean c : commentList) {
                try {
                    query = "SELECT userID FROM commentLikes WHERE commentID = " +c.getCommentID()+ ";";
                    ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            commentLikeBean clb = new commentLikeBean();
                            clb.setCommentID(c.getCommentID());
                            clb.setUserID(rs.getInt("UserID"));
                            commentLikes.add(clb);
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // add posts to the session
            request.setAttribute("currentPosts", currentPosts);
            request.setAttribute("postLikes", postLikes);
            request.setAttribute("commentList", commentList);
            request.setAttribute("commentLikes", commentLikes);
            
            boolean primary = false;
            try {
                    query = "SELECT * FROM pages WHERE PageID = " +ID+ ";";
                    ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            if (rs.getString("primarypage").equals("y")) 
                                primary = true;
                            else 
                                g.setPageID(ID);
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            boolean isGroup = false;
            if (primary == false) {
                try {
                    isGroup = true;
                    session.setAttribute("isGroup", true);
                    query = "SELECT * FROM fbgroup WHERE PageID = " +ID+ ";";
                    ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            g.setGroupName(rs.getString("groupname"));
                            g.setGroupOwner(rs.getInt("owner"));
                            g.setGroupID(rs.getInt("groupID"));
                            g.setPageID(rs.getInt("pageID"));
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                                
                // load group users
                ArrayList<userBean> users = new ArrayList<>();
                
                try {
                    query = "SELECT firstname, lastname, accountNumber FROM User " +
                        "JOIN groupMembership " +
                        "ON groupMembership.UserID = User.AccountNumber " +
                        "where groupMembership.groupID = " +g.getGroupID()+ ";";
                    ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            userBean newBean = new userBean();
                            newBean.setFirstName(rs.getString("firstname"));
                            newBean.setLastName(rs.getString("lastname"));
                            newBean.setAccountNumber(rs.getInt("accountnumber"));

                            users.add(newBean);
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("groupUsers", users);
                session.setAttribute("currentGroup", g);
                RequestDispatcher jsp;
                jsp = request.getRequestDispatcher("/page.jsp");
                jsp.forward(request,response);
            }
            else {
                session.setAttribute("isGroup", false);
                try {
                    query = "SELECT firstname, lastname, ownerid FROM pages JOIN user ON "
                            + "pages.ownerid = user.accountNumber where pageID = " +ID+ ";"; 
                    ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            g.setGroupName(rs.getString("firstname") +" "+ rs.getString("lastname") + "'s Wall");
                            g.setGroupOwner(rs.getInt("ownerid"));
                            g.setPageID(ID);
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("currentGroup", g);
                RequestDispatcher jsp;
                jsp = request.getRequestDispatcher("/page.jsp");
                jsp.forward(request,response);
            }
            }
        }catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
