package at.fhv.hike;

import at.fhv.hike.controllers.RouteController;
import at.fhv.hike.data.AttributeEntity;
import at.fhv.hike.data.Bitmask;
import at.fhv.hike.data.CoordinateEntity;
import at.fhv.hike.data.RouteEntity;
//import at.fhv.hike.data.TimeOfYearEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "TestServlet", urlPatterns = {"/route-test"})

public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // create mockdata
        AttributeEntity newAttributes = new AttributeEntity();
//        newAttributes.setAttributeId(UUID.randomUUID().toString());
        newAttributes.setStrength(2);
        newAttributes.setScenery(3);
        newAttributes.setExperience(4);
        newAttributes.setCondition(5);

        RouteEntity newRoute = new RouteEntity();
        newRoute.setName("testroute");
        newRoute.setLength(23.34);
        newRoute.setAltitude(12.2);
        newRoute.setLocation("Test location");
        newRoute.setDuration(2.35);
        newRoute.setDescription("Test description");
        newRoute.setAttributeEntity(newAttributes);
        newRoute.setMonths(7);

        CoordinateEntity startCoord = new CoordinateEntity();
        startCoord.setSequence(0);
        startCoord.setLatitude(9.342312);
        startCoord.setLongitude(17.23235);

        CoordinateEntity endCoord = new CoordinateEntity();
        endCoord.setSequence(0);
        endCoord.setLatitude(10.342312);
        endCoord.setLongitude(18.23235);

        newRoute.addCoordinate(startCoord);
        newRoute.addCoordinate(endCoord);

        ServletContext context = request.getServletContext();
        RouteController rc = new RouteController(context);
        rc.createRoute(newRoute);

        request.getRequestDispatcher("/create_route/create_confirmation.jsp").forward(request, response);
    }

}
