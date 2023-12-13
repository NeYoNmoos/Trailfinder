package at.fhv.hike;

import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            ServletContext context = request.getServletContext();
            RouteController rc = new RouteController(context);
            //set author to null to routes user created
            for (RouteEntity route:user.getRoutes()) {
                route.setAuthor(null);
                System.out.println(route.getName());
                rc.createOrUpdateRoute(route);
            }
            user.setRoutes(null);
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
