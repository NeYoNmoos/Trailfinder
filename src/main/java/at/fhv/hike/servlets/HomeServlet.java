package at.fhv.hike.servlets;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TrailfinderDatabaseFacade facade = new TrailfinderDatabaseFacade();
        List<RouteEntity> allRoutes = facade.getAllRoutes();

        request.setAttribute("allRoutes", allRoutes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/home_page/home_page.jsp");
        dispatcher.forward(request, response);
    }
}
