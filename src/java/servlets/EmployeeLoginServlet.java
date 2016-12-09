/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
public class EmployeeLoginServlet extends HttpServlet {

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
        
        // Get info from form
        int user = Integer.valueOf(request.getParameter("user"));
        String password = request.getParameter("password");
        
        /* get session object */
        HttpSession session = request.getSession();
        
        String pw = new String();
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
            query = "SELECT SSN, password FROM Employee WHERE (SSN = " +user+ 
                    " AND password = '" +password+ "');";
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    pw = rs.getString("password");
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }     
            
            if (pw != null && pw.equals(password)) {
            // success, the username and password checks out. Grab 
            // all information, and store it in the session.
            
                //Create and populate User bean
                employeeBean returningEmployee = new employeeBean();
                
                String query1 = "SELECT * FROM Employee WHERE (SSN = " +user+ 
                        " AND password = '" +password+ "');";         
                try {
                    ResultSet rs = stmt.executeQuery(query1) ;
                    while (rs.next()) {
                        returningEmployee.setFirstName(rs.getString("FirstName"));
                        returningEmployee.setLastName(rs.getString("LastName"));
                        boolean isM = rs.getBoolean("isManager");
                        if (isM)
                            returningEmployee.setManager(true);
                        else
                            returningEmployee.setManager(false);
                    }
                
                    // add user info to the session
                    session.setAttribute("employee", returningEmployee);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }          
                
                if (returningEmployee.isManager()) {
                    String query2 = "SELECT * FROM Employee;";  
                    ArrayList<employeeBean>employeeList = new ArrayList<>();
                    try {
                        ResultSet rs = stmt.executeQuery(query2) ;
                        while (rs.next()) {
                            employeeBean e = new employeeBean();
                            e.setFirstName(rs.getString("FirstName"));
                            e.setLastName(rs.getString("LastName"));
                            e.setManager(rs.getBoolean("isManager"));
                            e.setAddress(rs.getString("Address"));
                            e.setZipcode(rs.getString("zipcode"));
                            e.setCity(rs.getString("city"));
                            e.setState(rs.getString("state"));
                            e.setPay(rs.getDouble("hourlyRate"));
                            e.setStartDate(rs.getDate("startDate"));
                            e.setPhoneNum(rs.getString("telephone"));
                            e.setEmpID(rs.getInt("SSN"));
                            employeeList.add(e);
                        }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }      
                     // add user info to the session
                    session.setAttribute("employeeList", employeeList);
                }
            }
        }catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher jsp = request.getRequestDispatcher("/employeeIndex.jsp");
        jsp.forward(request, response);
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
