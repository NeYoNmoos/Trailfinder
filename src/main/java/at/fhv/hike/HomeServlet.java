package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TrailfinderDatabaseFacade facade = new TrailfinderDatabaseFacade();
        //List<RouteEntity> allRoutes = facade.getAllRoutes()
        String lengthMaxStr = request.getParameter("lengthMax");
        String lengthMinStr = request.getParameter("lengthMin");
        String altitudeMaxStr = request.getParameter("altitudeMax");
        String altitudeMinStr = request.getParameter("altitudeMin");
        String durationMaxStr = request.getParameter("durationMax");
        String durationMinStr = request.getParameter("durationMin");

// Initialize integer variables
        Integer lengthMax = null;
        Integer lengthMin = null;
        Integer altitudeMax = null;
        Integer altitudeMin = null;
        Integer durationMax = null;
        Integer durationMin = null;

// Parse string parameters into integers
        if (lengthMaxStr != null && !lengthMaxStr.isEmpty()) {
            lengthMax = Integer.parseInt(lengthMaxStr);
        }
        if (lengthMinStr != null && !lengthMinStr.isEmpty()) {
            lengthMin = Integer.parseInt(lengthMinStr);
        }
        if (altitudeMaxStr != null && !altitudeMaxStr.isEmpty()) {
            altitudeMax = Integer.parseInt(altitudeMaxStr);
        }
        if (altitudeMinStr != null && !altitudeMinStr.isEmpty()) {
            altitudeMin = Integer.parseInt(altitudeMinStr);
        }
        if (durationMaxStr != null && !durationMaxStr.isEmpty()) {
            durationMax = Integer.parseInt(durationMaxStr);
        }
        if (durationMinStr != null && !durationMinStr.isEmpty()) {
            durationMin = Integer.parseInt(durationMinStr);
        }

        RouteController rc = new RouteController();
        List<RouteEntity> allRoutes;

        if(lengthMin!=null || lengthMax!=null || altitudeMax!=null||altitudeMin!=null||durationMax!=null||durationMin!=null)
        {
            allRoutes=rc.getFilteredRoutes(lengthMax,lengthMin,durationMax,durationMin,altitudeMax,altitudeMin);
        }
        else
            allRoutes = rc.getAllRoutes();

        request.setAttribute("allRoutes", allRoutes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/home_page/home_page.jsp");
        dispatcher.forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TrailfinderDatabaseFacade facade = new TrailfinderDatabaseFacade();
        //List<RouteEntity> allRoutes = facade.getAllRoutes();
        String lengthMaxStr = request.getParameter("lengthMax");
        String lengthMinStr = request.getParameter("lengthMin");
        String altitudeMaxStr = request.getParameter("altitudeMax");
        String altitudeMinStr = request.getParameter("altitudeMin");
        String durationMaxStr = request.getParameter("durationMax");
        String durationMinStr = request.getParameter("durationMin");

// Initialize integer variables
        Integer lengthMax = null;
        Integer lengthMin = null;
        Integer altitudeMax = null;
        Integer altitudeMin = null;
        Integer durationMax = null;
        Integer durationMin = null;

// Parse string parameters into integers
        if (lengthMaxStr != null && !lengthMaxStr.isEmpty()) {
            lengthMax = Integer.parseInt(lengthMaxStr);
        }
        if (lengthMinStr != null && !lengthMinStr.isEmpty()) {
            lengthMin = Integer.parseInt(lengthMinStr);
        }
        if (altitudeMaxStr != null && !altitudeMaxStr.isEmpty()) {
            altitudeMax = Integer.parseInt(altitudeMaxStr);
        }
        if (altitudeMinStr != null && !altitudeMinStr.isEmpty()) {
            altitudeMin = Integer.parseInt(altitudeMinStr);
        }
        if (durationMaxStr != null && !durationMaxStr.isEmpty()) {
            durationMax = Integer.parseInt(durationMaxStr);
        }
        if (durationMinStr != null && !durationMinStr.isEmpty()) {
            durationMin = Integer.parseInt(durationMinStr);
        }

        RouteController rc = new RouteController();
        List<RouteEntity> allRoutes;

        if(lengthMin!=null || lengthMin!=null || altitudeMax!=null||altitudeMin!=null||durationMax!=null||durationMin!=null)
        {
            allRoutes=rc.getFilteredRoutes(lengthMax,lengthMin,durationMax,durationMin,altitudeMax,altitudeMin);
        }
        else
            allRoutes = rc.getAllRoutes();

        request.setAttribute("allRoutes", allRoutes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/home_page/home_page.jsp");
        dispatcher.forward(request, response);
    }
}
