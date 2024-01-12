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

@WebServlet(name = "ToDoServlet", urlPatterns = {"/todo"})
public class ToDoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = CookieController.getLogedInUserId(request.getCookies());
        UserController uc = new UserController(request.getServletContext());
        if(userId != null) {
            UserEntity user = uc.getUserById(userId);
            ServletContext context = request.getServletContext();
            RouteController rc = new RouteController(context);
            List<RouteEntity> myFavRoutes = user.getFavorite_routes();
            List<RouteEntity> myDoneRoutes=user.getDone_routes();

            List<RouteEntity> myRoutes = myFavRoutes;
            myRoutes.removeAll(myDoneRoutes);

            request.setAttribute("routes", myRoutes);

            request.setAttribute("user", user.getUsername());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile_page/todo_routes.jsp");
        dispatcher.forward(request, response);
    }
}