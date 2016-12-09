/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.advertisementBean;
import beans.employeeBean;
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
public class ItemPageLoadServlet extends HttpServlet {

    
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
        
        String companyWanted = request.getParameter("company");
        
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
            
            // get hottest trending items
            String query1 = "SELECT itemName, content, price, s.advertisementID, SUM(s.numUnits) as count FROM salesData s "
                    +"JOIN Advertisement ON "
                    +"advertisement.advertisementID = s.advertisementID "
                    +"GROUP BY advertisementID ORDER BY count DESC LIMIT 5;";
            ArrayList<advertisementBean> hottestItems = new ArrayList<>();
            try {
                ResultSet rs = stmt.executeQuery(query1) ;
                while (rs.next()) {
                    advertisementBean a = new advertisementBean();
                    a.setContent(rs.getString("content"));
                    a.setName(rs.getString("itemName"));
                    a.setPrice(rs.getDouble("price"));
                    a.setNumSold(rs.getInt("count"));
                    hottestItems.add(a);
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
            request.setAttribute("hottestItems", hottestItems);
            
            // get highest revenue generating employees
            String query2 = "SELECT firstName, lastName, itemName, employeeID, content, price, " +
                "s.advertisementID, SUM(s.numUnits)*price as revenue FROM salesData s " +
                "JOIN Advertisement a ON " +
                "a.advertisementID = s.advertisementID " +
                "JOIN Employee ON " +
                "employee.ssn = a.employeeID " +
                "GROUP BY employeeID ORDER BY revenue DESC LIMIT 3;";
            
             ArrayList<employeeBean> mostRevenue = new ArrayList<>();
            try {
                ResultSet rs = stmt.executeQuery(query2) ;
                while (rs.next()) {
                    employeeBean e = new employeeBean();
                    e.setFirstName(rs.getString("firstName"));
                    e.setLastName(rs.getString("lastName"));
                    e.setRevenue(rs.getDouble("revenue"));
                    mostRevenue.add(e);
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
            request.setAttribute("mostRevenue", mostRevenue);
        
        if (companyWanted!=null) {
            String query = "SELECT itemName, content, price FROM advertisement " 
                    + "WHERE company = '" + companyWanted+ "';";
            ArrayList<advertisementBean> itemsWanted = new ArrayList<>();
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    advertisementBean a = new advertisementBean();
                    a.setContent(rs.getString("content"));
                    a.setName(rs.getString("itemName"));
                    a.setPrice(rs.getDouble("price"));
                    itemsWanted.add(a);
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }    
            request.setAttribute("itemsWanted", itemsWanted);
        }
        
        RequestDispatcher jsp = request.getRequestDispatcher("./items.jsp");
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
