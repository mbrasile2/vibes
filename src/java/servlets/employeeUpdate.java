/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.employeeBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class employeeUpdate extends HttpServlet {

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
        if ((session.getAttribute("employee") == null)) {
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
            
            if (action.equals("add_employee")) {
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");
                String phone = request.getParameter("phone");
                String rate = request.getParameter("rate");
                double hourlyRate = 0;
                if (!rate.equals(""))
                    hourlyRate = Double.valueOf(rate);
                int SSN = Integer.valueOf(request.getParameter("SSN"));
                String password = String.valueOf(SSN);
                
                // must be in 00/00/0000 form
                String startDateString = (request.getParameter("startDate"));
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                // for debugging empty for now and NOT IN QUERY STRING
                /*Date date=null;
                try {
                    date = df.parse(startDateString);
                }catch(ParseException e) {
                    e.printStackTrace();
                }*/
                
                //boolean isManager = Boolean.valueOf(request.getParameter("isManager"));
            
                // for debugging, delete later
                boolean isManager = false;
                
                // make the account    
                String query = "INSERT INTO Employee (firstName, lastName, address,"
                    + " city, state, zipcode, telephone, hourlyRate, password, isManager, SSN) VALUES ('" 
                    +fname+ "', '" +lname+ "', '" +address+ "', '" +city+ "', '"
                    +state+ "', '" +zip+ "', '" +phone+ "'," +hourlyRate+ 
                    ", '" +password+ "', " +isManager+ ", " +SSN+ ");";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Store the session data
                // TO DO
                
                RequestDispatcher jsp = request.getRequestDispatcher("/employeeIndex.jsp");
                jsp.forward(request, response);            
            }
            if (action.equals("add_cust")) {
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                
                String zip = request.getParameter("zip");
                int phoneNum = 0;
                String phone = request.getParameter("phone");
                if (!phone.equals(""))
                    phoneNum = Integer.valueOf(phone);
                String sex = request.getParameter("sex");
                String email = request.getParameter("email");
                String prefs = request.getParameter("prefs");
                
                // make the account    
                String query = "INSERT INTO customers (firstName, lastName, address,"
                    + " city, state, zipcode, telephone, sex, emailID, preferences) VALUES ('" 
                    +fname+ "', '" +lname+ "', '" +address+ "', '" +city+ "', '"
                    +state+ "', '" +zip+ "', " +phoneNum+ ", '" +sex+ "', '" +email+ "', '" +prefs+ "');";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // get the newly created customer id
                String query9 = "SELECT customerID FROM Customers WHERE emailID = '" +email+
                        "';";
                
                int custNum=0;
                try {
                    ResultSet rs = stmt.executeQuery(query9);
                    while (rs.next()) 
                        custNum = rs.getInt("customerID");
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // get the account number
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
                
                // Make the account relation
                String query10 = "INSERT INTO accounts (accountnumber, customerID) VALUES (" +accountNum+
                        ", " +custNum+ ");";
                stmt.executeUpdate(query10);
                
                response.sendRedirect("/vibe/customer");
            }
            if (action.equals("edit_emp")) {
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");
                String phone = request.getParameter("phone");
                String rate = request.getParameter("rate");
                double hourlyRate = 0;
                if (!rate.equals(""))
                    hourlyRate = Double.valueOf(rate);
                int SSN = Integer.valueOf(request.getParameter("id"));
                               
                // must be in 00/00/0000 form
                String startDateString = (request.getParameter("startDate"));
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                // for debugging empty for now and NOT IN QUERY STRING
                /*Date date=null;
                try {
                    date = df.parse(startDateString);
                }catch(ParseException e) {
                    e.printStackTrace();
                }*/
                
                //boolean isManager = Boolean.valueOf(request.getParameter("isManager"));
            
                // for debugging, delete later
                boolean isManager = false;
                
                // make the account    
                String query = "UPDATE Employee SET firstName = '" +fname+ "', lastName = '"
                        +lname+ "', address = '" +address+ "', city = '" +city+ "', state = '" 
                        +state+ "', zipcode = '" +zip+ "', telephone = '" +phone+ "', hourlyRate = "
                        +hourlyRate+ ", isManager = " +isManager+ " WHERE SSN = " +SSN+ ";";                
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Store the session data
                // TO DO
                
                RequestDispatcher jsp = request.getRequestDispatcher("/employeeSettings.jsp");
                jsp.forward(request, response);            
            }
            if (action.equals("edit_customer")) {
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");
                int phone = Integer.valueOf(request.getParameter("phone"));
                //Date dob = Date.valueOf(request.getParameter("dob"));
                String sex = request.getParameter("sex");
                String email = request.getParameter("email");
                String prefs = request.getParameter("preferences");
                int id = Integer.valueOf(request.getParameter("id"));
                
                // get customer ID
                // String query2 = "SELECT customerID FROM accounts WHERE AccountNumber = " +id+ ";";
                
                // update customer
                String query = "UPDATE Customers SET firstName = '" +fname+ "', lastName = '"
                        +lname+ "', address = '" +address+ "', city = '" +city+ "', state = '" 
                        +state+ "', zipcode = '" +zip+ "', telephone = " +phone+ ", sex = '"                
                        +sex+ "', emailID = '" +email+ "', preferences = '" +prefs+ "' WHERE customerID = "
                        +id+ ";";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Store the session data
                // TO DO
                
                response.sendRedirect("/vibe/customer");            
            }
            if (action.equals("delete_cust")) {
                int id = Integer.valueOf(request.getParameter("id"));
                
                // make the account    
                String query = "DELETE FROM Customers WHERE (customerID = " +id+ ");";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                response.sendRedirect("/vibe/customer");
                return;
            }
            if (action.equals("delete_emp")) {
                int id = Integer.valueOf(request.getParameter("id"));
                
                // make the account    
                String query = "DELETE FROM Employee WHERE (ssn = " +id+ ");";
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
      
                // Remove from the session data
                ArrayList<employeeBean> employees = (ArrayList<employeeBean>)session.getAttribute("employeeList");
                employeeBean toBeDeleted = null;
                for (employeeBean e : employees) {
                    if (e.getEmpID() == id) {
                        toBeDeleted = e;
                        break;
                    }
                }
                employees.remove(toBeDeleted);
                session.setAttribute("employeeList", employees);
        
                response.sendRedirect("/vibe/employeeSettings.jsp");
                return;
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
