package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("/route_detail/delete_confirmation.jsp");
        dispatcher.forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String routeId = request.getParameter("routeId");

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        RouteEntity route = rc.getRouteById(routeId);

        rc.createRoute(route);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/route_detail/delete_confirmation.jsp");
        dispatcher.forward(request, response);
    }
}
