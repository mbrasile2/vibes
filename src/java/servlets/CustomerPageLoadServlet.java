/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.userBean;
import beans.customerBean;
import java.io.IOException;
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
public class CustomerPageLoadServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        
        String itemWanted = request.getParameter("item");
        request.setAttribute("itemWanted", itemWanted);
        
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
            
            // get customer objects
            String query8 = "SELECT * FROM customers;";
            ArrayList<customerBean> customerList = new ArrayList<>();
            try {
                ResultSet rs = stmt.executeQuery(query8);
                while (rs.next()) {
                    customerBean c = new customerBean();
                    c.setFirstName(rs.getString("firstName"));
                    c.setLastName(rs.getString("lastName"));
                    c.setSex(rs.getString("sex"));
                    c.setCustomerID(rs.getInt("customerID"));
                    c.setEmail(rs.getString("emailID"));
                    c.setDob(rs.getDate("dob"));
                    c.setAddress(rs.getString("Address"));
                    c.setCity(rs.getString("city"));
                    c.setState(rs.getString("state"));
                    c.setZipcode(rs.getString("zipcode"));
                    c.setTelephone(rs.getInt("telephone"));
                    c.setPreferences(rs.getString("preferences"));
                    customerList.add(c);
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
            request.setAttribute("customerList", customerList);
            
            
            // get highest revenue generating customers
            String query2 = "SELECT firstName, lastName, SUM(s.numUnits)*price as revenue FROM salesData s " +
                "JOIN Advertisement a ON " +
                "a.advertisementID = s.advertisementID " +
                "JOIN User ON " +
                "User.AccountNumber = s.accountNum " +
                "GROUP BY accountNumber ORDER BY revenue DESC LIMIT 3;";
            
            ArrayList<userBean> mostRevenue = new ArrayList<>();
            try {
                ResultSet rs = stmt.executeQuery(query2);
                while (rs.next()) {
                    userBean u = new userBean();
                    u.setFirstName(rs.getString("firstName"));
                    u.setLastName(rs.getString("lastName"));
                    u.setRevenue(rs.getDouble("revenue"));
                    mostRevenue.add(u);
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
            request.setAttribute("custRevenue", mostRevenue);
        
        if (itemWanted!=null) {
            String query = "SELECT firstName, lastName FROM salesData " +
                "JOIN User ON " +
                "User.accountNumber = salesdata.accountNum " +
                "JOIN Advertisement ON " +
                "advertisement.advertisementID = salesData.advertisementID " +
                "WHERE advertisement.itemName = '" +itemWanted+ "' " +
                "GROUP BY advertisement.advertisementID;";
            
            ArrayList<userBean> customersWanted = new ArrayList<>();
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    userBean u = new userBean();
                    u.setFirstName(rs.getString("firstName"));
                    u.setLastName(rs.getString("lastName"));
                    customersWanted.add(u);
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }    
            request.setAttribute("customersWanted", customersWanted);
        }
        
        RequestDispatcher jsp = request.getRequestDispatcher("./customers.jsp");
        jsp.forward(request,response);
            
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
