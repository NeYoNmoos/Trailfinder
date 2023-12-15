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

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TrailfinderDatabaseFacade facade = new TrailfinderDatabaseFacade();
        //List<RouteEntity> allRoutes = facade.getAllRoutes()
        String routename = request.getParameter("routename");

        String lengthMaxStr = request.getParameter("lengthMax");
        String lengthMinStr = request.getParameter("lengthMin");
        String altitudeMaxStr = request.getParameter("altitudeMax");
        String altitudeMinStr = request.getParameter("altitudeMin");
        String durationMaxStr = request.getParameter("durationMax");
        String durationMinStr = request.getParameter("durationMin");

// Initialize integer variables
        Integer lengthMax = 10000;
        Integer lengthMin = -1;
        Integer altitudeMax = 10000;
        Integer altitudeMin = -1;
        Integer durationMax = 10000;
        Integer durationMin = -1;

        Integer power = Integer.parseInt(request.getParameter("power"));
        Integer scenery = Integer.parseInt(request.getParameter("scenery"));
        Integer experience = Integer.parseInt(request.getParameter("experience"));
        Integer condition = Integer.parseInt(request.getParameter("condition"));
        System.out.println("power: " + power);
        System.out.println("scenery: " + scenery);
        System.out.println("experience: " + experience);
        System.out.println("condition: " + condition);

        Integer month=Integer.parseInt(request.getParameter("month"));
        System.out.println(month);

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

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        List<RouteEntity> allRoutes;

        //if(lengthMin!=null || lengthMax!=null || altitudeMax!=null||altitudeMin!=null||durationMax!=null||durationMin!=null)
        //{
            allRoutes=rc.getFilteredRoutes(routename, lengthMax,lengthMin,durationMax,durationMin,altitudeMax,altitudeMin,power,scenery,experience,condition,month);
       // }
        //else
           // allRoutes = rc.getAllRoutes();

        request.setAttribute("routes", allRoutes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/home_page/home_page.jsp");
        dispatcher.forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TrailfinderDatabaseFacade facade = new TrailfinderDatabaseFacade();
        //List<RouteEntity> allRoutes = facade.getAllRoutes();
        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);

        List<RouteEntity> allRoutes = rc.getAllRoutes();

        request.setAttribute("routes", allRoutes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/home_page/home_page.jsp");
        dispatcher.forward(request, response);
    }
}
