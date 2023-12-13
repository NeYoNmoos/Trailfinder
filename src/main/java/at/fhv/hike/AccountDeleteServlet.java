package at.fhv.hike;

import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.UserEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet(name="AccountDeleteServlet", urlPatterns = {"/deleteaccount"})
public class AccountDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/delete_account.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserController uc = new UserController(request.getServletContext());
        UserEntity user = uc.getUserById(CookieController.getLogedInUserId(request.getCookies()));

        String password = request.getParameter("Password");

        if( uc.checkPassword(user.getEmail(), password)!=null) {
            //Log out by deleting cookies
            //set all routes user made to author null or fix db

            //Delete account
            uc.deleteUser(user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/delete_account_confirmation.jsp");
            dispatcher.forward(request, response);
        }
        else {
            request.setAttribute("deleteAccError", "Wrong password");
            request.getRequestDispatcher("/profile_page/delete_account.jsp").forward(request, response);
        }
    }
}
