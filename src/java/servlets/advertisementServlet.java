/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.advertisementBean;
import entities.Employee;
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
 * @author Conor
 */
public class advertisementServlet extends HttpServlet {

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
        if (action.equals("create")){
            try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stmt = conn.createStatement();
            Employee currentEmployee = new Employee();
            currentEmployee = (Employee)session.getAttribute("employee");
            int employeeID = currentEmployee.getSsn();
            String query2 = "SELECT MAX(advertisementID) FROM advertisement;";
            int advertisementID = 0;
            try {
                    ResultSet rs = stmt.executeQuery(query2);
                    while (rs.next()) 
                        advertisementID = rs.getInt("MAX(advertisementID)");
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            advertisementID = advertisementID + 1;
            int date = 0;
            String type = request.getParameter("type");
            String iName = request.getParameter("iname");
            String company = request.getParameter("company");
            String content = request.getParameter("content");
            String priceString = request.getParameter("price");
            String uAvailableString = request.getParameter("uavailable");
            double price = Double.parseDouble(priceString);
            int uAvailable = Integer.parseInt(uAvailableString);
            String query = "INSERT INTO advertisement (advertisementID, employeeID, type, itemName, company, content, price, unitsAvailable) " +
                    "VALUES (" + advertisementID + ", " + employeeID + ", '" + type + "', '" + iName + "', '" + company + "', '" +
                    content + "', " + price + ", " + uAvailable + ");";
                    try {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
        }
            catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
        if(action.equals("delete_ad")){
            try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stmt = conn.createStatement();
            Employee currentEmployee = new Employee();
            currentEmployee = (Employee)session.getAttribute("employee");
            int aid = Integer.valueOf(request.getParameter("aid"));
            String query = "DELETE FROM advertisement WHERE advertisementID = " + aid + ";";
            int advertisementID = 0;
            try {
                    stmt.executeUpdate(query);
                   
              } catch (SQLException ex) {
                  ex.printStackTrace();
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            //update ad info
            ArrayList<advertisementBean> ads = new ArrayList<advertisementBean>();
                String adQuery = "SELECT * FROM advertisement;";
                try {
                    ResultSet rs = stmt.executeQuery(adQuery) ;
                    while (rs.next()) {
                        ads.add(new advertisementBean(rs.getInt("advertisementID"), rs.getInt("employeeID"), rs.getString("type"), 
                        rs.getString("itemName"), rs.getDate("dateCreated"), rs.getString("company"), rs.getString("content"), rs.getDouble("price"), rs.getInt("unitsAvailable")));
                        
                    }
                
                    // add user info to the session
                    session.removeAttribute("allAds");
                    session.setAttribute("allAds", ads);
                } catch (SQLException ex) {
                    Logger.getLogger(employeeLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
           
                    
        }
            catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher jsp = request.getRequestDispatcher("./advertisements.jsp");
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
