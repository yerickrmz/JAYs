/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.User;
import model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wendy
 */
@WebServlet(urlPatterns = {"/validation"})
public class UserControllerServlet extends HttpServlet {

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
            String ID = request.getParameter("id");
            String firstName = request.getParameter("firstName");
            String middleName = request.getParameter("middleName");
            String lastName = request.getParameter("lastName");
            String secondLastName = request.getParameter("secondLastName");
            String email = request.getParameter("email");
            String group = request.getParameter("group");
            String password = request.getParameter("password");
            boolean status = false;
            if (email == null || (email = htmlFilter(email.trim())).length()
                    == 0 || !emailValidate(email)) {
                request.setAttribute("error", "Email incorrecto");
            } else if ((group != null || (group = htmlFilter(password.trim())).length()
                    == 0 || !passwordValidate(password))) {
                request.setAttribute("error", "Password incorrecto");
            } else {
                xPath whriteStudent = new xPath();
                
               whriteStudent.addNewStudent(ID, firstName, middleName, lastName, secondLastName, email, Integer.parseInt(group));
//                UserModel model = new UserModel();
//                User user = model.validate(email, password);
//                if (user != null) {
//                    request.setAttribute("error", "Usuario no existente");
//                } else {
//                    request.setAttribute("user", user);
//                    status = true;
//                }
            }
            if (status) {
                RequestDispatcher rd = request.getRequestDispatcher("loginsuccess.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("loginerror.jsp");
                rd.forward(request, response);
            }
        }
    }

    // Filter the string for special HTML characters to prevent
    // command injection attack
    private static String htmlFilter(String message) {
        if (message == null) {
            return null;
        }
        int len = message.length();
        StringBuilder result = new StringBuilder(len + 20);
        char aChar;

        for (int i = 0; i < len; ++i) {
            aChar = message.charAt(i);
            switch (aChar) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(aChar);
            }
        }
        return (result.toString());
    }

    // Validate the email
    private static boolean emailValidate(String email) {
        Pattern pat = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$");
        Matcher mat = pat.matcher(email);
        return mat.matches();
    }

    // Validate the email
    private static boolean passwordValidate(String password) {
        Pattern pat = Pattern.compile("{1,20}");
        Matcher mat = pat.matcher(password);
        return mat.matches();
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
