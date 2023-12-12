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
import java.util.List;

@WebServlet(name = "MyRoutesServlet", urlPatterns = {"/my_routes"})
public class MyRoutesServlet  extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = CookieController.getLogedInUserId(request.getCookies());
        UserController uc = new UserController(request.getServletContext());
        if(userId != null) {
            UserEntity user = uc.getUserById(userId);
            request.setAttribute("user", user.getUsername());
            request.setAttribute("email", user.getEmail());
            ServletContext context = request.getServletContext();
            RouteController rc = new RouteController(context);

            List<RouteEntity> myRoutes = rc.getRoutesCreatedByUser(user);

            request.setAttribute("myRoutes", myRoutes);
            System.out.println(myRoutes.size());

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/my_routes.jsp");
        dispatcher.forward(request, response);
    }
}
