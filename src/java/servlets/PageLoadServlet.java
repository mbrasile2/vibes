/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.groupBean;
import java.io.IOException;
import java.io.PrintWriter;
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

import beans.postBean;
import java.util.ArrayList;

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
        int ID = (int)Long.parseLong(urlParts[urlParts.length-1]);
        
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
            query = "SELECT * FROM Posts WHERE (Page = " +ID+ ") ORDER BY postdate DESC;";
            ArrayList<postBean> currentPosts = new ArrayList<>();
            
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
            }
            // add posts to the session
            session.setAttribute("currentPosts", currentPosts);
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
            if (primary == false) {
                try {
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
                session.setAttribute("currentGroup", g);
                RequestDispatcher jsp;
                jsp = request.getRequestDispatcher("/groupjsp.jsp");
                jsp.forward(request,response);
            }
            else {
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
                jsp = request.getRequestDispatcher("/pagejsp.jsp");
                jsp.forward(request,response);
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
