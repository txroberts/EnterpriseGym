/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egym;

import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author Dreads
 */
@WebServlet(name = "editUserDetails", urlPatterns = {"/editUserDetails"})
public class editUserDetails extends HttpServlet {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";  
    String url = "jdbc:mysql://46.101.32.81:3306/EGAlexander";
    String user = "root";
    String password = "teameight";
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String matriculation = request.getParameter("matricNO");
            String email = request.getParameter("email");
            String phoneNo = request.getParameter("phoneNO");
            String Gender = request.getParameter("gender");
            String country = request.getParameter("country");
            String institution = request.getParameter("institution");
            String subInstitution = request.getParameter("subInstitution");
            String degree = request.getParameter("degree");
            String DoB = request.getParameter("dob");
            String YoS = request.getParameter("yos");
            int userStatus = Integer.parseInt(request.getParameter("userStatus"));
            char gender = Gender.charAt(0);
            int yos = Integer.parseInt(YoS);
            
            Timestamp datePosted = Timestamp.valueOf(DoB);
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
            CallableStatement cs = null;
            cs = this.con.prepareCall("{call edit_user_details(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"); 
            cs.setString(1, username);
            cs.setString(2, firstname);
            cs.setString(3, lastname);
            cs.setString(4, matriculation);
            cs.setString(5, email);
            cs.setString(6, phoneNo);
            cs.setString(7, String.valueOf(gender));
            cs.setString(8, country);
            cs.setString(9, institution);
            cs.setString(10, subInstitution);
            cs.setString(11, degree);
            cs.setTimestamp(12, datePosted);
            cs.setInt(13, yos);
            cs.setInt(14, userStatus);
            cs.executeQuery();
            cs.close();
            con.close();
            RequestDispatcher rd = request.getRequestDispatcher("/homePage");
            rd.forward(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(editUserDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editUserDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(editUserDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(editUserDetails.class.getName()).log(Level.SEVERE, null, ex);
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
