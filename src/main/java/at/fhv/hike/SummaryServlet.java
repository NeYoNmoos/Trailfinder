package at.fhv.hike;

import at.fhv.hike.controllers.CookieController;
import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.*;
import at.fhv.hike.controllers.UserController;
import at.fhv.hike.data.GalleryEntity;
import at.fhv.hike.data.RouteEntity;
import at.fhv.hike.data.UserEntity;
import at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SummaryServlet", urlPatterns = {"/summary"})
public class SummaryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String routeId = request.getParameter("routeId");

        //TrailfinderDatabaseFacade facade = new TrailfinderDatabaseFacade();
        //RouteEntity route = facade.getRouteById(routeId);
        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        RouteEntity route = rc.getRouteById(routeId);

        List<GalleryEntity> gallery = route.getGallery();

        request.setAttribute("gallery", gallery);

        request.setAttribute("route", route);

        // Huetten
        List<LodgeEntity> huetten = new ArrayList<>();
        huetten = rc.getAllHuettenForRoute(routeId);

        request.setAttribute("huetten", huetten);

        // Pois
        List<PointOfInterestEntity> pois = new ArrayList<>();
        pois = rc.getAllPoisForRoute(routeId);

        request.setAttribute("pois", pois);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/route_detail/summary.jsp");
        dispatcher.forward(request, response);
    }
}