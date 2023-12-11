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
import java.util.Map;
import java.util.Arrays;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/password_change.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       UserController uc = new UserController(request.getServletContext());
       UserEntity user = uc.getUserById(CookieController.getLogedInUserId(request.getCookies()));

       String oldPW = request.getParameter("oldPassword");
       String newPW = request.getParameter("newPassword");

       System.out.println("old " + oldPW + " new " + newPW);
       if(BCrypt.checkpw(oldPW, user.getPassword())) {
           user.setPassword(BCrypt.hashpw((String) request.getAttribute("newPassword"), BCrypt.gensalt()));
           user.setPassword(BCrypt.hashpw(newPW, BCrypt.gensalt()));
           uc.changePassword(user);
           System.out.println("Saved user");
           RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/password_change_confirmation.jsp");
           dispatcher.forward(request, response);
       }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/password_change_denial.jsp");
        dispatcher.forward(request, response);

    }
}
