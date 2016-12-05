/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.groupBean;
import beans.message;
import entities.Posts;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kATHRYN
 */
public class UpdateServlet extends HttpServlet {

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
        
        // Get info from session
        HttpSession session = request.getSession();
        
        // Get info from form
        String action = request.getParameter("action");
        
        // If the user isn't logged in, direct them to login page
        if ((session.getAttribute("user") == null) && !(action.equals("register"))) {
            response.sendRedirect("/vibe/");
        }

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
            
            if (action.equals("register")) {
                String password = request.getParameter("password");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String prefs = request.getParameter("prefs");
            
                // make the account    
                String query = "INSERT INTO User (firstName, lastName, address,"
                    + " city, state, zipcode, telephone, emailaddress, preferences, password) VALUES ('" 
                    +fname+ "', '" +lname+ "', '" +address+ "', '" +city+ "', '"
                    +state+ "', '" +zip+ "', '" +phone+ "', '" +email+ "', '" +prefs+
                    "', '" +password+ "');";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // get the newly created account number
                String query2 = "SELECT AccountNumber FROM User WHERE emailaddress = '" +email+
                        "';";
                
                int accountNum=0;
                try {
                    ResultSet rs = stmt.executeQuery(query2);
                    while (rs.next()) 
                        accountNum = rs.getInt("accountNumber");
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                // Now make the page
                String query3 = "INSERT INTO Pages (ownerID, postCount, primaryPage)"
                    + " VALUES (" +accountNum+ ", 0, 'y');";
                stmt.executeUpdate(query3);
                
                // acquire the pageID
                String query4 = "SELECT * FROM Pages WHERE (ownerID = '" +accountNum+
                        "' AND primarypage = 'y');";
                int s=0;
                try {
                    ResultSet rs2 = stmt.executeQuery(query4);
                    while (rs2.next()) 
                        s = rs2.getInt("pageID");
                                  
                    // add user info to the session
                    session.setAttribute("pageID", s);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Store the session data
                User returningUser = new User();
                
                returningUser.setFirstName(fname);
                returningUser.setLastName(lname);
                returningUser.setEmailAddress(email);
                returningUser.setAccountNumber(accountNum);
                
                session.setAttribute("user", returningUser);
            }
            if (action.equals("post")) {
                String post_data = request.getParameter("post_data");
                int acctNum = ((User)session.getAttribute("user")).getAccountNumber();
                
                  // add post to database   
                String query = "INSERT INTO Posts (author, content, page) VALUES (" +acctNum+ ", '"
                        +post_data+ "', " +session.getAttribute("pageID")+ ");";
                
                stmt.executeUpdate(query);
                
                // add post into the session data
                query = "SELECT * FROM Posts WHERE (Author = " +acctNum+
                        ") ORDER BY postdate DESC;";
                
                ArrayList<Posts> postList = new ArrayList<>();
               
                try {
                    ResultSet rs = stmt.executeQuery(query) ;
                    while (rs.next()) {
                        Posts p = new Posts();
                        p.setPostID(rs.getInt("PostID"));
                        p.setPostDate(rs.getDate("PostDate"));
                        p.setContent(rs.getString("content"));
                        postList.add(p);
                    }    
                
                    session.setAttribute("postlist", postList);
                    response.sendRedirect("./wall.jsp");
                
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            if (action.equals("delete_msg")) {
                int mid = Integer.valueOf(request.getParameter("mid"));
                
                // make the account    
                String query = "DELETE FROM Message WHERE (MessageId = " +mid+ ");";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
      
                // Remove from the session data
                ArrayList<message> mess = (ArrayList<message>)session.getAttribute("messages");
                message toBeDeleted = null;
                for (message m : mess) {
                    if (m.getMid() == mid) {
                        toBeDeleted = m;
                        break;
                    }
                }
                mess.remove(toBeDeleted);
                session.setAttribute("messages", mess);
        
                response.sendRedirect("./messages.jsp");
                return;
            }
            if (action.equals("send_msg")) { 
            
                String c1 = request.getParameter("content");
                String c2 = request.getParameter("sender");
                String c3 = request.getParameter("receiver");
                int c4 = 0;
                
                String query1 = "SELECT AccountNumber FROM User WHERE (EmailAddress = '" +c3+ "');";         
                try {
                    ResultSet rs = stmt.executeQuery(query1) ;
                    while (rs.next()) {
                        c4 = rs.getInt("AccountNumber");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // make the message    
                String query = "INSERT INTO Message (content, sender, receiver) VALUES ('" +c1+ "', "
                        +c2+ ", " +c4+ ");";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                response.sendRedirect("./messages.jsp");
                return;
            }
            if (action.equals("new_group")) {
                int owner = Integer.valueOf(request.getParameter("owner"));
                String groupName = request.getParameter("groupname");
            
                // make the new page
                String query3 = "INSERT INTO Pages (ownerID, postCount, primaryPage)"
                    + " VALUES (" +owner+ ", 0, 'x');";
                stmt.executeUpdate(query3);
                
                // acquire the pageID
                String query4 = "SELECT * FROM Pages WHERE (ownerID = '" +owner+
                        "' AND primarypage = 'x');";
                int s=0;
                try {
                    ResultSet rs2 = stmt.executeQuery(query4);
                    while (rs2.next()) 
                        s = rs2.getInt("pageID");
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // make the group   
                String query = "INSERT INTO fbGroup (owner, groupname, pageID) VALUES (" +owner
                        + ", '" +groupName+ "', " +s+ ");";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                                        
                // update pageID
                String query6 = "UPDATE Pages SET primarypage = 'n' WHERE PageID = " +s+ ";";
                try {
                    stmt.executeUpdate(query6);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // update session data
                ArrayList<groupBean> groups = (ArrayList<groupBean>)session.getAttribute("groups");
                groupBean newGroup = new groupBean();
                newGroup.setGroupName(groupName);
                newGroup.setPageID(s);
                groups.add(newGroup);
   
                session.setAttribute("groups", groups);
                response.sendRedirect("./groups.jsp");
                return;
            }
            if (action.equals("leave_group") {
                // update group membership
                String query6 = "DELETE FROM GroupMembership WHERE (groupID = ;";
                try {
                    stmt.executeUpdate(query6);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
            if (action.equals("edit_group")) {
                String gname = request.getParameter("gname");
                // TODO: edit this to page URL
                int gid = Integer.valueOf(request.getParameter("gid"));
                
                // edit the group    
                String query = "UPDATE fbGroup SET GroupName = '" +gname+ "' WHERE (GroupId = " +gid+ ");";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
      
                // Remove from the session data
                ArrayList<groupBean> gbeans = (ArrayList<groupBean>)session.getAttribute("groupList");
                groupBean toBeEdited = null;
                for (groupBean g : gbeans) {
                    if (g.getPageID() == gid) {
                        toBeEdited = g;
                        break;
                    }
                }
                gbeans.remove(toBeEdited);
                toBeEdited.setGroupName(gname);
                gbeans.add(toBeEdited);
                session.setAttribute("groupList", gbeans);
        
                response.sendRedirect("./groups.jsp");
                return;
            }
            if (action.equals("add_user")) {
                String email = request.getParameter("email");
                String groupID = request.getParameter("group");
                int accountNumber = -1;
                
                // check if user exists
                String query = "SELECT AccountNumber FROM User WHERE (EmailAddress = '" +email+ "');";
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    accountNumber = rs.getInt("AccountNumber");
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (accountNumber < 0) {
                // user does not exist. return
                return;
            }
            else {
                // add user    
                query = "INSERT INTO groupMembership (userID, groupID) VALUES (" +accountNumber+ ", "
                        +groupID+ ");";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
            
        } catch (SQLException ex) {
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
