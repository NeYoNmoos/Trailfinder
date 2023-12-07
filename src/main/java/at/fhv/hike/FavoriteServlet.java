package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.FavouriteRouteEntity;
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

@WebServlet(name = "FavoriteServlet", urlPatterns = {"/favorite"})
public class FavoriteServlet  extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String routeId = request.getParameter("routeId");

        System.out.println("TRYING to Save to favorite");
        System.out.println(userId);
        System.out.println(routeId);

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        UserController uc = new UserController(context);

        UserEntity user = uc.getUserById(userId);
        RouteEntity route = rc.getRouteById(routeId);


        System.out.println("route id that is saved in user");


        request.setAttribute("route", route);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/route_detail/route_detail.jsp");
        dispatcher.forward(request, response);
    }
}
