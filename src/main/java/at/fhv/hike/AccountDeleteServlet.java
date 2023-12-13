package at.fhv.hike;

import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.UserEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet(name="AccountDeleteServlet", urlPatterns = {"/accountdeletion"})
public class AccountDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/delete_account.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserController uc = new UserController(request.getServletContext());
        UserEntity user = uc.getUserById(CookieController.getLogedInUserId(request.getCookies()));

        String oldPW = request.getParameter("oldPassword");
        if(BCrypt.checkpw(oldPW, user.getPassword())) {
            uc.deleteUser(user);
            System.out.println("Deleted user");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home_page/home_page.css");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/delete_account.jsp");
        }
    }
}
