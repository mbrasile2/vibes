/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import servlets.LoginServlet;

/**
 *
 * @author Conor
 */
public class suggestionServlet extends HttpServlet {

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
            
            
            String query = "SELECT AccountNumber FROM user WHERE EmailAddress = '" + request.getParameter("customer") +"';";
            
            int userAccount = 0;
            try {
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        userAccount = rs.getInt("AccountNumber");}
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            String query2 = "SELECT advertisementID FROM salesdata WHERE accountNum = " + userAccount + ";";
            int adNum;
            ArrayList<Integer> adNumbers = new ArrayList<Integer>();
            try {
                boolean has = false;
                    ResultSet rs = stmt.executeQuery(query2);
                    while (rs.next()) {
                        adNum = rs.getInt("advertisementID");
                        for (int i = 0; i < adNumbers.size(); i++){
                            if (adNum == adNumbers.get(i)){
                                has = true;
                            }
                        }
                        if (has == false){
                            adNumbers.add(adNum);
                        }
                    }
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<String> otherAccountsQuery = new ArrayList<String>();
            for (int i = 0; i < adNumbers.size(); i++){
                otherAccountsQuery.add("SELECT accountNum FROM salesdata WHERE advertisementID = " + adNumbers.get(i) + ";");
            }
            ArrayList<Integer> otherAccountNum = new ArrayList<Integer>();
            int otherAccounts;
            for (int i = 0; i < otherAccountsQuery.size(); i++){
                try {
                boolean has = false;
                    ResultSet rs = stmt.executeQuery(otherAccountsQuery.get(i));
                    while (rs.next()) {
                        otherAccounts = rs.getInt("accountNum");
                        for (int j = 0; j < otherAccountNum.size(); j++){
                            if (otherAccountNum.get(j) == otherAccounts){
                                has = true;
                            }
                        }
                        if (has == false){
                            otherAccountNum.add(otherAccounts);
                        }
                    }
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            ArrayList<String> whatOthersBoughtQuery = new ArrayList<String>();
                for (int i = 0; i < otherAccountNum.size(); i++){
                    whatOthersBoughtQuery.add("SELECT advertisementID FROM salesdata WHERE accountNum = " + otherAccountNum.get(i) + ";");
                }
                int otherProducts;
                ArrayList<Integer> otherProductList = new ArrayList<Integer>();
                for (int i = 0; i < whatOthersBoughtQuery.size(); i++){
                    try {
                boolean has = false;
                    ResultSet rs = stmt.executeQuery(whatOthersBoughtQuery.get(i));
                    while (rs.next()) {
                        otherProducts = rs.getInt("advertisementID");
                        for (int j = 0; j < otherProductList.size(); j++){
                            if (otherProductList.get(j) == otherProducts){
                                has = true;
                            }
                        }
                        if (has == false){
                            otherProductList.add(otherProducts);
                        }
                    }
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                ArrayList<String> finalQuery = new ArrayList<String>();
                for (int i = 0; i < otherProductList.size(); i++){
                    finalQuery.add("SELECT ItemName FROM items WHERE AdvertisementID = " + otherProductList.get(i) + ";");
                }
                String item;
                ArrayList<String> itemList = new ArrayList<String>();
                for (int i = 0; i < finalQuery.size(); i++){
                    try {
                boolean has = false;
                    ResultSet rs = stmt.executeQuery(finalQuery.get(i));
                    while (rs.next()) {
                        item = rs.getString("ItemName");
                        for (int j = 0; j < itemList.size(); j++){
                            if (itemList.get(j).equals(item)){
                                has = true;
                            }
                        }
                        if (has == false){
                            itemList.add(item);
                        }
                       
                    }
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                     session.setAttribute("suggestions", itemList);
                }
            
            
                    
                    
        }
            catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        RequestDispatcher jsp = request.getRequestDispatcher("./customerInfo.jsp");
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
