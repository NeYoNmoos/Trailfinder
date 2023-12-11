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

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = CookieController.getLogedInUserId(request.getCookies());
        UserController uc = new UserController(request.getServletContext());
        if(userid != null) {
            UserEntity userEntity = uc.getUserById(userid);
            request.setAttribute("user", userEntity.getUsername());
            request.setAttribute("email", userEntity.getEmail());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/profile.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        }

    }
}
